package net.tslat.passivemobs;

import net.minecraft.entity.MobEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.tslat.passivemobs.PassiveMobs.MOD_ID;

@Mod(MOD_ID)
public class PassiveMobs {
	public static final String MOD_ID = "passivemobs";
	public static final String VERSION = "1.0";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public PassiveMobs() {
		MinecraftForge.EVENT_BUS.addListener(this::entityTarget);
	}

	private void entityTarget(final LivingSetAttackTargetEvent ev) {
		if (ev.getTarget() != null && ev.getEntityLiving() instanceof MobEntity)
			((MobEntity)ev.getEntityLiving()).setTarget(null);
	}
}
