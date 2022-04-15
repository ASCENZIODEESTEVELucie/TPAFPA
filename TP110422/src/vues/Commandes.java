package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.CommandeController;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Commandes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public Commandes() {
		//definition des caractetistiques du panel
		setBounds(0, 0, 1540, 840);
		setLayout(null);

		//appel du controller client pour setter l'ensemble des resultats de la table sur la page
		CommandeController com = new CommandeController();

		//panel avertissement suppression
		JPanel panelbis = new JPanel();
		panelbis.setLayout(null);
		panelbis.setBackground(Color.PINK);
		panelbis.setBounds(92, 7, 627, 80);
		panelbis.setVisible(false);
		add(panelbis);

		JButton btnOuibis = new JButton("oui");
		btnOuibis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>0) {
					int id = Integer.parseInt(textField.getText());
					try {
						com.deleteCom(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					model.removeRow(i);
				}else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
				removeAll();
				add(new Commandes());
				updateUI();
			}
		});
		btnOuibis.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnOuibis.setBorderPainted(false);
		btnOuibis.setBackground(new Color(240,0,32));
		btnOuibis.setBounds(41, 12, 119, 60);
		panelbis.add(btnOuibis);

		JButton btnNonbis = new JButton("non");
		btnNonbis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelbis.setVisible(false);
				updateUI();
			}
		});
		btnNonbis.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNonbis.setBorderPainted(false);
		btnNonbis.setBackground(new Color(119,221,119));
		btnNonbis.setBounds(458, 12, 119, 60);
		panelbis.add(btnNonbis);

		JLabel lbl1bis = new JLabel("Avertissement suppresion");
		lbl1bis.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl1bis.setBounds(218, -7, 230, 79);
		panelbis.add(lbl1bis);

		JLabel lbl2bis = new JLabel("de données");
		lbl2bis.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2bis.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbl2bis.setBounds(170, 27, 278, 60);
		panelbis.add(lbl2bis);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(92, 75, 627, 646);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(870, 75, 553, 435);
		add(panel_1);

		//déclaration du panel de scroll de la table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 607, 626);
		panel.add(scrollPane);

		//déclaration de la table permettant d'afficher les données en base
		table = new JTable();

		//création de la premiere ligne titre pour le tableau
		Vector<String> rowHeader = new Vector<String> ();      
		rowHeader.add ("ID");
		rowHeader.add ("ListeProd");
		rowHeader.add ("Total");
		rowHeader.add ("Notes");
		rowHeader.add ("ID_CLIENT");

		//affectation du model a ma table et ajout de la ligne titre a ce même model
		model = new DefaultTableModel(rowHeader,0);
		table.setModel(model); 
		model.addRow(rowHeader); 

		ResultSet res = null;
		try {
			res = com.seeCom();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//je creées le vecteur qui recevera mes rows de données
		Vector<String> rowData;      
		if (res != null) {
			try {//j'injecte les données que je recupere dans la table 
				while (res.next()){  
					rowData = new Vector<String>() ;  
					rowData.add (res.getString("ID"));
					rowData.add (res.getString("ListeProd"));
					rowData.add (res.getString("Total"));
					rowData.add (res.getString("Client"));
					rowData.add (res.getString("ID_CLIENT"));
					model.addRow(rowData); 
					model.setColumnIdentifiers(rowData);//pour chopper la colonne
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}     
		}

		//la le but est de faire afficher une grille pour bien séparer les données
		table.setShowGrid(true);
		table.setShowVerticalLines(true);

		//j'ajoute a ma table la capacité d'écouter au clic et de venir setter le text dans mes champs de text
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int i = table.getSelectedRow();

				textField.setText(model.getValueAt(i, 0).toString());
				textPane.setText(model.getValueAt(i, 1).toString());
				textField_2.setText(model.getValueAt(i, 2).toString());
				textPane_1.setText(model.getValueAt(i, 3).toString());
				textField_4.setText(model.getValueAt(i, 4).toString());
			}
		});
		panel.add(scrollPane);

		try {
			res.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(new Accueil());
				updateUI();
			}
		});
		BACK.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		BACK.setBorderPainted(false);
		BACK.setBackground(Color.WHITE);
		BACK.setBounds(10, 7, 81, 58);
		add(BACK);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String list=textPane.getText();
				Float total=Float.parseFloat(textField_2.getText());
				String n=textPane_1.getText();
				int IDcli=Integer.parseInt(textField_4.getText());
				if(list.equals("")||textField_2.getText().equals("")||n.equals("")||textField_4.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "veuillez remplir tous les champs");
				}
				else {
					try {
						//a faire plus tard

						com.insertCom(list, total, n, IDcli);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//faire des controllers pour le else if
				}
				removeAll();
				add(new Commandes());
				updateUI();
			}
		});
		btnNewButton.setBounds(870, 557, 169, 58);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("MODIFY");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = table.getSelectedRow();
				int ID= Integer.parseInt(textField.getText());
				String list=textPane.getText();
				Float total=Float.parseFloat(textField_2.getText());
				String n=textPane_1.getText();
				int IDcli=Integer.parseInt(textField_4.getText());
				
				if(list.equals("")||textField_2.getText().equals("")||n.equals("")||textField_4.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "veuillez selectionner une ligne et completer chaque champs");

				}
				else if(list.equals("")&&textField_2.getText().equals("")&&n.equals("")&&textField_4.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "veuillez selectionner une ligne et completer chaque champs");
					//faire tt les cas possible vu le nombre de champs prendrait du temps.
				}
				else {
					try {
						com.updateCom(ID, list, total, n, IDcli);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				removeAll();
				add(new Commandes());
				updateUI();
			}
		});
		btnNewButton_1.setBounds(1061, 557, 170, 58);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("SELECT");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("Pour selectionner choisissez une ligne qui vous interesse, puis Cliquer dessus pour en afficher le contenu");
			}
		});
		btnNewButton_2.setBounds(1061, 663, 169, 58);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelbis.setVisible(true);
				updateUI();
			}
		});
		btnNewButton_3.setBounds(1253, 663, 170, 58);
		add(btnNewButton_3);

		JButton btnNewButton_5 = new JButton("CLEAR");
		btnNewButton_5.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setText("");
				textPane.setText("");
				textField_2.setText("");
				textPane_1.setText("");
				textField_4.setText("");

			}
		});
		btnNewButton_5.setBounds(870, 663, 169, 58);
		add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("ETATS");
		btnNewButton_6.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtatCommandes et = new EtatCommandes();
				et.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(1253, 557, 169, 58);
		add(btnNewButton_6);

		JLabel lblNewLabel_1 = new JLabel("ListeProd");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(10, 99, 209, 46);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Total");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(10, 222, 249, 46);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Notes");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(10, 278, 209, 46);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("ID_CLIENT");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_4.setBounds(10, 360, 128, 46);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("COMMANDE");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_7.setBounds(229, 10, 209, 46);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Num\u00E9ro");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_8.setBounds(10, 43, 209, 46);
		panel_1.add(lblNewLabel_8);

		textField = new JTextField();
		textField.setBounds(163, 54, 380, 35);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(163, 234, 380, 35);
		panel_1.add(textField_2);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(163, 372, 380, 35);
		panel_1.add(textField_4);

		textPane = new JTextPane();
		textPane.setBounds(163, 99, 380, 125);
		textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel_1.add(textPane);

		textPane_1 = new JTextPane();
		textPane_1.setBounds(163, 278, 380, 83);
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel_1.add(textPane_1);

		JButton btnNewButton_4 = new JButton("CATALOGUE");
		btnNewButton_4.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setBackground(new Color(190, 220, 250));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apl fenetre de produits.
				Catalogue p = new Catalogue();
				p.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 144, 95, 27);
		panel_1.add(btnNewButton_4);

		JButton btnNewButton_4_1 = new JButton("CALCULETTE");
		btnNewButton_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		btnNewButton_4_1.setBorderPainted(false);
		btnNewButton_4_1.setBackground(new Color(190, 220, 250));
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apl fenetre de produits.
				Calculatrice c = new Calculatrice();
				c.setVisible(true);
			}
		});
		btnNewButton_4_1.setBounds(10, 261, 95, 27);
		panel_1.add(btnNewButton_4_1);

		JButton btnNewButton_4_2 = new JButton("LISTE");
		btnNewButton_4_2.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		btnNewButton_4_2.setBorderPainted(false);
		btnNewButton_4_2.setBackground(new Color(190, 220, 250));
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apl fenetre de produits.
				ListClient cl = new ListClient();
				cl.setVisible(true);
			}
		});
		btnNewButton_4_2.setBounds(10, 398, 60, 27);
		panel_1.add(btnNewButton_4_2);

		JButton btnNewButton_4_3 = new JButton("NEW");
		btnNewButton_4_3.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		btnNewButton_4_3.setBorderPainted(false);
		btnNewButton_4_3.setBackground(new Color(190, 220, 250));
		btnNewButton_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apl fenetre de produits.
				Lanceur n = new Lanceur();
				n.setContentPane(new Clients());
				n.setVisible(true);
			}
		});
		btnNewButton_4_3.setBounds(80, 398, 60, 27);
		panel_1.add(btnNewButton_4_3);

		//label permettant d'afficher le fond d'écran
		JLabel fond = new JLabel("");
		fond.setIcon(new ImageIcon("C:\\Users\\Lucie\\eclipse-workspace\\TP110422\\src\\image\\fondfin.png"));
		fond.setBounds(-76, 0, 1698, 840);
		add(fond);
	}
}
