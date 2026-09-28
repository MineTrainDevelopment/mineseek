package de.minetrain.mineseek.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import de.minetrain.mineseek.enums.PlayerRole;
import de.minetrain.mineseek.utils.ChatMessageHandler;


public class TeamManager {
	private HashMap<Player, PlayerRole> players;
	private Player nextSeeker;
	
	public TeamManager() {
		players = new HashMap<Player, PlayerRole>();
	}
	
	
	public void assignTeams(GameSettings settings){
		ArrayList<Player> onlinePlayers = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		Bukkit.getOnlinePlayers();
		
		Player seeker;
		if(settings.isFirstFoundSeeksNext() && nextSeeker != null){
			onlinePlayers.remove(nextSeeker);
			seeker = nextSeeker;
			nextSeeker = null;
		}else{
			seeker = onlinePlayers.remove(new Random().nextInt(onlinePlayers.size()));
		}
		
		setSeeker(seeker);
		onlinePlayers.forEach(this::setHider);
	}
	
	private void setSeeker(Player player) {
		ChatMessageHandler.broadcast("§5"+player.getName()+" §cist sucher!");

		setNamePrefix(player, "§cSucher§f: §5");
		player.setGameMode(GameMode.ADVENTURE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 255555, 255, true));
		players.put(player, PlayerRole.SEEKER);
	}
	
	public void setHider(Player player){
		setNamePrefix(player, "§9Verstecker§f: §5");
		player.setGameMode(GameMode.ADVENTURE);
		players.put(player, PlayerRole.HIDER);
	}
	
	public void setSpectator(Player player){
		setNamePrefix(player, "§eZuschauer: §5");
		player.setGameMode(GameMode.SPECTATOR);
		System.out.println("---------- "+players.get(player));
		players.put(player, PlayerRole.SPECTATOR);
		System.out.println("---------- "+players.get(player));
	}
	
	public void reset(){
		Location spawnLocation = new Location(Bukkit.getWorld("world"), 38, 65, 10, 90, 0);
		
		Bukkit.getOnlinePlayers().forEach(player -> {
			setNamePrefix(player, "§5");
			player.teleport(spawnLocation);
			player.setRespawnLocation(spawnLocation);
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.setGameMode(GameMode.ADVENTURE);
			player.getInventory().clear();
			players.put(player, PlayerRole.IDLE);
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
			players.entrySet().stream().filter(entry -> entry.getValue().equals(PlayerRole.SEEKER)).map(Entry::getKey).forEach(player -> {
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
	
	public void addPlayer(Player player){
		players.put(player, PlayerRole.IDLE);
	}
	
	public void removePlayer(Player player){
		players.remove(player);
		if(nextSeeker != null && nextSeeker.getUniqueId().equals(player.getUniqueId())){
			nextSeeker = null;
		}
	}
	
	public List<Player> getSeekers() {
		return players.entrySet().stream()
				.filter(entry -> entry.getValue().equals(PlayerRole.SEEKER))
				.map(Entry::getKey)
				.toList();
	}
	
	public List<Player> getHiders() {
		return players.entrySet().stream()
				.filter(entry -> entry.getValue().equals(PlayerRole.HIDER))
				.map(Entry::getKey)
				.toList();
	}
	
	public List<Player> getSpectators() {
		return players.entrySet().stream()
				.filter(entry -> entry.getValue().equals(PlayerRole.SPECTATOR))
				.map(Entry::getKey)
				.toList();
	}
	
	/**
	 * @return get all players apart from {@link PlayerRole#SPECTATOR}
	 */
	public List<Player> getPlayingPlayers() {
		return players.entrySet().stream()
				.filter(entry -> !entry.getValue().equals(PlayerRole.SPECTATOR))
				.map(Entry::getKey)
				.toList();
	}

	public Player getNextSeeker() {
		return nextSeeker;
	}

	public void setNextSeeker(Player nextSeeker) {
		this.nextSeeker = nextSeeker;
	}
	
	
}
