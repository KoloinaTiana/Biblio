
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FenetreAjoutJournal extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutJournal instance;

    private JTextField txtEditorial;
    private JTextField txtDate;
    private JTextField txtNom;

    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;
    private JLabel lbWarningMissTxt_3;
    

    public FenetreAjoutJournal()   {

    //Debut caracteristique de la fenetre

        super("Ajout Journal - B'ook la bibliotheque 2.0");
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

        KeyAdapter enterKeyAjoutJournal = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionAjoutJournal();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };
    //Debut caracteristique de la fenetre

    //Debut entree editorial du journal
        JLabel lbEditorial = new JLabel("Editorial :");
        lbEditorial.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbEditorial.setBounds(243, 20, 150, 20);
        contentPane.add(lbEditorial);

        txtEditorial = new JTextField();
        txtEditorial.setBounds(243, 50, 193, 28);
        getContentPane().add(txtEditorial);
        txtEditorial.setColumns(10);
    //Fin entree editorial du journal

    //Debut entree date du journal
        JLabel lbDate = new JLabel("Date :");
        lbDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbDate.setSize(150, 20);
        lbDate.setLocation(243, 100);
        contentPane.add(lbDate);

        txtDate = new JTextField("YYYY-MM-DD");
        txtDate.setBounds(243, 130, 193, 28);
        getContentPane().add(txtDate);
        txtDate.setColumns(10);
    //Fin entree date du journal

    //Debut entree nom du journal
        JLabel lbNom = new JLabel("Nom du journal:");
        lbNom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNom.setSize(150, 20);
        lbNom.setLocation(243, 170);
        contentPane.add(lbNom);
        
        txtNom = new JTextField();
        txtNom.setBounds(243, 200, 193, 28);
        getContentPane().add(txtNom);
        txtNom.setColumns(10);
    //Fin entree nom du journal
       
    //Debut boutons
        //bouton ajout d'un journal
        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 400, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionAjoutJournal();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyAjoutJournal);

        //bouton annuler l'ajout d'un journal
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(340, 400, 100, 40);
        getContentPane().add(btnAnnuler);
        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });
    //Fin boutons

    //Debut messages d'erreur
        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(243, 81, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(243, 152, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        lbWarningMissTxt_3 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_3.setForeground(Color.RED);
        lbWarningMissTxt_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_3.setBounds(243, 223, 110, 20);
        contentPane.add(lbWarningMissTxt_3);
        lbWarningMissTxt_3.setVisible(false);
    //Fin messages d'erreur

    }

    //Creation d'une instance d'ajout de journal
    public static FenetreAjoutJournal getInstance() {
        if( instance == null ) {
            instance = new FenetreAjoutJournal();
        }
        return instance;
    }

    //Fonction qui detruit l'instance
    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    //Verification des champs d'entrees et appel de l'insertion du journal dans la bdd
    private void actionAjoutJournal() throws SQLException {
        JTextField[] txtFields = {txtEditorial,txtDate,txtNom};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2,lbWarningMissTxt_3};
        boolean allChecked = true;

        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) { //si le(s) champ(s) sont/est vide(s)
                warningsMissTxts[i].setVisible(true); //montre le(s) message(s) d'erreur(s)
                allChecked = false;
            }else {
                warningsMissTxts[i].setVisible(false);
                if (txtFields[1].getText().equals("YYYY-MM-DD")) { //verifie si le champ date est sa valeur initiale
                    warningsMissTxts[1].setVisible(true); //affiche le message d'erreur
                }
            }
        }

    if (allChecked == true) { //si tous les champs sont ok
            Bibliotheque.getInstance().ajoutJournal(txtEditorial.getText(), txtDate.getText(), txtNom.getText());
            JOptionPane.showMessageDialog(this,"La Journal " + txtEditorial.getText() + " a bien ete ajoute","Confirmation ajout Jourrnal",JOptionPane.PLAIN_MESSAGE);
            FenetreAjoutJournal.killInstance(); //detruit l'instance de la fentre d'ajout de journal
        }
    }
}

	


