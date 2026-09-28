package de.minetrain.mineseek.gameplay;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.minetrain.mineseek.main.Main;
import de.minetrain.mineseek.resources.Messages;
import de.minetrain.mineseek.utils.ChatMessageHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class GameScheduler {
	private int startScheduler;
	private int hideScheduler;
	private int seakScheduler;
	
	
	public void startStarting(){
		startScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			int counter=GameSettings.getStartCountdownSeconds();
			@Override
			public void run(){
				if(counter>=0){
					for(Player target : Bukkit.getOnlinePlayers()){
						target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy("§2Start: §4"+counter));
					}
					
					counter--;
				}else{
					Bukkit.getScheduler().cancelTask(startScheduler);
					Main.getGameState().startHidePhase();
				}
			}
			
		}, 20, 20);
	}

	
	
	public void startHiding(GameState game){
		hideScheduler=Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			int counter = game.getSettings().getHideTime().getSeconds();
			@Override
			public void run(){
				if(counter>=0){
					if(check(game)){
						int minutes = (counter % 3600) / 60;
						int seconds = counter % 60;
						String timeString = String.format("%02d:%02d", minutes, seconds);
						
						TeamManager teamManager = game.getTeamManager();
						String barSuffix = " §2" + timeString + " §8[§e"+ teamManager.getSpectators().size() + "§8:§4" + teamManager.getSeekers().size() + "§8:§9" + teamManager.getHiders().size() + "§8]";
						
						teamManager.getSeekers().forEach(seeker -> seeker.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(Messages.HIDE_TIME_SEEKER + barSuffix)));
						teamManager.getHiders().forEach(hider -> hider.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(Messages.HIDE_TIME_HIDER + barSuffix)));
						
						counter--;
					}else{
						Bukkit.getScheduler().cancelTask(hideScheduler);
						game.reset();}
				}else{
					Bukkit.getScheduler().cancelTask(hideScheduler);
					game.seakTime();
				}
			}
			
		}, 20, 20);
	}
	
	
	public void startSeaking(GameState game){
		seakScheduler=Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			int counter = game.getSettings().getSeekTime().getSeconds();
			@Override
			public void run(){
				TeamManager teamManager = game.getTeamManager();
				if(counter >= 0){
					if(check(game)){
						int minutes = (counter % 3600) / 60;
						int seconds = counter % 60;
						String timeString = String.format("%02d:%02d", minutes, seconds);

						String barSuffix = " §2" + timeString + " §8[§e"+ teamManager.getSpectators().size() + "§8:§4" + teamManager.getSeekers().size() + "§8:§9" + teamManager.getHiders().size() + "§8]";
						
						teamManager.getSeekers().forEach(seeker -> seeker.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(Messages.SEEK_TIME_SEEKER + barSuffix)));
						teamManager.getHiders().forEach(hider -> hider.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(Messages.SEEK_TIME_HIDER + barSuffix)));
						
						counter--;
					}else{
						Bukkit.getScheduler().cancelTask(seakScheduler);
						game.reset();}
				}else{
					Bukkit.getScheduler().cancelTask(seakScheduler);
//					TODO: Reword manager.
//					for(Player hider : teamManager.getHiderList()){
//						new RewordManager().giveFritten(hider, 10);
//					}
					ChatMessageHandler.broadcast("§2Die zeit ist um! §6Verstecker §2haben gewonnen!");
					game.reset();
				}
			}
			
		}, 20, 20);
	}
	
	public void reset(){
		Bukkit.getScheduler().cancelTask(startScheduler);
		Bukkit.getScheduler().cancelTask(hideScheduler);
		Bukkit.getScheduler().cancelTask(seakScheduler);
	}
	
	
	public boolean check(GameState game){
		if(Bukkit.getOnlinePlayers().size() <= 1){
			ChatMessageHandler.broadcast("§cEs gibt nicht genügend mitspieler!");
			return false;
		}
		
		TeamManager teamManager = game.getTeamManager();
		
		if(teamManager.getHiders().isEmpty()){
			ChatMessageHandler.broadcast("§2Alle §6Verstecker §2wurden gefunden!");
			return false;
		}
		
		if(teamManager.getSeekers().isEmpty()){
			ChatMessageHandler.broadcast("§cDie §4Sucher §chaben aufgegeben!");
			return false;
		}
		
//		teamManager.giveTorch(game);
		return true;
	}

}
