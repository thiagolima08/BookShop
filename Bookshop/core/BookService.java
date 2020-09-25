package core;

import java.util.List;

import bookshop.dao.DaoInterface;
import bookshop.exception.DuplicatedResourceException;
import bookshop.exception.NonexistentResourceException;
import bookshop.exception.RequiredException;
import bookshop.exception.ValidationException;
import bookshop.model.Book;

public class BookService {
	private DaoInterface<Book> bookDao;

	public BookService(DaoInterface<Book> bookDao) {
		this.bookDao = bookDao;
	}

	private static void validateTitle(String title) throws Exception {
		if (title.isEmpty())
			throw new RequiredException("livro[título]");
	}

	private static void validateIsbn(String isbn) throws Exception {
		if (isbn.isEmpty())
			throw new RequiredException("livro[ISBN]");
		if (isbn.length() != 10 && isbn.length() != 13)
			throw new ValidationException("livro[ISBN] deve ter 10 ou 13 dígitos");
	}

	private static void validatePublisher(String publisher) throws Exception {
		if (publisher.isEmpty())
			throw new RequiredException("livro[editora]");
	}

	private static void validateAuthor(String author) throws Exception {
		if (author.isEmpty())
			throw new RequiredException("livro[autor]");
	}

	private static void validatePrice(Double price) throws Exception {
		if (price < 0)
			throw new ValidationException("livro[preço] não pode ser negativo");
	}

	public Book create(String title, String isbn, String publisher, String author, Double price) throws Exception {
		BookService.validateTitle(title);
		BookService.validateIsbn(isbn);
		BookService.validatePublisher(publisher);
		BookService.validateAuthor(author);
		BookService.validatePrice(price);

		Book book = bookDao.read(isbn);
		if (book != null) {
			throw new DuplicatedResourceException("livro");
		}

		book = new Book(title, isbn, publisher, author, price);
		bookDao.create(book);

		return book;
	}

	public Book update(String title, String isbn, String publisher, String author, Double price) throws Exception {
		BookService.validateTitle(title);
		BookService.validateIsbn(isbn);
		BookService.validatePublisher(publisher);
		BookService.validateAuthor(author);
		BookService.validatePrice(price);

		Book book = bookDao.read(isbn);
		if (book == null) {
			throw new NonexistentResourceException("livro");
		}
		
		book.setTitle(title);
		book.setPublisher(publisher);
		book.setAuthor(author);
		book.setPrice(price);
		bookDao.update(book);

		return book;
	}

	public Book remove(String isbn) throws Exception {
		BookService.validateIsbn(isbn);

		Book book = bookDao.read(isbn);

		if (book == null) {
			throw new NonexistentResourceException("livro");
		}

		bookDao.delete(book);

		return book;
	}

	public Book read(String isbn) {
		return bookDao.read(isbn);
	}

	public List<Book> readAll() {
		return bookDao.readAll();
	}

	public List<Book> searchByField(String field, Object value) {
		return bookDao.searchByField(field, value);
	}
}
