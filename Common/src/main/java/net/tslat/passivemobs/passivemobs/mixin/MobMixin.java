package net.tslat.passivemobs.passivemobs.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.tslat.passivemobs.passivemobs.ModConstants;
import net.tslat.passivemobs.passivemobs.Passification;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/// Handle methods related to setting or predicating targets for Mobs
@Mixin(Mob.class)
public class MobMixin {
	@Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
	public void passivemobs$cancelSetTarget(@Nullable LivingEntity target, CallbackInfo ci) {
		if (target != null) {
			Mob self = (Mob)(Object)this;

			if (!self.is(ModConstants.PACIFICATION_IMMUNE_TAG.get()) && !Passification.canTarget(self, target))
				ci.cancel();
		}
	}

	@Inject(method = "canAttack", at = @At("HEAD"), cancellable = true)
	public void passivemobs$injectAttackPredicate(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
		Mob self = (Mob)(Object)this;

		if (!self.is(ModConstants.PACIFICATION_IMMUNE_TAG.get()) && !Passification.canTarget(self, target))
			cir.setReturnValue(false);
	}
}
