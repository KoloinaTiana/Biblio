package com.company;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class FenetreConnexion extends JFrame {
	
	private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

	
	private JTextField txtIdentifiant;
	private JPasswordField pfMotDePasse;
	
	
	public FenetreConnexion() {
		super("Connexion - B'ook la bibliotheque 2.0");
		getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
		this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
		// centralise la fenetre et definit sa taille 
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		
		JLabel lbIdentifiant = new JLabel("Identifiant :");
		lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbIdentifiant.setBounds(243, 116, 150, 20);
		contentPane.add(lbIdentifiant);
		
		JLabel lbMotDePasse = new JLabel("Mot de passe :");
		lbMotDePasse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbMotDePasse.setSize(150, 20);
		lbMotDePasse.setLocation(243, 186);
		contentPane.add(lbMotDePasse);
		
		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(243, 147, 193, 28);
		getContentPane().add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		
		pfMotDePasse = new JPasswordField();
		pfMotDePasse.setBounds(243, 217, 193, 28);
		getContentPane().add(pfMotDePasse);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnConnexion.setBounds(203, 311, 273, 46);
		getContentPane().add(btnConnexion);
		
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnInscription.setBounds(203, 380, 273, 46);
		getContentPane().add(btnInscription);
		
		JButton btnOublieMDP = new JButton("Mot de passe oublie ?");
		btnOublieMDP.setBounds(200, 256, 180, 23);
		getContentPane().add(btnOublieMDP);
		
		btnOublieMDP.setBorderPainted(false);
		btnOublieMDP.setContentAreaFilled(false);
		
		JCheckBox chckbxSeSouvenir = new JCheckBox("Se souvenir de moi");
		chckbxSeSouvenir.setBounds(361, 256, 150, 23);
		getContentPane().add(chckbxSeSouvenir);
		
		
		
	}
	
}
