package my.game.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;

import my.game.enums.GameObjectType;
import my.game.interfaces.gamemap.GameMap;
import my.game.objects.Coordinate;

public abstract class AbstractGameMap implements GameMap, Serializable {

	private static final long serialVersionUID = 1l;
	private int width;
	private int height;
	private String name;

	private HashMap<Coordinate, AbstractGameObject> gameObjects = new HashMap<>(); // save all object game
	private EnumMap<GameObjectType, ArrayList<AbstractGameObject>> typeObjects = new EnumMap<>(GameObjectType.class);

	private boolean exitExits;
	private boolean goldManExist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void setGoldManExist(boolean goldManExist) {
		this.goldManExist = goldManExist;
	}

	public void setExitExits(boolean exitExits) {
		this.exitExits = exitExits;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isValidMap() {
		return goldManExist && exitExits;
	}

	public ArrayList<AbstractGameObject> getGameObjects(GameObjectType type) {
		return typeObjects.get(type);
	}

	public AbstractGameObject getPriorityObject(AbstractGameObject first, AbstractGameObject second) {
		return (first.getType().getPriority() > second.getType().getPriority()) ? first : second;
	}

	public AbstractGameObject getObjectByCoordinate(Coordinate coordinate) {
		return gameObjects.get(coordinate);
	}

	public AbstractGameObject getObjectByCoordinate(int x, int y) {
		return gameObjects.get(new Coordinate(x, y));
	}
	
	public void setObjectByCoordinate(Coordinate coordinate, AbstractGameObject value){
		gameObjects.replace(coordinate, value);
	}
	
	public void setObjectByCoordinate(int x, int y, AbstractGameObject value){
		gameObjects.remove(new Coordinate(x,y));
		gameObjects.replace(value.getCoordinate(), value);
	}
	
	public void addGameObject(AbstractGameObject gameObject) {

		ArrayList<AbstractGameObject> tmpList = typeObjects.get(gameObject.getType());

		if (tmpList == null) {
			tmpList = new ArrayList<>();
		}

		tmpList.add(gameObject);

		gameObjects.put(gameObject.getCoordinate(), gameObject);
		typeObjects.put(gameObject.getType(), tmpList);
	}

	public Collection<AbstractGameObject> getAllGameObjects(){
		return gameObjects.values();
	}

	public boolean isExitExits() {
		return this.exitExits;
	}

	public boolean isGoldManExist() {
		return goldManExist;
	}
}
