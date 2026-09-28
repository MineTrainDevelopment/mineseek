package de.minetrain.mineseek.enums;

public class PlayerPermissions {
	private boolean chat = true;
	private boolean start = true;
	private boolean startSettings = true;
	private boolean voteing = true;
	private boolean fritten = true;
	private boolean ping = true;
	private boolean show = true;
	private boolean reload = false;
	private boolean modifyWorld = false;
	private boolean admin = false;
	private boolean preSet = false;
	
	public void setConsole(){
		chat = false;
		start = false;
		startSettings = false;
		voteing = false;
		fritten = false;
		ping = false;
		show = false;
		reload = false;
		modifyWorld = false;
		admin = false;
		preSet = true;
	}

	public boolean isChat() {
		return chat;
	}

	public boolean isStart() {
		return start;
	}

	public boolean isStartSettings() {
		return startSettings;
	}

	public boolean isVoteing() {
		return voteing;
	}

	public boolean isFritten() {
		return fritten;
	}

	public boolean isPing() {
		return ping;
	}

	public boolean isShow() {
		return show;
	}

	public boolean isReload() {
		return reload;
	}

	public boolean isModifyWorld() {
		return modifyWorld;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isPreSet() {
		return preSet;
	}
	

}
