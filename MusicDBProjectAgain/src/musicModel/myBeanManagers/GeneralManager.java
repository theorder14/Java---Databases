package musicModel.myBeanManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import musicModel.MyBeans.Album;
import Connection.MyJDBCCloser;
import Connection.MyJDBCConnector;




public class GeneralManager {

	private static Connection conn = MyJDBCConnector.getJDBCConnection();
	

	
	public static String[] getColonTitles(String sql) {
		//String sql = "SELECT * FROM album";
		String[] colonTitles = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		//pk_album_id, album_name, fk_artist_id
		try {
			stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			colonTitles = new String[rsmd.getColumnCount()];
			for(int i=0; i<colonTitles.length; i++) {
				colonTitles[i] = rsmd.getColumnName(i+1);
			}
		} catch (SQLException e) {
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.err.println("SQL state: " +e.getSQLState());
		} finally {
			MyJDBCCloser.close(rs, stmt);
		}
		return colonTitles;
	}

	
	//TODO keep developing this method, it should be coolish called from TopModel class!
	public static List<Album> searchAlbums(String sql, String searchStr) {
		List<Album> albumList = new ArrayList<>();
//		String sql =
//		"SELECT album_name, artist_name FROM album INNER JOIN artist"
//		+ " ON fk_artist_id = pk_artist_id";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Album alb = new Album();
				alb.setPkAlbumId(rs.getInt("pk_album_id"));
				alb.setAlbumName(rs.getString("album_name"));
				alb.setFkArtistId(rs.getInt("fk_artist_id"));		
				albumList.add(alb);	
			}		
		} catch (SQLException e) {
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Error code: " +e.getErrorCode());
			System.err.println("SQL state: " +e.getSQLState());	
		}finally {
			MyJDBCCloser.close(rs, stmt);
		}
		return albumList;
	}

}

