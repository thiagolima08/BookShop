package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookshop.exception.ValidationException;
import core.BookshopFacade;

public class BuyerRemoveView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel cpf_label;
	private JTextField cpf_input;
	private JButton action_button;
	private JLabel alert_label;

	public BuyerRemoveView() {
		setLayout(null);

		createId();

		action_button = new JButton("Excluir");
		action_button.setBounds(108, 59, 150, 25);
		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alert_label.setText("");

				String cpf = cpf_input.getText();

				try {
					BookshopFacade.removeBuyer(cpf);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				cpf_input.setText("");
				alert_label.setText("exclus�o conclu�da");
			}
		});
		add(action_button);

		alert_label = new JLabel("");
		alert_label.setBounds(10, 170, 365, 20);
		add(alert_label);
	}

	private void createId() {
		cpf_label = new JLabel("CPF:");
		cpf_label.setBounds(10, 10, 75, 20);
		add(cpf_label);

		cpf_input = new JTextField();
		cpf_input.setBounds(95, 10, 280, 20);
		add(cpf_input);
	}
}
