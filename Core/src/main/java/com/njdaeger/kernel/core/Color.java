package com.njdaeger.kernel.core;

/**
 * Color codes for text
 */
public enum Color {
	
	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	AQUA('3'),
	DARK_RED('4'),
	PURPLE('5'),
	GOLD('6'),
	SILVER('7'),
	GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	LIGHT_BLUE('b'),
	RED('c'),
	MAGENTA('d'),
	YELLOW('e'),
	WHITE('f'),
	MAGIC('k'),
	BOLD('l'),
	STRIKE('m'),
	UNDERLINE('n'),
	ITALICS('o'),
	RESET('r');
	
	private char code;
	private static final char CHAR = '\u00A7';
	
	Color(char code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return new String(new char[] {CHAR, code});
	}
	
	/**
	 * Makes the given string have color with any character
	 * @param specialCharacter The prefix to a chat color
	 * @param base The string to parse
	 * @return The parsed string.
	 */
	public String translate(char specialCharacter, String base) {
		return base.replace(specialCharacter, CHAR);
	}
}
