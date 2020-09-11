package bookshop.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookshop.exception.ValidationException;
import bookshop.model.Buyer;
import core.BookshopFacade;

public class BuyerEditView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel name_label;
	private JTextField name_input;
	private JLabel cpf_label;
	private JTextField cpf_input;
	private JButton search_button;
	private JLabel phone_label;
	private JTextField phone_input;
	private JLabel email_label;
	private JTextField email_input;
	private JButton cancel_button;
	private JButton action_button;
	private JLabel alert_label;

	public BuyerEditView() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		createName();
		createCpf();
		createPhone();
		createEmail();
		createButtons();
		createAlert();

		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				String cpf = cpf_input.getText();
				
				Buyer buyer;
				try {
					buyer = BookshopFacade.getBuyer(cpf);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}
				
				name_input.setText(buyer.getName());
				phone_input.setText(buyer.getPhone());
				email_input.setText(buyer.getEmail());
				
				cpf_input.setEnabled(false);
				search_button.setEnabled(false);
			}
		});

		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				name_input.setText("");
				cpf_input.setText("");
				phone_input.setText("");
				email_input.setText("");
				
				cpf_input.setEnabled(true);
				search_button.setEnabled(true);
			}
		});

		action_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				alert_label.setText("");

				String name = name_input.getText();
				String cpf = cpf_input.getText();
				String phone = phone_input.getText();
				String email = email_input.getText();

				try {
					BookshopFacade.updateBuyer(name, cpf, phone, email);
				} catch (ValidationException e) {
					alert_label.setText(e.getMessage());
					return;
				} catch (Exception e) {
					alert_label.setText("erro interno");
					return;
				}

				name_input.setText("");
				cpf_input.setText("");
				phone_input.setText("");
				email_input.setText("");

				cpf_input.setEnabled(true);
				search_button.setEnabled(true);

				alert_label.setText("atualiza��o conclu�da");
			}
		});
	}

	private void createName() {
		name_label = new JLabel("Nome:");
		name_label.setBounds(10, 10, 50, 20);
		add(name_label);

		name_input = new JTextField();
		name_input.setBounds(86, 10, 310, 20);
		add(name_input);
	}

	private void createCpf() {
		cpf_label = new JLabel("CPF:");
		cpf_label.setBounds(10, 35, 50, 20);
		add(cpf_label);

		cpf_input = new JTextField();
		cpf_input.setBounds(86, 35, 165, 20);
		add(cpf_input);
		
		search_button = new JButton("buscar");
		search_button.setBounds(261, 32, 135, 25);
		add(search_button);
	}

	private void createPhone() {
		phone_label = new JLabel("Telefone:");
		phone_label.setBounds(10, 60, 64, 20);
		add(phone_label);

		phone_input = new JTextField();
		phone_input.setBounds(86, 60, 310, 20);
		add(phone_input);
	}

	private void createEmail() {
		email_label = new JLabel("Email:");
		email_label.setBounds(10, 85, 50, 20);
		add(email_label);

		email_input = new JTextField();
		email_input.setBounds(86, 85, 310, 20);
		add(email_input);
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
