package vues;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Accueil() {
		//definition des caractetistiques du panel
		setBounds(0, 0, 1540, 840);
		setLayout(null);

		JButton btnNewButton = new JButton("CLIENTS");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new Clients());
				updateUI();
			}
		});
		btnNewButton.setBounds(870, 557, 255, 58);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("COMMANDES");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new Commandes());
				updateUI();
			}
		});
		btnNewButton_1.setBounds(1168, 557, 255, 58);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("CATEGORIES");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new Categories());
				updateUI();
			}
		});
		btnNewButton_2.setBounds(870, 663, 255, 58);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("PRODUITS");
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new Produits());
				updateUI();
			}
		});
		btnNewButton_3.setBounds(1168, 663, 255, 58);
		add(btnNewButton_3);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(122, 154, 269, 189);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bienvenue dans le");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 10, 209, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("logiciel d'administration");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(10, 66, 249, 46);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("de la boulangerie");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(10, 120, 209, 46);
		panel.add(lblNewLabel_2);

		//label permettant d'afficher le fond d'écran
		JLabel fond = new JLabel("");
		fond.setIcon(new ImageIcon("C:\\Users\\Lucie\\eclipse-workspace\\TP110422\\src\\image\\fondfin.png"));
		fond.setBounds(-76, 0, 1698, 840);
		add(fond);

	}
}
