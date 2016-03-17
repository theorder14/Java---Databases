package musicModel.MyBeans;

import java.io.Serializable;


public class Track implements Serializable{


	private static final long serialVersionUID = -9022840962289518837L;
	private int pkTrackId;					
	private String trackName;							
	private int trackTime;			
	private int fkArtistId;		
	private int fkAlbumId;		
	private int releaseDate;
	private int fkGenreId;
	
	public Track() {
		
	}
    public Track(String trackName, int trackTime, int fkArtistId, int fkAlbumId, int releaseDate, int fkGenreId ) {
    	this.trackName = trackName;
    	this.trackTime = trackTime;
    	this.fkArtistId = fkArtistId;
    	this.fkAlbumId = fkAlbumId;
    	this.releaseDate = releaseDate;
    	this.fkGenreId = fkGenreId;
    }
    
    //I have autoIncrement on pk, so I need to constructor above aswell.
    public Track(int pkTrackId, String trackName, int trackTime, int fkArtistId, int fkAlbumId, int releaseDate, int fkGenreId ) {
    	this.pkTrackId = pkTrackId;
    	this.trackName = trackName;
    	this.trackTime = trackTime;
    	this.fkArtistId = fkArtistId;
    	this.fkAlbumId = fkAlbumId;
    	this.releaseDate = releaseDate;
    	this.fkGenreId = fkGenreId;
    }
	
	//getters
	public int getPkTrackId() {
		return pkTrackId;
	}
	public String getTrackName() {
		return trackName;
	}
	public int getTrackTime() {
		return trackTime;
	}
	public int getFkArtistId() {
		return fkArtistId;
	}
	public int getFkAlbumId() {
		return fkAlbumId;
	}
	public int getReleaseDate() {
		return releaseDate;
	}
	public int getFkGenreId() {
		return fkGenreId;
	}
	
	//setters
	public void setPkTrackId(int pkTrackId) {
		this.pkTrackId = pkTrackId;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public void setTrackTime(int trackTime) {
		this.trackTime = trackTime;
	}
	public void setFkArtistId(int fkArtistId) {
		this.fkArtistId = fkArtistId;
	}
	public void setFkAlbumId(int fkAlbumId) {
		this.fkAlbumId = fkAlbumId;
	}
	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setFkGenreId(int fkGenreId) {
		this.fkGenreId = fkGenreId;
	}
	
	public String toString() {
		return pkTrackId   + ", " +
				trackName  + ", " +
				trackTime  + ", " +
				fkArtistId + ", " +
				fkAlbumId  + ", " +
				releaseDate;
	}
}
