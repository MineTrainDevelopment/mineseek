package de.minetrain.mineseek.gameplay;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Worlds {
	CITY("§8City"){
		@Override
		public void teleport(TeamManager team){
//			TeamManager teamManager = new TeamManager();
			
			//Seeker spawn locations
			List<Location> locations = List.of(
	            new Location(Bukkit.getWorld("world"), 63, 74, -28, 0, 0),
	            new Location(Bukkit.getWorld("world"), 54, 55, -4, 70, 0),
	            new Location(Bukkit.getWorld("world"), 30, 64, 14, -160, 12),
	            new Location(Bukkit.getWorld("world"), 79, 66, 32, -74, 7));
			
			team.getSeekers().forEach(seeker -> seeker.teleport(locations.get(new Random().nextInt(locations.size()))));
			team.getHiders().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 51, 63, 47, -180, 0)));
		}
		
		@Override
		public void teleportSpectator(Player spectator) {
			spectator.teleport(new Location(Bukkit.getWorld("world"), 51, 63, 47, -180, 0));
		}
	},
	
	POMMES("§8Pommes"){
		@Override
		public void teleport(TeamManager team){
			team.getSeekers().forEach(seeker -> seeker.teleport(new Location(Bukkit.getWorld("world"), 71, 63, 180, 90, 0)));
			team.getHiders().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 68, 63, 108, 0, 0)));
		}
		
		@Override
		public void teleportSpectator(Player spectator) {
			spectator.teleport(new Location(Bukkit.getWorld("world"), 71, 63, 180, 90, 0));
		}
	},
	
	SKY("§8Sky"){
		@Override
		public void teleport(TeamManager team){
			team.getSeekers().forEach(seeker -> seeker.teleport(new Location(Bukkit.getWorld("world"), 199, 82, 151, -190, 0)));
			team.getHiders().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 201, 94, 148, -45, 3)));
		}
		
		@Override
		public void teleportSpectator(Player spectator) {
			spectator.teleport(new Location(Bukkit.getWorld("world"), 199, 82, 151, -190, 0));
		}
	};
	
//	CITY2("§8City2");

	public void teleport(TeamManager team){}
	public void teleportSpectator(Player spectator){}
	private final String name;
	
	private Worlds(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}
	
	public Worlds next() {
		return values()[(ordinal() + 1) % values().length];
	}
	
	public Worlds getRandom(){
		return values()[(new Random().nextInt(values().length))];
	}
	
}