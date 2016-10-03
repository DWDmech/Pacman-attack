	package my.game.interfaces.gamemap;

	//for game map;
public interface GameMap {
	
	int getHeight();
	
	int getWidth();
	
	boolean saveMap(Object source);
	
	boolean loadMap(Object source);
	
}
