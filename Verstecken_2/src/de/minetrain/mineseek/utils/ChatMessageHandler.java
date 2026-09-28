package de.minetrain.mineseek.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ChatMessageHandler {
	private static final Logger logger = LoggerFactory.getLogger(ChatMessageHandler.class);
	
	public static void sendSpacer(){broadcast("§b-------------------", false);}
	
	/**
	 * Clean the players chat!
	 */
	public static void clearChat(){
		for(int i=0; i<150; i++){broadcast("", false);}
	}
	
	/**
	 * Broadcast a message to all Players!
	 * @param message
	 */
	public static void broadcast(String message){
		logger.info(message);
		for(Player player : Bukkit.getOnlinePlayers()){
			player.sendMessage(message);
		}
	}
	
	/**
	 * Broadcast a message to all Players!
	 * @param message
	 */
	public static void broadcast(String message, boolean logging){
		if(logging){logger.info(message);}
		for(Player player : Bukkit.getOnlinePlayers()){
			player.sendMessage(message);
		}
	}
}
