import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FenetreAjoutSalle extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

    private static FenetreAjoutSalle instance;

    private JTextField txtChaise;
    private JTextField txtTable;
    private JTextField txtTaille;
    private JCheckBox proj;

    private JLabel lbWarningMissTxt_1;
    private JLabel lbWarningMissTxt_2;

    public FenetreAjoutSalle() {

        super("Ajout Salle - B'ook la bibliotheque 2.0");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                killInstance();
                dispose();
            }
        });
        this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

        String[] optionsToChoose = {"Autre", "Sport", "Histoire", "Informatique", "Geographie", "Roman"};

        getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
        // centralise la fenetre et definit sa taille
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        KeyAdapter enterKeyAjoutSalle = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        actionAjoutSalle();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };

        JLabel lbChaise = new JLabel("Nombre de chaise :");
        lbChaise.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbChaise.setBounds(243, 80, 150, 20);
        contentPane.add(lbChaise);

        txtChaise = new JTextField();
        txtChaise.setBounds(243, 110, 193, 28);
        getContentPane().add(txtChaise);
        txtChaise.setColumns(10);

        JLabel lbTable = new JLabel("Nombre de table : ");
        lbTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTable.setSize(150, 20);
        lbTable.setLocation(243, 180);
        contentPane.add(lbTable);

        txtTable = new JTextField();
        txtTable.setBounds(243, 210, 193, 28);
        getContentPane().add(txtTable);
        txtTable.setColumns(10);

        JLabel lbTaille = new JLabel("Taille : ");
        lbTaille.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTaille.setSize(150, 20);
        lbTaille.setLocation(243, 280);
        contentPane.add(lbTaille);

        txtTable = new JTextField();
        txtTable.setBounds(243, 310, 193, 28);
        getContentPane().add(txtTable);
        txtTable.setColumns(10);



        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 350, 100, 40);
        getContentPane().add(btnAjout);
        btnAjout.addActionListener(e-> {
            try {
                actionAjoutSalle();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnAjout.addKeyListener(enterKeyAjoutSalle);


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

        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });

    }

    public static FenetreAjoutSalle getInstance() {
        if( instance == null ) {
            instance = new FenetreAjoutSalle();
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionAjoutSalle() throws SQLException {
        JTextField[] txtFields = {txtTaille};
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
            //Bibliotheque.getInstance().ajoutsalle(txtChaise.getText(),txtTable.getText(),txtTaille.getText(), txtTaille.getText());
            JOptionPane.showMessageDialog(this,"La salle a bien été ajouté","Confirmation ajout salle",JOptionPane.PLAIN_MESSAGE);
            txtTaille.setText("");
            txtChaise.setText("0");
            txtTable.setText("0");
        }
    }
}

