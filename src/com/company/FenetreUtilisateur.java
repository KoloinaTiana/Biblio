import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class FenetreUtilisateur extends JFrame{

	private static final long serialVersionUID = 5739633010639612024L;
	private Client client;
	private static FenetreUtilisateur instance;
	
	private FenetreUtilisateur(Client _client) {
		super("Interface utilisateur - B'ook la bibliothèque 2.0");
		
		this.client = _client;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termine le processus si la derniere fenêtre a été fermé
		this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

		// centralise la fenetre et definit sa taille
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);

		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIENVENUE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(680, 25, 240, 66);
		contentPane.add(lblNewLabel);
		
		JPanel paneReservation = new JPanel();
		paneReservation.setBorder(new LineBorder(Color.BLACK, 2));
		paneReservation.setBounds(412, 113, 731, 504);
		contentPane.add(paneReservation);
		paneReservation.setLayout(null);
		
		JButton btnReserverUneSalle = new JButton("Reserver une salle");
		btnReserverUneSalle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserverUneSalle.setBounds(54, 28, 273, 46);
		paneReservation.add(btnReserverUneSalle);
		
		JButton btnReserverUnPc = new JButton("Reserver un pc");
		btnReserverUnPc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserverUnPc.setBounds(407, 28, 273, 46);
		paneReservation.add(btnReserverUnPc);
		
		JPanel paneEmprunt = new JPanel();
		paneEmprunt.setBorder(new LineBorder(Color.BLACK, 2));
		paneEmprunt.setBounds(412, 113, 731, 504);
		contentPane.add(paneEmprunt);
		paneEmprunt.setLayout(null);
		
		JButton btnLivre = new JButton("Livre");
		btnLivre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLivre.setBounds(54, 28, 273, 46);
		paneEmprunt.add(btnLivre);
		
		JButton btnAutreDocument = new JButton("Autre document");
		btnAutreDocument.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAutreDocument.setBounds(407, 28, 273, 46);
		paneEmprunt.add(btnAutreDocument);
		
		JButton btnReserver = new JButton("Reserver une salle ou un pc");
		btnReserver.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserver.setBounds(61, 42, 273, 46);
		contentPane.add(btnReserver);
		btnReserver.addActionListener(e->{paneReservation.setVisible(true);paneEmprunt.setVisible(false);});
		
		JButton btnEmprunter = new JButton("Emprunter un livre");
		btnEmprunter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEmprunter.setBounds(61, 118, 273, 46);
		contentPane.add(btnEmprunter);
		btnEmprunter.addActionListener(e->{paneEmprunt.setVisible(true);paneReservation.setVisible(false);});
	}
	
	public static FenetreUtilisateur getInstance(Client _client) {
		if( instance == null ) {
			instance = new FenetreUtilisateur(_client);
		}
		return instance;
	}
	
	public static void killInstance() {
		if (instance != null)
			instance.setVisible(false);
		instance = null;
	}
}
