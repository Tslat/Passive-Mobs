package net.tslat.passivemobs.passivemobs.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.tslat.passivemobs.passivemobs.Passification;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Handle direct-[memory](MemoryModuleType) injections for targeting-related memories
@Mixin(Brain.class)
public class BrainMixin {
	@Inject(method = "setMemoryInternal(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;Ljava/lang/Object;)V", at = @At("HEAD"), cancellable = true)
	private <U> void passivemobs$cancelTargetMemory(MemoryModuleType<U> memoryType, @Nullable U memory, CallbackInfo ci) {
		if (memory != null && passiveMobs$shouldCancelTarget(memoryType, memory))
			ci.cancel();
	}

	@Inject(method = "setMemoryInternal(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;Ljava/lang/Object;J)V", at = @At("HEAD"), cancellable = true)
	private <U> void passivemobs$cancelTemporaryTargetMemory(MemoryModuleType<U> memoryType, U memory, long tileToLive, CallbackInfo ci) {
		if (memory != null && passiveMobs$shouldCancelTarget(memoryType, memory))
			ci.cancel();
	}

	@Unique
	private static <M> boolean passiveMobs$shouldCancelTarget(MemoryModuleType<M> memoryType, M memory) {
		if (memoryType == MemoryModuleType.ATTACK_TARGET || memoryType == MemoryModuleType.NEAREST_ATTACKABLE) {
			if (memory instanceof Entity target && !Passification.canTarget(target))
				return true;
		}

		return memoryType == MemoryModuleType.RAM_TARGET;
	}
}
