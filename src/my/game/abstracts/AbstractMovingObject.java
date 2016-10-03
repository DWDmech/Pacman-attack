package my.game.abstracts;

import javax.swing.ImageIcon;

import my.game.enums.MovingDirection;
import my.game.interfaces.gameobjects.MoveObject;
import my.game.objects.Coordinate;

public abstract class AbstractMovingObject extends AbstractGameObject implements MoveObject{

	private ImageIcon iconLeft;
	private ImageIcon iconRight;
	private ImageIcon iconUP;
	private ImageIcon iconDown;
	
	public ImageIcon getIconLeft() {
		return this.iconLeft;
	}

	public ImageIcon getIconRight() {
		return this.iconRight;
	}

	public ImageIcon getIconUP() {
		return this.iconUP;
	}

	public ImageIcon getIconDown() {
		return this.iconDown;
	}

	public void setIconLeft(ImageIcon iconLeft) {
		this.iconLeft = iconLeft;
	}

	public void setIconRight(ImageIcon iconRight) {
		this.iconRight = iconRight;
	}

	public void setIconUP(ImageIcon iconUP) {
		this.iconUP = iconUP;
	}

	public void setIconDown(ImageIcon iconDown) {
		this.iconDown = iconDown;
	}

	public void move(MovingDirection direction) {
		int x = this.getCoordinate().getX();
		int y = this.getCoordinate().getY();
		
		Coordinate newCoordinate = new Coordinate(x,y);
		
		switch(direction){
			case UP: {
				super.setIcon(getIconUP());
				newCoordinate.setXY(x, y - 1);
				break;
			}
			case DOWN: {
				super.setIcon(getIconDown());
				newCoordinate.setXY(x, y + 1);
				break;
			}
			case LEFT: {
				super.setIcon(getIconLeft());
				newCoordinate.setXY(x - 1, y);
				break;
			}
			case RIGHT: {
				super.setIcon(getIconRight());
				newCoordinate.setXY(x + 1, y);
				break;
			}
		}
		
		setCoordinate(newCoordinate);
	}

	public void getMoveResult(AbstractGameObject obj) {
		throw new UnsupportedOperationException("no supported yet");
	}
	
}
