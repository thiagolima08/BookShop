package bookshop.dao;

import java.util.List;

import com.db4o.query.Query;

import bookshop.model.Buyer;

public class BuyerDao extends AbstractDao<Buyer> {
	private String ID = "cpf";

	public BuyerDao() {
		AbstractDao.config.common().objectClass(Buyer.class).objectField(ID).indexed(true);

		AbstractDao.config.common().objectClass(Buyer.class).cascadeOnUpdate(true);
		AbstractDao.config.common().objectClass(Buyer.class).cascadeOnDelete(true);
		AbstractDao.config.common().objectClass(Buyer.class).cascadeOnActivate(true);
	}

	public Buyer read(Object chave) {
		Query q = manager.query();
		q.constrain(Buyer.class);
		q.descend(ID).constrain((String) chave);

		List<Buyer> buyers = q.execute();
		if (buyers.size() > 0)
			return buyers.get(0);
		else
			return null;
	}
}
