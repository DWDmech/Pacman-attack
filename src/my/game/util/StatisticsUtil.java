package my.game.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import my.game.objects.Players;

public class StatisticsUtil {
	
	private static final String URL = "jdbc:sqlite:F:\\forJavaEE\\Golden Man\\stats.sqlite";
	
	private static Connection connect;
	
	static{
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Players> getAll() throws SQLException{
		Statement statement = null;
		String query = "SELECT nick_name, lvls, score FROM stats ORDER BY score;";
		ArrayList<Players> list = null;
		
		if(list == null){
			list = new ArrayList<>();
		}
		
		statement = connect.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		
		do{
			list.add(new Players(rs.getString(1), rs.getInt(2), rs.getInt(3)));
		}while(!rs.next());
		
		rs.close();
		statement.close();
		
		return list;
	}
	
	public void add(Players player) throws SQLException{
		Statement statement = null;
		String query = "INSERT INTO stats(nick_name, lvls, score) values '" + player.getNickName() + "', " + player.getLvls() + ", " + player.getScore() + ";";
		
		statement = connect.createStatement();
		statement.executeUpdate(query);
		
		statement.close();
	}
	
}
