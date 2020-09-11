package bookshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private int number;
	private LocalDateTime createdAt;
	private Buyer buyer;
	private List<Book> books;
	private double value;
	private boolean paid;

	public Order(Buyer buyer, List<Book> books, boolean paid) {
		this.number = Math.abs(LocalDateTime.now().hashCode());
		this.createdAt = LocalDateTime.now();

		this.buyer = buyer;
		this.buyer.getOrders().add(this);

		this.books = books;
		for (Book book: this.books) {
			this.value += book.getPrice();
			book.getOrders().add(this);
		}

		this.paid = paid;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer.getOrders().remove(this);
		this.buyer = buyer;
		this.buyer.getOrders().add(this);
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		for (Book book: this.books) {
			this.value -= book.getPrice();
			book.getOrders().remove(this);
		}
		this.books = books;
		for (Book book: this.books) {
			this.value += book.getPrice();
			book.getOrders().add(this);
		}
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		String status = paid ? "pago" : "à pagar";

		return String.format(
			"Pedido [número=%s, data do pedido=%s, cliente=%s, livros=%s, preço=R$ %.2f, %s]",
			number,createdAt, buyer, books, value, status
		);
	}
}
