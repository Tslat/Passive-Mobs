package net.tslat.passivemobs;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Constants {
	public static final String VERSION = "1.3";
	public static final String MOD_ID = "passivemobs";
	public static final String MOD_NAME = "PassiveMobs";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static final Supplier<TagKey<EntityType<?>>> PACIFICATION_IMMUNE_TAG = Suppliers.memoize(() -> TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("immune_to_pacification")));
}