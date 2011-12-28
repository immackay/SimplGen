package wgen.simple.gen;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class SimplGen extends ChunkGenerator {
    //This is where you stick your populators - these will be covered in another tutorial
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator) new OceanPopulator());
    }
    //This needs to be set to return true to override minecraft's default behaviour
    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }
    //This converts relative chunk locations to bytes that can be written to the chunk
    public int xyzToByte(int x, int y, int z) {
    return (x * 16 + z) * 128 + y;
    }

    @Override
    public byte[] generate(World world, Random random, int chunkX, int chunkZ) {
    	// This is just the blank byte array for the chunk
    	byte[] b = new byte[16*16*128];
    	// This ensures a set random. (Let's not go into it, it just works.)
    	Random seed = new Random(world.getSeed());
    	// This generates an "octave generator" from the random - the same one each time
    	SimplexOctaveGenerator gen =  new SimplexOctaveGenerator(seed, 8);
    	// This set's the "scale" ie how spread out it is - 64 is a good number for hilly terrain
    	gen.setScale(1/64.0);
    	// This loops through the chunk
    	for (int x=0; x<16; x++){
    		for (int z=0; z<16; z++){
    			// This generates noise based on the absolute x and z (between -1 and 1) - it will be smooth noise if all goes well - multiply it by the desired value - 16 is good for hilly terrain.
    			double noise = gen.noise(x+chunkX*16, z+chunkZ*16, 0.5, 0.5)*16;
    			for(int y=0; y<32+noise; y++){
    				// Just some checks I use - you don't need this.
    				if(b[xyzToByte(x,y,z)] ==0) {
    					// Obviously sets that byte[] corresponding to the chunk x,y,z to stone - you can use your preferred way of doing this.
    					b[xyzToByte(x,y,z)] = (byte)  (Material.STONE).getId();
    				}
    			}
    		}
    	}
    	for (int y=0; y<24; y++) {
    		
    	}

    	return b;
    	}

}