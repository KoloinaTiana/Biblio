import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

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

		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File("Logo_off.png"));
			JButton buttonOff = new JButton(new ImageIcon(myPicture));
			buttonOff.setLocation(1103, 34);
			buttonOff.setSize(40, 40);
			buttonOff.setBorderPainted(false);
			buttonOff.setContentAreaFilled(false);
			buttonOff.setFocusPainted(false);
			buttonOff.setOpaque(false);
			contentPane.add(buttonOff);
			buttonOff.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					buttonOff.setBorderPainted(false);
					buttonOff.setContentAreaFilled(false);
					buttonOff.setFocusPainted(false);
					buttonOff.setOpaque(false);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					buttonOff.setBorderPainted(true);
					buttonOff.setContentAreaFilled(true);
					buttonOff.setFocusPainted(true);
					buttonOff.setOpaque(true);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("BIENVENUE");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(54, 23, 240, 66);
		contentPane.add(lblNewLabel);

		JPanel paneReservation = new JPanel();
		paneReservation.setBorder(new LineBorder(Color.BLACK, 2));
		paneReservation.setBounds(412, 142, 731, 475);
		contentPane.add(paneReservation);
		paneReservation.setLayout(null);

		JButton btnReserverUneSalle = new JButton("Reserver une salle");
		btnReserverUneSalle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserverUneSalle.setBounds(54, 27, 273, 46);
		paneReservation.add(btnReserverUneSalle);

		JButton btnReserverUnPc = new JButton("Reserver un pc");
		btnReserverUnPc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserverUnPc.setBounds(404, 27, 273, 46);
		paneReservation.add(btnReserverUnPc);

		JPanel paneEmprunt = new JPanel();
		paneEmprunt.setBorder(new LineBorder(Color.BLACK, 2));
		paneEmprunt.setBounds(412, 142, 731, 475);
		contentPane.add(paneEmprunt);
		paneEmprunt.setLayout(null);
		paneEmprunt.setVisible(false);

		JButton btnLivre = new JButton("Livre");
		btnLivre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLivre.setBounds(54, 28, 273, 46);
		paneEmprunt.add(btnLivre);

		JButton btnAutreDocument = new JButton("Autre document");
		btnAutreDocument.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAutreDocument.setBounds(407, 28, 273, 46);
		paneEmprunt.add(btnAutreDocument);

		JPanel paneAbonnement = new JPanel();
		paneAbonnement.setBorder(new LineBorder(Color.BLACK, 2));
		paneAbonnement.setBounds(28, 419, 326, 198);
		contentPane.add(paneAbonnement);
		paneAbonnement.setLayout(null);

		JLabel lblAbonnementRestant = new JLabel("Temps d'abonnement restant : 19 jours");
		lblAbonnementRestant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAbonnementRestant.setBounds(10, 31, 306, 24);
		paneAbonnement.add(lblAbonnementRestant);

		JButton btnRechargerAbonnement = new JButton("Recharger");
		btnRechargerAbonnement.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnRechargerAbonnement.setBounds(77, 100, 164, 46);
		paneAbonnement.add(btnRechargerAbonnement);

		JButton btnReserver = new JButton("Reserver une salle ou un pc");
		btnReserver.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnReserver.setBounds(480, 67, 273, 46);
		contentPane.add(btnReserver);
		btnReserver.addActionListener(e->{paneReservation.setVisible(true);paneEmprunt.setVisible(false);});

		JButton btnEmprunter = new JButton("Emprunter un livre");
		btnEmprunter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnEmprunter.setBounds(802, 67, 273, 46);
		contentPane.add(btnEmprunter);

		JLabel lbPrenom = new JLabel(client.prenom);
		lbPrenom.setBounds(46, 113, 350, 24);
		contentPane.add(lbPrenom);

		JLabel lbNom = new JLabel(client.nom);
		lbNom.setBounds(46, 142, 308, 14);
		contentPane.add(lbNom);

		JLabel lbIdentifiant = new JLabel(client.identifiant);
		lbIdentifiant.setBounds(46, 188, 308, 14);
		contentPane.add(lbIdentifiant);

		JButton btnModifCompte = new JButton("Modifier le compte");
		btnModifCompte.setBounds(28, 214, 129, 23);
		contentPane.add(btnModifCompte);
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
