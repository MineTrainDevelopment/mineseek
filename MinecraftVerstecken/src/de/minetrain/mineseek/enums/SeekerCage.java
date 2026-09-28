package de.minetrain.mineseek.enums;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import de.minetrain.mineseek.main.Main;

public enum SeekerCage  {
	LOFT(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(32,62,12).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(32,62,12).setType(Material.REDSTONE_BLOCK);
		}
	},

	GARDEN(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(77,66,38).setType(Material.REDSTONE_TORCH);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(77,66,38).setType(Material.AIR);
		}
	},

	CYBERTEC(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(76,73,-21).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(76,73,-21).setType(Material.AIR);
		}
	},

	SUBWAY(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(45,53,-1).setType(Material.STONE);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(45,53,-1).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	PRISSON(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(198,80,151).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(198,80,151).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	POMMES(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(68, 62, 178).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(68, 62, 178).setType(Material.AIR);
		}
	};

	public void open(){}
	public void close(){}
	
	private SeekerCage() {
		
	}
	
	public static void openAll(){
		Stream.of(values()).forEach(SeekerCage::open);
	}
	
	public static void closeAll(){
		Stream.of(values()).forEach(SeekerCage::close);
	}


}
