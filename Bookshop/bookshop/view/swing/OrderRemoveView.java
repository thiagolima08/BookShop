package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookshop.exception.ValidationException;
import core.BookshopFacade;

public class OrderRemoveView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel number_label;
	private JTextField number_input;
	private JButton action_button;
	private JLabel alert_label;

	public OrderRemoveView() {
		setLayout(null);

		createId();

		action_button = new JButton("Excluir");
		action_button.setBounds(128, 55, 150, 25);
		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alert_label.setText("");

				String number = number_input.getText();

				try {
					BookshopFacade.removeOrder(Integer.valueOf(number));
				} catch (NumberFormatException e) {
					alert_label.setText("Número precisar ser um valor numérico");
					return;
				}  catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				number_input.setText("");
				alert_label.setText("exclusão concluída");
			}
		});
		add(action_button);

		alert_label = new JLabel("");
		alert_label.setBounds(10, 170, 365, 20);
		add(alert_label);
	}

	private void createId() {
		number_label = new JLabel("Número:");
		number_label.setBounds(10, 10, 75, 20);
		add(number_label);

		number_input = new JTextField();
		number_input.setBounds(95, 10, 280, 20);
		add(number_input);
	}
}
