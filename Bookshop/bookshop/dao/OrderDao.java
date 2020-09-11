package bookshop.dao;

import java.util.List;

import com.db4o.query.Query;

import bookshop.model.Order;

public class OrderDao extends AbstractDao<Order> {
	private String ID = "number";
	public OrderDao() {
		AbstractDao.config.common().objectClass(Order.class).objectField(ID).indexed(true);
		
		AbstractDao.config.common().objectClass(Order.class).cascadeOnUpdate(true);
		AbstractDao.config.common().objectClass(Order.class).cascadeOnDelete(false);
		AbstractDao.config.common().objectClass(Order.class).cascadeOnActivate(true);
	}

	public Order read(Object chave) {
		Query q = manager.query();
		q.constrain(Order.class);
		try {			
			q.descend(ID).constrain((int) chave);
		} catch (NumberFormatException e) {
			return null;
		}

		List<Order> orders = q.execute();
		if (orders.size() > 0)
			return orders.get(0);
		else
			return null;
	}
}
