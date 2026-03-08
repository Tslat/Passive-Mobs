package net.tslat.passivemobs.passivemobs;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/// Mod constants class.
///
/// All the mod's shared static instances are stored here
public final class ModConstants {
    public static final String MODID = "passivemobs";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    private static final Identifier BASE_ID = Identifier.fromNamespaceAndPath(MODID, "");

    public static final Supplier<TagKey<EntityType<?>>> PACIFICATION_IMMUNE_TAG = Suppliers.memoize(() -> TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("c", "immune_to_pacification")));

    public static void init() {}

    /// Create a new [Identifier] with this mod's namespace
    public static Identifier id(String path) {
        return BASE_ID.withPath(path);
    }
}
