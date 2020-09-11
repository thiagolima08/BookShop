package bookshop.view.console;

import core.BookshopFacade;

public class Search {

	private Search() {

	}

	public static void execute() {
		System.out.println("executando consultar");

		BookshopFacade.start();

		try {
			System.out.println(String.format("consultando cliente: %s", "fu"));
			System.out.println(BookshopFacade.findBuyersByName("fu"));

			System.out.println(String.format("consultando cliente: %s", "jo"));
			System.out.println(BookshopFacade.findBuyersByName("jo"));

			System.out.println(String.format("consultando livro: %s", "Harry Potter"));
			System.out.println(BookshopFacade.findBooksByTitle("Harry Potter"));

			System.out.println(String.format("consultando pedido do cpf: %s", "00000000000"));
			System.out.println(BookshopFacade.findOrdersByCpf("00000000000"));
			
			System.out.println(String.format("consultando pedido do isbn: %s", "8532530826"));
			System.out.println(BookshopFacade.findOrdersByIsbn("8532530826"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		BookshopFacade.end();

		System.out.println("fim do comando");
	}

	public static void main(String[] args) {
		Search.execute();
	}
}
