package net.tslat.passivemobs.passivemobs;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Enemy;
import org.jspecify.annotations.Nullable;

/// General-purpose helper methods for testing PassiveMobs' functionality
public final class Passification {
    /// Determine whether an entity can be targeted without assuming anything about the attacking entity
    public static boolean canTarget(Entity target) {
        return canTarget(null, target);
    }

    /// Determine whether an entity can be targeted, optionally testing for attacker variables
    public static boolean canTarget(@Nullable Entity attacker, Entity target) {
        if (attacker != null) {
            if (attacker instanceof Enemy)
                return false;

            if (attacker instanceof NeutralMob && !(target instanceof Enemy))
                return false;
        }

        if (target instanceof Enemy)
            return true;

        if (target instanceof OwnableEntity ownable && !(ownable.getOwner() instanceof Enemy))
            return false;

        return true;
    }
}
