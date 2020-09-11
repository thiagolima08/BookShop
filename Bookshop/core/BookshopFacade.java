package core;

import java.util.ArrayList;
import java.util.List;

import bookshop.dao.AbstractDao;
import bookshop.dao.BookDao;
import bookshop.dao.BuyerDao;
import bookshop.dao.OrderDao;
import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;

public class BookshopFacade {
	private static BuyerDao buyerDao = new BuyerDao();
	private static BookDao bookDao = new BookDao();
	private static OrderDao orderDao = new OrderDao();

	public static void start() {
		AbstractDao.open();
	}

	public static void end() {
		AbstractDao.close();
	}

	/*
	 * Buyers
	 */
	public static void createBuyer(String name, String cpf, String phone, String email) throws Exception {
		AbstractDao.begin();
		try {
			BuyerService.create(buyerDao, name, cpf, phone, email);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void updateBuyer(String name, String cpf, String phone, String email) throws Exception {
		AbstractDao.begin();
		try {
			BuyerService.update(buyerDao, name, cpf, phone, email);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void removeBuyer(String cpf) throws Exception {
		AbstractDao.begin();
		try {
			BuyerService.remove(buyerDao, cpf);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static Buyer getBuyer(String cpf) throws Exception {
		Buyer buyer = null;

		AbstractDao.begin();
		try {
			buyer = BuyerService.read(buyerDao, cpf);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return buyer;
	}

	public static List<Buyer> listAllBuyers() throws Exception {
		List<Buyer> buyers = null;

		AbstractDao.begin();
		try {
			buyers = BuyerService.readAll(buyerDao);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return buyers;
	}

	public static List<Buyer> findBuyersByName(String name) throws Exception {
		List<Buyer> buyers = null;

		AbstractDao.begin();
		try {
			buyers = BuyerService.searchByField(buyerDao, "name", name);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return buyers;
	}

	/*
	 * Books
	 */
	public static void createBook(String title, String isbn, String publisher, String author, Double price) throws Exception {
		AbstractDao.begin();
		try {
			BookService.create(bookDao, title, isbn, publisher, author, price);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void updateBook(String title, String isbn, String publisher, String author, Double price) throws Exception {
		AbstractDao.begin();
		try {
			BookService.update(bookDao, title, isbn, publisher, author, price);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void removeBook(String isbn) throws Exception {
		AbstractDao.begin();
		try {
			BookService.remove(bookDao, isbn);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static Book getBook(String isbn) throws Exception {
		Book book = null;

		AbstractDao.begin();
		try {
			book = BookService.read(bookDao, isbn);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return book;
	}

	public static List<Book> listAllBooks() throws Exception {
		List<Book> books = null;

		AbstractDao.begin();
		try {
			books = BookService.readAll(bookDao);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return books;
	}

	public static List<Book> findBooksByTitle(String title) throws Exception {
		List<Book> books = null;

		AbstractDao.begin();
		try {
			books = BookService.searchByField(bookDao, "title", title);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return books;
	}

	/*
	 * Orders
	 */
	public static void createOrder(String cpf, List<String> isbns, Boolean paid) throws Exception {
		AbstractDao.begin();
		try {
			Buyer buyer = BuyerService.read(buyerDao, cpf);
			List<Book> books = new ArrayList<Book>();
			for (String isbn : isbns) {
				books.add(BookService.read(bookDao, isbn));
			}
			OrderService.create(orderDao, buyer, books, paid);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void updateOrder(Integer number, String cpf, List<String> isbns, Boolean paid) throws Exception {
		AbstractDao.begin();
		try {
			Buyer buyer = BuyerService.read(buyerDao, cpf);
			List<Book> books = new ArrayList<Book>();
			for (String isbn : isbns) {
				books.add(BookService.read(bookDao, isbn));
			}
			OrderService.update(orderDao, number, buyer, books, paid);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static void removeOrder(Integer number) throws Exception {
		AbstractDao.begin();
		try {
			OrderService.remove(orderDao, number);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();
	}

	public static Order getOrder(Integer number) throws Exception {
		Order order = null;

		AbstractDao.begin();
		try {
			order = OrderService.read(orderDao, number);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return order;
	}

	public static List<Order> listAllOrders() throws Exception {
		List<Order> orders = null;

		AbstractDao.begin();
		try {
			orders = OrderService.readAll(orderDao);
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return orders;
	}

	public static List<Order> findOrdersByIsbn(String isbn) throws Exception {
		List<Order> orders = null;

		AbstractDao.begin();
		try {
			orders = BookService.read(bookDao, isbn).getOrders();
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return orders;
	}
	
	public static List<Order> findOrdersByCpf(String cpf) throws Exception {
		List<Order> orders = null;

		AbstractDao.begin();
		try {
			orders = BuyerService.read(buyerDao, cpf).getOrders();
		} catch (Exception e) {
			AbstractDao.rollback();
			throw e;
		}
		AbstractDao.commit();

		return orders;
	}
}
