package de.minetrain.mineseek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minetrain.mineseek.gameplay.TipHandler;

public class RandomTipCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player player) TipHandler.randomTip(player);
		return false;
	}

}
