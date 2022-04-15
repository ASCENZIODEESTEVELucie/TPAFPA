package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lanceur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	//int I = 0; au debut on voulait get notre idcompte dans la frame mais nous avons changé d'idée car après tout 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lanceur frame = new Lanceur();
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
	public Lanceur() {
		//Détermination des caractéristiques de la fenetre
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1540, 840);

		contentPane = new Accueil();
		setContentPane(contentPane);
	}

}
