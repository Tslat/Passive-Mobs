package net.tslat.passivemobs.mixin;

import net.minecraft.world.entity.ai.memory.ExpirableValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

@Mixin(Brain.class)
public class BrainMixin {
	@Inject(method = "setMemoryInternal", at = @At("HEAD"), cancellable = true)
	private <U> void setMemoryInternal(MemoryModuleType<U> moduleType, Optional<? extends ExpirableValue<?>> memory,
									   CallbackInfo callback) {
		if (moduleType == MemoryModuleType.ATTACK_TARGET)
			callback.cancel();
	}
}
