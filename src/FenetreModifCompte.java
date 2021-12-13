import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class FenetreModifCompte extends JFrame {

    private static final long serialVersionUID = 5739633010639612024L;
    private Client client;
    private static FenetreModifCompte instance;

    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtIdentifiant;
    private JTextField txtAdresse;
    private JTextField txtNumeroTelephone;
    private JPasswordField pwdMDP;
    private JPasswordField pwdMDPConfirm;
    private JTextField txtEmail;
    private JLabel lbWarningMissTxt_1, lbWarningMissTxt_2, lbWarningMissTxt_3, lbWarningMissTxt_4,lbWarningMissTxt_5,lbWarningMissTxt_6,lbWarningMissTxt_7,lbWarningMDPconfirm,lbWarningUserExist;

    private FenetreModifCompte(Client _client) {
        super("Interface modification compte- B'ook la bibliotheque 2.0");

        this.client = _client;

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
        lbIdentifiant.setBounds(88, 100, 91, 20);
        contentPane.add(lbIdentifiant);

        KeyAdapter enterKeyModifCompte = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    actionModifCompte();
            }
        };

        txtIdentifiant = new JTextField(client.identifiant);
        txtIdentifiant.setBounds(198, 98, 100, 28);
        contentPane.add(txtIdentifiant);
        txtIdentifiant.setColumns(10);
        txtIdentifiant.addKeyListener(enterKeyModifCompte);

        JLabel lbPrenom = new JLabel("Prenom :");
        lbPrenom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbPrenom.setBounds(88, 140, 73, 20);
        contentPane.add(lbPrenom);

        txtPrenom = new JTextField(client.prenom);
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(178, 138, 100, 28);
        contentPane.add(txtPrenom);
        txtPrenom.addKeyListener(enterKeyModifCompte);

        JLabel lbNom = new JLabel("Nom :");
        lbNom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNom.setBounds(312, 140, 51, 20);
        contentPane.add(lbNom);

        txtNom = new JTextField(client.nom);
        txtNom.setColumns(10);
        txtNom.setBounds(380, 138, 100, 28);
        contentPane.add(txtNom);
        txtNom.addKeyListener(enterKeyModifCompte);

        JLabel lbAdresse = new JLabel("Adresse :");
        lbAdresse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbAdresse.setBounds(88, 180, 76, 20);
        contentPane.add(lbAdresse);

        txtAdresse = new JTextField(client.adresse);
        txtAdresse.setColumns(10);
        txtAdresse.setBounds(183, 178, 200, 28);
        contentPane.add(txtAdresse);
        txtAdresse.addKeyListener(enterKeyModifCompte);

        JLabel lbNumeroTelephone = new JLabel("Numero de telephone :");
        lbNumeroTelephone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNumeroTelephone.setBounds(88, 220, 181, 20);
        contentPane.add(lbNumeroTelephone);

        txtNumeroTelephone = new JTextField(client.numeroTelephone);
        txtNumeroTelephone.setColumns(10);
        txtNumeroTelephone.setBounds(288, 218, 120, 28);
        contentPane.add(txtNumeroTelephone);
        txtNumeroTelephone.addKeyListener(enterKeyModifCompte);

        JLabel lbEmail = new JLabel("E-mail :");
        lbEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbEmail.setBounds(88, 260, 63, 20);
        contentPane.add(lbEmail);

        txtEmail = new JTextField(client.eMail);
        txtEmail.setColumns(10);
        txtEmail.setBounds(170, 258, 190, 28);
        contentPane.add(txtEmail);
        txtEmail.addKeyListener(enterKeyModifCompte);

        JLabel lbMDP = new JLabel("Mot de passe :");
        lbMDP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbMDP.setBounds(88, 300, 116, 20);
        contentPane.add(lbMDP);

        pwdMDP = new JPasswordField(client.motDePasse);
        pwdMDP.setBounds(220, 298, 100, 28);
        contentPane.add(pwdMDP);
        pwdMDP.addKeyListener(enterKeyModifCompte);

        JLabel lbMDPConfirm = new JLabel("Confirmer Mot de passe :");
        lbMDPConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbMDPConfirm.setBounds(88, 340, 204, 20);
        contentPane.add(lbMDPConfirm);

        pwdMDPConfirm = new JPasswordField();
        pwdMDPConfirm.setBounds(310, 338, 100, 28);
        contentPane.add(pwdMDPConfirm);
        pwdMDPConfirm.addKeyListener(enterKeyModifCompte);

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

        JButton btnValider = new JButton("Valider");
        btnValider.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnValider.setBounds(100, 396, 173, 46);
        contentPane.add(btnValider);
        btnValider.addActionListener(e->actionModifCompte());
        btnValider.addKeyListener(enterKeyModifCompte);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(400, 396, 173, 46);
        contentPane.add(btnAnnuler);
        btnAnnuler.addActionListener(e->killInstance());

        JLabel lbInscription = new JLabel("MODIFICATION");
        lbInscription.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbInscription.setBounds(165, 32, 270, 47);
        contentPane.add(lbInscription);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 79, 600, 20);
        contentPane.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 385, 600, 20);
        contentPane.add(separator_1);

        lbWarningMissTxt_1 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(195, 122, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(175, 162, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        lbWarningMissTxt_3 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_3.setForeground(Color.RED);
        lbWarningMissTxt_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_3.setBounds(377, 162, 105, 20);
        contentPane.add(lbWarningMissTxt_3);
        lbWarningMissTxt_3.setVisible(false);

        lbWarningMissTxt_4 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_4.setForeground(Color.RED);
        lbWarningMissTxt_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_4.setBounds(180, 202, 105, 20);
        contentPane.add(lbWarningMissTxt_4);
        lbWarningMissTxt_4.setVisible(false);

        lbWarningMissTxt_5 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_5.setForeground(Color.RED);
        lbWarningMissTxt_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_5.setBounds(285, 242, 105, 20);
        contentPane.add(lbWarningMissTxt_5);
        lbWarningMissTxt_5.setVisible(false);

        lbWarningMissTxt_6 = new JLabel("Ce champ doit etre rempli");
        lbWarningMissTxt_6.setForeground(Color.RED);
        lbWarningMissTxt_6.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_6.setBounds(167, 282, 105, 20);
        contentPane.add(lbWarningMissTxt_6);
        lbWarningMissTxt_6.setVisible(false);

        lbWarningMissTxt_7 = new JLabel("Ce champ doit etre rempli");
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

    }

    public static FenetreModifCompte getInstance(Client _client) {
        if( instance == null ) {
            instance = new FenetreModifCompte(_client);
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionModifCompte() {
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
            if(Bibliotheque.getInstance().isDifferentUser(client.getID(),txtIdentifiant.getText())) {
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
            Bibliotheque.getInstance().modificationCompte(client.getID(),txtIdentifiant.getText(), String.valueOf(pwdMDP.getPassword()), txtPrenom.getText(), txtNom.getText(), txtAdresse.getText(), txtNumeroTelephone.getText(), txtEmail.getText());
            JOptionPane.showMessageDialog(this,"Votre compte a bien ete mis a jour","Confirmation modification",JOptionPane.PLAIN_MESSAGE);
            FenetreInscription.killInstance();
        }


    }
}
