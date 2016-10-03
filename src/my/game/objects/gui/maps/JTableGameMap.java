package my.game.objects.gui.maps;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import my.creators.MapCreator;
import my.game.abstracts.AbstractGameMap;
import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;
import my.game.enums.LocationType;
import my.game.interfaces.gamemap.DrawbleMap;
import my.game.objects.Coordinate;
import my.game.objects.Nothing;
import my.game.objects.Wall;

public class JTableGameMap implements DrawbleMap{

	private JTable jTableMap = new JTable();
	
	private AbstractGameMap gameMap;
	
	private String[] columnNames; 
	
	private AbstractGameObject[][] mapObjects;
	
	public JTableGameMap(LocationType type, Object source) {

		jTableMap.setEnabled(false);
		jTableMap.setSize(new java.awt.Dimension(300, 300));
		jTableMap.setRowHeight(30);
		jTableMap.setRowSelectionAllowed(false);
		jTableMap.setShowHorizontalLines(false);
		jTableMap.setShowVerticalLines(false);
		jTableMap.setTableHeader(null);
		jTableMap.setUpdateSelectionOnSort(false);
		jTableMap.setVerifyInputWhenFocusTarget(false);
		 
		gameMap = MapCreator.getInstance().createMap(type);
		gameMap.loadMap(source);
	}
	
	private void updateObjectArray() {
		
			mapObjects = new AbstractGameObject[gameMap.getHeight()][gameMap.getWidth()];
		
			fillEmptyMap(gameMap.getWidth(),gameMap.getHeight());
		
		for(AbstractGameObject gameObj: gameMap.getAllGameObjects()){
			if(!gameObj.getType().equals(GameObjectType.NOTHING)){ // if 'nothing' inside cell, then don't add 
				int x = gameObj.getCoordinate().getX();
				int y = gameObj.getCoordinate().getY();
				if(!(mapObjects[y][x] instanceof Nothing) & //if in this place all ready have some object 
						!(mapObjects[y][x] instanceof Wall)){//different at 'nothing' and 'wall'
					AbstractGameObject tmpObj = mapObjects[y][x];
					mapObjects[y][x] = gameMap.getPriorityObject(tmpObj, gameObj);
				}else{
					mapObjects[y][x] = gameObj;//sets game object at his coordinate
				}
			}
		}
		
	}

	private void fillEmptyMap(int width, int height){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				mapObjects[y][x] = new Nothing(new Coordinate(x,y));
			}
		}
	}
	
	public Component getMap() {
		return jTableMap;
	}
	
	public AbstractGameMap getGameMap(){
		return gameMap;
	}

	public boolean drawMap() {
		
		updateObjectArray();
		
		try{
			int count = jTableMap.getColumnCount();
			columnNames = new String[gameMap.getWidth()];
			
			for(int i = 0; i < columnNames.length; i++){
				columnNames[i] = "";
			}
			
			jTableMap.setModel(new DefaultTableModel(mapObjects, columnNames));
			
			for(int i = 0; i < count; i++){
				jTableMap.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
				TableColumn a = jTableMap.getColumnModel().getColumn(i);
				a.setPreferredWidth(30);
			}
		
				
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
