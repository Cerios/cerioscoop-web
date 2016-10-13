package nl.cerios.cerioscoop.service;

import javax.ejb.Stateless;

import nl.cerios.cerioscoop.domain.Show;

@Stateless
public class CustomerService {

	public float calculateTotalPrice(Show show, int reservedPlaces) {
		float totalPrice = show.getShowPrice()*reservedPlaces;
		return totalPrice;
	}
}