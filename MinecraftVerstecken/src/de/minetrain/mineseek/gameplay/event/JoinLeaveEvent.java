package de.minetrain.mineseek.gameplay.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.gameplay.TeamManager;
import de.minetrain.mineseek.main.Main;

public class JoinLeaveEvent implements Listener{
	
	@EventHandler(ignoreCancelled = true)
	private void onPlayerJoin(PlayerJoinEvent event) {
		GameState game = Main.getGameState();
		TeamManager teamManager = game.getTeamManager();
		Player player = event.getPlayer();
		
		player.getWorld().getBlockAt(200,111,18).setType(Material.REDSTONE_BLOCK);
		teamManager.setNamePrefix(player, "§5");
		
		
		if(game.isRunning()){
			teamManager.setSpectator(player);
			game.getSettings().getCurrentMap().teleportSpectator(player);
		}else{
			teamManager.addPlayer(player);
			player.teleport(new Location(Bukkit.getWorld("world"), 38, 65, 10, 90, 0));
		}
		
		event.setJoinMessage("§5" + player.getName() + " §8[§a+§8]");
	}

	
	@EventHandler(ignoreCancelled = true)
	private void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		player.getInventory().clear();
		
		Main.getGameState().getTeamManager().removePlayer(player);
		event.setQuitMessage("§5" + player.getName() + " §8[§4-§8]");
	}
	

}