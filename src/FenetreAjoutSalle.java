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
    private JButton plus1;
    private JButton plus2;
    private JButton moins1;
    private JButton moins2;

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
        lbChaise.setBounds(243, 80, 160, 20);
        contentPane.add(lbChaise);

        moins1 = new JButton("-");
        moins1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        moins1.setBounds(243, 110, 50, 28);
        getContentPane().add(moins1);
        moins1.addActionListener(e->actionmoins1());

        txtChaise = new JTextField("0");
        txtChaise.setBounds(298, 110, 50, 28);
        getContentPane().add(txtChaise);
        txtChaise.setColumns(10);
        txtChaise.setEditable(false);

        plus1 = new JButton("+");
        plus1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        plus1.setBounds(353, 110, 50, 28);
        getContentPane().add(plus1);
        plus1.addActionListener(e->actionplus1());

        JLabel lbTable = new JLabel("Nombre de table : ");
        lbTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTable.setSize(150, 20);
        lbTable.setLocation(243, 160);
        contentPane.add(lbTable);

        moins2 = new JButton("-");
        moins2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        moins2.setBounds(243, 190, 50, 28);
        getContentPane().add(moins2);
        moins2.addActionListener(e->actionmoins2());

        txtTable = new JTextField("0");
        txtTable.setBounds(298, 190, 50, 28);
        getContentPane().add(txtTable);
        txtTable.setColumns(10);
        txtTable.setEditable(false);

        plus2 = new JButton("+");
        plus2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        plus2.setBounds(353, 190, 50, 28);
        getContentPane().add(plus2);
        plus2.addActionListener(e->actionplus2());

        JLabel lbTaille = new JLabel("Taille : ");
        lbTaille.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTaille.setSize(150, 20);
        lbTaille.setLocation(243, 250);
        contentPane.add(lbTaille);

        txtTaille= new JTextField("");
        txtTaille.setBounds(243, 280, 193, 28);
        getContentPane().add(txtTaille);
        txtTaille.setColumns(10);

        proj = new JCheckBox("Avec un projecteur");
        proj.setBounds(243, 335, 136, 23);
        getContentPane().add(proj);



        //boutons

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 370, 100, 40);
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
        btnAnnuler.setBounds(340, 370, 100, 40);
        getContentPane().add(btnAnnuler);

        //Message d'erreur

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(243, 355, 105, 20);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                killInstance();
            }
        });

    }

    private void actionmoins1() {
        int chaise = Integer.parseInt(txtChaise.getText());
        if (chaise > 0) {
            chaise -= 1;
            txtChaise.setText("" + chaise);
        }else{txtChaise.setText("0");}
    }

    private void actionplus1() {
        int chaise = Integer.parseInt(txtChaise.getText());
        chaise += 1;
        txtChaise.setText(""+chaise);
    }

    private void actionmoins2() {
        int table = Integer.parseInt(txtTable.getText());
        if (table > 0) {
            table -= 1;
            txtTable.setText("" + table);
        }else{txtTable.setText("0");}
    }

    private void actionplus2() {
        int table = Integer.parseInt(txtTable.getText());
        table += 1;
        txtTable.setText(""+table);
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
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1};
        boolean allChecked = true;
        int projecteur;

        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) {
                warningsMissTxts[i].setVisible(true);
                allChecked = false;
            }else
                warningsMissTxts[i].setVisible(false);
        }

        if (allChecked == true) {
            if (proj.isSelected())
                projecteur = 0;
            else
                projecteur = 1;

            Bibliotheque.getInstance().ajoutsalle(Integer.parseInt(txtChaise.getText()),Integer.parseInt(txtTable.getText()),projecteur,Integer.parseInt(txtTaille.getText()));
            JOptionPane.showMessageDialog(this,"La salle a bien été ajouté","Confirmation ajout salle",JOptionPane.PLAIN_MESSAGE);
            txtTaille.setText("");
            txtChaise.setText("0");
            txtTable.setText("0");
        }
    }
}

