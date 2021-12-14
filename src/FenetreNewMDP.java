import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;


public class FenetreNewMDP extends JFrame{

    private static final long serialVersionUID = 5983062096580235299L;

    private static FenetreNewMDP instance;
    private JPasswordField pwdNouveauMDP;
    private JPasswordField pwdNouveauMDPComfirm;
    private JTextField CodeVerif;
    private JLabel lbWarningMDPconfirm, lbWarningMissTxt_1, lbWarningMissTxt_2, lbWarningIncorrectCode;

    private String m;

    /**  initialise la fenetre (design, taille , bouton etc...)
    * @param _m mot de passe
     * @return  fenetre
     */
    
    private FenetreNewMDP(String _m) {
        super("Nouveau mot de passe - B'ook la bibliotheque 2.0");
        this.m = _m;
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                killInstance();
                dispose();
            }
        });
        this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

        //definit sa taille
        this.setSize(600, 500);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        JLabel lblOublieMDP = new JLabel("NOUVEAU MOT DE PASSE");
        lblOublieMDP.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblOublieMDP.setBounds(68, 32, 500, 47);
        getContentPane().add(lblOublieMDP);

        JLabel lbCode = new JLabel("Code de verification :");
        lbCode.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbCode.setBounds(88, 125, 200, 24);
        getContentPane().add(lbCode);

        CodeVerif = new JTextField();
        CodeVerif.setBounds(295, 125, 100, 28);
        getContentPane().add(CodeVerif);

        JLabel lbNouveauMDP = new JLabel("Nouveau mot de passe :");
        lbNouveauMDP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNouveauMDP.setBounds(88, 200, 190, 24);
        getContentPane().add(lbNouveauMDP);

        pwdNouveauMDP = new JPasswordField();
        pwdNouveauMDP.setBounds(295, 200, 200, 28);
        getContentPane().add(pwdNouveauMDP);

        JLabel lbNouveauMDPConfirm = new JLabel("Confirmer nouveau mot de passe :");
        lbNouveauMDPConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbNouveauMDPConfirm.setBounds(88, 275, 272, 24);
        getContentPane().add(lbNouveauMDPConfirm);

        pwdNouveauMDPComfirm = new JPasswordField();
        pwdNouveauMDPComfirm.setBounds(376, 275, 200, 28);
        getContentPane().add(pwdNouveauMDPComfirm);

        lbWarningMDPconfirm = new JLabel("Les mots de passe ne correspondent pas");
        lbWarningMDPconfirm.setForeground(Color.RED);
        lbWarningMDPconfirm.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMDPconfirm.setBounds(376, 300, 200, 28);
        contentPane.add(lbWarningMDPconfirm);
        lbWarningMDPconfirm.setVisible(false);

        lbWarningMissTxt_1 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_1.setForeground(Color.RED);
        lbWarningMissTxt_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_1.setBounds(295, 150, 200, 28);
        contentPane.add(lbWarningMissTxt_1);
        lbWarningMissTxt_1.setVisible(false);

        lbWarningMissTxt_2 = new JLabel("Ce champ est obligatoire !");
        lbWarningMissTxt_2.setForeground(Color.RED);
        lbWarningMissTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMissTxt_2.setBounds(295, 225, 200, 28);
        contentPane.add(lbWarningMissTxt_2);
        lbWarningMissTxt_2.setVisible(false);

        lbWarningIncorrectCode = new JLabel("Code de verification incorrect");
        lbWarningIncorrectCode.setForeground(Color.RED);
        lbWarningIncorrectCode.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningIncorrectCode.setBounds(295, 150, 200, 28);
        contentPane.add(lbWarningIncorrectCode);
        lbWarningIncorrectCode.setVisible(false);

        JButton btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnConfirmer.setBounds(164, 396, 273, 46);
        getContentPane().add(btnConfirmer);
        btnConfirmer.addActionListener(e-> {
            try {
                actionNewMDP();
            } catch (IncorrectCodeException incorrectCodeException) {
                incorrectCodeException.printStackTrace();
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 90, 600, 20);
        getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 373, 600, 20);
        getContentPane().add(separator_1);

        JCheckBox showPasswordCheckbox = new JCheckBox("Afficher mots de passe");
        showPasswordCheckbox.setBounds(88, 325, 250, 23);
        getContentPane().add(showPasswordCheckbox);

        showPasswordCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    pwdNouveauMDP.setEchoChar((char) 0);
                    pwdNouveauMDPComfirm.setEchoChar((char) 0);
                } else {
                    pwdNouveauMDP.setEchoChar('*');
                    pwdNouveauMDPComfirm.setEchoChar('*');
                }
            }
        });
    }
     /**
     * Renvoie l'instance de la FenetreNewMDP si elle existe ou en crée une si elle n'existe pas
     * @param m mot de passe
     * @return instance du singleton FenetreNewMDP
     */

    public static FenetreNewMDP getInstance(String m) {

        if( instance == null ) {
            instance = new FenetreNewMDP(m);
        }
        return instance;
    }

    /**
     * Supprime l'instance de la FenetreNewMdp et ferme la fenetre à l'ecran
     */
    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    /**
    * Modifie le mot de passe d'un client suivant les informations entrées dans la fenetre
    */
    private void actionNewMDP() throws IncorrectCodeException {
        lbWarningMDPconfirm.setVisible(false);
        lbWarningIncorrectCode.setVisible(false);
        lbWarningMissTxt_1.setVisible(false);
        lbWarningMissTxt_2.setVisible(false);

        JTextField[] txtFields = {CodeVerif,pwdNouveauMDP};
        JLabel[] warningsMissTxts = {lbWarningMissTxt_1,lbWarningMissTxt_2};

        boolean allChecked = true;
        for(int i = 0; i<txtFields.length; i++) {
            if(txtFields[i].getText().length() == 0) { //si le(s) champ(s) sont/est vide(s)
                warningsMissTxts[i].setVisible(true); //montre le(s) message(s) d'erreur
                allChecked = false;
            }else {
                warningsMissTxts[i].setVisible(false);
                if(!String.valueOf(pwdNouveauMDP.getPassword()).equals(String.valueOf(pwdNouveauMDPComfirm.getPassword()))) {
                    lbWarningMDPconfirm.setVisible(true);
                    allChecked = false;
                }else {
                    lbWarningMDPconfirm.setVisible(false);
                    if(CodeVerif.getText().length() > 0) {
                        try {
                            String c = Bibliotheque.getInstance().findCode(this.m, Integer.parseInt(CodeVerif.getText()));
                            if (c == "OK") {
                                lbWarningIncorrectCode.setVisible(false);
                                allChecked = true;
                            } else {
                                lbWarningIncorrectCode.setVisible(true);
                                allChecked = false;
                            }
                        } catch (IncorrectCodeException e) {
                            lbWarningIncorrectCode.setVisible(true);
                            throw new IncorrectCodeException();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }

                }
            }
        }


        if (allChecked == true) {
            Bibliotheque.getInstance().newMDP(this.m, String.valueOf(pwdNouveauMDP.getPassword()));
            JOptionPane.showMessageDialog(this,"Le mot de passe a ete reinitialise","Confirmation reinitilisation mot de passe",JOptionPane.PLAIN_MESSAGE);
            FenetreNewMDP.killInstance(); //Detruit l'instance
        }

    }
}
