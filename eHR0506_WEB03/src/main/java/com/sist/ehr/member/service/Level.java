/**
 * 
 */
package com.sist.ehr.member.service;

/**
 * @author sist
 *
 */
public enum Level {

	//BASIC(1),SILVER(2),GOLD(3);//세 개의 이늄 오브젝트
	GOLD(3,null),SILVER(2,GOLD),BASIC(1,SILVER);
	
	private final int value;
	
	private final Level next;//다음 단계의 Level
	
	
	Level(int value,Level next) {
		this.value = value;
		this.next  = next;
	}
	
	/**
	 * Next Level 
	 * @return Next Level 
	 */
	public Level getNextLevel() {
		return this.next;
	}
	
	//value
	public int intValue() {
		return value;
	}
	
	
	public static Level valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default: throw new AssertionError("Unknown value"+value);
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	