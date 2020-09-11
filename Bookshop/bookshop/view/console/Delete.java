package bookshop.view.console;

import java.util.List;

import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;
import core.BookshopFacade;

public class Delete {

	private Delete() {
	}

	public static void execute() {

		System.out.println("executando deletar");

		BookshopFacade.start();

		try {
			List<Order> orders = BookshopFacade.listAllOrders();
			for (Order order : orders) {
				System.out.println("excluindo " + order.getNumber());
				BookshopFacade.removeOrder(order.getNumber());
			}
			
			List<Buyer> buyers = BookshopFacade.listAllBuyers();
			for (Buyer buyer : buyers) {
				System.out.println("excluindo " + buyer.getName());
				BookshopFacade.removeBuyer(buyer.getCpf());
			}
			
			List<Book> books = BookshopFacade.listAllBooks();
			for (Book book : books) {
				System.out.println("excluindo " + book.getTitle());
				BookshopFacade.removeBook(book.getIsbn());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		BookshopFacade.end();

		System.out.println("fim do comando");
	}

	public static void main(String[] args) {
		Delete.execute();
	}
}
