import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class FenetreOublieMDP extends JFrame {

    private static final long serialVersionUID = 5983062096580235299L;

    private static FenetreOublieMDP instance;
    private JTextField txtEmail;

    private JLabel lbWarningMailNotFound;
    private JLabel lbWarningMissingText;

    private Connection connexion;

    /**
     * Constructeur de l'instance FenetreOublieMDP, initialise la fenetre (design, taille , bouton etc...) 
     */
    private FenetreOublieMDP() {
        super("Mot de passe oublie - B'ook la bibliotheque 2.0");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
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

        JLabel lblOublieMDP = new JLabel("MOT DE PASSE OUBLI\u00C9");
        lblOublieMDP.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblOublieMDP.setBounds(68, 32, 464, 47);
        getContentPane().add(lblOublieMDP);

        JButton btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnConfirmer.setBounds(164, 396, 273, 46);
        getContentPane().add(btnConfirmer);
        btnConfirmer.addActionListener(e -> {
            try {
                actionEnvoyerMail();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 90, 600, 20);
        getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 373, 600, 20);
        getContentPane().add(separator_1);

        JLabel lbEmail = new JLabel("Entrez votre adresse email :");
        lbEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbEmail.setBounds(198, 190, 250, 30);
        getContentPane().add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(198, 240, 250, 30);
        getContentPane().add(txtEmail);
        txtEmail.setColumns(10);


        lbWarningMissingText = new JLabel("Ce champ est obligatoire !");
        lbWarningMissingText.setBounds(198, 280, 250, 30);
        lbWarningMissingText.setForeground(Color.RED);
        lbWarningMissingText.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        getContentPane().add(lbWarningMissingText);
        lbWarningMissingText.setVisible(false);

        lbWarningMailNotFound = new JLabel("Aucun compte associe a cette adresse email");
        lbWarningMailNotFound.setForeground(Color.RED);
        lbWarningMailNotFound.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lbWarningMailNotFound.setBounds(198, 280, 300, 30);
        getContentPane().add(lbWarningMailNotFound);
        lbWarningMailNotFound.setVisible(false);
    }

    /**
     * Renvoie l'instance de la FenetreOublieMDP si elle existe ou en crée une si elle n'existe pas
     * @return instance du singleton FenetreOublieMDP
     */
    public static FenetreOublieMDP getInstance() {
        if (instance == null) {
            instance = new FenetreOublieMDP();
        }
        return instance;
    }

    /**
     * Supprime l'instance de la FenetreOublieMDP et ferme la fenetre à l'ecran
     */
    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    /**
     * Permet d'envoyer un mail à un client suivant les informations entrées dans la fenetre
     * @throws SQLException
     */
    private void actionEnvoyerMail() throws SQLException {

        lbWarningMailNotFound.setVisible(false);
        lbWarningMissingText.setVisible(false);

        boolean allChecked = true;
        if (txtEmail.getText().length() == 0) { //si le(s) champ(s) sont/est vide(s)
            lbWarningMissingText.setVisible(true); //montre le(s) message(s) d'erreur
            allChecked = false;
        } else {
            try {
                String c = Bibliotheque.getInstance().findMail(txtEmail.getText());
                if (c == "OK") {
                    lbWarningMailNotFound.setVisible(false);
                } else {
                    lbWarningMailNotFound.setVisible(true);
                    allChecked = false;
                }

            } catch (MailNotFoundException e1) {
                System.out.println(e1);
                lbWarningMailNotFound.setVisible(true);
                allChecked = false;

            }
        }

        if (allChecked == true) { //si les champs sont ok
            int[] array = new int[4];
            int min = 1;
            int max = 9;
            for (int i = 0; i < 3; i++) {
                int getRandomValue = (int) (Math.random() * (max - min)) + min;
                array[i] = getRandomValue;
            }

            String code = "" + array[0] + "" + array[1] + "" + array[2] + "" + array[3];

            String requete = "UPDATE `client` SET `Code` ='" + Integer.parseInt(code) + "' WHERE `Email` ='" + txtEmail.getText() + "'";
            try {
                connexion = Database.connexion();
                Statement stmt = connexion.createStatement();
                int nbMaj = stmt.executeUpdate(requete);
                System.out.println(nbMaj + " code modifie");
            } catch (SQLException e) {
                System.out.println(e);
                throw new SQLException();
            }

            // Recipient's email ID needs to be mentioned.
            String to = txtEmail.getText();

            // Sender's email ID needs to be mentioned
            String from = "book.biblio.project@gmail.com";

            // Assuming you are sending email from localhost
            String host = "smtp.gmail.com";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            // Get the Session object.// and pass username and password
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("book.biblio.project@gmail.com", "Book2021");

                }

            });

            // Used to debug SMTP issues
            session.setDebug(true);

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("Code Mot de passe");

                // Now set the actual message
                message.setText("Votre code de verification est : "+code);

                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");

                JOptionPane.showMessageDialog(this,"Un code de verification a ete envoye a : " + txtEmail.getText(),"Confirmation envoi code de verification",JOptionPane.PLAIN_MESSAGE);
                FenetreOublieMDP.killInstance();
                FenetreNewMDP fenetreNewMDP = FenetreNewMDP.getInstance(txtEmail.getText());
                fenetreNewMDP.setVisible(true);

            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }


    }

}
