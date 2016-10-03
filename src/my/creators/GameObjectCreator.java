package my.creators;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.FileCharacterType;
import my.game.enums.GameObjectType;
import my.game.objects.*;

public class GameObjectCreator{

	private static GameObjectCreator instance;
	
	public static GameObjectCreator getInsatce(){
		if(instance == null){
			instance = new GameObjectCreator();
		}
		return instance;
	}
	
	public AbstractGameObject createObject(GameObjectType type, Coordinate coordinate){
		
		AbstractGameObject obj = null;
		
		switch(type){
		 	case WALL: {
		 		obj = new Wall(coordinate);
		 		break;
		 	}
		 	case NOTHING: {
		 		obj = new Nothing(coordinate);
		 		break;
		 	} 
		 	case PLAYER: {
		 		obj = new Players(coordinate);
		 		break;
		 	}
		 	case GOLD: {
		 		obj = new Gold(coordinate);
		 		break;
	 		}
		 	case MONSTER: {
		 		obj = new Monster(coordinate);
		 		break;
		 	}
		 	case EXIT: {
		 		obj = new Exit(coordinate);
		 		break;
		 	}
		 	default: 
		 		throw new IllegalArgumentException("Can't crete object type: " + type);
		}
		
		return obj;
	}
	
	public AbstractGameObject createObject(FileCharacterType type, Coordinate coordinate){
		
		AbstractGameObject obj = null;
		
		switch(type){
		 	case WALL: {
		 		obj = new Wall(coordinate);
		 		break;
		 	}
		 	case NOTHING: {
		 		obj = new Nothing(coordinate);
		 		break;
		 	} 
		 	case PLAYER: {
		 		obj = new Players(coordinate);
		 		break;
		 	}
		 	case GOLD: {
		 		obj = new Gold(coordinate);
		 		break;
	 		}
		 	case MONSTER: {
		 		obj = new Monster(coordinate);
		 		break;
		 	}
		 	case EXIT: {
		 		obj = new Exit(coordinate);
		 		break;
		 	}
		 	default: 
		 		throw new IllegalArgumentException("Can't crete object type: " + type);
		}
		
		return obj;
	}
}
