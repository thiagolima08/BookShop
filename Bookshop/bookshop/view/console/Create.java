package bookshop.view.console;

import java.util.ArrayList;
import java.util.List;

import bookshop.model.Buyer;
import bookshop.model.Book;
import core.BookshopFacade;

public class Create {

	private Create() {
	}

	protected static Buyer getBuyer(String name, String cpf, String phone, String email) {
		Buyer buyer = null;
		try {
			buyer = BookshopFacade.getBuyer(cpf);
			if (buyer == null) {
				System.out.println(String.format("cadastrando cliente: %s", name));
				BookshopFacade.createBuyer(name, cpf, phone, email);

				buyer = BookshopFacade.getBuyer(cpf);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buyer;
	}

	protected static Book getBook(String title, String isbn, String publisher, String author, Double price) {
		Book book = null;
		try {
			book = BookshopFacade.getBook(isbn);
			if (book == null) {
				System.out.println(String.format("cadastrando livro: %s", title));
				BookshopFacade.createBook(title, isbn, publisher, author, price);

				book = BookshopFacade.getBook(isbn);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return book;
	}

	public static void execute() {
		System.out.println("executando cadastrar");

		BookshopFacade.start();

		Buyer buyer = Create.getBuyer("Fulano", "00000000000", "000000000", "fulano@email.com");

		List<Book> books = new ArrayList<Book>();
		books.add(getBook("Harry Potter e a Pedra Filosofal", "8532530788", "Rocco", "J. K. Rowling", 28.73));
		books.add(getBook("Harry Potter e a Câmara Secreta", "8532530796", "Rocco", "J. K. Rowling", 29.93));
		books.add(getBook("Harry Potter e o Prisioneiro de Azkaban", "853253080X", "Rocco", "J. K. Rowling", 28.90));
		books.add(getBook("Harry Potter e o Cálice de Fogo", "8532530818", "Rocco", "J. K. Rowling", 38.53));
		books.add(getBook("Harry Potter e a Ordem da Fênix", "8532530826", "Rocco", "J. K. Rowling", 52.40));
		books.add(getBook("Harry Potter e o Enigma do Príncipe", "8532530834", "Rocco", "J. K. Rowling", 35.40));
		books.add(getBook("Harry Potter e as Relíquias da Morte", "8532530842", "Rocco", "J. K. Rowling", 48.68));

		List<String> isbns = new ArrayList<String>();
		Double total = 0.0;
		for (Book book : books) {
			total += book.getPrice();
			isbns.add(book.getIsbn());
		}

		try {
			System.out.println("cadastrando pedido");
			BookshopFacade.createOrder(buyer.getCpf(), isbns, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		BookshopFacade.end();

		System.out.println("fim do comando");
	}

	public static void main(String[] args) {
		Create.execute();
	}
}
