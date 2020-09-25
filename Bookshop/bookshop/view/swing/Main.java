package bookshop.view.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.BookshopFacade;

public class Main {

	private JFrame frame;
	private JMenuBar menuBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		frame = new JFrame();
		frame.setTitle("Livraria");
		frame.setBounds(100, 100, 472, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				BookshopFacade.start();
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				BookshopFacade.end();
				JOptionPane.showMessageDialog(null, "sistema finalizado !");
			}
		});

		menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);

		createClientesMenu();
		createLivrosMenu();
		createPedidosMenu();
		createOthersMenu();

		frame.setJMenuBar(menuBar);
	}

	private void createClientesMenu() {
		JMenu menu = new JMenu("Clientes");

		JMenuItem menuItem = new JMenuItem("Cadastrar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Cadastrar cliente", new BuyerCreateView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Editar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Editar cliente", new BuyerEditView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Excluir");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Excluir cliente", new BuyerRemoveView());
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);
	}

	private void createLivrosMenu() {
		JMenu menu = new JMenu("Livros");

		JMenuItem menuItem = new JMenuItem("Cadastrar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Cadastrar livro", new BookCreateView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Editar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Editar livro", new BookEditView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Excluir");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Excluir livro", new BookRemoveView());
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);
	}

	private void createPedidosMenu() {
		JMenu menu = new JMenu("Pedidos");

		JMenuItem menuItem = new JMenuItem("Cadastrar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Cadastrar pedido", new OrderCreateView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Editar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Editar pedido", new OrderEditView());
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Excluir");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePane("Excluir pedido", new OrderRemoveView());
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);
	}

	private void createOthersMenu() {
		JMenu menu = new JMenu("Outros");

		JMenuItem menuItem = new JMenuItem("Listar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OtherListView();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Consultar Clientes e Livros por name");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OtherSearchBuyerAndBookView();
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Consultar Pedidos por Clientes e Livros");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OtherSearchOrderView();
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);
	}

	public void changePane(String title, JPanel pane) {
		frame.setTitle(title);
		frame.setContentPane(pane);
		frame.getContentPane().revalidate();
		frame.repaint();
	}
}
