package my.game.gui;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import my.game.objects.Players;
import my.game.util.StatisticsUtil;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;


public class StatisticsFrame extends BaseFrame {
	private static final long serialVersionUID = -3921015870021339946L;
	
	private JTable table;
	private String [][] data;
	private StatisticsUtil util = new StatisticsUtil();
	
	public StatisticsFrame() {
		
		try {
			setData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initComponent();
	}
	
	private void initComponent(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("<< Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(10, 11, 110, 35);
		btnBack.addActionListener(e->{
			_closeFrame();
		});
		getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 424, 202);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Nick name", "Levels", "score"
			}
		) {
			private static final long serialVersionUID = 3308740201238341449L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("     Statistics of players");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(130, 11, 304, 35);
		getContentPane().add(lblNewLabel);
	}
	
	private void setData() throws SQLException{
		
		ArrayList <Players> listData = (ArrayList<Players>) util.getAll();
		data = new String[listData.size()][3];
		for(int x = 0 ; x < listData.size(); x++){
				for(int y = 0; y < 3; y++){
					if(y == 0)
					data[x][y] = listData.get(x).getNickName();
					else if(y == 1)
					data[x][y] = String.valueOf(listData.get(x).getLvls());
					else 
					data[x][y] = String.valueOf(listData.get(x).getScore());
				}
		}
	}
	
	private void _closeFrame(){
		closeFrame();
	}
}
