package de.minetrain.mineseek.resources;

public enum Messages {
	RELOAD ("Reload confirmt"),

	HIDE_TIME_SEEKER ("Warte bis du die Verstecker Suchen kannst!"),
	HIDE_TIME_HIDER ("Verstecke dich!"),
	
	SEEK_TIME_SEEKER ("Suche die Verstecker!"),
	SEEK_TIME_HIDER ("Es wird nach dir Gesucht!"),
	
	SEEKER_ON_WAY ("§cDer §4Sucher kommt!"),
	ROUND_CANCEL ("§eRunde §4abgebrochen!"),
	
	CMD_BOOST_ARGS ("§c/boost §e>§8boost§e<"),
	CMD_BOOST_HIDER_IN_USE ("§cVerstecker-boost ist in Verwendung!"),
	CMD_BOOST_HIDER_AKTIV ("§2Verstecker boost §aaktiviert!"),
	CMD_BOOST_HIDER_DEAKTIV ("§2Verstecker boost §cdeaktiviert!"),

	CMD_PERM_ARGS ("§c/permission §e>§5PlayerName§e<"),
	CMD_PERM_PLAYER_NOT_EXIST ("§cEs konnte kein Datensatz gefunden werden für §5"),
	CMD_PERM_SELF_ADMIN ("§4Du darfst dir nicht selbst §cAdmin §4weg nehmen!"),

	ERROR_CHAT_MESSAGES ("§cError"),
	ERROR_CMD_SHOW ("§4Du darfst §e/show §4nicht Benutzen!"),
	ERROR_CMD_PING ("§4Du darfst §e/piep §4& §e/ping §4nicht Benutzen!"),
	ERROR_CMD_FRITTEN ("§4Du darfst §e/fritten §4nicht Benutzen!"),
	ERROR_CMD_START ("§4Du darfst §e/start §4nicht Benutzen!"),
	ERROR_CMD_START_ALLONE ("§cDu darfst nicht Alleine Starten!"),
	ERROR_CMD_START_SETTINGS ("§4Du darfst §e/system §4nicht Benutzen!"),
	ERROR_CMD_VOTE_STOP ("§4Du darfst §e/vote-stop §4nicht Benutzen!"),
	ERROR_CMD_VOTE_VIWER ("§eZuschauer §cdürfen nicht Abstimmen!"),
	ERROR_CMD_VOTE_DUPLICATE ("§4Du darfst nicht 2x Abstimmen!"),
	ERROR_CMD_PERMISSION ("§4Du darfst §e/permission §4nicht Benutzen!"),

	GUI_NAMES_PERMISSIONS ("§8Permissions Menu §5"),
	
	GUI_PAPER_START ("§e/start"),
	GUI_PAPER_START_SETTING ("§e/start-settings"),
	GUI_PAPER_CHAT ("§eChat §efreischaltung"),
	GUI_PAPER_VOTE_STOP ("§e/stop-vote"),
	GUI_PAPER_RELOAD ("§e/rl"),
	GUI_PAPER_FRITTEN ("§e/fritten"),
	GUI_PAPER_PING ("§e/piep §8& §e/ping"),
	GUI_PAPER_SHOW ("§e/show"),
	GUI_PAPER_ADMIN ("§4admin"),
	GUI_PAPER_MODIFY_WORLD ("§4Modify World"),
	GUI_NAMETAG_PRESET_ADMIN ("§2Pre-Admin"),
	GUI_NAMETAG_PRESET_NORMAL ("§bPre-Normal"),
	GUI_NAMETAG_PRESET_NO_PERM ("§4Pre-noPerm");
	
	
//	ERROR_
	
	private String text;
	public String getText(){return text;}
	
	private Messages(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return getText();
	}
	

}
