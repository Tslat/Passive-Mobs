package net.tslat.passivemobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.tslat.passivemobs.PassiveMobs.MOD_ID;

@Mod(MOD_ID)
public class PassiveMobs {
	public static final String MOD_ID = "passivemobs";
	public static final Lazy<ITag.INamedTag<EntityType<?>>> PACIFICATION_IMMUNE_TAG = Lazy.of(() -> EntityTypeTags.bind("immune_to_pacification"));

	public PassiveMobs() {
		MinecraftForge.EVENT_BUS.addListener(this::entityTarget);
	}

	private void entityTarget(final LivingSetAttackTargetEvent ev) {
		if (ev.getTarget() != null && ev.getEntityLiving() instanceof MobEntity && !ev.getEntityLiving().getType().is(PACIFICATION_IMMUNE_TAG.get()))
			((MobEntity)ev.getEntityLiving()).setTarget(null);
	}
}
