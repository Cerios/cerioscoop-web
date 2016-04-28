package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Voorstelling {

	private int voorstellingID;
	private String filmNaam;
	private int minutenFilm;
	private int typeFilm;
	private String taal;
	private Date premiereDatum;
	private Time premiereTijd;
	private Date laatsteVoorstelling;
	private Date laatsteVoorstellingTijd;
	private String zaalNaam;
	
	public Voorstelling() {
		
	}

	
	
	public int getVoorstellingID() {
		return voorstellingID;
	}

	public void setVoorstellingID(int voorstellingID) {
		this.voorstellingID = voorstellingID;
	}

	public String getFilmNaam() {
		return filmNaam;
	}

	public void setFilmNaam(String filmNaam) {
		this.filmNaam = filmNaam;
	}

	public int getMinutenFilm() {
		return minutenFilm;
	}

	public void setMinutenFilm(int minutenFilm) {
		this.minutenFilm = minutenFilm;
	}

	public int getTypeFilm() {
		return typeFilm;
	}

	public void setTypeFilm(int typeFilm) {
		this.typeFilm = typeFilm;
	}

	public String getTaal() {
		return taal;
	}

	public void setTaal(String taal) {
		this.taal = taal;
	}

	public Date getPremiereDatum() {
		return premiereDatum;
	}

	public void setPremiereDatum(Date premiereDatum) {
		this.premiereDatum = premiereDatum;
	}

	public Time getPremiereTijd() {
		return premiereTijd;
	}

	public void setPremiereTijd(Time premiereTijd) {
		this.premiereTijd = premiereTijd;
	}

	public Date getLaatsteVoorstelling() {
		return laatsteVoorstelling;
	}

	public void setLaatsteVoorstelling(Date laatsteVoorstelling) {
		this.laatsteVoorstelling = laatsteVoorstelling;
	}

	public Date getLaatsteVoorstellingTijd() {
		return laatsteVoorstellingTijd;
	}

	public void setLaatsteVoorstellingTijd(Date laatsteVoorstellingTijd) {
		this.laatsteVoorstellingTijd = laatsteVoorstellingTijd;
	}

	public String getZaalNaam() {
		return zaalNaam;
	}

	public void setZaalNaam(String zaalNaam) {
		this.zaalNaam = zaalNaam;
	}
}
