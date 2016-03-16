package musicModel.MyBeans;

import java.io.Serializable;

public class Album implements Serializable {

	private static final long serialVersionUID = -2066275953075423489L;
	private int pkAlbumId;
	private String albumName;
	private int fkArtistId;
	
	
    public Album() {
    	
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
