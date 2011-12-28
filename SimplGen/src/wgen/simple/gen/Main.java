package wgen.simple.gen;

import java.util.logging.Logger;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new SimplGen();
    }
	@Override
	public void onDisable() {
		log.info("[SimpleGen] Shutting down!");
	}

	@Override
	public void onEnable() {
		log.info("[SimplGen] Started up!");
	}

}
