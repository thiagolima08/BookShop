package bookshop.dao;

import bookshop.model.Order;

public class OrderDao extends GenericDao<Order> {
	public OrderDao() {
		super("number", true, false, true);
	}
}
