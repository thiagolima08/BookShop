package bookshop.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

public abstract class AbstractDao<T> implements DaoInterface<T> {
	protected static ObjectContainer manager;
	protected static EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();

	/*
	 * connection
	 */
	public static void open() {
		if (manager != null)
			return;

		config.common().messageLevel(0);
		manager = Db4oEmbedded.openFile(config, "database.db4o");
	}

	public static void close() {
		if (manager == null)
			return;

		manager.close();
		manager = null;
	}

	/*
	 * transaction
	 */
	public static void begin() {
	}

	public static void commit() {
		manager.commit();
	}

	public static void rollback() {
		manager.rollback();
	}

	/*
	 * interface
	 */
	public void create(T obj) {
		manager.store(obj);
	}

	public void update(T obj) {
		manager.store(obj);
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	public abstract T read(Object chave);

	public List<T> readAll() {
		Query q = manager.query();
		q.constrain(this.getKlass());
		return q.execute();
	}

	public List<T> searchByField(String field, Object value) {
		Query q = manager.query();
		q.constrain(this.getKlass());
		q.descend(field).constrain(value).like();

		return q.execute();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getKlass() {
		ParameterizedType genericKlass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type type = genericKlass.getActualTypeArguments()[0];
		return (Class<T>) type;
	}
}
