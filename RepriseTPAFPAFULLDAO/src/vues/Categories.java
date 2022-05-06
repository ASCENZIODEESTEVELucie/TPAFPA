package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.CategorieDAO;
import entites.Categorie;
import entites.Db;

public class Categories extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Categories() {
		Db.Connect();
		//definition des caractetistiques du panel
		setBounds(0, 0, 1540, 840);
		setLayout(null);

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
					int index=table.getSelectedRow();
					int selectedId = Integer.parseInt(table.getModel().getValueAt(index, 0).toString());
					CategorieDAO dao=new CategorieDAO();
					dao.deleteById(selectedId);
					((DefaultTableModel)table.getModel()).removeRow(index);
					JOptionPane.showMessageDialog(null, "La donnée a bien été supprimée ");
					panelbis.setVisible(false);
					updateUI();
					textField.setText("");
					textField_1.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
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

		table = new JTable();
		panel.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 607, 626);
		panel.add(scrollPane);

		CategorieDAO catdao=new CategorieDAO();

		ArrayList<Categorie> cats = catdao.getAll();

		String columns[] = {"ID","Type"};
		String data[][] = new String[cats.size()][columns.length];

		int i = 0;

		for(Categorie cat:cats) {
			int id = cat.getId();
			String titre = cat.getTitre();
			data[i][0] = id + "";
			data[i][1] = titre;
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table.setModel(model);
		
		//j'ajoute a ma table la capacité d'écouter au clic et de venir setter le text dans mes champs de text
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						int i = table.getSelectedRow();

						textField.setText(model.getValueAt(i, 0).toString());
						textField_1.setText(model.getValueAt(i, 1).toString());
					}
		});

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

		JButton btnNewButton_1 = new JButton("SAVE");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Nm=textField_1.getText();
				if(textField.getText().equals("")) {
					if(Nm.equals("")) {
						JOptionPane.showMessageDialog(null, "veuillez selectionner une ligne ou entrer un nom");
					}else {
						Categorie u =  new Categorie(Nm);
						CategorieDAO dao = new CategorieDAO();
						dao.save(u);
					}
					removeAll();
					add(new Categories());
					updateUI();
				}
				else if(!textField.getText().equals("")) {
					int ID = Integer.parseInt(textField.getText());
					if(Nm.equals("")) {
						JOptionPane.showMessageDialog(null, "veuillez entrer un nom de catégorie");
					}else {
						Categorie u =  new Categorie(ID, Nm);
						CategorieDAO dao = new CategorieDAO();
						dao.save(u);
					}
					removeAll();
					add(new Categories());
					updateUI();
				}else {
					JOptionPane.showMessageDialog(null, "veuillez cliquer sur une ligne ou entrer un nom pour creer une catégorie");
				}	
			}
		});
		btnNewButton_1.setBounds(870, 557, 553, 58);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("SELECT");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("Pour selectionner");
				textField_1.setText("Cliquer sur une ligne qui vous interesse pour en afficher le contenu");
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

		JButton btnNewButton_4 = new JButton("CLEAR");
		btnNewButton_4.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnNewButton_4.setBounds(870, 663, 169, 58);
		add(btnNewButton_4);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(10, 99, 209, 46);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_7 = new JLabel("CATEGORIE");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_7.setBounds(229, 10, 209, 46);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Num\u00E9ro");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel_8.setBounds(10, 43, 209, 46);
		panel_1.add(lblNewLabel_8);

		textField = new JTextField();
		textField.setBounds(163, 54, 148, 35);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 110, 380, 35);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("POUR CREER UNE CATEGORIE NE PAS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
		lblNewLabel.setBounds(329, 0, 291, 118);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("RENSEIGNER DE NUMERO");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 10));
		lblNewLabel_2.setBounds(330, 43, 163, 83);
		panel_1.add(lblNewLabel_2);

		//label permettant d'afficher le fond d'écran
		JLabel fond = new JLabel("");
		fond.setIcon(new ImageIcon("C:\\Users\\Lucie\\eclipse-workspace\\TP110422\\src\\image\\fondfin.png"));
		fond.setBounds(-76, 0, 1698, 840);
		add(fond);
	}
}
