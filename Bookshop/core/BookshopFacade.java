package core;

import java.util.ArrayList;
import java.util.List;

import bookshop.dao.BookDao;
import bookshop.dao.BuyerDao;
import bookshop.dao.GenericDao;
import bookshop.dao.OrderDao;
import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;

public class BookshopFacade {
	private static BookService bookService = new BookService(new BookDao());
	private static BuyerService buyerService = new BuyerService(new BuyerDao());
	private static OrderService orderService = new OrderService(new OrderDao());

	public static void start() {
		GenericDao.open();
	}

	public static void end() {
		GenericDao.close();
	}

	/*
	 * Buyers
	 */
	public static void createBuyer(String name, String cpf, String phone, String email) throws Exception {
		GenericDao.begin();
		try {
			buyerService.create(name, cpf, phone, email);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void updateBuyer(String name, String cpf, String phone, String email) throws Exception {
		GenericDao.begin();
		try {
			buyerService.update(name, cpf, phone, email);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void removeBuyer(String cpf) throws Exception {
		GenericDao.begin();
		try {
			buyerService.remove(cpf);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static Buyer getBuyer(String cpf) throws Exception {
		Buyer buyer = null;

		GenericDao.begin();
		try {
			buyer = buyerService.read(cpf);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return buyer;
	}

	public static List<Buyer> listAllBuyers() throws Exception {
		List<Buyer> buyers = null;

		GenericDao.begin();
		try {
			buyers = buyerService.readAll();
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return buyers;
	}

	public static List<Buyer> findBuyersByName(String name) throws Exception {
		List<Buyer> buyers = null;

		GenericDao.begin();
		try {
			buyers = buyerService.searchByField("name", name);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return buyers;
	}

	/*
	 * Books
	 */
	public static void createBook(String title, String isbn, String publisher, String author, Double price) throws Exception {
		GenericDao.begin();
		try {
			bookService.create(title, isbn, publisher, author, price);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void updateBook(String title, String isbn, String publisher, String author, Double price) throws Exception {
		GenericDao.begin();
		try {
			bookService.update(title, isbn, publisher, author, price);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void removeBook(String isbn) throws Exception {
		GenericDao.begin();
		try {
			bookService.remove(isbn);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static Book getBook(String isbn) throws Exception {
		Book book = null;

		GenericDao.begin();
		try {
			book = bookService.read(isbn);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return book;
	}

	public static List<Book> listAllBooks() throws Exception {
		List<Book> books = null;

		GenericDao.begin();
		try {
			books = bookService.readAll();
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return books;
	}

	public static List<Book> findBooksByTitle(String title) throws Exception {
		List<Book> books = null;

		GenericDao.begin();
		try {
			books = bookService.searchByField("title", title);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return books;
	}

	/*
	 * Orders
	 */
	public static void createOrder(String cpf, List<String> isbns, Boolean paid) throws Exception {
		GenericDao.begin();
		try {
			Buyer buyer = buyerService.read(cpf);
			List<Book> books = new ArrayList<Book>();
			for (String isbn : isbns) {
				books.add(bookService.read(isbn));
			}
			orderService.create(buyer, books, paid);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void updateOrder(Integer number, String cpf, List<String> isbns, Boolean paid) throws Exception {
		GenericDao.begin();
		try {
			Buyer buyer = buyerService.read(cpf);
			List<Book> books = new ArrayList<Book>();
			for (String isbn : isbns) {
				books.add(bookService.read(isbn));
			}
			orderService.update(number, buyer, books, paid);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static void removeOrder(Integer number) throws Exception {
		GenericDao.begin();
		try {
			orderService.remove(number);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();
	}

	public static Order getOrder(Integer number) throws Exception {
		Order order = null;

		GenericDao.begin();
		try {
			order = orderService.read(number);
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return order;
	}

	public static List<Order> listAllOrders() throws Exception {
		List<Order> orders = null;

		GenericDao.begin();
		try {
			orders = orderService.readAll();
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return orders;
	}

	public static List<Order> findOrdersByIsbn(String isbn) throws Exception {
		List<Order> orders = null;

		GenericDao.begin();
		try {
			orders = bookService.read(isbn).getOrders();
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return orders;
	}
	
	public static List<Order> findOrdersByCpf(String cpf) throws Exception {
		List<Order> orders = null;

		GenericDao.begin();
		try {
			orders = buyerService.read(cpf).getOrders();
		} catch (Exception e) {
			GenericDao.rollback();
			throw e;
		}
		GenericDao.commit();

		return orders;
	}
}
