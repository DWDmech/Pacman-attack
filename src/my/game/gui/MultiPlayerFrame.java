package my.game.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import my.game.abstracts.AbstractGameObject;
import my.game.abstracts.AbstractMovingObject;
import my.game.enums.GameObjectType;
import my.game.enums.LocationType;
import my.game.enums.MovingDirection;
import my.game.objects.Exit;
import my.game.objects.Gold;
import my.game.objects.Monster;
import my.game.objects.Players;
import my.game.objects.Wall;
import my.game.objects.gui.maps.JTableGameMap;
import my.game.util.StatisticsUtil;

public class MultiPlayerFrame extends BaseFrame {
	private static final long serialVersionUID = -3093033146529884551L;

		private JTableGameMap map;

		private JLabel lblDigitalScore;
		private JPanel mapPanel;
		private JDesktopPane desktopPane;
		private StatisticsUtil util = new StatisticsUtil();
		
		public MultiPlayerFrame(JTableGameMap map) {

			this.map = map;
			initComponent();
			
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
			btnRight.setBounds(142, 81, 70, 60);
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
			lblDigitalScore.setText("");
			mainPanel.add(lblDigitalScore);
			
			JLabel lblLevel = new JLabel("Level:");
			lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLevel.setBounds(21, 208, 59, 25);
			mainPanel.add(lblLevel);
			
			JLabel lblDigitalLevel = new JLabel("0");
			lblDigitalLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDigitalLevel.setBounds(79, 208, 133, 25);
			lblDigitalLevel.setText("");
			mainPanel.add(lblDigitalLevel);

			mapPanel = new JPanel();
			mapPanel.setBounds(1, 1, 340, 370);
			mapPanel.add(map.getMap());
			desktopPane.add(mapPanel);
			map.drawMap();
			
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
		}

		private void moveGoldMan(MovingDirection direction) {

			AbstractGameObject goldMan = map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0);
			AbstractGameObject obj;

			if (goldMan instanceof AbstractMovingObject) {

				obj = getNextTypeOfObject(direction, goldMan);
				
				if (obj instanceof Monster) {
					saveAfterDead();
					dieGoldMan();
				}

				if (obj instanceof Exit) {
					exitFromLvl();
				}

				if (obj instanceof Gold) {
					takeGold(obj);
				}
				
				if (!(obj instanceof Wall)) {
					((AbstractMovingObject) goldMan).move(direction);
					map.drawMap();
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

		private void dieGoldMan(){
			
			map = new JTableGameMap(LocationType.FS, "F:\\forJavaEE\\GoldLook\\src\\my\\game\\texture\\game.map");
			mapPanel = new JPanel();
			mapPanel.setBounds(1, 1, 340, 370);
			mapPanel.add(map.getMap());
			desktopPane.add(mapPanel);
			map.drawMap();
			((Players)map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).setToZero();
			lblDigitalScore.setText(String.valueOf(((Players)map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore()));
		
		}
		
		private void exitFromLvl(){
			
			JOptionPane.showMessageDialog(null, "Well done!");
			
			map = new JTableGameMap(LocationType.FS, "F:\\forJavaEE\\GoldLook\\src\\my\\game\\texture\\lvl1.map");
			mapPanel = new JPanel();
			mapPanel.setBounds(1, 1, 340, 370);
			mapPanel.add(map.getMap());
			desktopPane.add(mapPanel);
			map.drawMap();
		}
		
		private void takeGold(AbstractGameObject gold){
			
			((Players)map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).incScore(5);
			lblDigitalScore.setText(String.valueOf(((Players)map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0)).getScore()));
			
			map.getGameMap().getObjectByCoordinate(gold.getCoordinate()).setType(GameObjectType.NOTHING);
			
		}

		private void saveAfterDead(){
			String nickName = JOptionPane.showInputDialog("You are die. Enter nick name for save: ");
			try {
				if(nickName != null){
					Players player = (Players) map.getGameMap().getGameObjects(GameObjectType.PLAYER).get(0);
					player.setNickName(nickName);
					util.add(player);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
