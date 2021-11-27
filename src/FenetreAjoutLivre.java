import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FenetreAjoutLivre extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutLivre instance;

    private JTextField txtTitre;
    private JTextField txtAuteur;
    private JComboBox<String> jComboBox;
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
        this.setResizable(false);// empÃªche toutes modifications de la taille de la fenÃªtre

        String[] optionsToChoose = {"Autre", "Sport", "Histoire", "Informatique", "Geographie", "Roman"};

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
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionAjoutLivre();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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

        jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(243, 200, 150, 28);
        contentPane.add(jComboBox);

        JLabel lbResume = new JLabel("Resume :");
        lbResume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbResume.setSize(150, 20);
        lbResume.setLocation(243, 240);
        contentPane.add(lbResume);

        txtResume= new JTextArea();
        txtResume.setBounds(243, 270, 193, 100);
        txtResume.setLineWrap(true);
        txtResume.setWrapStyleWord(true);
        getContentPane().add(txtResume);
        JScrollPane scrollPane = new JScrollPane(txtResume);
        scrollPane.setBounds(243, 270, 193, 100);
        getContentPane().add(scrollPane);

        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 400, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionAjoutLivre();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
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
        lbWarningMissTxt_2.setBounds(243, 152, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);


        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
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

    private void actionAjoutLivre() throws SQLException {
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
            Bibliotheque.getInstance().ajoutlivre(txtTitre.getText(), txtAuteur.getText(), jComboBox.getSelectedItem().toString(), txtResume.getText());
            JOptionPane.showMessageDialog(this,"Le livre " + txtTitre.getText() + " a bien été ajouté","Confirmation ajout livre",JOptionPane.PLAIN_MESSAGE);
            FenetreAjoutLivre.killInstance();
        }
    }
}
