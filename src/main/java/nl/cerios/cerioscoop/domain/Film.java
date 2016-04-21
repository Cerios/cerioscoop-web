package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Film {

	private int id;
	private String naam;
	private int minuten;
	private int type;
	private String taal;
	private Date premiereDatum;
	private Time premiereTijd;
	private Date laatsteVoorstelling;
	
	public Film(){
		
	}
	
	public Film(int id, String naam, int minuten, int type, String taal, Date premiereDatum, Time premiereTijd, Date laatsteVoorstelling) {
		this.id = id;
		this.naam = naam;
		this.minuten = minuten; 
		this.type = type;
		this.taal = taal;
		this.premiereDatum = premiereDatum;
		this.setPremiereTijd(premiereTijd);
		this.laatsteVoorstelling = laatsteVoorstelling;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getMinuten() {
		return minuten;
	}
	public void setMinuten(int minuten) {
		this.minuten = minuten;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getPremiereDatum() {
		return premiereDatum;
	}
	public void setPremiereDatum(Date premiereDatum) {
		this.premiereDatum = premiereDatum;
	}
	public Date getLaatsteVoorstelling() {
		return laatsteVoorstelling;
	}
	public void setLaatsteVoorstelling(Date laatsteVoorstelling) {
		this.laatsteVoorstelling = laatsteVoorstelling;
	}

	public String getTaal() {
		return taal;
	}

	public void setTaal(String taal) {
		this.taal = taal;
	}

	public Time getPremiereTijd() {
		return premiereTijd;
	}

	public void setPremiereTijd(Time premiereTijd) {
		this.premiereTijd = premiereTijd;
	}
	
}
