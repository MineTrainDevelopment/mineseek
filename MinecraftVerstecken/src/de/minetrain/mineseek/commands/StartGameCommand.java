package de.minetrain.mineseek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.main.Main;

public class StartGameCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(Main.getGameState().getCurrentGamePhase().equals(GamePhase.IDLE)){
			Main.getGameState().start();
		}
		return false;
	}

}
