package vues;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controllers.ProduitController;

public class Catalogue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;

	//int I = 0; au debut on voulait get notre idcompte dans la frame mais nous avons changé d'idée car après tout 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Catalogue frame = new Catalogue();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	/**
	 * Create the frame.
	 */
	public Catalogue() {
		//Détermination des caractéristiques de la fenetre
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 850, 787);
		
		setBackground(Color.WHITE);
		setBounds(92, 75, 770, 770);
		setLayout(null);

		//déclaration du panel de scroll de la table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 736, 660);
		add(scrollPane);

		//déclaration de la table permettant d'afficher les données en base
		table = new JTable();

		//création de la premiere ligne titre pour le tableau
		Vector<String> rowHeader = new Vector<String> ();      
		rowHeader.add ("ID");
		rowHeader.add ("Nom");
		rowHeader.add ("Description");
		rowHeader.add ("Prix");
		rowHeader.add ("ID_CATEGORIE");

		//affectation du model a ma table et ajout de la ligne titre a ce même model
		model = new DefaultTableModel(rowHeader,0);
		table.setModel(model); 
		model.addRow(rowHeader); 

		//appel du controller client pour setter l'ensemble des resultats de la table sur la page
		ProduitController p = new ProduitController();
		ResultSet res = null;
		try {
			res = p.seeProd();
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
					rowData.add (res.getString("Nom"));
					rowData.add (res.getString("Description"));
					rowData.add (res.getString("Prix"));
					rowData.add (Integer.toString(res.getInt("ID_CATEGORIE"))); 
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
		add(scrollPane);

		try {
			res.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//trop bien j'arrive a sort a partir de ma bd je suis joie
		TableRowSorter<TableModel> rowSorter
		= new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 675, 750, 51);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SAISISSEZ VOTRE RECHERCHE");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 6, 400, 39);
		panel.add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setBounds(352, 6, 388, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		textField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

			@Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

			@Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

}


