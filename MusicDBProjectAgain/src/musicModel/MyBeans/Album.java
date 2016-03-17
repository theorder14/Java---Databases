package musicModel.MyBeans;

import java.io.Serializable;

public class Album implements Serializable {

	private static final long serialVersionUID = -2066275953075423489L;
	private int pkAlbumId;
	private String albumName;
	private int fkArtistId;
	
	
    public Album() {
    	
    }
    
    public Album(String albumName, int fkArtistId) {
    	this.albumName = albumName;
    	this.fkArtistId = fkArtistId;
    }
    
    //I have autoIncrement on pk, so I need to constructor above aswell.
    public Album(int pkAlbumId, String albumName, int fkArtistId) {
    	this.pkAlbumId = pkAlbumId;
    	this.albumName = albumName;
    	this.fkArtistId = fkArtistId;
    }
    
    //getters
    public int getPkAlbumId() {
    	return pkAlbumId;
    }
    public String getAlbumName() {
    	return albumName;
    }
    public int getFkArtistId() {
    	return fkArtistId;
    } 
    
    //setters
    public void setPkAlbumId(int pkAlbumId) {
         this.pkAlbumId = pkAlbumId;       	
    }
    public void setAlbumName(String albumName ){
    	this.albumName = albumName;
    }
    public void setFkArtistId (int fkArtistId){
    	this.fkArtistId = fkArtistId;
    }
    
    public String toString() {
    	return pkAlbumId + ", " + albumName + ", " + fkArtistId;
    }
}
