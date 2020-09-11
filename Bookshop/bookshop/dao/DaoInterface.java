package bookshop.dao;

import java.util.List;

public interface DaoInterface<T> {
	public void create(T obj);
	public void update(T obj);
	public void delete(T obj) ;
	public T read(Object chave);
	public List<T> readAll();
	public List<T> searchByField(String field, Object value);
}
