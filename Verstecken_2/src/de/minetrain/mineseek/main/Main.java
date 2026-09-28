package de.minetrain.mineseek.main;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.minetrain.mineseek.commands.StartGameCommand;
import de.minetrain.mineseek.commands.StartSettingsCommand;
import de.minetrain.mineseek.gameplay.GameState;
import de.minetrain.mineseek.gameplay.event.DeathEvent;
import de.minetrain.mineseek.gameplay.event.JoinLeaveEvent;

public class Main extends JavaPlugin {
	public static final String pluginName = "MineSeek_DEV - 0.0.1";
	public static Main plugin;
	public static GameState gameState;
	
	@Override
	public void onEnable() {
		super.onEnable();
		plugin = this;
		logInfo("----------------------------------");
		logInfo(pluginName + " starting...");
		
		logInfo("Load Event Listener...");
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new DeathEvent(), this);
		pluginManager.registerEvents(new JoinLeaveEvent(), this);

		getCommand("start").setExecutor(new StartGameCommand());
		getCommand("system").setExecutor(new StartSettingsCommand(){});
		

		logInfo("Create GameState");
		gameState = new GameState();
		
		
		//TODO: Rewordmanager and grant points on winning at #GameScheduler

		logInfo(pluginName+" finished loading!");
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
		return plugin;
	}
	
	public static void SendConsoleCommand(String command, String Message) {
		Bukkit.dispatchCommand(Main.getPlugin().getServer().getConsoleSender(), command);
	}
	

}
