package nl.cerios.cerioscoop.ValueObjects;

import nl.cerios.cerioscoop.domain.Show;

public class ShowPresentationVO {
	public Show show;
	public Boolean soldOut;
	
	public ShowPresentationVO() {
		
	}
	
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public Boolean getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(Boolean soldOut) {
		this.soldOut = soldOut;
	}
	
	
}
