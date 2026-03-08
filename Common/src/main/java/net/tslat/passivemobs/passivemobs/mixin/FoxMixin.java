package net.tslat.passivemobs.passivemobs.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fox.Fox;
import net.tslat.passivemobs.passivemobs.ModConstants;
import net.tslat.passivemobs.passivemobs.Passification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// [Fox]-specific handling for target predication
@Mixin(Fox.class)
public class FoxMixin {
    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    public void passivemobs$cancelSetTarget(LivingEntity target, CallbackInfo ci) {
        if (target != null) {
            Fox self = (Fox)(Object)this;

            if (!self.is(ModConstants.PACIFICATION_IMMUNE_TAG.get()) && !Passification.canTarget(self, target))
                ci.cancel();
        }
    }
}
