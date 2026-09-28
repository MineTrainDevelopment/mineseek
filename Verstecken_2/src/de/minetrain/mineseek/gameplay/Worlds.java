package de.minetrain.mineseek.gameplay;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Worlds{
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
			
			team.getSeekerList().forEach(seeker -> seeker.teleport(locations.get(new Random().nextInt(locations.size()))));
			team.getHiderList().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 51, 63, 47, -180, 0)));
		}
		
		@Override
		public void teleportSpectator(Player spectator) {
			spectator.teleport(new Location(Bukkit.getWorld("world"), 51, 63, 47, -180, 0));
		}
	},
	
	POMMES("§8Pommes"){
		@Override
		public void teleport(TeamManager team){
			team.getSeekerList().forEach(seeker -> seeker.teleport(new Location(Bukkit.getWorld("world"), 71, 63, 180, 90, 0)));
			team.getHiderList().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 68, 63, 108, 0, 0)));
		}
		
		@Override
		public void teleportSpectator(Player spectator) {
			spectator.teleport(new Location(Bukkit.getWorld("world"), 71, 63, 180, 90, 0));
		}
	},
	
	SKY("§8Sky"){
		@Override
		public void teleport(TeamManager team){
			team.getSeekerList().forEach(seeker -> seeker.teleport(new Location(Bukkit.getWorld("world"), 199, 82, 151, -190, 0)));
			team.getHiderList().forEach(hider -> hider.teleport(new Location(Bukkit.getWorld("world"), 201, 94, 148, -45, 3)));
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
	
	/**
	 * WARNING: This is NOT save! This is a Hardcoded Enum size!
	 * @return
	 */
	public Worlds getRandom(){
		int i = new Random().nextInt(3);
		return values()[(i-1)];
	}
	
//	java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 3
//    at MineSeek-0.0.1-SNAPSHOT.jar/de.minetrain.mineseek.gameplay.Worlds.getRandom(Worlds.java:83) ~[MineSeek-0.0.1-SNAPSHOT.jar:?]
//    at MineSeek-0.0.1-SNAPSHOT.jar/de.minetrain.mineseek.gameplay.WorldManager.pickWorld(WorldManager.java:20) ~[MineSeek-0.0.1-SNAPSHOT.jar:?]
//    at MineSeek-0.0.1-SNAPSHOT.jar/de.minetrain.mineseek.gameplay.GameState.startHidePhase(GameState.java:35) ~[MineSeek-0.0.1-SNAPSHOT.jar:?]
//    at MineSeek-0.0.1-SNAPSHOT.jar/de.minetrain.mineseek.gameplay.GameScheduler$1.run(GameScheduler.java:35) ~[MineSeek-0.0.1-SNAPSHOT.jar:?]
//    at org.bukkit.craftbukkit.scheduler.CraftTask.run(CraftTask.java:78) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at org.bukkit.craftbukkit.scheduler.CraftScheduler.mainThreadHeartbeat(CraftScheduler.java:474) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1659) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1529) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1251) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at net.minecraft.server.MinecraftServer.lambda$spin$2(MinecraftServer.java:310) ~[paper-1.21.4.jar:1.21.4-189-a866e36]
//    at java.base/java.lang.Thread.run(Thread.java:1583) ~[?:?]
	
}