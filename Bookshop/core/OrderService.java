package core;

import java.util.List;

import bookshop.dao.DaoInterface;
import bookshop.exception.NonexistentResourceException;
import bookshop.exception.RequiredException;
import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;

public class OrderService {
	private DaoInterface<Order> orderDao;
	
	public OrderService(DaoInterface<Order> orderDao) {
		this.orderDao = orderDao;
	}

	private static void validateBuyer(Buyer buyer) throws Exception {
		if (buyer == null)
			throw new RequiredException("pedido[cliente]");
	}

	private static void validateBooks(List<Book> books) throws Exception {
		if (books == null || books.isEmpty())
			throw new RequiredException("pedido[livros]");
	}

	public Order create(Buyer buyer, List<Book> books, Boolean paid) throws Exception {
		OrderService.validateBuyer(buyer);
		OrderService.validateBooks(books);
		Order order = new Order(buyer, books, paid);
		orderDao.create(order);
		return order;
	}

	public Order update(Integer number, Buyer buyer, List<Book> books, Boolean paid) throws Exception {
		OrderService.validateBuyer(buyer);
		OrderService.validateBooks(books);

		Order order = orderDao.read(number);
		if (order == null) {
			throw new NonexistentResourceException("pedido");
		}

		order.setBooks(books);
		order.setBuyer(buyer);
		order.setPaid(paid);
		orderDao.update(order);

		return order;
	}

	public Order remove(Integer number) throws Exception {
		Order order = orderDao.read(number);

		if (order == null) {
			throw new NonexistentResourceException("pedido");
		}

		orderDao.delete(order);

		return order;
	}

	public Order read(Integer number) {
		return orderDao.read(number);
	}

	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public List<Order> searchByField(String field, Object value) {
		return orderDao.searchByField(field, value);
	}
}
