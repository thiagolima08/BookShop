package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookshop.exception.ValidationException;
import core.BookshopFacade;

public class BookRemoveView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel isbn_label;
	private JTextField isbn_input;
	private JButton action_button;
	private JLabel alert_label;

	public BookRemoveView() {
		setLayout(null);

		createId();

		action_button = new JButton("Excluir");
		action_button.setBounds(124, 60, 150, 25);
		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alert_label.setText("");

				String isbn = isbn_input.getText();

				try {
					BookshopFacade.removeBook(isbn);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				isbn_input.setText("");
				alert_label.setText("exclus�o conclu�da");
			}
		});
		add(action_button);

		alert_label = new JLabel("");
		alert_label.setBounds(10, 170, 365, 20);
		add(alert_label);
	}

	private void createId() {
		isbn_label = new JLabel("ISBN:");
		isbn_label.setBounds(10, 10, 75, 20);
		add(isbn_label);

		isbn_input = new JTextField();
		isbn_input.setBounds(95, 10, 280, 20);
		add(isbn_input);
	}
}
