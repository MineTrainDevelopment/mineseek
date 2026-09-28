package de.minetrain.mineseek.enums;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import de.minetrain.mineseek.main.Main;

public enum Barrier {
	EXAMPLE(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME);
		}
	},
	
	TUNNEL_GUTTER(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(51, 70, 59).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(51, 70, 57).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	NEW_DISTRICT(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(11, 81, 8).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(11, 81, 7).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	LIBRARY(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(69,89,-14).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(69,89,-14).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	OLD_TOWN(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(69,94,-14).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(69,94,-14).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	BASTI_TOWER(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(254,90,164).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(254,90,164).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	HOME(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(63,61,20).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(63,61,20).setType(Material.AIR);
		}
	},
	
	HOME_ROOF(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(74,83,8).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(74,83,8).setType(Material.AIR);
		}
	},
	
	HOME_BASEMENT(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(79,59,20).setType(Material.AIR);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(79,59,20).setType(Material.REDSTONE_BLOCK);
		}
	},
	
	HOME_BASEMENT_LIGHT(){
		@Override
		public void open(){
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(67,61,25).setType(Material.REDSTONE_BLOCK);
		}
		
		@Override
		public void close() {
			Bukkit.getWorld(Main.WORLD_NAME).getBlockAt(67,61,25).setType(Material.AIR);
		}
	};

	public void open(){}
	public void close(){}
	
	private Barrier() {
		
	}


}
