package net.tslat.passivemobs.mixin;

import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.Memory;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Brain.class)
public class BrainMixin {
	@Inject(method = "setMemoryInternal", at = @At("HEAD"), cancellable = true)
	private <U> void setMemoryInternal(MemoryModuleType<U> moduleType, Optional<? extends Memory<?>> memory, CallbackInfo callback) {
		if (moduleType == MemoryModuleType.ATTACK_TARGET)
			callback.cancel();
	}
}
