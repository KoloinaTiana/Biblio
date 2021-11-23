import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FenetreDeleteUser extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreDeleteUser instance;

    private JTextField txtIdentifiant;

    private JLabel lbWarningMissTxt_0;
    private JLabel lbWarningUserNotFound;
    private  boolean exist = false;

    public FenetreDeleteUser() {

        super("Supprimer un utilisateur - B'ook la bibliotheque 2.0");
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

        KeyAdapter enterKeyDeleteUser= new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionDeleteUser();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };

        JLabel lbIdentifiant = new JLabel("Identifiant du client à supprimer :");
        lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbIdentifiant.setBounds(220, 150, 280, 20);
        contentPane.add(lbIdentifiant);

        txtIdentifiant = new JTextField();
        txtIdentifiant.setBounds(220, 230, 280, 35);
        contentPane.add(txtIdentifiant);
        txtIdentifiant.setColumns(10);

        //boutons

        JButton btnAjout = new JButton("Supprimer");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(220, 350, 120, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionFind();
                actionDeleteUser();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyDeleteUser);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(380, 350, 120, 40);
        getContentPane().add(btnAnnuler);

        //Message d'erreur

        lbWarningMissTxt_0 = new JLabel("Choisir un client à supprimer !");
        lbWarningMissTxt_0.setForeground(Color.RED);
        lbWarningMissTxt_0.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_0.setBounds(220, 280, 280, 20);
        contentPane.add(lbWarningMissTxt_0);
        lbWarningMissTxt_0.setVisible(false);

        lbWarningUserNotFound = new JLabel("Cet utilisateur n'existe pas");
        lbWarningUserNotFound.setForeground(Color.RED);
        lbWarningUserNotFound.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningUserNotFound.setBounds(220, 280, 280, 20);
        contentPane.add(lbWarningUserNotFound);
        lbWarningUserNotFound.setVisible(false);


        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });

    }

    private void actionFind() {
        lbWarningUserNotFound.setVisible(false);
        if(txtIdentifiant.getText().length() == 0) {
            lbWarningMissTxt_0.setVisible(true);
        }else {
            lbWarningMissTxt_0.setVisible(false);
            try {
                Client c = Bibliotheque.getInstance().trouverUtilisateur(txtIdentifiant.getText());
                if (c != null) {
                    System.out.println("client : " + c.identifiant);
                    exist = true;
                }else{
                    lbWarningUserNotFound.setVisible(true);
                }

            } catch (UserNotFoundException | SQLException e1) {
                System.out.println(e1);
                lbWarningUserNotFound.setVisible(true);
            }
        }
    }

    public static FenetreDeleteUser getInstance() {
        if( instance == null ) {
            instance = new FenetreDeleteUser();
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionDeleteUser() throws SQLException {
        JTextField[] txtFields = {txtIdentifiant};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_0};
        boolean allChecked = true;
            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getText().length() == 0) {
                    warningsMissTxts[i].setVisible(true);
                    allChecked = false;
                } else
                    warningsMissTxts[i].setVisible(false);
            }
            if (allChecked == true && exist == true) {
                Bibliotheque.getInstance().deleteUser(txtIdentifiant.getText());
                JOptionPane.showMessageDialog(this, "Utilisateur " + txtIdentifiant.getText() + " a bien été supprimé", "Confirmation suppression", JOptionPane.PLAIN_MESSAGE);
                FenetreDeleteUser.killInstance();
            }
    }
}
