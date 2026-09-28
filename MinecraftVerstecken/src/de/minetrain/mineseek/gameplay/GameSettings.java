package de.minetrain.mineseek.gameplay;

import de.minetrain.mineseek.enums.Difficulty;
import de.minetrain.mineseek.enums.HideTime;
import de.minetrain.mineseek.enums.SeekTime;
import de.minetrain.mineseek.enums.TimeOfDay;
import de.minetrain.mineseek.enums.Weather;

public class GameSettings {
	private static final int START_COUNTDOWN_SECONDS = 30;
	private static final int DOOR_CLOSE_COUNTDOWN_SECONDS = 5;

	private HideTime hideTime;
	private SeekTime seekTime;
	private Worlds currentMap;
	
	private boolean randomMap;
	private Weather weather;
	private TimeOfDay timeOfDay;
	private Difficulty difficulty;
	
	private boolean firstFoundSeeksNext;
	
	private int voteStop;
	
	public GameSettings() {
		hideTime = HideTime.SHORT;
		seekTime = SeekTime.MIDDEL;
		currentMap = Worlds.CITY;
		
		randomMap = false;
		weather = Weather.CLEAR;
		timeOfDay = TimeOfDay.DAY;
		difficulty = Difficulty.NORMAL;
		
		firstFoundSeeksNext = true;
		voteStop = 0;
	}
	
	
	public boolean isRandomMap() {
		return randomMap;
	}
	
	public boolean isFirstFoundSeeksNext() {
		return firstFoundSeeksNext;
	}
	
	public Worlds getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(Worlds currentMap) {
		this.currentMap = currentMap;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public HideTime getHideTime() {
		return hideTime;
	}

	public void setHideTime(HideTime hideTime) {
		this.hideTime = hideTime;
	}

	public SeekTime getSeekTime() {
		return seekTime;
	}

	public void setSeekTime(SeekTime seekTime) {
		this.seekTime = seekTime;
	}


	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public TimeOfDay getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(TimeOfDay timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public static int getStartCountdownSeconds() {
		return START_COUNTDOWN_SECONDS;
	}

	public static int getDoorCloseCountdownSeconds() {
		return DOOR_CLOSE_COUNTDOWN_SECONDS;
	}

	public void setRandomMap(boolean randomMap) {
		this.randomMap = randomMap;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setFirstFoundSeeksNext(boolean firstFoundSeeksNext) {
		this.firstFoundSeeksNext = firstFoundSeeksNext;
	}


	public int getVoteStop() {
		return voteStop;
	}


	public void setVoteStop(int voteStop) {
		this.voteStop = voteStop;
	}
	
	
	
	

}
