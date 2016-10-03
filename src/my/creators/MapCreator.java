package my.creators;

import my.game.abstracts.AbstractGameMap;
import my.game.enums.LocationType;
import my.game.maploader.FSGameMap;
import my.game.maploader.FSMultiPlayerGameMap;

public class MapCreator {

	private static MapCreator instance;
	
	public  static MapCreator getInstance(){
		
		if(instance == null){
			instance = new MapCreator();
		}
		
		return instance;
	}
	
		public AbstractGameMap createMap(LocationType type){
			AbstractGameMap map = null;
				
			switch(type){
					case FS:{
						map = new FSGameMap();
						break;
					}
					case BD:{
						map = null;
						break;
					}
					case MFS:{
						map = new FSMultiPlayerGameMap();
						break;
					}
				default:
					throw new IllegalArgumentException("Can't create map for: " + type);
			}
			return map;
	}
}
