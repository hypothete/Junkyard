package com.hypothete.Junkyard;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;


import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;



public class DataTweak extends BlockPopulator {
	
	
	public void populate(World world, Random random, Chunk chunk) {
		
		int x, z, y;
		for(x=0; x<16; ++x){
			for(z=0; z<16; ++z){
				for(y=0; y<255; y++){
					
					Block betweaked = chunk.getBlock(x, y, z);
					//betweaked.setData((byte)random.nextInt(4));
					
					if(betweaked.getType() == Material.WOOL){
						betweaked.setData((byte)random.nextInt(16));
					}
					
					else if(betweaked.getType() == Material.SAPLING){
						betweaked.setData((byte)random.nextInt(4));
					}
					
					else if(betweaked.getType() == Material.LOG){
						betweaked.setData((byte)random.nextInt(4));
					}
					
					else if(betweaked.getType() == Material.LEAVES){
						betweaked.setData((byte)random.nextInt(4));
					}
					
					else if(betweaked.getType() == Material.getMaterial(43)){
						betweaked.setData((byte)random.nextInt(4));
					}
					
					else if(betweaked.getType() == Material.getMaterial(44)){
						betweaked.setData((byte)random.nextInt(4));
					}
					
					else if(betweaked.getType() == Material.getMaterial(98)){
						betweaked.setData((byte)random.nextInt(4));
					}
					else if(betweaked.getType() == Material.getMaterial(125)){
						betweaked.setData((byte)random.nextInt(4));
					}
					else if(betweaked.getType() == Material.getMaterial(126)){
						betweaked.setData((byte)random.nextInt(4));
					}
					else if(betweaked.getType() == Material.getMaterial(140)){
						betweaked.setData((byte)random.nextInt(11));
					}

					
				}
				
			}
	
		}
		
	}
}