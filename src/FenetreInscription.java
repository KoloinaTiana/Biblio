import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class FenetreInscription extends JFrame{

	private static final long serialVersionUID = -4215662694350105451L;

	private static FenetreInscription instance;

	private JTextField txtIdentifiant;
	private JTextField txtAdresse;
	private JTextField txtNumeroTelephone;
	private JTextField txtEmail;
	private JPasswordField pwdMDP;
	private JPasswordField pwdMDPConfirm;
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JLabel lbWarningMissTxt_1;
	private JLabel lbWarningMissTxt_2;
	private JLabel lbWarningMissTxt_3;
	private JLabel lbWarningMissTxt_4;
	private JLabel lbWarningMissTxt_5;
	private JLabel lbWarningMissTxt_6;
	private JLabel lbWarningMissTxt_7;
	private JLabel lbWarningMDPconfirm;
	private JLabel lbWarningUserExist;


	private FenetreInscription() {
		super("Inscription - B'ook la bibliothèque 2.0");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				killInstance();
				dispose();
			}
		});
		this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

		//definit sa taille
		this.setSize(600, 500);

		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);

		JLabel lbIdentifiant = new JLabel("Identifiant :");
		lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbIdentifiant.setBounds(88, 100, 91, 20);
		contentPane.add(lbIdentifiant);

		KeyAdapter enterKeyInscription = new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					actionInscription();
			}
		};

		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(198, 98, 100, 28);
		contentPane.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		txtIdentifiant.addKeyListener(enterKeyInscription);

		JLabel lbPrenom = new JLabel("Prenom :");
		lbPrenom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbPrenom.setBounds(88, 140, 73, 20);
		contentPane.add(lbPrenom);

		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(178, 138, 100, 28);
		contentPane.add(txtPrenom);
		txtPrenom.addKeyListener(enterKeyInscription);

		JLabel lbNom = new JLabel("Nom :");
		lbNom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNom.setBounds(312, 140, 51, 20);
		contentPane.add(lbNom);

		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(380, 138, 100, 28);
		contentPane.add(txtNom);
		txtNom.addKeyListener(enterKeyInscription);

		JLabel lbAdresse = new JLabel("Adresse :");
		lbAdresse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbAdresse.setBounds(88, 180, 76, 20);
		contentPane.add(lbAdresse);

		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(183, 178, 200, 28);
		contentPane.add(txtAdresse);
		txtAdresse.addKeyListener(enterKeyInscription);

		JLabel lbNumeroTelephone = new JLabel("Numero de telephone :");
		lbNumeroTelephone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNumeroTelephone.setBounds(88, 220, 181, 20);
		contentPane.add(lbNumeroTelephone);

		txtNumeroTelephone = new JTextField();
		txtNumeroTelephone.setColumns(10);
		txtNumeroTelephone.setBounds(288, 218, 120, 28);
		contentPane.add(txtNumeroTelephone);
		txtNumeroTelephone.addKeyListener(enterKeyInscription);

		JLabel lbEmail = new JLabel("E-mail :");
		lbEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbEmail.setBounds(88, 260, 63, 20);
		contentPane.add(lbEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(170, 258, 190, 28);
		contentPane.add(txtEmail);
		txtEmail.addKeyListener(enterKeyInscription);

		JLabel lbMDP = new JLabel("Mot de passe :");
		lbMDP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbMDP.setBounds(88, 300, 116, 20);
		contentPane.add(lbMDP);

		pwdMDP = new JPasswordField();
		pwdMDP.setBounds(220, 298, 100, 28);
		contentPane.add(pwdMDP);
		pwdMDP.addKeyListener(enterKeyInscription);

		JLabel lbMDPConfirm = new JLabel("Confirmer Mot de passe :");
		lbMDPConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbMDPConfirm.setBounds(88, 340, 204, 20);
		contentPane.add(lbMDPConfirm);

		pwdMDPConfirm = new JPasswordField();
		pwdMDPConfirm.setBounds(310, 338, 100, 28);
		contentPane.add(pwdMDPConfirm);
		pwdMDPConfirm.addKeyListener(enterKeyInscription);

		JButton btnInscrire = new JButton("S'incrire");
		btnInscrire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnInscrire.setBounds(164, 396, 273, 46);
		contentPane.add(btnInscrire);
		btnInscrire.addActionListener(e->actionInscription());
		btnInscrire.addKeyListener(enterKeyInscription);

		JLabel lbInscription = new JLabel("INSCRIPTION");
		lbInscription.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lbInscription.setBounds(165, 32, 269, 47);
		contentPane.add(lbInscription);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 79, 600, 20);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 385, 600, 20);
		contentPane.add(separator_1);

		lbWarningMissTxt_1 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_1.setForeground(Color.RED);
		lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_1.setBounds(195, 122, 105, 20);
		contentPane.add(lbWarningMissTxt_1);
		lbWarningMissTxt_1.setVisible(false);

		lbWarningMissTxt_2 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_2.setForeground(Color.RED);
		lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_2.setBounds(175, 162, 105, 20);
		contentPane.add(lbWarningMissTxt_2);
		lbWarningMissTxt_2.setVisible(false);

		lbWarningMissTxt_3 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_3.setForeground(Color.RED);
		lbWarningMissTxt_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_3.setBounds(377, 162, 105, 20);
		contentPane.add(lbWarningMissTxt_3);
		lbWarningMissTxt_3.setVisible(false);

		lbWarningMissTxt_4 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_4.setForeground(Color.RED);
		lbWarningMissTxt_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_4.setBounds(180, 202, 105, 20);
		contentPane.add(lbWarningMissTxt_4);
		lbWarningMissTxt_4.setVisible(false);

		lbWarningMissTxt_5 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_5.setForeground(Color.RED);
		lbWarningMissTxt_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_5.setBounds(285, 242, 105, 20);
		contentPane.add(lbWarningMissTxt_5);
		lbWarningMissTxt_5.setVisible(false);

		lbWarningMissTxt_6 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_6.setForeground(Color.RED);
		lbWarningMissTxt_6.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_6.setBounds(167, 282, 105, 20);
		contentPane.add(lbWarningMissTxt_6);
		lbWarningMissTxt_6.setVisible(false);

		lbWarningMissTxt_7 = new JLabel("Ce champ doit être rempli");
		lbWarningMissTxt_7.setForeground(Color.RED);
		lbWarningMissTxt_7.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMissTxt_7.setBounds(217, 322, 105, 20);
		contentPane.add(lbWarningMissTxt_7);
		lbWarningMissTxt_7.setVisible(false);

		lbWarningMDPconfirm = new JLabel("Les mots de passe ne correspondent pas");
		lbWarningMDPconfirm.setForeground(Color.RED);
		lbWarningMDPconfirm.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningMDPconfirm.setBounds(307, 362, 159, 20);
		contentPane.add(lbWarningMDPconfirm);
		lbWarningMDPconfirm.setVisible(false);

		lbWarningUserExist = new JLabel("Cette utilisateur existe deja");
		lbWarningUserExist.setForeground(Color.RED);
		lbWarningUserExist.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbWarningUserExist.setBounds(195, 122, 109, 20);
		contentPane.add(lbWarningUserExist);
		lbWarningUserExist.setVisible(false);

		JCheckBox showPasswordCheckbox = new JCheckBox("Afficher mots de passe");
		showPasswordCheckbox.setBounds(400, 308, 250, 23);
		getContentPane().add(showPasswordCheckbox);

		showPasswordCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					pwdMDP.setEchoChar((char) 0);
					pwdMDPConfirm.setEchoChar((char) 0);
				} else {
					pwdMDP.setEchoChar('*');
					pwdMDPConfirm.setEchoChar('*');
				}
			}
		});
	}

	public static FenetreInscription getInstance() {
		if( instance == null ) {
			instance = new FenetreInscription();
		}
		return instance;
	}

	public static void killInstance() {
		if (instance != null)
			instance.setVisible(false);
		instance = null;
	}

	private void actionInscription() {
		JTextField[] txtFields = {txtIdentifiant,txtPrenom,txtNom,txtAdresse,txtNumeroTelephone,txtEmail};
		JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2,lbWarningMissTxt_3,lbWarningMissTxt_4,lbWarningMissTxt_5,lbWarningMissTxt_6};
		boolean allChecked = true;

		for(int i = 0; i<txtFields.length; i++) {
			if(txtFields[i].getText().length() == 0) {
				warningsMissTxts[i].setVisible(true);
				allChecked = false;
			}else
				warningsMissTxts[i].setVisible(false);
		}

		if(String.valueOf(pwdMDP.getPassword()).length() == 0){
			lbWarningMissTxt_7.setVisible(true);
			allChecked = false;
		}else
			lbWarningMissTxt_7.setVisible(false);

		try {
			if(Bibliotheque.getInstance().isUserExist(txtIdentifiant.getText())) {
				lbWarningUserExist.setVisible(true);
				allChecked = false;
			}else
				lbWarningUserExist.setVisible(false);
		} catch (SQLException e) {
			e.printStackTrace();
			allChecked = false;
		}

		if(!String.valueOf(pwdMDP.getPassword()).equals(String.valueOf(pwdMDPConfirm.getPassword()))) {
			lbWarningMDPconfirm.setVisible(true);
			allChecked = false;
		}else
			lbWarningMDPconfirm.setVisible(false);

		if (allChecked == true) {
			System.out.println("Nouveau compte client cree");
			Bibliotheque.getInstance().inscriptionClient(txtIdentifiant.getText(), String.valueOf(pwdMDP.getPassword()), txtPrenom.getText(), txtNom.getText(), txtAdresse.getText(), txtNumeroTelephone.getText(), txtEmail.getText());
			JOptionPane.showMessageDialog(this,"L'utilisateur " + txtIdentifiant.getText() + " a bien ete inscrit","Confirmation inscription",JOptionPane.PLAIN_MESSAGE);
			FenetreInscription.killInstance();
		}


	}
}
