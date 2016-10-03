package my.game.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import my.game.enums.LocationType;
import my.game.objects.gui.maps.JTableGameMap;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 265316776577142300L;
	
	private GameFrame gameFrame;
	private StatisticsFrame statisticsFrame;
	private MultiPlayerFrame multiPlayerFrame;
	
	private JTableGameMap gameMap;

	private JButton btnStart;
	private JButton btnStatistics;
	private JButton btnExit;
	private JButton btnMenu;
	private JButton btnMultiplayer;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void gameFrameActionPerformed() {
		gameMap = new JTableGameMap(LocationType.FS, "src\\my\\game\\maps\\game.map");
		gameFrame = new GameFrame(gameMap);
		gameFrame.showFrame(this);
	}
	
	private void multiPlayerActionPerformed(){
		gameMap = new JTableGameMap(LocationType.MFS, "src\\my\\game\\maps\\MultiGame.map");
		multiPlayerFrame = new MultiPlayerFrame(gameMap);
		multiPlayerFrame.showFrame(this);
	}

	private void statisticsFrameAtionPerformed() {
		statisticsFrame = new StatisticsFrame();
		statisticsFrame.showFrame(this);
	}

	private void ExitActionPerformed() {
		System.exit(1);
	}

	public MainMenuFrame() {
		setBounds(800, 600, 381, 351);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Golden Man");

		btnStart = new JButton("Start");
		btnStart.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/my/game/texture/sand-bucket.png")));
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameFrameActionPerformed();
			}
		});

		btnStatistics = new JButton("Statistics");
		btnStatistics.setIcon(new ImageIcon(getClass().getResource("/my/game/texture/statistics.png")));
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statisticsFrameAtionPerformed();
			}
		});
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 25));

		btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(getClass().getResource("/my/game/texture/exit.png")));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitActionPerformed();
			}
		});

		btnMultiplayer = new JButton("Multiplayer");
		btnMultiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Comming soon");
			}
		});
		btnMultiplayer.setFont(new Font("Tahoma", Font.PLAIN, 25));

		btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "it was misstake");
				System.exit(0);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMultiplayer, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
						.addComponent(btnStatistics, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
						.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
						.addContainerGap()));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(btnMultiplayer, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnStatistics, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnMenu, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addGap(16)));
		
		getContentPane().setLayout(groupLayout);

	}
}
