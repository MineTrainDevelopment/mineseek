package de.minetrain.mineseek.gameplay;

import org.bukkit.Bukkit;

import de.minetrain.mineseek.enums.Barrier;
import de.minetrain.mineseek.enums.Difficulty;
import de.minetrain.mineseek.enums.SeekerCage;
import de.minetrain.mineseek.main.Main;
import de.minetrain.mineseek.utils.ChatMessageHandler;

public class WorldManager {

	
	public void setPVP(boolean state){
		Bukkit.getWorld(Main.WORLD_NAME).setPVP(state);
	}
	
	
	public void pickWorld(GameSettings settings){
		if(settings.isRandomMap()){
			settings.setCurrentMap(settings.getCurrentMap().getRandom());
			ChatMessageHandler.broadcast("§bWelt: "+settings.getCurrentMap().getName());
		}else{
			ChatMessageHandler.broadcast("§bWelt: "+settings.getCurrentMap().getName());
		}
	}
	
	public void prepairWorld(){
		Barrier.TUNNEL_GUTTER.close();
		Barrier.NEW_DISTRICT.close();
	}
	
	public void resetWorld(){
		SeekerCage.openAll();
		Barrier.TUNNEL_GUTTER.open();
		Barrier.NEW_DISTRICT.open();
		Barrier.LIBRARY.open();
		Barrier.OLD_TOWN.open();
		Barrier.BASTI_TOWER.open();
		Barrier.HOME.open();
		Barrier.HOME_ROOF.open();
		Barrier.HOME_BASEMENT.open();
		Barrier.HOME_BASEMENT_LIGHT.open();
		Barrier.TERRA_ROOF.open();
		Barrier.TERRA_FORREST.open();
		Barrier.TERRA_STORAGE.open();
	}
	
	
	public void loadDifficulty(GameState game){
		Difficulty difficulty = game.getSettings().getDifficulty();
		difficulty.forceSettings(game.getSettings());
		difficulty.giveItems(game);
		ChatMessageHandler.broadcast("§bSchwierigkeit: "+difficulty.getName());
		
		
		switch(difficulty){
		case EINFACH:
			Barrier.LIBRARY.close();
			Barrier.OLD_TOWN.close();
			Barrier.BASTI_TOWER.close();
			Barrier.HOME.close();
			Barrier.HOME_ROOF.close();
			Barrier.HOME_BASEMENT.close();
			Barrier.HOME_BASEMENT_LIGHT.open();
			Barrier.TERRA_ROOF.close();
			Barrier.TERRA_FORREST.close();
			Barrier.TERRA_STORAGE.close();
			break;
			
		case NORMAL:
			Barrier.LIBRARY.close();
			Barrier.OLD_TOWN.open();
			Barrier.BASTI_TOWER.open();
			Barrier.HOME.open();
			Barrier.HOME_ROOF.close();
			Barrier.HOME_BASEMENT.open();
			Barrier.HOME_BASEMENT_LIGHT.open();
			Barrier.TERRA_ROOF.open();
			Barrier.TERRA_FORREST.close();
			Barrier.TERRA_STORAGE.close();
			break;
			
		case SCHWER:
			Barrier.LIBRARY.open();
			Barrier.OLD_TOWN.open();
			Barrier.BASTI_TOWER.open();
			Barrier.HOME.open();
			Barrier.HOME_ROOF.open();
			Barrier.HOME_BASEMENT.open();
			Barrier.HOME_BASEMENT_LIGHT.close();
			Barrier.TERRA_ROOF.open();
			Barrier.TERRA_FORREST.open();
			Barrier.TERRA_STORAGE.close();
			break;
			
		case HARDCORE:
			Barrier.TUNNEL_GUTTER.open();
			Barrier.NEW_DISTRICT.open();
			Barrier.LIBRARY.open();
			Barrier.OLD_TOWN.open();
			Barrier.BASTI_TOWER.open();
			Barrier.HOME.open();
			Barrier.HOME_ROOF.open();
			Barrier.HOME_BASEMENT.open();
			Barrier.HOME_BASEMENT_LIGHT.close();
			Barrier.TERRA_ROOF.open();
			Barrier.TERRA_FORREST.open();
			Barrier.TERRA_STORAGE.open();
			break;
		}
	}
	
}
