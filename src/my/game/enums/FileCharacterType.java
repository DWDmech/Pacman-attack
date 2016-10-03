package my.game.enums;

public enum FileCharacterType {
	
	PLAYER('#'), 
	GOLD('*'), 
	WALL('|'), 
	EXIT('$'), 
	MONSTER('^'), 
	NOTHING(' ');
	
	private int character;
	
	FileCharacterType(char character){
		this.character = character;
	}

	public int getCharacter() {
		return character;
	}
}
