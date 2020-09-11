package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;
import core.BookshopFacade;

public class OtherListView {

	private JFrame frame;
	private JTextArea textArea;
	private JButton buyers_button;
	private JButton orders_button;
	private JButton books_button;
	private JLabel alert_label;

	public OtherListView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Listar");
		frame.setBounds(100, 100, 535, 202);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		createTextArea();
		createButtons();
		createAlert();

		buyers_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textArea.setText("");
				try {
					List<Buyer> buyers = BookshopFacade.listAllBuyers();
					String text = "---------- Clientes ----------\n";
					for (Buyer buyer : buyers) {
						text += buyer + "\n";
					}
					textArea.setText(text);
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
			}
		});

		orders_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textArea.setText("");
				try {
					List<Order> orders = BookshopFacade.listAllOrders();
					String text = "---------- Pedidos ----------\n";
					for (Order order : orders) {
						text += order + "\n";
					}
					textArea.setText(text);
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
			}
		});

		books_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textArea.setText("");
				try {
					List<Book> books = BookshopFacade.listAllBooks();
					String text = "---------- Livros ----------\n";
					for (Book book : books) {
						text += book + "\n";
					}
					textArea.setText(text);
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
			}
		});

		frame.setVisible(true);
	}

	private void createTextArea() {
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(51, 6, 432, 100);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
	}

	private void createButtons() {
		buyers_button = new JButton("Clientes");
		buyers_button.setBounds(80, 111, 100, 25);
		frame.getContentPane().add(buyers_button);

		orders_button = new JButton("Pedidos");
		orders_button.setBounds(205, 111, 100, 25);
		frame.getContentPane().add(orders_button);

		books_button = new JButton("Livros");
		books_button.setBounds(325, 111, 100, 25);
		frame.getContentPane().add(books_button);
	}

	private void createAlert() {
		alert_label = new JLabel("");
		alert_label.setBounds(10, 140, 365, 20);
		frame.getContentPane().add(alert_label);
	}
}
