package vues;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

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

import DAO.ProduitDAO;
import entites.Db;
import entites.Produit;

public class Catalogue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;

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
		//connexion à la base de données
		Db.Connect();
		//Détermination des caractéristiques de la fenetre
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 850, 787);
		
		setBackground(Color.WHITE);
		setBounds(92, 75, 770, 770);
		setLayout(null);

		table = new JTable();
		getContentPane().add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 5, 736, 660);
		getContentPane().add(scrollPane);

		ProduitDAO prddao=new ProduitDAO();

		ArrayList<Produit> prds = prddao.getAll();

		String columns[] = {"ID","Nom","Description","Prix","ID_CATEGORIE"};
		String data[][] = new String[prds.size()][columns.length];

		int i = 0;

		for(Produit prod:prds) {
			int id = prod.getId();
			String n = prod.getTitre();
			String d = prod.getDescription();
			Float p = prod.getPrix();
			int cat = prod.getCat();
			data[i][0] = id + "";
			data[i][1] = n;
			data[i][2] = d;
			data[i][3] = p + ""; //evite de parse grace a la concatenation
			data[i][4] = cat + "";
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table.setModel(model);
		
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


