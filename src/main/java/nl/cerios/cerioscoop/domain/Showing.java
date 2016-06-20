package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Showing {
	private int showingId;
	private int filmId;
	private int roomId;
	private Date premiereDate;
	private Time premiereTime;
	private Date lastShowingDate;
	private Time lastShowingTime;
	
	// TODO this constructor is never used, do we need it?
	public Showing() {
		
	}
	public Showing(final int showingId, final int filmId, final int roomId, final Date premiereDate, final Time premiereTime, final Date lastShowingDate, final  Time lastShowingTime) {
		this.showingId = showingId;
		this.filmId = filmId;
		this.roomId = roomId; 
		this.premiereDate = premiereDate;
		this.premiereTime = premiereTime;
		this.lastShowingDate = lastShowingDate;
		this.lastShowingTime = lastShowingTime;
		
	}
	public int getShowingId() {
		return showingId;
	}
	public void setShowingId(final int showingId) {
		this.showingId = showingId;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(final int filmId) {
		this.filmId = filmId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(final int roomId) {
		this.roomId = roomId;
	}
	public Date getPremiereDate() {
		return premiereDate;
	}
	public void setPremiereDate(final Date premiereDate) {
		this.premiereDate = premiereDate;
	}
	public Time getPremiereTime() {
		return premiereTime;
	}
	public void setPremiereTime(final Time premiereTime) {
		this.premiereTime = premiereTime;
	}
	public Date getLastShowingDate() {
		return lastShowingDate;
	}
	public void setLastShowingDate(final Date lastShowingDate) {
		this.lastShowingDate = lastShowingDate;
	}
	public Time getLastShowingTime() {
		return lastShowingTime;
	}
	public void setLastShowingTime(final Time lastShowingTime) {
		this.lastShowingTime = lastShowingTime;
	}
}
