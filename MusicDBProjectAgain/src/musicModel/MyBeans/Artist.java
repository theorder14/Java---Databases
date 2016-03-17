package musicModel.MyBeans;

import java.io.Serializable;


public class Artist implements Serializable {

	

	private static final long serialVersionUID = -5773235641959046128L;
//pk_artist_id		artist_name			country
	private int pkArtistId;
	private String artistName;
	private String country;
	
	public Artist() {
	
	}
	
    public Artist(String artistName, String country) {
    	this.artistName = artistName;
    	this.country = country;
    }
    
    //I have autoIncrement on pk, so I need to constructor above aswell.
    public Artist(int pkArtistId, String artistName, String country) {
    	this.pkArtistId = pkArtistId;
    	this.artistName = artistName;
    	this.country = country;
    }
	
	//getters
	public int getPkArtistId() {
		return pkArtistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getCountry() {
		return country;
	}
	
	//setters
	public void setPkArtistId(int pkArtistId) {
		this.pkArtistId = pkArtistId;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		return pkArtistId + " " + artistName + " " + country;
	}
}
