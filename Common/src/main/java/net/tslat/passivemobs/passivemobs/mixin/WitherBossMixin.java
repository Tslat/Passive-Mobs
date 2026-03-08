package net.tslat.passivemobs.passivemobs.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.tslat.passivemobs.passivemobs.ModConstants;
import net.tslat.passivemobs.passivemobs.Passification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Special-case handling for Wither-specific targeting
@Mixin(WitherBoss.class)
public class WitherBossMixin {
	@Inject(method = "setAlternativeTarget", at = @At("HEAD"), cancellable = true)
	public void setTarget(int head, int id, CallbackInfo callback) {
		if (id > 0) {
			WitherBoss self = (WitherBoss)(Object) this;

			if (!self.is(ModConstants.PACIFICATION_IMMUNE_TAG.get())) {
				Entity target = self.level().getEntity(id);

				if (target != null && !Passification.canTarget(self, target))
					callback.cancel();
			}
		}
	}
}
