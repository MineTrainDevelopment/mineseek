package de.minetrain.mineseek.gameplay;

import java.util.List;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.minetrain.mineseek.main.Main;

public class TipHandler {
	
	public static void showPlayer(Player player){
		player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20, 0));
	}
	
	public static void showAll(){
		Main.getGameState().getTeamManager().getHiders().forEach(TipHandler::showPlayer);
	}
	
	public static void pingPlayer(Player player){
		pingAllPlayers(List.of(player));
	}
	
	public static void pingAllPlayers(List<Player> players){
		new BukkitRunnable() {
			private int index = 0;
			
//			Sound.BLOCK_NOTE_BLOCK_PLING
//			Sound.ENTITY_PLAYER_BURP
			
			@Override
			public void run() {
				index++;
				players.forEach(player -> {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
				});
				
				if(index >= 2){
					cancel();
				}
			}
		}.runTaskTimer(Main.getPlugin(), 0, 5);
	}
	
	public static void randomTip(Player player){
		int nextInt = new Random().nextInt(2);
		switch (nextInt) {
		case 0:
			showPlayer(player);
			break;
			
		case 1:
			pingPlayer(player);
			break;

		default:
			break;
		}
	}
	
	public static void randomTipAll(Player player){
		Main.getGameState().getTeamManager().getHiders().forEach(TipHandler::randomTipAll);
	}
}
