package de.minetrain.mineseek.gameplay.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.gameplay.TeamManager;
import de.minetrain.mineseek.main.Main;

public class DeathEvent implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	private void onPlayerDeath(PlayerDeathEvent event){
		GameState game = Main.getGameState();
		if(game.getCurrentGamePhase().equals(GamePhase.SEEKING)){
			Player player = event.getEntity().getPlayer();
			TeamManager teamManager = game.getTeamManager();
			
			teamManager.setSpectator(player);
			game.getSettings().getCurrentMap().teleportSpectator(player);
			event.setDeathMessage("§5" + player.getName() + " §8wurde Gefunden!");
			
			if (teamManager.getNextSeeker() == null) {
				teamManager.setNextSeeker(player);
			}
		}
	}
}
