package bookshop.model;

import java.util.ArrayList;

public class Book {
	private String title;
	private String isbn;
	private String publisher;
	private String author;
	private Double price;
	private ArrayList<Order> orders;

	public Book(String title, String isbn, String publisher, String author, Double price) {
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.author = author;
		this.price = price;
		this.orders = new ArrayList<Order>();
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return String.format(
			"Livro [título=%s, ISBN=%s, editora=%s, autor=%s, preço=R$ %.2f, pedidos=%d]",
			title, isbn, publisher, author, price, orders.size()
		);
	}
}
