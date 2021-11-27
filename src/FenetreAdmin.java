import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

public class FenetreAdmin extends JFrame{

    private static final long serialVersionUID = 5739633010639612024L;
    private Admin admin;
    private static FenetreAdmin instance;

    JPanel contentPane;
    JTable table;
    private JTextField txtRecherche;

    private FenetreAdmin(Admin _admin) {
        super("Interface administrateur - B'ook la bibliothéque 2.0");

        this.admin = _admin;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termine le processus si la derniere fenÃªtre a Ã©tÃ© fermÃ©
        this.setResizable(false);// empÃªche toutes modifications de la taille de la fenÃªtre

        // centralise la fenetre et definit sa taille
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File("Logo_off.png"));
            JButton buttonOff = new JButton(new ImageIcon(myPicture));
            buttonOff.setLocation(1103, 34);
            buttonOff.setSize(40, 40);
            buttonOff.setBorderPainted(false);
            buttonOff.setContentAreaFilled(false);
            buttonOff.setFocusPainted(false);
            buttonOff.setOpaque(false);
            contentPane.add(buttonOff);
            buttonOff.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    buttonOff.setBorderPainted(false);
                    buttonOff.setContentAreaFilled(false);
                    buttonOff.setFocusPainted(false);
                    buttonOff.setOpaque(false);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    buttonOff.setBorderPainted(true);
                    buttonOff.setContentAreaFilled(true);
                    buttonOff.setFocusPainted(true);
                    buttonOff.setOpaque(true);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub

                }
            });
            buttonOff.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    FenetreConnexion.getInstance().setVisible(true);
                    FenetreAdmin.killInstance();

                }
            });
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JLabel lblNewLabel = new JLabel("BIENVENUE");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel.setBounds(54, 23, 240, 66);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel1 = new JLabel("ADMINISTRATEUR");
        lblNewLabel1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel1.setBounds(94, 85, 260, 40);
        contentPane.add(lblNewLabel1);

        JPanel paneSP = new JPanel();
        paneSP.setBorder(new LineBorder(Color.BLACK, 2));
        paneSP.setBounds(412, 142, 731, 475);
        contentPane.add(paneSP);
        paneSP.setLayout(null);

        JButton btnAjouterUneSalle = new JButton("Ajouter une salle");
        btnAjouterUneSalle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjouterUneSalle.setBounds(54, 27, 273, 46);
        paneSP.add(btnAjouterUneSalle);
        btnAjouterUneSalle.addActionListener(e->actionAjoutSalle());

        JButton btnAjouterUnPc = new JButton("Ajouter un pc");
        btnAjouterUnPc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjouterUnPc.setBounds(404, 27, 273, 46);
        paneSP.add(btnAjouterUnPc);
        btnAjouterUnPc.addActionListener(e->actionAjoutPc());

        JPanel paneDoc = new JPanel();
        paneDoc.setBorder(new LineBorder(Color.BLACK, 2));
        paneDoc.setBounds(412, 142, 731, 475);
        contentPane.add(paneDoc);
        paneDoc.setLayout(null);
        paneDoc.setVisible(false);

        //gestion des utilisateurs
        JPanel paneUtilisateur = new JPanel();
        paneUtilisateur.setBorder(new LineBorder(Color.BLACK, 2));
        paneUtilisateur.setBounds(28, 419, 326, 198);
        contentPane.add(paneUtilisateur);
        paneUtilisateur.setLayout(null);

        JLabel lblTitre = new JLabel("Gestion des utilisateurs");
        lblTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTitre.setBounds(10, 31, 306, 24);
        paneUtilisateur.add(lblTitre);

        JPanel paneUser = new JPanel();
        paneUser.setBorder(new LineBorder(Color.BLACK, 2));
        paneUser.setBounds(412, 142, 731, 475);
        contentPane.add(paneUser);
        paneUser.setLayout(null);
        paneUser.setVisible(false);

        table = new JTable();
        table.setBounds(0, 0, 658, 322);
        JScrollPane scrollPane = new JScrollPane(table);
        paneUser.add(scrollPane);
        scrollPane.setBounds(36, 118, 658, 322);

        //Boutons

        JButton btnLivre = new JButton("Livre");
        btnLivre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLivre.setBounds(54, 28, 273, 46);
        paneDoc.add(btnLivre);
        btnLivre.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    actionAjoutLivre();
            }
        });
        btnLivre.addActionListener(e->actionAjoutLivre());

        JButton btnFilm = new JButton("Film");
        btnFilm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnFilm.setBounds(407, 28, 273, 46);
        paneDoc.add(btnFilm);
        btnFilm.addActionListener(e->actionAjoutFilm());

        JButton btnJournal = new JButton("Journal");
        btnJournal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnJournal.setBounds(54, 80, 273, 46);
        btnJournal.addActionListener(e->actionAjoutJournal());
        paneDoc.add(btnJournal);

        JButton btnAjouterSalle = new JButton("Ajouter une salle ou un pc");
        btnAjouterSalle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjouterSalle.setBounds(480, 67, 273, 46);
        contentPane.add(btnAjouterSalle);
        btnAjouterSalle.addActionListener(e->{paneSP.setVisible(true);paneDoc.setVisible(false);paneUser.setVisible(false);});

        JButton btnAjouterDoc = new JButton("Ajouter un document");
        btnAjouterDoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjouterDoc.setBounds(802, 67, 273, 46);
        contentPane.add(btnAjouterDoc);
        btnAjouterDoc.addActionListener(e->{paneDoc.setVisible(true);paneSP.setVisible(false);paneUser.setVisible(false);});

        JLabel lbPrenom = new JLabel(admin.prenom);
        lbPrenom.setBounds(46, 113, 350, 24);
        contentPane.add(lbPrenom);

        JLabel lbNom = new JLabel(admin.nom);
        lbNom.setBounds(46, 142, 308, 14);
        contentPane.add(lbNom);

        JLabel lbIdentifiant = new JLabel(admin.identifiant);
        lbIdentifiant.setBounds(46, 188, 308, 14);
        contentPane.add(lbIdentifiant);

        JButton btnModifCompte = new JButton("Modifier le compte");
        btnModifCompte.setBounds(28, 214, 129, 23);
        contentPane.add(btnModifCompte);

        JButton btnGerer = new JButton("Gérer");
        btnGerer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnGerer.setBounds(77, 100, 164, 46);
        paneUtilisateur.add(btnGerer);
        btnGerer.addActionListener(e->{paneUser.setVisible(true);paneDoc.setVisible(false);paneSP.setVisible(false);
            try {
                afficheliste();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        JButton btnModif = new JButton("Modifier un utilisateur");
        btnModif.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnModif.setBounds(280, 40, 200, 35);
        paneUser.add(btnModif);
        btnModif.addActionListener(e->actionModif());

        JButton btnDelete = new JButton("Supprimer un utilisateur");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnDelete.setBounds(500, 40, 200, 35);
        paneUser.add(btnDelete);
        btnDelete.addActionListener(e->actionDelete());

        JLabel lbRechercher = new JLabel("Rechercher :");
        lbRechercher.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbRechercher.setBounds(40, 49, 80, 14);
        paneUser.add(lbRechercher);
        table.setDefaultEditor(Object.class, null);

        txtRecherche = new JTextField();
        txtRecherche.setBounds(130, 40, 141, 35);
        paneUser.add(txtRecherche);
        txtRecherche.setColumns(10);
        txtRecherche.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                afficherTableau();

            }
        });
    }

    private void actionDelete() {
        FenetreDeleteUser fenetreDeleteUser = FenetreDeleteUser.getInstance();
        fenetreDeleteUser.setVisible(true);
        fenetreDeleteUser.setLocationRelativeTo(contentPane);
    }

    private void actionModif() {
        FenetreModifUser fenetreModifUser = FenetreModifUser.getInstance();
        fenetreModifUser.setVisible(true);
        fenetreModifUser.setLocationRelativeTo(contentPane);
    }

    private void actionAjoutFilm() {
        FenetreAjoutFilm fenetreAjoutFilm = FenetreAjoutFilm.getInstance();
        fenetreAjoutFilm.setVisible(true);
        fenetreAjoutFilm.setLocationRelativeTo(contentPane);
    }

    private void actionAjoutJournal() {
        FenetreAjoutJournal fenetreAjoutJournal = FenetreAjoutJournal.getInstance();
        fenetreAjoutJournal.setVisible(true);
        fenetreAjoutJournal.setLocationRelativeTo(contentPane);
    }

    private void actionAjoutSalle() {
        FenetreAjoutSalle fenetreAjoutSalle = FenetreAjoutSalle.getInstance();
        fenetreAjoutSalle.setVisible(true);
        fenetreAjoutSalle.setLocationRelativeTo(contentPane);
    }

    private void afficherTableau() {

        try {
            PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT * FROM client WHERE Nom LIKE '%" + txtRecherche.getText() +"%' OR Prenom LIKE '%" + txtRecherche.getText() +"%'");
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    private void afficheliste() throws SQLException {
        try {
            Connection con = Bibliotheque.getInstance().getConnexion();
            String requete = "SELECT * FROM client";

            try {
                PreparedStatement ps = con.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException e) {
                throw new SQLException();
            }

        }
        catch (SQLException ex) {
            throw new SQLException();
        }
    }

    public static FenetreAdmin getInstance(Admin _admin) {
        if( instance == null ) {
            instance = new FenetreAdmin(_admin);
        }
        return instance;
    }

    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }

    private void actionAjoutLivre(){
        FenetreAjoutLivre fenetreAjoutLivre = FenetreAjoutLivre.getInstance();
        fenetreAjoutLivre.setVisible(true);
        fenetreAjoutLivre.setLocationRelativeTo(contentPane);
    }

    private void actionAjoutPc(){
        FenetreAjoutPc fenetreAjoutPc = FenetreAjoutPc.getInstance();
        fenetreAjoutPc.setVisible(true);
        fenetreAjoutPc.setLocationRelativeTo(contentPane);
    }
}
