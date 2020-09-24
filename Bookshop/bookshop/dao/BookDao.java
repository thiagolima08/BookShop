package bookshop.dao;

import bookshop.model.Book;

public class BookDao extends GenericDao<Book> {
	public BookDao() {
		super("isbn", true, true, true);
	}
}
