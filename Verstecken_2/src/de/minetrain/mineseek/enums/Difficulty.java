package de.minetrain.mineseek.enums;

import de.minetrain.mineseek.gameplay.GameSettings;
import de.minetrain.mineseek.gameplay.GameState;

public enum Difficulty {
	EINFACH(3, true, true, "§aEinfach") {
		@Override
		public void suggestSettings(GameSettings settings) {
			settings.setSeekTime(SeekTime.LONG);
			settings.setHideTime(HideTime.LONG);
		}
	},
	
	NORMAL(1, true, true, "§2Normal") {
		@Override
		public void suggestSettings(GameSettings settings) {
			settings.setSeekTime(SeekTime.MIDDEL);
			settings.setHideTime(HideTime.MIDDEL);
		}
	},
	
	SCHWER(0, false, true, "§cSchwer") {
		@Override
		public void suggestSettings(GameSettings settings) {
			settings.setSeekTime(SeekTime.SHORT);
			settings.setHideTime(HideTime.SHORT);
		}
	},
	
	HARDCORE(0, false, false, "§4Hardcore §e{§bAlpha§e}") {
		@Override
		public void suggestSettings(GameSettings settings) {
			settings.setSeekTime(SeekTime.EXTREME);
			settings.setHideTime(HideTime.EXTREME);
		}
		
		@Override
		public void forceSettings(GameSettings settings) {
			settings.setWeather(Weather.THUNDER);
			settings.setTimeOfDay(TimeOfDay.NIGHT);
		}
	};

	private final int arrow;
	private final boolean bow;
	private final boolean torch;
	private final String name;

	public void suggestSettings(GameSettings settings){}
	public void forceSettings(GameSettings settings){}
	
	private Difficulty(int arrows, boolean bow, boolean torch, String name){
		this.arrow = arrows;
		this.bow = bow;
		this.torch = torch;
		this.name = name;
	}
	
	public int getArrow(){
		return arrow;
	}

	public boolean isBow(){
		return bow;
	}

	public boolean isTorch(){
		return torch;
	}

	public String getName(){
		return name;
	}
	
	public void giveItems(GameState game){
		game.getTeamManager().giveItmes(game);
	}
	
	public Difficulty next() {
		return values()[(ordinal() + 1) % values().length];
	}
}
