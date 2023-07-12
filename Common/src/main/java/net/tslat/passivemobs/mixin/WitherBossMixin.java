package net.tslat.passivemobs.mixin;

import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.tslat.passivemobs.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherBoss.class)
public class WitherBossMixin {
	@Inject(method = "setAlternativeTarget", at = @At("HEAD"), cancellable = true)
	public void setTarget(int head, int id, CallbackInfo callback) {
		if (id > 0 && !((WitherBoss)(Object)this).getType().is(Constants.PACIFICATION_IMMUNE_TAG.get()))
			callback.cancel();
	}
}
