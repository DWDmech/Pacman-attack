package my.game.gui;

import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.Timer;

import my.game.abstracts.AbstractGameObject;
import my.game.abstracts.AbstractMovingObject;
import my.game.enums.GameObjectType;
import my.game.enums.LocationType;
import my.game.enums.MovingDirection;
import my.game.objects.*;
import my.game.objects.gui.maps.JTableGameMap;
import my.game.sounds.SoundLoader;
import my.game.util.StatisticsUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class GameFrame extends BaseFrame {
	private static final long serialVersionUID = -5481770571794506627L;

	private JTableGameMap map;

	private JLabel lblDigitalScore;
	private JPanel mapPanel;
	private JDesktopPane desktopPane;
	private StatisticsUtil util = new StatisticsUtil();
	private Thread sound;
	private Timer drawMap = new Timer(1, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			map.drawMap();
		}
	});

	private Timer movementsEnemys = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int digitalDirection = (int) (Math.random() * 110) % 4;

			switch (digitalDirection) {
			case 0:
				movementsEnemys(MovingDirection.DOWN);
				break;
			case 1:
				movementsEnemys(MovingDirection.LEFT);
				break;
			case 2:
				movementsEnemys(MovingDirection.RIGHT);
				break;
			case 3:
				movementsEnemys(MovingDirection.UP);
				break;
			}
		}
	});
	public GameFrame(JTableGameMap map) {

		this.map = map;
		initComponent();
		drawMap.start();
		movementsEnemys.start();

		sound = new Thread(new Runnable() {
			public void run() {
				SoundLoader load = SoundLoader.getInstance();
				load.loadSound();
				load.startSound();
			}
		});
		sound.start();
		
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					jbtnUpActionPerformed(e);
				}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					jbtnDownActionPerformed(e);
				}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
					jbtnLeftActionPerformed(e);
				}else{
					jbtnRightActionPerformed(e);
				}
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				stopped();
			}
			
		});
	}

	private void initComponent() {
		setBounds(555, 550, 589, 410);

		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(351, 1, 222, 370);
		desktopPane.add(mainPanel);
		mainPanel.setLayout(null);

		JButton btnUP = new JButton("");
		btnUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpActionPerformed(e);
			}
		});
		btnUP.setIcon(new ImageIcon(GameFrame.class.getResource("/my/game/texture/up-arrow.png")));
		btnUP.setBounds(79, 0, 60, 70);
		mainPanel.add(btnUP);

		JButton btnLeft = new JButton("");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnLeftActionPerformed(e);
			}
		});
		btnLeft.setIcon(new ImageIcon(GameFrame.class.getResource("/my/game/texture/left-arrow.png")));
		btnLeft.setBounds(10, 81, 70, 60);
		mainPanel.add(btnLeft);

		JButton btnRight = new JButton("");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnRightActionPerformed(e);
			}
		});
		btnRight.setIcon(new ImageIcon(GameFrame.class.getResource("/my/game/texture/right-arrow.png")));
		btnRight.setBounds(138, 81, 70, 60);
		mainPanel.add(btnRight);

		JButton btnDown = new JButton("");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDownActionPerformed(e);
			}
		});
		btnDown.setIcon(new ImageIcon(GameFrame.class.getResource("/my/game/texture/down-arrow.png")));
		btnDown.setBounds(79, 71, 60, 70);
		mainPanel.add(btnDown);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExit();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(21, 322, 164, 37);
		mainPanel.add(btnExit);

		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScore.setBounds(21, 233, 56, 37);
		mainPanel.add(lblScore);

		lblDigitalScore = new JLabel("");
		lblDigitalScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDigitalScore.setBounds(87, 244, 98, 18);
		lblDigitalScore.setText(
				String.valueOf(((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore()));
		mainPanel.add(lblDigitalScore);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLevel.setBounds(21, 208, 59, 25);
		mainPanel.add(lblLevel);

		JLabel lblDigitalLevel = new JLabel("0");
		lblDigitalLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDigitalLevel.setBounds(79, 208, 133, 25);
		((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).incLvlsCount();
		lblDigitalLevel.setText(
				String.valueOf(((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getLvls()));
		mainPanel.add(lblDigitalLevel);

		mapPanel = new JPanel();
		mapPanel.setBounds(1, 1, 340, 370);
		mapPanel.add(map.getMap());
		desktopPane.add(mapPanel);
		map.drawMap();

	}
	
	private void jbtnUpActionPerformed(KeyEvent e) {
		moveGoldMan(MovingDirection.UP);
	}

	private void jbtnDownActionPerformed(KeyEvent e) {
		moveGoldMan(MovingDirection.DOWN);
	}

	private void jbtnLeftActionPerformed(KeyEvent e) {
		moveGoldMan(MovingDirection.LEFT);
	}

	private void jbtnRightActionPerformed(KeyEvent e) {
		moveGoldMan(MovingDirection.RIGHT);
	}
	
	private void jbtnUpActionPerformed(ActionEvent e) {
		moveGoldMan(MovingDirection.UP);
	}

	private void jbtnDownActionPerformed(ActionEvent e) {
		moveGoldMan(MovingDirection.DOWN);
	}

	private void jbtnLeftActionPerformed(ActionEvent e) {
		moveGoldMan(MovingDirection.LEFT);
	}

	private void jbtnRightActionPerformed(ActionEvent e) {
		moveGoldMan(MovingDirection.RIGHT);
	}

	private void jbtnExit() {
		JOptionPane.showMessageDialog(this, "Your soul be suffer! \\(*0*)/");
		closeFrame();
		stopped();
	}
	
	private void stopped(){
		drawMap.stop();
		movementsEnemys.stop();
		sound.stop();
	}
	
	private void moveGoldMan(MovingDirection direction) {

		AbstractGameObject goldMan = map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0);
		AbstractGameObject obj;
		Coordinate tmp = goldMan.getCoordinate();

		if (goldMan instanceof AbstractMovingObject) {

			obj = getNextTypeOfObject(direction, goldMan);

			if (obj instanceof Monster) {
				saveAfterDead();
				dieGoldMan();
				return;
			}

			if (obj instanceof Exit) {
				exitFromLvl();
				return;
			}

			if (obj instanceof Gold) {
				takeGold(obj);
			}

			if (!(obj instanceof Wall)) {
				((AbstractMovingObject) goldMan).move(direction);
				map.getGameMap().setObjectByCoordinate(goldMan.getCoordinate(), goldMan);
				map.getGameMap().setObjectByCoordinate(tmp, new Nothing());
			}

		}
	}

	private void movementsEnemys(MovingDirection direction) {

		AbstractGameObject enemy;
		AbstractGameObject obj;

		int count = map.getGameMap().getGameObjects(GameObjectType.MONSTER).size();
		for (int i = 0; i < count; i++) {
			enemy = map.getGameMap().getGameObjects(GameObjectType.MONSTER).get(i);
			Coordinate tmp = enemy.getCoordinate();

			if (enemy instanceof AbstractMovingObject) {

				obj = getNextTypeOfObject(direction, enemy);

				if (obj instanceof Monster) {
					continue;
				}

				if (obj instanceof Players) {
					saveAfterDead();
					dieGoldMan();
					return;
				}
				if (obj instanceof Gold) {
					continue;
				}

				if (!(obj instanceof Wall)) {
					((AbstractMovingObject) enemy).move(direction);
					map.getGameMap().setObjectByCoordinate(enemy.getCoordinate(), enemy);
					map.getGameMap().setObjectByCoordinate(tmp, new Nothing());
				}
			}
		}
	}

	private AbstractGameObject getNextTypeOfObject(MovingDirection direction, AbstractGameObject obj) {
		int x = obj.getCoordinate().getX();
		int y = obj.getCoordinate().getY();

		switch (direction) {
		case UP: {
			y = y - 1;
			break;
		}
		case DOWN: {
			y = y + 1;
			break;
		}
		case LEFT: {
			x = x - 1;
			break;
		}
		case RIGHT: {
			x = x + 1;
			break;
		}
		}
		return map.getGameMap().getObjectByCoordinate(x, y);
	}

	private void dieGoldMan() {

		map = new JTableGameMap(LocationType.FS, "src\\my\\game\\maps\\game.map");
		mapPanel = new JPanel();
		mapPanel.setBounds(1, 1, 340, 370);
		mapPanel.add(map.getMap());
		desktopPane.add(mapPanel);
		((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).setToZero();

		lblDigitalScore.setText(
				String.valueOf(((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore()));

	}

	private void exitFromLvl() {
		int score = ((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore();

		JOptionPane.showMessageDialog(null, "Well done!");

		map = new JTableGameMap(LocationType.FS, "src\\my\\game\\maps\\lvl1.map");

		mapPanel = new JPanel();
		mapPanel.setBounds(1, 1, 340, 370);
		mapPanel.add(map.getMap());
		desktopPane.add(mapPanel);
		((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).setScore(score);
		map.drawMap();
	}

	private void takeGold(AbstractGameObject gold) {

		((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).incScore(5);
		lblDigitalScore.setText(
				String.valueOf(((Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore()));

		map.getGameMap().getObjectByCoordinate(gold.getCoordinate()).setType(GameObjectType.NOTHING);

	}

	private void saveAfterDead() {
		String nickName = JOptionPane.showInputDialog("You are die. Enter nick name for save: ");
		try {
			if (nickName != null) {
				Players player = (Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0);
				player.setNickName(nickName);
				util.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}