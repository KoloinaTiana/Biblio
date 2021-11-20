import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FenetreConnexion extends JFrame {

	private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

	private JTextField txtIdentifiant;
	private JPasswordField pwdMotDePasse;

	private JLabel lbWarningUserNotFound;
	private JLabel lbWarningIncorrectPassword;

	private static FenetreConnexion instance;

	JPanel contentPane;

	FenetreConnexion() {
		super("Connexion - B'ook la bibliothèque 2.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termine le processus si la derniere fenêtre a été fermé
		this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

		// centralise la fenetre et definit sa taille
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);

		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File("Logo_connexion.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setLocation(213, 0);
			picLabel.setSize(273, 242);
			contentPane.add(picLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JLabel lbMotDePasse = new JLabel("Mot de passe :");
		lbMotDePasse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbMotDePasse.setSize(150, 20);
		lbMotDePasse.setLocation(253, 303);
		contentPane.add(lbMotDePasse);

		JLabel lbIdentifiant = new JLabel("Identifiant : ");
		lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbIdentifiant.setSize(150, 20);
		lbIdentifiant.setLocation(253, 232);
		contentPane.add(lbIdentifiant);

		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnConnexion.setBounds(213, 410, 273, 46);
		contentPane.add(btnConnexion);
		btnConnexion.addActionListener(e->actionConnexion());
		KeyAdapter enterKeyOuvertureCompte = new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					actionConnexion();
			}
		};
		btnConnexion.addKeyListener(enterKeyOuvertureCompte);

		txtIdentifiant = new JTextField("Benji4411");
		txtIdentifiant.setBounds(253, 263, 193, 28);
		contentPane.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		txtIdentifiant.addKeyListener(enterKeyOuvertureCompte);

		pwdMotDePasse = new JPasswordField("Benji41");
		pwdMotDePasse.setBounds(253, 335, 193, 28);
		contentPane.add(pwdMotDePasse);
		pwdMotDePasse.addKeyListener(enterKeyOuvertureCompte);

		JButton btnInscription = new JButton("Inscription");
		btnInscription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnInscription.setBounds(213, 468, 273, 46);
		contentPane.add(btnInscription);
		btnInscription.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					actionInscription();
			}
		});
		btnInscription.addActionListener(e->actionInscription());

		String oublieMDP = "Mot de passe oublié ?";
		JLabel lbOublieMDP = new JLabel(oublieMDP);
		Color oublieMDPColor = lbOublieMDP.getForeground();
		lbOublieMDP.setBounds(172, 375, 125, 23);
		contentPane.add(lbOublieMDP);
		lbOublieMDP.setFocusable(true);
		lbOublieMDP.addMouseListener(new MouseListener() {

			@Override
			public void mouseEntered(MouseEvent e) {
				lbOublieMDP.setText("<html><body><u>" + oublieMDP + "</u></body></html>");// souligne le texte lorsque la souris passe dessus

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbOublieMDP.setText(oublieMDP);// enlève le soulignage du texte lorsque la souris sort
				lbOublieMDP.setForeground(oublieMDPColor);//enlèvee le highlight du texte lorque la souris sort
			}

			@Override
			public void mouseClicked(MouseEvent e){}

			@Override
			public void mousePressed(MouseEvent e) {

				lbOublieMDP.setForeground(Color.gray);// highlight le texte lorsque l'utilisateur presse le bouton

				lbOublieMDP.requestFocus();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Rectangle rectangle = new Rectangle(170, 23);
				if (rectangle.contains(e.getPoint())) {        //si la souris se trouve toujours sur le contentPanel lorsque l'utilisateur relâche le clic alors on ouvre la fenetre mot de passe oublié
					actionOublieMDP();
				}

			}
		});
		lbOublieMDP.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					actionOublieMDP();
			}
		});
		lbOublieMDP.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				lbOublieMDP.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void focusGained(FocusEvent e) {
				lbOublieMDP.setBorder(BorderFactory.createLineBorder(new Color(175,196,218)));

			}
		});

		JCheckBox chckbxSeSouvenir = new JCheckBox("Se souvenir de moi");
		chckbxSeSouvenir.setBounds(416, 375, 136, 23);
		contentPane.add(chckbxSeSouvenir);

		lbWarningUserNotFound = new JLabel("Utilisateur inconnu");
		lbWarningUserNotFound.setForeground(Color.RED);
		lbWarningUserNotFound.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningUserNotFound.setBounds(369, 289, 77, 20);
		contentPane.add(lbWarningUserNotFound);
		lbWarningUserNotFound.setVisible(false);

		lbWarningIncorrectPassword = new JLabel("Mot de passe incorrect");
		lbWarningIncorrectPassword.setForeground(Color.RED);
		lbWarningIncorrectPassword.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningIncorrectPassword.setBounds(356, 361, 90, 20);
		contentPane.add(lbWarningIncorrectPassword);
		lbWarningIncorrectPassword.setVisible(false);

	}

	private void actionConnexion() {
		lbWarningUserNotFound.setVisible(false);
		lbWarningIncorrectPassword.setVisible(false);
		try {
			Compte c = Bibliotheque.getInstance().connexion(txtIdentifiant.getText(),
					String.valueOf(pwdMotDePasse.getPassword()));
			if (c instanceof Client) {
				System.out.println("Ouverture compte client : " + c.identifiant);
				FenetreUtilisateur fenetreUtilisateur = FenetreUtilisateur.getInstance((Client)c);
				fenetreUtilisateur.setVisible(true);
				FenetreOublieMDP.killInstance();
				FenetreInscription.killInstance();
				FenetreConnexion.killInstance();
			}
			else
				System.out.println("Ouverture compte admin : " + c.identifiant);

		} catch (UserNotFoundException e1) {
			System.out.println(e1);
			lbWarningUserNotFound.setVisible(true);

		} catch (IncorrectPasswordException e2) {
			System.out.println(e2);
			lbWarningIncorrectPassword.setVisible(true);
		}
	}

	private void actionInscription(){
		FenetreInscription fenetreInscription = FenetreInscription.getInstance();
		fenetreInscription.setVisible(true);
		fenetreInscription.setLocationRelativeTo(contentPane);
		FenetreOublieMDP.killInstance();
	}

	private void actionOublieMDP() {
		FenetreOublieMDP fenetreOublieMDP = FenetreOublieMDP.getInstance();
		fenetreOublieMDP.setVisible(true);
		fenetreOublieMDP.setLocationRelativeTo(contentPane);
		FenetreInscription.killInstance();
	}

	public static FenetreConnexion getInstance() {
		if( instance == null ) {
			instance = new FenetreConnexion();
		}
		return instance;
	}

	public static void killInstance() {
		if (instance != null)
			instance.setVisible(false);
		instance = null;
	}
}
