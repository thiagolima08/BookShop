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

import bookshop.model.Order;
import core.BookshopFacade;

public class OtherSearchOrderView {

	private JFrame frame;
	private JTextArea textArea;
	private JTextField search_input;
	private JButton buyers_button;
	private JButton books_button;
	private JLabel alert_label;

	public OtherSearchOrderView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Listar Pedidos por Clientes e Livros");
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

				String cpf = search_input.getText();
				if (cpf.isEmpty()) {
					textArea.setText("Coloque o cpf do cliente.");
					return;
				}

				try {
					List<Order> orders = BookshopFacade.findOrdersByCpf(cpf);
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

				String isbn = search_input.getText();
				if (isbn.isEmpty()) {
					textArea.setText("Coloque o isbn do livro.");
					return;
				}

				try {
					List<Order> orders = BookshopFacade.findOrdersByIsbn(isbn);
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
		search_input.setBounds(71, 115, 137, 20);
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
		
		JLabel lblNewLabel = new JLabel("ISBN/CPF:");
		lblNewLabel.setBounds(14, 118, 84, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
