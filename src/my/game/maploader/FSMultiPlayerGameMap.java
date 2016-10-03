package my.game.maploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import my.creators.GameObjectCreator;
import my.game.abstracts.AbstractGameMap;
import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;
import my.game.objects.Coordinate;

public class FSMultiPlayerGameMap extends AbstractGameMap{
	private static final long serialVersionUID = 7865885000169944872L;

	public boolean saveMap(Object source) {
		throw new UnsupportedOperationException("not working yet");
	}

	public boolean loadMap(Object source) {
	File file = new File(source.toString());
		
		if (!file.exists()) {
			throw new IllegalArgumentException("File not found ");
			
		}

		try {
			setExitExits(false);
			setGoldManExist(false);

			setHeight(getLineNumber(file));

			@SuppressWarnings("resource")
			BufferedReader fin = new BufferedReader(new FileReader(file));

			String lineStr = fin.readLine().trim();

			setName(lineStr.split(",")[0]);

			setWidth(Integer.valueOf(lineStr.split(",")[1]).intValue());
			
			int x = 0;
			int y = 0;

			while ((lineStr = fin.readLine()) != null) {
				x = 0;
				
				for (String str: lineStr.split(",")) {
					createGameObject(str, new Coordinate(x, y));
					x++;
				}
				
				y++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	private int getLineNumber(File file){ 
		int lineCount = 0;
		BufferedReader fread = null;

		try {
			fread = new BufferedReader(new FileReader(file));
			while (fread.readLine() != null) {
				lineCount++;
			}
			lineCount -= 1; // first line is line information
		} catch (IOException e) {
			Logger.getLogger(FSGameMap.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				fread.close();
			} catch (IOException e) {
				Logger.getLogger(FSGameMap.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return lineCount; 
	}
	
	private void createGameObject(String str, Coordinate coordinate){
		GameObjectType type = GameObjectType.valueOf(str.toUpperCase());
		
		AbstractGameObject newObj = GameObjectCreator.getInsatce().createObject(type, coordinate);
		
		addGameObject(newObj);
		
		if(newObj.getType() == GameObjectType.EXIT){
			setExitExits(true);
		}else if (newObj.getType() == GameObjectType.PLAYER){
			setGoldManExist(true);
		}
	}

}
