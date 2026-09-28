package de.minetrain.mineseek.gameplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import de.minetrain.mineseek.enums.Difficulty;
import de.minetrain.mineseek.utils.ChatMessageHandler;

public class WorldManager {
	private static World world = Bukkit.getWorld("world");
	
	public static void setPVP(boolean state){
		world.setPVP(state);
	}
	
	
	public static void pickWorld(GameSettings settings){
		if(settings.isRandomMap()){
			settings.setCurrentMap(settings.getCurrentMap().getRandom());
			ChatMessageHandler.broadcast("§bWelt: "+settings.getCurrentMap().getName());
		}else{
			ChatMessageHandler.broadcast("§bWelt: "+settings.getCurrentMap().getName());
		}
	}
	
	public static void prepairWorld(){
		world.getBlockAt(51, 70, 57).setType(Material.REDSTONE_BLOCK); 	//Tunnel-Gitter ZU
		world.getBlockAt(11, 81, 7).setType(Material.REDSTONE_BLOCK); 	  	//Stadtteil 2-Sperre ZU
	}
	
	public static void resetWorld(){
		world.getBlockAt(51, 70, 59).setType(Material.REDSTONE_BLOCK); 	//Tunnel-Gitter AUF
		world.getBlockAt(11, 81, 8).setType(Material.REDSTONE_BLOCK); 		//Stadtteil 2-Sperre AUF
		
		openSeekerCage();
		world.getBlockAt(69,89,-14).setType(Material.AIR); 			//Bibliothek AN
		world.getBlockAt(69,94,-14).setType(Material.AIR); 			//Altstadt AN
		world.getBlockAt(254,90,164).setType(Material.AIR);			//Basti-trum AN
		world.getBlockAt(63,61,20).setType(Material.REDSTONE_BLOCK);	//Wohnhaus AN
		world.getBlockAt(74,83,8).setType(Material.REDSTONE_BLOCK);	//Wohnhaus Dach AN
		world.getBlockAt(67,61,25).setType(Material.REDSTONE_BLOCK);	//Keller licht AN
		world.getBlockAt(79,59,20).setType(Material.AIR);  			//Keller AN
		world.getBlockAt(75,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
		world.getBlockAt(76,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
		world.getBlockAt(51, 70, 59).setType(Material.REDSTONE_BLOCK);	//Tunnel-Gitter AUF
		world.getBlockAt(11, 81, 9).setType(Material.REDSTONE_BLOCK);	//Stadtteil 2-Sperre AUF
	}
	
	public static void openSeekerCage(){
		world.getBlockAt(32,62,12).setType(Material.AIR); 	  			 	//Loft-Tür AUF
		world.getBlockAt(77,66,38).setType(Material.REDSTONE_TORCH); 	 	//Garten-Tür AUF
		world.getBlockAt(76,73,-21).setType(Material.REDSTONE_BLOCK); 	 	//Cybertec-Tür AUF
		world.getBlockAt(45,53,-1).setType(Material.STONE); 	  		  	//U-bahn-Tür AUF
		world.getBlockAt(198,80,151).setType(Material.AIR);				//Gefängnis-Tür AUF
		world.getBlockAt(68, 62, 178).setType(Material.REDSTONE_BLOCK);	//Pommes-Tür AUF
	}
	
	public static void closeSeekerCage(){
		world.getBlockAt(32,62,12).setType(Material.REDSTONE_BLOCK); 	  	//Loft-Tür ZU
		world.getBlockAt(77,66,38).setType(Material.AIR); 	  			  	//Garten-Tür ZU
		world.getBlockAt(76,73,-21).setType(Material.AIR); 	            //Cybertec-Tür ZU
		world.getBlockAt(45,53,-1).setType(Material.REDSTONE_BLOCK); 	  	//U-bahn-Tür ZU
		world.getBlockAt(198,80,151).setType(Material.REDSTONE_BLOCK); 	//Gefängnis-Tür ZU
		world.getBlockAt(68, 62, 178).setType(Material.AIR); 	  		  	//Pommes-Tür ZU
	}
	
	public static void loadDifficulty(GameState game){
		Difficulty difficulty = game.getSettings().getDifficulty();
		difficulty.forceSettings(game.getSettings());
		ChatMessageHandler.broadcast("§bSchwierigkeit: "+difficulty.getName());
		difficulty.giveItems(game);
		
		
		switch(difficulty){
		case EINFACH:
			world.getBlockAt(69,89,-14).setType(Material.REDSTONE_BLOCK); //Bibliothek AUS
			world.getBlockAt(69,94,-14).setType(Material.REDSTONE_BLOCK); //Altstadt AUS
			world.getBlockAt(254,90,164).setType(Material.REDSTONE_BLOCK);//Basti-trum AUS
			world.getBlockAt(63,61,20).setType(Material.AIR);			  //Wohnhaus AUS
			world.getBlockAt(74,83,8).setType(Material.AIR);			  //Wohnhaus Dach AUS
			world.getBlockAt(67,61,25).setType(Material.REDSTONE_BLOCK);  //Keller licht AN
			world.getBlockAt(79,59,20).setType(Material.REDSTONE_BLOCK);  //Keller AUS
			world.getBlockAt(75,60,21).setType(Material.AIR);  //Keller-Tür ZU
			world.getBlockAt(76,60,21).setType(Material.AIR);  //Keller-Tür ZU
			break;
			
		case NORMAL:
			world.getBlockAt(69,89,-14).setType(Material.REDSTONE_BLOCK); //Bibliothek AUS
			world.getBlockAt(69,94,-14).setType(Material.AIR); 			  //Altstadt AN
			world.getBlockAt(254,90,164).setType(Material.AIR);			  //Basti-trum AN
			world.getBlockAt(63,61,20).setType(Material.REDSTONE_BLOCK);	//Wohnhaus AN
			world.getBlockAt(74,83,8).setType(Material.AIR);				//Wohnhaus Dach AUS
			world.getBlockAt(67,61,25).setType(Material.REDSTONE_BLOCK);	//Keller licht AN
			world.getBlockAt(79,59,20).setType(Material.AIR);				//Keller AN
			world.getBlockAt(75,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
			world.getBlockAt(76,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
			break;
			
		case SCHWER:
			world.getBlockAt(69,89,-14).setType(Material.AIR); 			  //Bibliothek AN
			world.getBlockAt(69,94,-14).setType(Material.AIR); 			  //Altstadt AN
			world.getBlockAt(254,90,164).setType(Material.AIR);			  //Basti-trum AN
			world.getBlockAt(63,61,20).setType(Material.REDSTONE_BLOCK);	//Wohnhaus AN
			world.getBlockAt(74,83,8).setType(Material.REDSTONE_BLOCK);	//Wohnhaus Dach AN
			world.getBlockAt(67,61,25).setType(Material.AIR);  			//Keller licht AUS
			world.getBlockAt(79,59,20).setType(Material.AIR);				//Keller AN
			world.getBlockAt(75,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
			world.getBlockAt(76,60,21).setType(Material.REDSTONE_TORCH);	//Keller-Tür AUF
			break;
			
		case HARDCORE:
			world.getBlockAt(69,89,-14).setType(Material.AIR); 			  //Bibliothek AN
			world.getBlockAt(69,94,-14).setType(Material.AIR); 			  //Altstadt AN
			world.getBlockAt(254,90,164).setType(Material.AIR);			  //Basti-trum AN
			world.getBlockAt(63,61,20).setType(Material.REDSTONE_BLOCK);  //Wohnhaus AN
			world.getBlockAt(74,83,8).setType(Material.REDSTONE_BLOCK);	  //Wohnhaus Dach AN
			world.getBlockAt(67,61,25).setType(Material.AIR);  			  //Keller licht AUS
			world.getBlockAt(79,59,20).setType(Material.AIR);  			  //Keller AN
			world.getBlockAt(75,60,21).setType(Material.REDSTONE_TORCH);  //Keller-Tür AUF
			world.getBlockAt(76,60,21).setType(Material.REDSTONE_TORCH);  //Keller-Tür AUF
			world.getBlockAt(51, 70, 59).setType(Material.REDSTONE_BLOCK);	//Tunnel-Gitter AUF
			world.getBlockAt(11, 81, 9).setType(Material.REDSTONE_BLOCK);	//Stadtteil 2-Sperre AUF
			break;
		}
	}
	
}
