import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreAjoutLivre extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutLivre instance;

    private JTextField txtTitre;
    private JTextField txtAuteur;
    private JTextArea txtResume;

    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;


    public FenetreAjoutLivre() {

        super("Ajout livre - B'ook la bibliotheque 2.0");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                killInstance();
                dispose();
            }
        });
        this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

        String[] optionsToChoose = {"Autre", "Sport", "Histoire", "Informatique", "Geographie"};

        getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
        // centralise la fenetre et definit sa taille
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        KeyAdapter enterKeyAjoutLivre = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    actionAjoutLivre();
            }
        };

        JLabel lbTitre = new JLabel("Titre :");
        lbTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTitre.setBounds(243, 20, 150, 20);
        contentPane.add(lbTitre);

        txtTitre = new JTextField();
        txtTitre.setBounds(243, 50, 193, 28);
        getContentPane().add(txtTitre);
        txtTitre.setColumns(10);

        JLabel lbAuteur = new JLabel("Auteur :");
        lbAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbAuteur.setSize(150, 20);
        lbAuteur.setLocation(243, 100);
        contentPane.add(lbAuteur);

        txtAuteur = new JTextField();
        txtAuteur.setBounds(243, 130, 193, 28);
        getContentPane().add(txtAuteur);
        txtAuteur.setColumns(10);

        JLabel lbSujet = new JLabel("Sujet :");
        lbSujet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbSujet.setSize(150, 20);
        lbSujet.setLocation(243, 170);
        contentPane.add(lbSujet);

        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(243, 200, 150, 28);
        contentPane.add(jComboBox);

        JLabel lbResume = new JLabel("Resume :");
        lbResume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbResume.setSize(150, 20);
        lbResume.setLocation(243, 240);
        contentPane.add(lbResume);

        txtResume= new JTextArea();
        txtResume.setBounds(243, 270, 193, 100);
        getContentPane().add(txtResume);
        txtResume.setColumns(10);

        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 400, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e->actionAjoutLivre());
        btnAjout.addKeyListener(enterKeyAjoutLivre);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(340, 400, 100, 40);
        getContentPane().add(btnAnnuler);
        
        //Message d'erreur

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(243, 81, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(243, 151, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        btnAjout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(txtResume.getColumns());
                System.out.println(txtResume.getRows());
            }
        });

        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

    }

    public static FenetreAjoutLivre getInstance() {
        if( instance == null ) {
            instance = new FenetreAjoutLivre();
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionAjoutLivre(){
        JTextField[] txtFields = {txtTitre,txtAuteur};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2};
        boolean allChecked = true;

        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) {
                warningsMissTxts[i].setVisible(true);
                allChecked = false;
            }else
                warningsMissTxts[i].setVisible(false);
        }

        if (allChecked == true) {
            System.out.println("Livre ajouté");
            Livre.getInstance().inscription(txtTitre.getText(), String.valueOf(pwdMDP.getPassword()), txtPrenom.getText(), txtNom.getText(), txtAdresse.getText(), txtNumeroTelephone.getText(), txtEmail.getText());
            //JOptionPane.showMessageDialog(this,"L'utilisateur " + txtIdentifiant.getText() + " a bien été inscrit","Confirmation inscription",JOptionPane.PLAIN_MESSAGE);
        }


    }
}
