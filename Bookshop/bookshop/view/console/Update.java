package bookshop.view.console;

import core.BookshopFacade;

public class Update {

	private Update() {
	}
	
	public static void execute() {
		System.out.println("executando atualizar");

		BookshopFacade.start();

		Create.getBuyer("Sicrano", "02045678901", "321321321", "sicrano@email.com");

		try {
			System.out.println("alterando...");
			BookshopFacade.updateBuyer("joana", "02045678901", "654654654", "joana@email.com");
			System.out.println(BookshopFacade.getBuyer("02045678901"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		BookshopFacade.end();

		System.out.println("fim do comando");
	}

	public static void main(String[] args) {
		Update.execute();
	}
}
