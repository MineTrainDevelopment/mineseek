package de.minetrain.mineseek.commands;

import java.util.List;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.minetrain.mineseek.enums.Difficulty;
import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.enums.TimeOfDay;
import de.minetrain.mineseek.gameplay.GameSettings;
import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.main.Main;
import de.minetrain.mineseek.resources.Messages;
import de.minetrain.mineseek.utils.ChatMessageHandler;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public abstract class StartSettingsCommand implements CommandExecutor, TabCompleter{
	enum CommandList{SEAKTIME, HIDETIME, RANDOMMODE, WETHER, DAYTIME, WORLD, DIFFICULTY, NON}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		GameState game = Main.gameState;
		//Catch if you can use the command.
		String messageError = Messages.ERROR_CMD_START_SETTINGS.getText();
		if(!(sender instanceof Player) || !game.defaultPermissions.isStartSettings()){
			sender.sendMessage(messageError);
			return false;
		}

		//Catch if game is running
		GamePhase currentGamePhase = game.getCurrentGamePhase();
		if(currentGamePhase.equals(GamePhase.HIDING) || currentGamePhase.equals(GamePhase.SEEKING)){
			sender.sendMessage(messageError);
			return false;
		}
		
		//Catch if command is manipulated.
		if(args.length!=1){
			sender.sendMessage(messageError);
			return false;
		}
		
		GameSettings settings = game.getSettings();
		Player player = (Player) sender;
		
		switch (args[0]) {
		case "setSeakTime":
			settings.setSeekTime(settings.getSeekTime().next());
			break;
			
		case "setHideTime":
			settings.setHideTime(settings.getHideTime().next());
			break;
			
		case "setRandom":
			settings.setRandomMap(!settings.isRandomMap());
			break;
			
		case "setWether":
			settings.setWeather(settings.getWeather().next());
			break;
			
		case "setDayTime":
			settings.setTimeOfDay(settings.getTimeOfDay().next());
			break;
			
		case "setWorld":
			settings.setCurrentMap(settings.getCurrentMap().next());
			break;
			
		case "setDifficulty":
			settings.setDifficulty(settings.getDifficulty().next());
			break;
		
		case "non":
			break;
			
		default:
			player.sendMessage(messageError);
			return false;
		}
		
		sendSettings(game, player.getWorld());
		return false;
	}
	
	public static void sendSettings(GameState game, World world){
		GameSettings settings = game.getSettings();
		ChatMessageHandler.clearChat();
		ChatMessageHandler.sendSpacer();
		
		//Suggest gmae settings based on difficulty.
		settings.getDifficulty().suggestSettings(settings);
		
		broadcast("§bWelt: "+settings.getCurrentMap().getName(), CommandList.WORLD);
			
		if(settings.isRandomMap()){
			broadcast("§bRandom: §2True", CommandList.RANDOMMODE);
		}else{
			broadcast("§bRandom: §4False", CommandList.RANDOMMODE);
		}
		
		broadcast("§bSchwierigkeit: "+settings.getDifficulty().getName(), CommandList.DIFFICULTY);
		
		ChatMessageHandler.sendSpacer();
		
		broadcast("§bVersteckdauer: "+settings.getHideTime().getName(), CommandList.HIDETIME);
		broadcast("§bSpieldauer: "+settings.getSeekTime().getName(), CommandList.SEAKTIME);
		
		//Block the player from changing time of day on Hardcore.
		if(settings.getDifficulty() == Difficulty.HARDCORE){
			broadcast("§8Tageszeit: "+TimeOfDay.NIGHT.getName(), CommandList.NON);
		}else{
			broadcast("§bTageszeit: "+settings.getTimeOfDay().getName(), CommandList.DAYTIME);
		}
		settings.getTimeOfDay().setGameTime(world);
		
		broadcast("§bWetter: "+settings.getWeather().getName(), CommandList.WETHER);
		settings.getWeather().setGameWeather(world);
		
		ChatMessageHandler.sendSpacer();
	}
	
	
	
	
	/**
	 * Broadcast message
	 * @param message String
	 * @param command enum
	 */
	private static void broadcast(String message, CommandList command){
		StringBuilder runCommand = new StringBuilder();
		runCommand.append("/system ");
		
		switch(command){
		case WORLD: runCommand.append("setWorld"); break;
		case DAYTIME: runCommand.append("setDayTime"); break;
		case DIFFICULTY: runCommand.append("setDifficulty"); break;
		case SEAKTIME: runCommand.append("setSeakTime"); break;
		case HIDETIME: runCommand.append("setHideTime"); break;
		case RANDOMMODE: runCommand.append("setRandom"); break;
		case WETHER: runCommand.append("setWether"); break;
		default: runCommand.append("non");}
		
		TextComponent Message = new TextComponent(message);
		Message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(message)));
		Message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,(runCommand.toString())));
		
		for(Player player : Bukkit.getOnlinePlayers()){
			player.spigot().sendMessage(Message);
		}
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if(args.length != 1){return null;}
		return Stream.of("§cSystem command! §4PLS DONT use it!").filter(s -> s.toLowerCase().contains(args[0].toLowerCase())).toList();
	}
}
