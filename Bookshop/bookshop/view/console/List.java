package bookshop.view.console;

import core.BookshopFacade;

public class List {

	private List() {

	}

	public static void execute() {
		System.out.println("executando listar");

		BookshopFacade.start();

		try {
			System.out.println("listando clientes");
			System.out.println(BookshopFacade.listAllBuyers());

			System.out.println("listando livros");
			System.out.println(BookshopFacade.listAllBooks());

			System.out.println("listando pedidos");
			System.out.println(BookshopFacade.listAllOrders());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		BookshopFacade.end();

		System.out.println("fim do comando");
	}

	public static void main(String[] args) {
		List.execute();
	}
}
