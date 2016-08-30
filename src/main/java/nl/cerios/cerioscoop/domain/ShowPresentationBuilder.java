package nl.cerios.cerioscoop.domain;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class ShowPresentationBuilder {
	private BigInteger showingId;
	private String movieTitle;
	private String roomName;
	private Date showingDate;
	private Time showingTime;
	private BigInteger chairAmount;
	private String trailer;
	private BigInteger chairsSold;
	
	public ShowPresentationBuilder withShowingId(final BigInteger value) {
		showingId = value;
		return this;
	}
	public ShowPresentationBuilder withMovieTitle(final String value) {
		movieTitle = value;
		return this;
	}
	public ShowPresentationBuilder withRoomName(final String value) {
		roomName = value;
		return this;
	}
	public ShowPresentationBuilder withShowingDate(final Date value) {
		showingDate = value;
		return this;
	}
	public ShowPresentationBuilder withShowingTime(final Time value) {
		showingTime = value;
		return this;
	}
	public ShowPresentationBuilder withChairAmount(final BigInteger value) {
		chairAmount = value;
		return this;
	}
	public ShowPresentationBuilder withTrailer(final String value) {
		trailer = value;
		return this;
	}
	public ShowPresentationBuilder withChairsSold(final BigInteger value) {
		chairsSold = value;
		return this;
	}
	public ShowPresentation build() {
		return new ShowPresentation(showingId, movieTitle, roomName, showingDate, showingTime, chairAmount, trailer, chairsSold);
	}
}

