package net.tslat.passivemobs.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.tslat.passivemobs.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Mob.class)
public class MobMixin {
	@Inject(
			method = "setTarget",
			at = @At("HEAD"),
			cancellable = true
	)
	public void setTarget(@Nullable LivingEntity target, CallbackInfo callback) {
		if (target != null && !((Mob)(Object)this).getType().is(Constants.PACIFICATION_IMMUNE_TAG.get()))
			callback.cancel();
	}
}
