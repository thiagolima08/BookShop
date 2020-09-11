package bookshop.model;

import java.util.ArrayList;

public class Buyer {
	private String name;
	private String cpf;
	private String phone;
	private String email;
	private ArrayList<Order> orders;

	public Buyer(String name, String cpf, String phone, String email) {
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.email = email;
		this.orders = new ArrayList<Order>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
			"Cliente [nome=%s, CPF=%s, telefone=%s, email=%s, pedidos=%d]",
			name, cpf, phone, email, orders.size()
		);
	}
}
