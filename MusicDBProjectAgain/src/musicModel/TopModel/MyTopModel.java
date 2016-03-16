package musicModel.TopModel;



import java.util.List;

import javax.swing.table.DefaultTableModel;

import musicModel.MyBeans.Album;
import musicModel.MyBeans.Artist;
import musicModel.MyBeans.Genre;
import musicModel.MyBeans.Track;
import musicModel.myBeanManagers.AlbumManager;
import musicModel.myBeanManagers.ArtistManager;
import musicModel.myBeanManagers.GenreManager;
import musicModel.myBeanManagers.TrackManager;


public class MyTopModel {

	
	//Class to handle data from beans, by using the Managers.
	
	
	public DefaultTableModel getTableData(Table table, String searchStr) {
		
		//DefaultTableModel dm = new DefaultTableModel();
		DefaultTableModel dm = new DefaultTableModel() {
			//making ALL cells uneditable for user.
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		String[] colonTitles = null;
		Object[] rowData = null;
		
		switch(table) {
			case ALBUM:
				List<Album> albList = AlbumManager.searchAlbums(searchStr);
				colonTitles = AlbumManager.getColonTitles();
				addColonTitlesToTable(dm,colonTitles);
				
				rowData = new Object[3];
				System.out.println(rowData.length);
				for(int i=0; i<albList.size(); i++) {
					rowData[0] = albList.get(i).getPkAlbumId();
					rowData[1] = albList.get(i).getAlbumName();
					rowData[2] = albList.get(i).getFkArtistId();
					dm.insertRow(i, rowData);
				}
				return dm;
			case ARTIST:
				List<Artist> artList = ArtistManager.searchArtists(searchStr);
				colonTitles = ArtistManager.getColonTitles();
				addColonTitlesToTable(dm,colonTitles);
				
				rowData = new Object[3];
				System.out.println(rowData.length);
				for(int i=0; i<artList.size(); i++) {
					rowData[0] = artList.get(i).getPkArtistId();
					rowData[1] = artList.get(i).getArtistName();
					rowData[2] = artList.get(i).getCountry();
					dm.insertRow(i, rowData);
				}
				return dm;
			case TRACK:
				List<Track> trkList= TrackManager.searchTracks(searchStr);
				colonTitles = TrackManager.getColonTitles();
				addColonTitlesToTable(dm,colonTitles);
				rowData = new Object[7];
				System.out.println(rowData.length);
				for(int i=0; i<trkList.size(); i++) {
					rowData[0] = trkList.get(i).getPkTrackId();
					rowData[1] = trkList.get(i).getTrackName();
					rowData[2] = trkList.get(i).getTrackTime();
					rowData[3] = trkList.get(i).getFkArtistId();
					rowData[4] = trkList.get(i).getFkAlbumId();
					rowData[5] = trkList.get(i).getReleaseDate();
					rowData[6] = trkList.get(i).getFkGenreId();
					dm.insertRow(i, rowData);
				}
				return dm;
			case GENRE:
				List<Genre> genList= GenreManager.searchGenres(searchStr);
				colonTitles = GenreManager.getColonTitles();
				addColonTitlesToTable(dm,colonTitles);
				rowData = new Object[2];
				System.out.println(rowData.length);
				for(int i=0; i<genList.size(); i++) {
					rowData[0] = genList.get(i).getPkGenreId();
					rowData[1] = genList.get(i).getGenreName();
					dm.insertRow(i, rowData);
				}
				return dm;
			default:
				System.out.println("No DefaultTableModel");
				return null;
		}
	}
	
	private void addColonTitlesToTable(DefaultTableModel dm, String[] colonTitles) {
		for(int i=0; i<colonTitles.length;i++)
			dm.addColumn(colonTitles[i]);
	}
	
}
