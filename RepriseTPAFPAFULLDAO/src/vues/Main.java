package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	//int I = 0; au debut on voulait get notre idcompte dans la frame mais nous avons chang� d'id�e car apr�s tout 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		//D�termination des caract�ristiques de la fenetre
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1540, 840);

		contentPane = new Accueil();
		setContentPane(contentPane);
	}

}
