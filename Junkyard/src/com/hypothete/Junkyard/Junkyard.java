package com.hypothete.Junkyard;

import java.util.logging.Logger;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

//Enormous kudos to Jacek (betterphp on youtube) for his videos on chunkgenerator plugins!

public class Junkyard extends JavaPlugin {

	private Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		this.logMessage("Enabled.");
	}
	
	public void onDisable(){
		this.logMessage("Disabled.");
	}
	
	public void logMessage(String msg){
		PluginDescriptionFile pdfile = this.getDescription();
		this.log.info(pdfile.getName() + " " + pdfile.getVersion() + " " + msg);
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String uid){
		return new JunkyardGenerator();
	}
}
