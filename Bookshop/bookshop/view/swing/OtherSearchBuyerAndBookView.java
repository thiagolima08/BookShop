package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import bookshop.model.Book;
import bookshop.model.Buyer;
import core.BookshopFacade;

public class OtherSearchBuyerAndBookView {

	private JFrame frame;
	private JTextArea textArea;
	private JTextField search_input;
	private JButton buyers_button;
	private JButton books_button;
	private JLabel alert_label;

	public OtherSearchBuyerAndBookView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Listar Clientes e Livros por nome");
		frame.setBounds(100, 100, 400, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		createTextArea();
		createInput();
		createButtons();
		createAlert();

		buyers_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textArea.setText("");

				String name = search_input.getText();
				if (name.isEmpty()) {
					textArea.setText("Coloque o nome do cliente.");
					return;
				}

				try {
					List<Buyer> buyers = BookshopFacade.findBuyersByName(name);
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

		books_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textArea.setText("");

				String title = search_input.getText();
				if (title.isEmpty()) {
					textArea.setText("Coloque o título do livro.");
					return;
				}

				try {
					List<Book> books = BookshopFacade.findBooksByTitle(title);
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
		scroll.setBounds(10, 10, 365, 100);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
	}
	
	private void createInput() {
		search_input = new JTextField();
		search_input.setBounds(58, 115, 150, 20);
		frame.getContentPane().add(search_input);
	}

	private void createButtons() {
		buyers_button = new JButton("Clientes");
		buyers_button.setBounds(212, 113, 84, 25);
		frame.getContentPane().add(buyers_button);

		books_button = new JButton("Livros");
		books_button.setBounds(298, 113, 77, 25);
		frame.getContentPane().add(books_button);
	}

	private void createAlert() {
		alert_label = new JLabel("");
		alert_label.setBounds(10, 140, 365, 20);
		frame.getContentPane().add(alert_label);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(14, 118, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
