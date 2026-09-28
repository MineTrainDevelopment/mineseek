package de.minetrain.mineseek.gameplay;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.minetrain.mineseek.utils.ChatMessageHandler;


public class TeamManager {
	private ArrayList<Player> seekerList = new ArrayList<Player>();
	private ArrayList<Player> hiderList = new ArrayList<Player>();
	private ArrayList<Player> spectatorList = new ArrayList<Player>();
	private Player nextSeeker;
	
	
	public void assignTeams(GameSettings settings){
		ArrayList<Player> onlinePlayers = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		
		Player seeker;
		if(settings.isFirstFoundSeeksNext() && nextSeeker != null){
			onlinePlayers.remove(nextSeeker);
			seeker = nextSeeker;
			nextSeeker = null;
		}else{
			seeker = onlinePlayers.remove(new Random().nextInt(onlinePlayers.size()));
		}
		
		setSeeker(seeker);
		onlinePlayers.forEach(player -> setHider(player));
	}
	
	private void setSeeker(Player player) {
		if(hiderList.contains(player)){hiderList.remove(player);}
		if(spectatorList.contains(player)){spectatorList.remove(player);}
		
		ChatMessageHandler.broadcast("§5"+player.getName()+" §cist sucher!");

		setNamePrefix(player, "§cSucher§f: §5");
		player.setGameMode(GameMode.ADVENTURE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 255555, 255, true));
		seekerList.add(player);
	}
	
	public void setHider(Player player){
		if(seekerList.contains(player)){seekerList.remove(player);}
		if(spectatorList.contains(player)){spectatorList.remove(player);}
		
		setNamePrefix(player, "§9Verstecker§f: §5");
		player.setGameMode(GameMode.ADVENTURE);
		hiderList.add(player);
	}
	
	public void setSpectator(Player player){
		if(seekerList.contains(player)){seekerList.remove(player);}
		if(hiderList.contains(player)){hiderList.remove(player);}
		
		setNamePrefix(player, "§eZuschauer: §5");
		player.setGameMode(GameMode.SPECTATOR);
		spectatorList.add(player);
	}
	
	public void reset(){
		hiderList.clear();
		seekerList.clear();
		spectatorList.clear();
		Location spawnLocation = new Location(Bukkit.getWorld("world"), 38, 65, 10, 90, 0);
		
		Bukkit.getOnlinePlayers().forEach(player -> {
			setNamePrefix(player, "§5");
			player.teleport(spawnLocation);
			player.setRespawnLocation(spawnLocation);
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.setGameMode(GameMode.ADVENTURE);
			player.getInventory().clear();
			//remove vote stop for player.
		});
	}
	
	public void setNamePrefix(Player player, String prefix){
		player.setDisplayName(prefix+player.getName());
		player.setPlayerListName(prefix+player.getName());
	}
	
	public void giveItmes(GameState game) {
		ItemStack seekerBow = new ItemStack(Material.BOW);
		ItemMeta itemMeta = seekerBow.getItemMeta();
		
		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 69420, true);
		itemMeta.setDisplayName("§bEinwegbogen");
		seekerBow.setItemMeta(itemMeta);
		
		if(game.getSettings().getDifficulty().isBow()){
			seekerList.forEach(player -> {
				player.getInventory().setItem(0, seekerBow);
				player.getInventory().setItem(1, new org.bukkit.inventory.ItemStack(Material.ARROW, game.getSettings().getDifficulty().getArrow()));
			});

			giveTorch(game);
		}
	}

	public void giveTorch(GameState game) {
		if(game.getSettings().getDifficulty().isTorch()){
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.getInventory().setItem(8, new ItemStack(Material.TORCH));
			});
		}
	}
	
	public ArrayList<Player> getSeekerList() {
		return seekerList;
	}
	
	public ArrayList<Player> getHiderList() {
		return hiderList;
	}
	
	public ArrayList<Player> getSpectatorList() {
		return spectatorList;
	}

	public Player getNextSeeker() {
		return nextSeeker;
	}

	public void setNextSeeker(Player nextSeeker) {
		this.nextSeeker = nextSeeker;
	}
	
	
}
