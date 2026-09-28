package de.minetrain.mineseek.main;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.minetrain.mineseek.commands.RandomTipCommand;
import de.minetrain.mineseek.commands.ResetCommand;
import de.minetrain.mineseek.commands.StartGameCommand;
import de.minetrain.mineseek.commands.StartSettingsCommand;
import de.minetrain.mineseek.commands.TipPingCommand;
import de.minetrain.mineseek.commands.TipShowCommand;
import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.gameplay.event.DeathEvent;
import de.minetrain.mineseek.gameplay.event.JoinLeaveEvent;

public class Main extends JavaPlugin { 
	public static final String PLUGIN_NAME = "MineSeek_DEV - 0.0.1";
	public static final String WORLD_NAME = "world";
	private static Main PLUGIN;
	private static GameState GAME_STATE;
	
	@Override
	public void onEnable() {
		super.onEnable();
		PLUGIN = this;
		logInfo("----------------------------------");
		logInfo(PLUGIN_NAME + " starting...");
		
		logInfo("Load Event Listener...");
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new DeathEvent(), this);
		pluginManager.registerEvents(new JoinLeaveEvent(), this);

		getCommand("start").setExecutor(new StartGameCommand());
		getCommand("system").setExecutor(new StartSettingsCommand(){});

		getCommand("tip-show").setExecutor(new TipShowCommand());
		getCommand("tip-random").setExecutor(new RandomTipCommand());
		
		TipPingCommand tipPingCommand = new TipPingCommand();
		getCommand("tip-ping").setExecutor(tipPingCommand);
		getCommand("pip").setExecutor(tipPingCommand);
		
		ResetCommand resetCommand = new ResetCommand();
		getCommand("reset").setExecutor(resetCommand);
		getCommand("vote-stop").setExecutor(resetCommand);
		
	
		logInfo("Create GameState");
		this.GAME_STATE = new GameState();
		
		
		//TODO: Rewordmanager and grant points on winning at #GameScheduler

		logInfo(PLUGIN_NAME+" finished loading!");
		logInfo("----------------------------------");
		
		//Schwirichkeit ändert settings nicht
		
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	private void logInfo(String message){
		getLogger().log(Level.INFO, message);
	}
	
	public static Main getPlugin() {
		return PLUGIN;
	}
	
	public static GameState getGameState() {
		return GAME_STATE;
	}
	
	public static void SendConsoleCommand(String command, String Message) {
		Bukkit.dispatchCommand(Main.getPlugin().getServer().getConsoleSender(), command);
	}
}
