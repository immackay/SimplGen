package wgen.simple.gen;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class OceanPopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random random, Chunk source) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 10; y < 25; y++) {
					if (!source.getBlock(x, y, z).equals(Material.STONE)) {
						source.getBlock(x, y, z).setType(Material.WATER);
					}
				}
			}
		}
	}
}
