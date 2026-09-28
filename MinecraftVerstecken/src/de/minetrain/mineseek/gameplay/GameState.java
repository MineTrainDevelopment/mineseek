package de.minetrain.mineseek.gameplay;

import org.bukkit.Bukkit;

import de.minetrain.mineseek.commands.StartSettingsCommand;
import de.minetrain.mineseek.enums.GamePhase;
import de.minetrain.mineseek.enums.PlayerPermissions;
import de.minetrain.mineseek.enums.SeekerCage;
import de.minetrain.mineseek.utils.ChatMessageHandler;

public class GameState {
	private GameSettings settings;
	private TeamManager teamManager;
	public PlayerPermissions defaultPermissions;
	private GameScheduler gameScheduler;
	private WorldManager worldManager;
	private GamePhase currentGamePhase;
	
	public GameState() {
		settings = new GameSettings();
		teamManager = new TeamManager();
		defaultPermissions = new PlayerPermissions();
		gameScheduler = new GameScheduler();
		worldManager = new WorldManager();
		currentGamePhase = GamePhase.IDLE;
	}
	
	public void start(){
		currentGamePhase = GamePhase.PRE_HIDING;
		worldManager.setPVP(false);
		StartSettingsCommand.sendSettings(this, Bukkit.getWorld("world"));
		gameScheduler.startStarting();
	}
	
	public void startHidePhase(){
		currentGamePhase = GamePhase.HIDING;
		
		ChatMessageHandler.clearChat();

		teamManager.assignTeams(settings);
		
		if (!gameScheduler.check(this)) {
			reset();
			return;
		}

		worldManager.pickWorld(settings);
		worldManager.prepairWorld();
		worldManager.loadDifficulty(this);
		SeekerCage.closeAll();
		
		settings.getCurrentMap().teleport(teamManager);
		gameScheduler.startHiding(this);
	}
	
	public void seakTime(){
		if (!gameScheduler.check(this)) {
			reset();
			return;
		}
		
		currentGamePhase = GamePhase.SEEKING;
		worldManager.setPVP(true);
		SeekerCage.openAll();
		ChatMessageHandler.broadcast("§4Der sucher kommt!");
		gameScheduler.startSeaking(this);
	}
	
	public void reset(){
		currentGamePhase = GamePhase.IDLE;
		
		teamManager.reset();
		gameScheduler.reset();
		worldManager.resetWorld();
		SeekerCage.openAll();
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
	
	public GameScheduler getGameScheduler() {
		return gameScheduler;
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
