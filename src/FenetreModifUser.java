import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FenetreModifUser extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreModifUser instance;

    private JTextField txtIdentifiant;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtTelephone;
    private JTextField txtAdresse;
    private JTextField txtEmail;

    private JLabel lbWarningMissTxt_0;
    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;
    private JLabel lbWarningMissTxt_3;
    private JLabel lbWarningMissTxt_4;
    private JLabel lbWarningMissTxt_5;
    private JLabel lbWarningUserNotFound;

    public FenetreModifUser() {

    //Debut caracteristiques de la fenetre

        super("Modifier un utilisateur - B'ook la bibliotheque 2.0");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                killInstance();
                dispose();
            }
        });
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre

        getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
        // centralise la fenetre et definit sa taille
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        KeyAdapter enterKeyModifUser= new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionModifUser();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };
    //Fin caracteristiques de la fenetre

    //Debut entree identifiant
        JLabel lbIdentifiant = new JLabel("Identifiant du client à modifier :");
        lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbIdentifiant.setBounds(38, 50, 250, 20);
        contentPane.add(lbIdentifiant);

        txtIdentifiant = new JTextField();
        txtIdentifiant.setBounds(350, 48, 130, 28);
        contentPane.add(txtIdentifiant);
        txtIdentifiant.setColumns(10);

        //Bouton qui lance la recherche de l'utilisateur à modifier
        JButton btnFind = new JButton("Rechercher");
        btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnFind.setBounds(500, 48, 120, 28);
        getContentPane().add(btnFind);
        btnFind.addActionListener(e-> {
            actionFind();
        });
    //Fin entree identifiant

    //Debut entree prenom
        JLabel lbPrenom = new JLabel("Prenom :");
        lbPrenom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbPrenom.setBounds(88, 140, 73, 20);
        contentPane.add(lbPrenom);

        txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(178, 138, 100, 28);
        contentPane.add(txtPrenom);
    //Fin entree prenom

    //Debut entree nom
        JLabel lbNom = new JLabel("Nom :");
        lbNom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNom.setBounds(312, 140, 51, 20);
        contentPane.add(lbNom);

        txtNom = new JTextField();
        txtNom.setColumns(10);
        txtNom.setBounds(380, 138, 100, 28);
        contentPane.add(txtNom);
    //Fin entree nom

    //Debut entree adresse
        JLabel lbAdresse = new JLabel("Adresse :");
        lbAdresse.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbAdresse.setBounds(88, 180, 76, 20);
        contentPane.add(lbAdresse);

        txtAdresse = new JTextField();
        txtAdresse.setColumns(10);
        txtAdresse.setBounds(183, 178, 200, 28);
        contentPane.add(txtAdresse);
    //Fin entree adresse

    //Debut entree numero de telephone
        JLabel lbNumeroTelephone = new JLabel("Numero de telephone :");
        lbNumeroTelephone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNumeroTelephone.setBounds(88, 220, 181, 20);
        contentPane.add(lbNumeroTelephone);

        txtTelephone = new JTextField();
        txtTelephone.setColumns(10);
        txtTelephone.setBounds(288, 218, 120, 28);
        contentPane.add(txtTelephone);
    //Fin entree numero de telephone

    //Debut entree email
        JLabel lbEmail = new JLabel("E-mail :");
        lbEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbEmail.setBounds(88, 260, 63, 20);
        contentPane.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(170, 258, 190, 28);
        contentPane.add(txtEmail);
    //Fin entree email

    //Debut des boutons

        //Bouton enregistrer la modification
        JButton btnAjout = new JButton("Enregistrer");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 350, 120, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionModifUser();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyModifUser);

        //Bouton annuler la modification
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(400, 350, 120, 40);
        getContentPane().add(btnAnnuler);
        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });
    //Fin des boutons

    //Debut messages d'erreur
        lbWarningMissTxt_0 = new JLabel("Choisir un client à modifier !");
        lbWarningMissTxt_0.setForeground(Color.RED);
        lbWarningMissTxt_0.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_0.setBounds(350, 78, 115, 20);
        contentPane.add(lbWarningMissTxt_0);
        lbWarningMissTxt_0.setVisible(false);

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(178, 162, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(380, 162, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        lbWarningMissTxt_3 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_3.setForeground(Color.RED);
        lbWarningMissTxt_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_3.setBounds(183, 202, 105, 20);
        contentPane.add(lbWarningMissTxt_3);
        lbWarningMissTxt_3.setVisible(false);

        lbWarningMissTxt_4 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_4.setForeground(Color.RED);
        lbWarningMissTxt_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_4.setBounds(288, 242, 105, 20);
        contentPane.add(lbWarningMissTxt_4);
        lbWarningMissTxt_4.setVisible(false);

        lbWarningMissTxt_5 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_5.setForeground(Color.RED);
        lbWarningMissTxt_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_5.setBounds(170, 282, 105, 20);
        contentPane.add(lbWarningMissTxt_5);
        lbWarningMissTxt_5.setVisible(false);

        lbWarningUserNotFound = new JLabel("Cet utilisateur n'existe pas");
        lbWarningUserNotFound.setForeground(Color.RED);
        lbWarningUserNotFound.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningUserNotFound.setBounds(350, 78, 115, 20);
        contentPane.add(lbWarningUserNotFound);
        lbWarningUserNotFound.setVisible(false);

    //Fin messages d'erreur
    }

    //Bouton qui recherche le client dans la bdd
    private void actionFind() {
        lbWarningUserNotFound.setVisible(false);
        if(txtIdentifiant.getText().length() == 0) { //champ identifiant vide
            lbWarningMissTxt_0.setVisible(true); //montre le message d'erreur
        }else {
            lbWarningMissTxt_0.setVisible(false);
            try {
                Client c = Bibliotheque.getInstance().trouverUtilisateur(txtIdentifiant.getText());
                if (c != null) { //utilisateur existant
                    System.out.println("client : " + c.identifiant);

                    //remplis les champs par rapport au client entré
                    txtNom.setText(c.nom);
                    txtPrenom.setText(c.prenom);
                    txtAdresse.setText(c.adresse);
                    txtTelephone.setText(c.numeroTelephone);
                    txtEmail.setText(c.eMail);
                }else{
                    lbWarningUserNotFound.setVisible(true);
                }

            } catch (UserNotFoundException | SQLException e1) {
                System.out.println(e1);
                lbWarningUserNotFound.setVisible(true);
            }
        }
    }

    //Creation de l'instance modification utilisateur
    public static FenetreModifUser getInstance() {
        if( instance == null ) {
            instance = new FenetreModifUser();
        }
        return instance;
    }

    //Destruction de l'instance
    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    //Verification du champ et appel modification de l'utilisateur dans la bdd
    private void actionModifUser() throws SQLException {
        JTextField[] txtFields = {txtNom,txtPrenom,txtAdresse,txtTelephone,txtEmail};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2,lbWarningMissTxt_3,lbWarningMissTxt_4,lbWarningMissTxt_5};
        boolean allChecked = true;
        lbWarningMissTxt_0.setVisible(false);

        if (txtIdentifiant.getText().length() > 0) { //si identifiant non vide

            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getText().length() == 0) { //si le(s) champ(s) sont/est vide(s)
                    warningsMissTxts[i].setVisible(true); //montre le(s) message(s) d'erreur
                    allChecked = false;
                } else
                    warningsMissTxts[i].setVisible(false);
            }

            if (allChecked == true) { //si tous les champs sont ok
                Bibliotheque.getInstance().modifUser(txtIdentifiant.getText(), txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtTelephone.getText(), txtEmail.getText());
                JOptionPane.showMessageDialog(this, "Utilisateur " + txtIdentifiant.getText() + " a bien ete modifie", "Confirmation modification", JOptionPane.PLAIN_MESSAGE);
                FenetreModifUser.killInstance(); //Detruit l'instance
            }
        }else{
            lbWarningMissTxt_0.setVisible(true); //montre le message d'erreur
        }
    }
}