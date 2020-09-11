package bookshop.dao;

import java.util.List;

import com.db4o.query.Query;

import bookshop.model.Book;

public class BookDao extends AbstractDao<Book> {
	private String ID = "isbn";

	public BookDao() {
		AbstractDao.config.common().objectClass(Book.class).objectField(ID).indexed(true);
		
		AbstractDao.config.common().objectClass(Book.class).cascadeOnUpdate(true);
		AbstractDao.config.common().objectClass(Book.class).cascadeOnDelete(true);
		AbstractDao.config.common().objectClass(Book.class).cascadeOnActivate(true);
	}

	public Book read(Object chave) {
		Query q = manager.query();
		q.constrain(Book.class);
		q.descend(ID).constrain((String) chave);

		List<Book> books = q.execute();

		if (books.size() > 0)
			return books.get(0);
		else
			return null;
	}
}
