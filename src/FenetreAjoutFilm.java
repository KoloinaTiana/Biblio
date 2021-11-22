import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;

public class FenetreAjoutFilm extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutFilm instance;

    private JTextField txtTitre;
    private JTextField txtRealisateur;
    private JComboBox<String> jComboBox;

    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;


    public FenetreAjoutFilm() {

        super("Ajout Film - B'ook la bibliotheque 2.0");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                killInstance();
                dispose();
            }
        });
        this.setResizable(false);// empÃªche toutes modifications de la taille de la fenÃªtre

        String[] optionsToChoose = {"2021", "2020",
                "2019", "2018", "2017", "2016","2015", "2014", "2013", "2012", "2011", "2010",
                "2009", "2008", "2007", "2006","2005", "2004", "2003", "2002", "2001", "2000",
                "1999", "1998", "1997", "1996","1995", "1994", "1993", "1992", "1991", "1990",
                "1989", "1988", "1987", "1986","1985", "1984", "1983", "1982", "1981", "1980",
                "1979", "1978", "1977", "1976","1975", "1974", "1973", "1972", "1971", "1970",
        };

        getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
        // centralise la fenetre et definit sa taille
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        KeyAdapter enterKeyAjoutFilm = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionAjoutFilm();
                    } catch (SQLException | ParseException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };

        JLabel lbTitre = new JLabel("Titre :");
        lbTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTitre.setBounds(243, 50, 150, 20);
        contentPane.add(lbTitre);

        txtTitre = new JTextField();
        txtTitre.setBounds(243, 100, 193, 28);
        getContentPane().add(txtTitre);
        txtTitre.setColumns(10);

        JLabel lbAuteur = new JLabel("Directeur :");
        lbAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbAuteur.setSize(150, 20);
        lbAuteur.setLocation(243, 150);
        contentPane.add(lbAuteur);

        txtRealisateur = new JTextField();
        txtRealisateur.setBounds(243, 200, 193, 28);
        getContentPane().add(txtRealisateur);
        txtRealisateur.setColumns(10);

        JLabel lbSujet = new JLabel("Annee de sortie :");
        lbSujet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbSujet.setSize(150, 20);
        lbSujet.setLocation(243, 250);
        contentPane.add(lbSujet);

        jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(243, 300, 150, 28);
        contentPane.add(jComboBox);

        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 380, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionAjoutFilm();
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyAjoutFilm);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(340, 380, 100, 40);
        getContentPane().add(btnAnnuler);

        //Message d'erreur

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(243, 130, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(243, 230, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);


        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });

    }

    public static FenetreAjoutFilm getInstance() {
        if( instance == null ) {
            instance = new FenetreAjoutFilm();
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionAjoutFilm() throws SQLException, ParseException {
        JTextField[] txtFields = {txtTitre,txtRealisateur};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2};
        boolean allChecked = true;
        int date = Integer.parseInt(jComboBox.getSelectedItem().toString());
        Year y = Year.of(date);

        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) {
                warningsMissTxts[i].setVisible(true);
                allChecked = false;
            }else
                warningsMissTxts[i].setVisible(false);
        }

        if (allChecked == true) {
            Bibliotheque.getInstance().ajoutFilm(txtTitre.getText(), txtRealisateur.getText(), y);
            JOptionPane.showMessageDialog(this,"Le film " + txtTitre.getText() + " a bien été ajouté","Confirmation ajout Film",JOptionPane.PLAIN_MESSAGE);
            FenetreAjoutFilm.killInstance();
        }
    }
}
