package bookshop.dao;

import bookshop.model.Buyer;

public class BuyerDao extends GenericDao<Buyer> {
	public BuyerDao() {
		super("cpf", true, true, true);
	}
}
