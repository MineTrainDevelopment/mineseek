package de.minetrain.mineseek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.main.Main;
import de.minetrain.mineseek.utils.ChatMessageHandler;

public class ResetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		GameState game = Main.getGameState();
		if(!game.getCurrentGamePhase().equals(GamePhase.IDLE)){
			
			int votes = game.getSettings().getVoteStop();
			int players = game.getTeamManager().getPlayingPlayers().size() / 2;
			votes++;
			
			if(sender.isOp() || votes > players){
				ChatMessageHandler.clearChat();
				ChatMessageHandler.broadcast("§4Vote reset!");
				game.reset();
				game.getSettings().setVoteStop(0);
				return true;
			}
			
			
			ChatMessageHandler.broadcast("§8Vote reset! [§4" + votes + "§8/§2" + players + "§8]");
			game.getSettings().setVoteStop(votes);
			
		}
		return false;
	}

}
