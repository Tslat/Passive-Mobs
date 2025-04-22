package net.tslat.passivemobs.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Enemy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Brain.class)
public class BrainMixin {
	@Inject(method = "setMemoryInternal", at = @At("HEAD"), cancellable = true)
	private <U> void setMemoryInternal(MemoryModuleType<U> moduleType, Optional<? extends ExpirableValue<?>> memory, CallbackInfo callback) {
		if (memory.isPresent()) {
			if (moduleType == MemoryModuleType.ATTACK_TARGET || moduleType == MemoryModuleType.NEAREST_ATTACKABLE) {
				if (memory.get().getValue() instanceof LivingEntity target) {
					if (!(target instanceof Enemy) && (!(target instanceof OwnableEntity ownable) || !(ownable.getOwner() instanceof Enemy)))
						callback.cancel();
				}
			}
			else if (moduleType == MemoryModuleType.RAM_TARGET) {
				callback.cancel();
			}
		}
	}
}
