package de.minetrain.mineseek.enums;

import org.bukkit.World;


public enum Weather {
	CLEAR("§eSchön"){
		@Override
		public void setGameWeather(World world){
			world.setStorm(false);
			world.setThundering(false);
		}
	}, 
	
	RAIN("§9Regen"){
		@Override
		public void setGameWeather(World world){
			world.setStorm(true);
			world.setThundering(false);
		}
	},
	
	THUNDER("§8Gewitter"){
		@Override
		public void setGameWeather(World world){
			world.setStorm(true);
			world.setThundering(true);
		}
	};
	
	
	
	public void setGameWeather(World world){}
	private final String name;

	private Weather(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Weather next() {
		return values()[(ordinal() + 1) % values().length];
	}
}
