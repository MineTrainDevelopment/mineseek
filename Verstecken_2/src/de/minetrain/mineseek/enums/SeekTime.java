package de.minetrain.mineseek.enums;


public enum SeekTime {
	/**10.min*/
	SHORT(600, "§810.min"),

	/**15.min*/
	MIDDEL(900, "§815.min"),

	/**20.min*/
	LONG(1200, "§820.min"),

	/**30.min*/
	EXTREME(1800, "§830.min");
	
	public int seconds;
	public int getSeconds(){return seconds;}
	
	private final String name;
	public String getName(){return name;}

	private SeekTime(int seconds, String name) {
		this.seconds = seconds;
		this.name = name;
	}
	
	public SeekTime next() {
		return values()[(ordinal() + 1) % values().length];
	}
	
}
