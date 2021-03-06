package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import bookshop.exception.ValidationException;
import bookshop.model.Book;
import bookshop.model.Buyer;
import bookshop.model.Order;
import core.BookshopFacade;

public class OrderEditView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel cpf_label;
	private JTextField cpf_input;
	private JButton search_buyer_button;
	private JLabel isbn_label;
	private JTextField isbn_input;
	private JButton remove_book_button;
	private JButton add_book_button;
	private JTextArea textArea;
	private JLabel paid_label;
	private JCheckBox paid_input;
	private JLabel order_label;
	private JTextField order_input;
	private JButton order_button;
	private JButton action_button;
	private JLabel alert_label;
	private Buyer buyer;
	private List<Book> books;

	public OrderEditView() {
		initialize();
	}

	private void initialize() {
		setLayout(null);
		
		books = new ArrayList<Book>();

		createCpf();
		createIsbn();
		createTextArea();
		createPaid();
		createOrder();
		createButtons();
		createAlert();
		
		order_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!order_input.isEnabled()) {
					order_input.setEnabled(true);
					order_input.setText("");
					order_button.setText("Buscar");
					buyer = null;
					books = new ArrayList<Book>();
					paid_input.setSelected(false);
					cpf_input.setText("");
					return;
				}

				String number = order_input.getText();
				Order order = null;
				try {
					order = BookshopFacade.getOrder(Integer.valueOf(number));
				} catch (NumberFormatException e) {
					alert_label.setText("N�mer precisar ser um valor num�rico");
					return;
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				buyer = order.getBuyer();
				books = order.getBooks();
				order_input.setEnabled(false);
				order_button.setText("Cancelar");
				paid_input.setSelected(order.isPaid());
				cpf_input.setText(buyer.getCpf());
				updateTextArea();
			}
		});
		
		search_buyer_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String cpf = cpf_input.getText();
				try {
					buyer = BookshopFacade.getBuyer(cpf);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				updateTextArea();
			}
		});
		
		remove_book_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String isbn = isbn_input.getText();
				Book book = null;
				try {
					book = BookshopFacade.getBook(isbn);
					if (book != null)
						books.remove(book);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				updateTextArea();
			}
		});

		add_book_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String isbn = isbn_input.getText();
				Book book = null;
				try {
					book = BookshopFacade.getBook(isbn);
					if (book != null)
						books.add(book);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				updateTextArea();
			}
		});

		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				if (buyer == null) {
					alert_label.setText("Adicione um cliente");
					return;
				}
				if (books.isEmpty()) {
					alert_label.setText("Adicione um livro");
					return;
				}

				String number = order_input.getText();
				boolean paid = paid_input.isSelected();
				String cpf = buyer.getCpf();
				List<String> isbns = new ArrayList<String>();
				for (Book book: books) {
					isbns.add(book.getIsbn());
				}

				try {
					BookshopFacade.updateOrder(Integer.valueOf(number), cpf, isbns, paid);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				paid_input.setSelected(false);

				alert_label.setText("cadastro conclu�do");
			}
		});
	}
	
	private void updateTextArea() {
		String text = "Cliente: ";
		text += buyer == null ? "-" : buyer.getName();
		text += "\n";
		
		double price = 0.0;

		text += "livros: \n";
		for (Book book : books) {
			text += book.getTitle() + "\n";
			price += book.getPrice();
		}
		text += "preço: " + price;
		textArea.setText(text);
	}

	private void createCpf() {
		cpf_label = new JLabel("CPF:");
		cpf_label.setBounds(10, 10, 45, 20);
		add(cpf_label);

		cpf_input = new JTextField();
		cpf_input.setBounds(55, 10, 130, 20);
		add(cpf_input);

		search_buyer_button = new JButton("Mudar");
		search_buyer_button.setBounds(190, 6, 90, 25);
		add(search_buyer_button);
	}

	private void createIsbn() {
		isbn_label = new JLabel("ISBN:");
		isbn_label.setBounds(10, 35, 45, 20);
		add(isbn_label);

		isbn_input = new JTextField();
		isbn_input.setBounds(55, 35, 130, 20);
		add(isbn_input);
		
		remove_book_button = new JButton("Remover");
		remove_book_button.setBounds(285, 34, 90, 25);
		add(remove_book_button);
		
		add_book_button = new JButton("Adicionar");
		add_book_button.setBounds(190, 34, 90, 25);
		add(add_book_button);
	}

	private void createTextArea() {
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(33, 67, 365, 80);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scroll);
	}

	private void createPaid() {
		paid_label = new JLabel("Pago:");
		paid_label.setBounds(285, 10, 45, 20);
		add(paid_label);
		
		paid_input = new JCheckBox();
		paid_input.setBounds(320, 10, 180, 20);
		add(paid_input);
	}
	
	private void createOrder() {
		order_label = new JLabel("Número:");
		order_label.setBounds(10, 165, 60, 20);
		add(order_label);

		order_input = new JTextField();
		order_input.setBounds(75, 165, 110, 20);
		add(order_input);
	}

	
	private void createButtons() {
		order_button = new JButton("Buscar");
		order_button.setBounds(198, 165, 100, 25);
		add(order_button);

		action_button = new JButton("Cadastrar");
		action_button.setBounds(298, 165, 100, 25);
		add(action_button);
	}
	
	private void createAlert() {
		alert_label = new JLabel("");
		alert_label.setBounds(10, 170, 365, 20);
		add(alert_label);
	}
}
