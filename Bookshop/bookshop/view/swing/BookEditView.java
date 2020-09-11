package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookshop.exception.ValidationException;
import bookshop.model.Book;
import core.BookshopFacade;

public class BookEditView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel title_label;
	private JTextField title_input;
	private JLabel isbn_label;
	private JTextField isbn_input;
	private JButton search_button;
	private JLabel author_label;
	private JTextField author_input;
	private JLabel publisher_label;
	private JTextField publisher_input;
	private JLabel price_label;
	private JTextField price_input;
	private JButton cancel_button;
	private JButton action_button;
	private JLabel alert_label;

	public BookEditView() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		createTitle();
		createIsbn();
		createAuthor();
		createPublisher();
		createButtons();
		createAlert();
		createPrice();

		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				String isbn = isbn_input.getText();
				
				Book book;
				try {
					
					book = BookshopFacade.getBook(isbn);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				title_input.setText(book.getTitle());
				author_input.setText(book.getAuthor());
				publisher_input.setText(book.getPublisher());
				price_input.setText(String.valueOf(book.getPrice()));
				
				isbn_input.setEnabled(false);
				search_button.setEnabled(false);
			}
		});

		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				title_input.setText("");
				isbn_input.setText("");
				author_input.setText("");
				publisher_input.setText("");
				price_input.setText("");
				
				isbn_input.setEnabled(true);
				search_button.setEnabled(true);
			}
		});

		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				String title = title_input.getText();
				String isbn = isbn_input.getText();
				String author = author_input.getText();
				String publisher = publisher_input.getText();
				String price = price_input.getText();

				try {
					BookshopFacade.updateBook(title, isbn, publisher, author, Double.valueOf(price));
				} catch (NumberFormatException e) {
					alert_label.setText("Pre�o precisar ser num�rico");
					return;
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}

				title_input.setText("");
				isbn_input.setText("");
				author_input.setText("");
				publisher_input.setText("");
				price_input.setText("");

				isbn_input.setEnabled(true);
				search_button.setEnabled(true);

				alert_label.setText("atualização concluída");
			}
		});
	}

	private void createTitle() {
		title_label = new JLabel("Título:");
		title_label.setBounds(10, 10, 55, 20);
		add(title_label);

		title_input = new JTextField();
		title_input.setBounds(67, 10, 320, 20);
		add(title_input);
	}

	private void createIsbn() {
		isbn_label = new JLabel("ISBN:");
		isbn_label.setBounds(10, 35, 45, 20);
		add(isbn_label);

		isbn_input = new JTextField();
		isbn_input.setBounds(67, 35, 180, 20);
		add(isbn_input);
		
		search_button = new JButton("buscar");
		search_button.setBounds(252, 34, 135, 25);
		add(search_button);
	}

	private void createAuthor() {
		author_label = new JLabel("Autor:");
		author_label.setBounds(10, 60, 45, 20);
		add(author_label);

		author_input = new JTextField();
		author_input.setBounds(67, 60, 320, 20);
		add(author_input);
	}

	private void createPublisher() {
		publisher_label = new JLabel("Editora:");
		publisher_label.setBounds(10, 85, 55, 20);
		add(publisher_label);

		publisher_input = new JTextField();
		publisher_input.setBounds(67, 85, 320, 20);
		add(publisher_input);
	}
	
	private void createPrice() {
		price_label = new JLabel("Preço:");
		price_label.setBounds(10, 110, 45, 20);
		add(price_label);

		price_input = new JTextField();
		price_input.setBounds(67, 110, 320, 20);
		add(price_input);
	}

	private void createButtons() {
		cancel_button = new JButton("Cancelar");
		cancel_button.setBounds(20, 145, 150, 25);
		add(cancel_button);
		
		action_button = new JButton("Cadastrar");
		action_button.setBounds(215, 145, 150, 25);
		add(action_button);
	}
	
	private void createAlert() {
		alert_label = new JLabel("");
		alert_label.setBounds(10, 170, 365, 20);
		add(alert_label);
	}
}
