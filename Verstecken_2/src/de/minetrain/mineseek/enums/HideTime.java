package de.minetrain.mineseek.enums;

public enum HideTime {
	/**30.sec*/
	SHORT(30, "§830.sec"),
	
	/**1.min*/
	MIDDEL(60, "§81.min"),
	
	/**2.min*/
	LONG(120, "§82.min"),
	
	/**3.min*/
	EXTREME(180, "§83.min");

	public int seconds;
	public int getSeconds(){return seconds;}
	
	private final String name;
	public String getName(){return name;}

	private HideTime(int seconds, String name) {
		this.seconds = seconds;
		this.name = name;
	}
	
	public HideTime next() {
		return values()[(ordinal() + 1) % values().length];
	}
	
}
