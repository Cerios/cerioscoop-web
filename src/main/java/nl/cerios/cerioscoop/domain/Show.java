package nl.cerios.cerioscoop.domain;

import java.sql.Date;
import java.sql.Time;

public class Show {
	private int showId;
	private Movie movie;
	private Room room;
	private Date showDate;
	private Time showTime;
	private int ticketsSold;
	private float showPrice;
	
	public Show() {
	}
	
	public Show(final int showId, final Movie movie,final Date showDate, final Time showTime) {
		this.showId = showId;
		this.movie = movie;
		this.showDate = showDate;
		this.showTime = showTime;
	}
	
	public Show(final int showId, final Movie movie,final Date showDate, final Time showTime , final int ticketsSold) {
		this.showId = showId;
		this.movie = movie;
		this.showDate = showDate;
		this.showTime = showTime;
		this.ticketsSold = ticketsSold;
	}
	public Show(final int showId, final Movie movie, final Room room, final Date showDate, final Time showTime, final int ticketsSold, final float showPrice) {
		this.showId = showId;
		this.movie = movie;
		this.room = room; 
		this.showDate = showDate;
		this.showTime = showTime;
		this.ticketsSold = ticketsSold;
		this.showPrice = showPrice;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(final int showId) {
		this.showId = showId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(final Date showDate) {
		this.showDate = showDate;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(final Time showTime) {
		this.showTime = showTime;
	}
	public int getTicketsSold() {
		return ticketsSold;
	}
	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}
	public float getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(int showPrice) {
		this.showPrice = showPrice;
	}
}
