package core;

import java.util.List;

import bookshop.dao.DaoInterface;
import bookshop.exception.DuplicatedResourceException;
import bookshop.exception.NonexistentResourceException;
import bookshop.exception.RequiredException;
import bookshop.exception.ValidationException;
import bookshop.model.Buyer;

public class BuyerService {
	private static void validateName(String name) throws Exception {
		if (name.isEmpty())
			throw new RequiredException("cliente[nome]");
	}

	private static void validateCpf(String cpf) throws Exception {
		if (cpf.isEmpty())
			throw new RequiredException("cliente[cpf]");
		if (cpf.length() != 11)
			throw new ValidationException("cliente[cpf] deve ter 11 dígitos");
	}

	private static void validatePhone(String phone) throws Exception {
		if (phone.isEmpty())
			throw new RequiredException("cliente[telefone]");
		if (phone.length() != 9)
			throw new ValidationException("cliente[telefone] deve ter 9 dígitos");
	}

	private static void validateEmail(String email) throws Exception {
		if (email.isEmpty())
			throw new RequiredException("cliente[email]");
	}

	public static Buyer create(DaoInterface<Buyer> buyerDao, String name, String cpf, String phone, String email) throws Exception {
		BuyerService.validateName(name);
		BuyerService.validateCpf(cpf);
		BuyerService.validatePhone(phone);
		BuyerService.validateEmail(email);

		Buyer buyer = (Buyer) buyerDao.read(cpf);
		if (buyer != null) {
			throw new DuplicatedResourceException("cliente");
		}

		buyer = new Buyer(name, cpf, phone, email);
		buyerDao.create(buyer);

		return buyer;
	}

	public static Buyer update(DaoInterface<Buyer> buyerDao, String name, String cpf, String phone, String email) throws Exception {
		BuyerService.validateName(name);
		BuyerService.validateCpf(cpf);
		BuyerService.validatePhone(phone);
		BuyerService.validateEmail(email);

		Buyer buyer = buyerDao.read(cpf);
		if (buyer == null) {
			throw new NonexistentResourceException("cliente");
		}

		buyer.setName(name);
		buyer.setPhone(phone);
		buyer.setEmail(email);
		buyerDao.update(buyer);

		return buyer;
	}

	public static Buyer remove(DaoInterface<Buyer> buyerDao, String cpf) throws Exception {
		BuyerService.validateCpf(cpf);

		Buyer buyer = buyerDao.read(cpf);

		if (buyer == null) {
			throw new NonexistentResourceException("cliente");
		}

		buyerDao.delete(buyer);

		return buyer;
	}

	public static Buyer read(DaoInterface<Buyer> buyerDao, String cpf) {
		return buyerDao.read(cpf);
	}

	public static List<Buyer> readAll(DaoInterface<Buyer> buyerDao) {
		return buyerDao.readAll();
	}

	public static List<Buyer> searchByField(DaoInterface<Buyer> buyerDao, String field, Object value) {
		return buyerDao.searchByField(field, value);
	}
}
