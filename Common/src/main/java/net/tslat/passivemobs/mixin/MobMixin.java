package net.tslat.passivemobs.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Monster;
import net.tslat.passivemobs.Constants;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
	@Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
	public void setTarget(@Nullable LivingEntity target, CallbackInfo callback) {
		if (target != null) {
			Mob self = (Mob)(Object)this;

			if (!self.getType().is(Constants.PACIFICATION_IMMUNE_TAG.get())) {
				if (self instanceof Enemy || (self instanceof NeutralMob && !(target instanceof Enemy)) || (target instanceof OwnableEntity ownable && !(ownable.getOwner() instanceof Enemy)))
					callback.cancel();
			}
		}
	}
}
