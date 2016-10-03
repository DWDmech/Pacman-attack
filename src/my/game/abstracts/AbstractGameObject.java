package my.game.abstracts;

import javax.swing.ImageIcon;

import my.game.enums.GameObjectType;
import my.game.interfaces.gameobjects.StaticObject;
import my.game.objects.Coordinate;

public abstract class AbstractGameObject implements StaticObject{

	private ImageIcon icon = getImageIcon("/my/game/texture/error.png");
	private Coordinate coordinate;
	private GameObjectType type;
	
	public AbstractGameObject() {
	}
	
	public ImageIcon getIcon() {
		return icon;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public GameObjectType getType() {
		return type;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setType(GameObjectType type) {
		this.type = type;
	}

	protected ImageIcon getImageIcon(String path){
		return new ImageIcon(getClass().getResource(path));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractGameObject other = (AbstractGameObject) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
