package pl.edu.agh.dronka.shop.model;

public class User {
	private final String name;
	
	private final String surname;
	
	private final Cart cart;
	

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
		
		this.cart = new Cart();
	}

	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public Cart getCart() {
		return cart;
	}
}
