package com.hypothete.Junkyard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class JunkyardGenerator extends ChunkGenerator{
	
	
	public List<BlockPopulator> getDefaultPopulators(World world){
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		populators.add(new DataTweak());

		

		return populators;
	}
	
	public Location getFixedSpawnLocation(World world, Random random){
		return new Location(world, 0, 128, 0);
	}
	
	//borrowing this from jtjj222's experiments w/ 3d noise. thanks!
	void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
		if (chunk[y>>4] == null) chunk[y>>4] = new byte[16*16*16];
		if (!(y<=256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0)) return; //Out of bounds
		try {
			chunk[y>>4][((y & 0xF) << 8) | (z << 4) | x] = (byte)material.getId();
		}
		catch (Exception e) {
		//do nothing
			}
	}
	
	public byte[][] generateBlockSections(World world, Random random, int ChunkX, int ChunkZ, BiomeGrid biomes){
		byte[][] blocks = new byte[world.getMaxHeight() / 16][];
		int x, y, z;
		
		Random rand = new Random(world.getSeed());
		

		SimplexOctaveGenerator octaveground = new SimplexOctaveGenerator(rand, 16);
		SimplexOctaveGenerator octaveground2 = new SimplexOctaveGenerator(rand, 8);
		SimplexOctaveGenerator octaveground3 = new SimplexOctaveGenerator(rand, 8);
		octaveground.setScale(1/64.0);
		octaveground2.setScale(1/128.0);
		octaveground3.setScale(1/16.0);
		for(x=0; x < 16; x++){
			for(z=0; z<16; z++){
				
				biomes.setBiome(x, z, Biome.FOREST);
				
				int real_x = x+ChunkX * 16;
				int real_z = z+ChunkZ*16;
				setBlock(x, 0, z, blocks, Material.BEDROCK);

				
				double groundheight = Math.max(octaveground.noise(real_x, real_z, 0.5, 0.5), octaveground2.noise(real_x, real_z, 0.5, 0.5))*32;
				groundheight = groundheight + octaveground3.noise(real_x, groundheight, real_z, 0.5, 0.5)*4 + 32;
				
				for(y=1; y < (int)groundheight; y++){
					
					if(y > 3*groundheight/4 && y < 255){
					
					int blockinquestion = random.nextInt(145);
					
					if(random.nextInt(80) > 5){
						blockinquestion = 3;
					}
					
					//
					if(blockinquestion == 7 || blockinquestion == 34 || blockinquestion == 26 || blockinquestion == 64 || blockinquestion == 71 || blockinquestion == 74 || blockinquestion == 75 || blockinquestion == 94 || blockinquestion == 95 || blockinquestion == 104 ||blockinquestion == 105 || blockinquestion == 120 || blockinquestion == 122 || blockinquestion == 124 || blockinquestion == 90 || blockinquestion == 119 || blockinquestion == 132 || blockinquestion == 137 || blockinquestion == 144){
						blockinquestion = 3;
					}
					else if(blockinquestion == 6 || blockinquestion == 31 || blockinquestion == 32 || blockinquestion == 37 || blockinquestion == 38 || blockinquestion == 39 ||blockinquestion == 40 ){
						setBlock(x, y-1, z, blocks, Material.DIRT);
					}
					else if(blockinquestion == 115){
						setBlock(x, y-1, z, blocks, Material.SOUL_SAND);
					}
					else if(blockinquestion == 81){
						setBlock(x, y-1, z, blocks, Material.SAND);
					}
					else if(blockinquestion == 52 || blockinquestion == 62 ){
						blockinquestion = 3;
					}
					else if(blockinquestion == 54 || blockinquestion == 130 ){
						if(random.nextInt(500) > 5){
							blockinquestion = 17;
						}

					}
					if(blockinquestion == 8 || blockinquestion == 9 || blockinquestion == 10 || blockinquestion == 11 ){
						if(random.nextInt(8) > 5){
							blockinquestion = 3;
						}
						
					}
					else if(blockinquestion == 59 || blockinquestion == 104 || blockinquestion == 105 || blockinquestion == 141 || blockinquestion == 142 ){
						setBlock(x, y-1, z, blocks,Material.getMaterial(60));
					}					
					
					setBlock(x, y, z, blocks, Material.getMaterial(blockinquestion));
					
					}
					else{
						setBlock(x, y, z, blocks, Material.STONE);
					}
			}
			
		}
		
		
	}
		
		return blocks;	
}
	
}
