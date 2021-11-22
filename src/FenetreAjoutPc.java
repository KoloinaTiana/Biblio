import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FenetreAjoutPc extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutPc instance;

    private JTextField txtMarque;
    private JTextField txtSn;

    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;
    private JLabel lbWarningErrorTxt;

    public FenetreAjoutPc() {

        super("Ajout PC - B'ook la bibliotheque 2.0");
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

        KeyAdapter enterKeyAjoutPc = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionAjoutPc();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };

        JLabel lbTitre = new JLabel("Marque :");
        lbTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTitre.setBounds(243, 120, 150, 20);
        contentPane.add(lbTitre);

        txtMarque = new JTextField();
        txtMarque.setBounds(243, 150, 193, 28);
        getContentPane().add(txtMarque);
        txtMarque.setColumns(10);

        JLabel lbSn = new JLabel("Numéro de série : (15 caractères)");
        lbSn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbSn.setSize(150, 20);
        lbSn.setLocation(243, 200);
        contentPane.add(lbSn);

        txtSn = new JTextField();
        txtSn.setBounds(243, 230, 193, 28);
        getContentPane().add(txtSn);
        txtSn.setColumns(10);



        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 350, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionAjoutPc();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyAjoutPc);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(340, 350, 100, 40);
        getContentPane().add(btnAnnuler);

        //Message d'erreur

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(243, 181, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(243, 261, 105, 20);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        lbWarningErrorTxt = new JLabel("Doit étre inférieur à  15 caractères !");
        lbWarningErrorTxt.setForeground(Color.RED);
        lbWarningErrorTxt.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningErrorTxt.setBounds(243, 261, 105, 20);
        contentPane.add(lbWarningErrorTxt);
        lbWarningErrorTxt.setVisible(false);


        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });

    }

    public static FenetreAjoutPc getInstance() {
        if( instance == null ) {
            instance = new FenetreAjoutPc();
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionAjoutPc() throws SQLException {
        JTextField[] txtFields = {txtMarque,txtSn};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2};
        boolean allChecked = true;

        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) {
                warningsMissTxts[i].setVisible(true);
                allChecked = false;
            }else
                warningsMissTxts[i].setVisible(false);
        }

        if(txtSn.getText().length() > 15) {
            lbWarningErrorTxt.setVisible(true);
            allChecked = false;
        }else
            lbWarningErrorTxt.setVisible(false);

        if (allChecked == true) {
            Bibliotheque.getInstance().ajoutpc(txtMarque.getText(), txtSn.getText());
            JOptionPane.showMessageDialog(this,"Le pc " + txtSn.getText() + " a bien été ajouté","Confirmation ajout pc",JOptionPane.PLAIN_MESSAGE);
            FenetreAjoutPc.killInstance();
        }
    }
}