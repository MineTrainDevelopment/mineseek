package de.minetrain.mineseek.enums;

import org.bukkit.World;

public enum TimeOfDay {
	SUNRISE("§6Sonnenaufgang"){
		@Override
		public void setGameTime(World world){
			world.setTime(23500);
		}
	},
	
	DAY("§eTag"){
		@Override
		public void setGameTime(World world){
			world.setTime(6000);
		}
	},
	
	SUNSET("§6Sonnenuntergang"){
		@Override
		public void setGameTime(World world){
			world.setTime(12500);
		}
	},
	
	NIGHT("§9Nacht"){
		@Override
		public void setGameTime(World world){
			world.setTime(18000);
		}
	};
	
	
	public void setGameTime(World world){}
	private final String name;

	private TimeOfDay(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public TimeOfDay next() {
		return values()[(ordinal() + 1) % values().length];
	}
}
