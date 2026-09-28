package de.minetrain.mineseek.gameplay;

import org.bukkit.Bukkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.minetrain.mineseek.commands.StartSettingsCommand;
import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.enums.PlayerPermissions;
import de.minetrain.mineseek.utils.ChatMessageHandler;

public class GameState {
	private GameSettings settings = new GameSettings();
	private TeamManager teamManager = new TeamManager();
	public PlayerPermissions defaultPermissions = new PlayerPermissions();
	private GameScheduler gameScheduler = new GameScheduler();
	private GamePhase currentGamePhase = GamePhase.IDLE;
	
	public void start(){
		currentGamePhase = GamePhase.PRE_HIDING;
		WorldManager.setPVP(false);
		StartSettingsCommand.sendSettings(this, Bukkit.getWorld("world"));
		gameScheduler.startStarting();
	}
	
	public void startHidePhase(){
		currentGamePhase = GamePhase.HIDING;
		
		ChatMessageHandler.clearChat();

		teamManager.assignTeams(settings);
		
		if(!gameScheduler.check(this)){reset(); return;}

		WorldManager.pickWorld(settings);
		WorldManager.prepairWorld();
		WorldManager.closeSeekerCage();
		WorldManager.loadDifficulty(this);
		
		settings.getCurrentMap().teleport(teamManager);
		gameScheduler.startHiding(this);
	}
	
	public void seakTime(){
		if(!gameScheduler.check(this)){reset(); return;}
		currentGamePhase = GamePhase.SEEKING;
		WorldManager.setPVP(true);
		WorldManager.openSeekerCage();
		ChatMessageHandler.broadcast("§4Der sucher kommt!");
		gameScheduler.startSeaking(this);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(GameState.class);
	public void reset(){
		currentGamePhase = GamePhase.IDLE;
		
		teamManager.reset();
		WorldManager.openSeekerCage();
		WorldManager.resetWorld();
	}
	
	public boolean isRunning(){
		return currentGamePhase.equals(GamePhase.HIDING) || currentGamePhase.equals(GamePhase.SEEKING);
	}
	
	public GameSettings getSettings() {
		return settings;
	}
	
	public TeamManager getTeamManager() {
		return teamManager;
	}
	
	public GamePhase getCurrentGamePhase() {
		return currentGamePhase;
	}

	public PlayerPermissions getDefaultPermissions() {
		return defaultPermissions;
	}

	public void setDefaultPermissions(PlayerPermissions defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}

	public void setSettings(GameSettings settings) {
		this.settings = settings;
	}

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	public void setCurrentGamePhase(GamePhase currentGamePhase) {
		this.currentGamePhase = currentGamePhase;
	}
	
	
	
}
