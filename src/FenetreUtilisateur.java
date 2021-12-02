import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.proteanit.sql.DbUtils;


public class FenetreUtilisateur extends JFrame {

    private static final long serialVersionUID = 5739633010639612024L;
    private Client client;
    private static FenetreUtilisateur instance;
    private JTable tableLivre;
    private JTable tableSalle;
    private JTable tablePc;
    private JTextField txtRechercheLivre;

    private FenetreUtilisateur(Client _client) {
        super("Interface utilisateur - B'ook la bibliothèque 2.0");

        this.client = _client;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termine le processus si la derniere fenêtre a été fermé
        this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

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
                    FenetreUtilisateur.killInstance();

                }
            });
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JLabel lbBienvenue = new JLabel("BIENVENUE");
        lbBienvenue.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbBienvenue.setBounds(54, 23, 240, 66);
        contentPane.add(lbBienvenue);

        JButton btnReservationsEmprunts = new JButton("Mes reservations et emprunts");
        btnReservationsEmprunts.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnReservationsEmprunts.setBounds(54, 300, 273, 46);
        contentPane.add(btnReservationsEmprunts);
        btnReservationsEmprunts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreMesReservationsEmprunts fenetreOublieMDP = FenetreMesReservationsEmprunts.getInstance(client);
                fenetreOublieMDP.setVisible(true);
                fenetreOublieMDP.setLocationRelativeTo(contentPane);
            }
        });


        JPanel paneEmprunt = new JPanel();
        paneEmprunt.setBorder(new LineBorder(Color.BLACK, 2));
        paneEmprunt.setBounds(412, 142, 731, 475);
        contentPane.add(paneEmprunt);
        paneEmprunt.setLayout(null);
        paneEmprunt.setVisible(false);

        JButton btnLivre = new JButton("Livre");
        btnLivre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnLivre.setBounds(54, 11, 273, 46);
        paneEmprunt.add(btnLivre);

        JButton btnAutreDocument = new JButton("Autre document");
        btnAutreDocument.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAutreDocument.setBounds(407, 11, 273, 46);
        paneEmprunt.add(btnAutreDocument);

        txtRechercheLivre = new JTextField();
        txtRechercheLivre.setBounds(150, 150, 141, 35);
        paneEmprunt.add(txtRechercheLivre);
        txtRechercheLivre.setColumns(10);
        txtRechercheLivre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                afficherTableauLivre();

            }
        });

        tableLivre = new JTable();
        tableLivre.setBounds(0, 0, 658, 322);
        JScrollPane scrollPaneLivre = new JScrollPane(tableLivre);
        paneEmprunt.add(scrollPaneLivre);
        scrollPaneLivre.setBounds(36, 190, 658, 245);
        tableLivre.setDefaultEditor(Object.class, null);
        tableLivre.getTableHeader().setResizingAllowed(false);
        tableLivre.getTableHeader().setReorderingAllowed(false);
        tableLivre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        afficherTableauLivre();


        JLabel lbRechercherTitre = new JLabel("Rechercher titre:");
        lbRechercherTitre.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbRechercherTitre.setBounds(42, 160, 120, 14);
        paneEmprunt.add(lbRechercherTitre);

        JLabel lbLivre = new JLabel("Livre");
        lbLivre.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbLivre.setBounds(335, 80, 150, 30);
        paneEmprunt.add(lbLivre);


        JPanel paneReservation = new JPanel();
        paneReservation.setBorder(new LineBorder(Color.BLACK, 2));
        paneReservation.setBounds(412, 142, 731, 475);
        contentPane.add(paneReservation);
        paneReservation.setLayout(null);

        JPanel paneSalle = new JPanel();
        paneSalle.setBounds(10, 80, 700, 322);
        paneReservation.add(paneSalle);
        paneSalle.setLayout(null);
        paneSalle.setVisible(true);

        JPanel panePc = new JPanel();
        panePc.setBounds(10, 80, 700, 322);
        paneReservation.add(panePc);
        panePc.setLayout(null);
        panePc.setVisible(false);

        tableSalle = new JTable();
        tableSalle.setBounds(20, 80, 658, 322);
        JScrollPane scrollPaneSalle = new JScrollPane(tableSalle);
        paneSalle.add(scrollPaneSalle);
        scrollPaneSalle.setBounds(20, 80, 658, 245);
        tableSalle.setDefaultEditor(Object.class, null);
        tableSalle.getTableHeader().setResizingAllowed(false);
        tableSalle.getTableHeader().setReorderingAllowed(false);
        afficherTableauSalle();

        tablePc = new JTable();
        tablePc.setBounds(150, 80, 448, 322);
        JScrollPane scrollPanePc = new JScrollPane(tablePc);
        panePc.add(scrollPanePc);
        scrollPanePc.setBounds(150, 80, 448, 245);
        tablePc.setDefaultEditor(Object.class, null);
        tablePc.getTableHeader().setResizingAllowed(false);
        tablePc.getTableHeader().setReorderingAllowed(false);
        afficherTableauPc();

        JLabel lbSalle = new JLabel("Salle");
        lbSalle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbSalle.setBounds(335, 20, 150, 30);
        paneSalle.add(lbSalle);

        JLabel lbPc = new JLabel("PC");
        lbPc.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbPc.setBounds(335, 20, 150, 30);
        panePc.add(lbPc);

        JPanel paneAbonnement = new JPanel();
        paneAbonnement.setBorder(new LineBorder(Color.BLACK, 2));
        paneAbonnement.setBounds(28, 419, 326, 198);
        contentPane.add(paneAbonnement);
        paneAbonnement.setLayout(null);

        JLabel lblAbonnementRestant = new JLabel("Temps d'abonnement restant : 19 jours");
        lblAbonnementRestant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAbonnementRestant.setBounds(10, 31, 306, 24);
        paneAbonnement.add(lblAbonnementRestant);

        JButton btnReserverUneSalle = new JButton("Reserver une salle");
        btnReserverUneSalle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnReserverUneSalle.setBounds(54, 27, 273, 46);
        paneReservation.add(btnReserverUneSalle);
        btnReserverUneSalle.addActionListener(e -> {
            paneSalle.setVisible(true);
            panePc.setVisible(false);
        });

        JButton btnReserverUnPc = new JButton("Reserver un pc");
        btnReserverUnPc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnReserverUnPc.setBounds(404, 27, 273, 46);
        paneReservation.add(btnReserverUnPc);
        btnReserverUnPc.addActionListener(e -> {
            paneSalle.setVisible(false);
            panePc.setVisible(true);
        });

        JButton btnRechargerAbonnement = new JButton("Recharger");
        btnRechargerAbonnement.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnRechargerAbonnement.setBounds(77, 100, 164, 46);
        paneAbonnement.add(btnRechargerAbonnement);

        JButton btnReserver = new JButton("Reserver une salle ou un pc");
        btnReserver.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnReserver.setBounds(480, 67, 273, 46);
        contentPane.add(btnReserver);
        btnReserver.addActionListener(e -> {
            paneReservation.setVisible(true);
            paneEmprunt.setVisible(false);
        });

        JButton btnEmprunter = new JButton("Emprunter un livre");
        btnEmprunter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnEmprunter.setBounds(802, 67, 273, 46);
        contentPane.add(btnEmprunter);
        btnEmprunter.addActionListener(e -> {
            paneEmprunt.setVisible(true);
            paneReservation.setVisible(false);
        });

        JLabel lbPrenom = new JLabel(client.prenom);
        lbPrenom.setBounds(46, 113, 350, 24);
        contentPane.add(lbPrenom);

        JLabel lbNom = new JLabel(client.nom);
        lbNom.setBounds(46, 142, 308, 14);
        contentPane.add(lbNom);

        JLabel lbIdentifiant = new JLabel(client.identifiant);
        lbIdentifiant.setBounds(46, 188, 308, 14);
        contentPane.add(lbIdentifiant);

        JButton btnModifCompte = new JButton("Modifier le compte");
        btnModifCompte.setBounds(28, 214, 129, 23);
        contentPane.add(btnModifCompte);

    }


    public void afficherTableauLivre() {

        try {
            PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_Livre, Titre, Auteur, Sujet FROM livre WHERE Titre LIKE '%" + txtRechercheLivre.getText() + "%' AND EstDisponible = 1");
            ResultSet rs = ps.executeQuery();
            tableLivre.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableModel model = (DefaultTableModel) tableLivre.getModel();
            model.addColumn("Info");
            model.addColumn("Emprunter");
            tableLivre.setRowHeight(30);
            tableLivre.getColumnModel().getColumn(0).setHeaderValue("ID");
            tableLivre.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableLivre.getColumnModel().getColumn(1).setPreferredWidth(250);
            tableLivre.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableLivre.getColumnModel().getColumn(3).setPreferredWidth(65);
            tableLivre.getColumnModel().getColumn(4).setPreferredWidth(48);
            tableLivre.getColumnModel().getColumn(5).setPreferredWidth(114);
            tableLivre.getColumn("Info").setCellRenderer(new ButtonRendererInfo());
            tableLivre.getColumn("Info").setCellEditor(new ButtonEditorInfo());
            tableLivre.getColumn("Emprunter").setCellRenderer(new ButtonRendererEmprunter());
            tableLivre.getColumn("Emprunter").setCellEditor(new ButtonEditorEmprunter());
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public void afficherTableauSalle() {
        try {
            PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_salle, Nombre_chaise, Nombre_table, Projecteur , Taille FROM salle WHERE EstDisponible = 1");
            ResultSet rs = ps.executeQuery();
            int size = 0;
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
            if (size > 0) {
                tableSalle.setModel(DbUtils.resultSetToTableModel(rs));
                DefaultTableModel model = (DefaultTableModel) tableSalle.getModel();
                model.addColumn("Reserver");
                tableSalle.setRowHeight(30);
                tableSalle.getColumnModel().getColumn(0).setHeaderValue("ID");
                tableSalle.getColumnModel().getColumn(0).setPreferredWidth(40);
                tableSalle.getColumnModel().getColumn(1).setPreferredWidth(250);
                tableSalle.getColumnModel().getColumn(2).setPreferredWidth(120);
                tableSalle.getColumnModel().getColumn(3).setPreferredWidth(65);
                tableSalle.getColumnModel().getColumn(4).setPreferredWidth(48);
                tableSalle.getColumnModel().getColumn(5).setPreferredWidth(114);
                tableSalle.getColumn("Reserver").setCellRenderer(new ButtonRendererReserver());
                tableSalle.getColumn("Reserver").setCellEditor(new ButtonEditorReserver());
            }else{
                tableSalle.setModel(DbUtils.resultSetToTableModel(rs));
                tableSalle.getColumnModel().getColumn(0).setHeaderValue("ID");
                DefaultTableModel model = (DefaultTableModel) tableSalle.getModel();
                model.addRow(new Object[]{"Aucun resultat"});
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void afficherTableauPc() {
        try {
            PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_pc, Marque, SN FROM pc WHERE EstDisponible = 1");
            ResultSet rs = ps.executeQuery();
            int size = 0;
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
            if (size > 0) {
                tablePc.setModel(DbUtils.resultSetToTableModel(rs));
                DefaultTableModel model = (DefaultTableModel) tablePc.getModel();
                model.addColumn("Reserver");
                tablePc.setRowHeight(30);
                tablePc.getColumnModel().getColumn(0).setHeaderValue("ID");
                tablePc.getColumnModel().getColumn(0).setPreferredWidth(40);
                tablePc.getColumnModel().getColumn(1).setPreferredWidth(100);
                tablePc.getColumnModel().getColumn(2).setPreferredWidth(150);
                tablePc.getColumnModel().getColumn(2).setPreferredWidth(114);
                tablePc.getColumn("Reserver").setCellRenderer(new ButtonRendererReserver());
                tablePc.getColumn("Reserver").setCellEditor(new ButtonEditorReserver());
            }else{
                tablePc.setModel(DbUtils.resultSetToTableModel(rs));
                tablePc.getColumnModel().getColumn(0).setHeaderValue("ID");
                DefaultTableModel model = (DefaultTableModel) tablePc.getModel();
                model.addRow(new Object[]{"Aucun resultat"});
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }


    public static FenetreUtilisateur getInstance(Client _client) {
        if (instance == null) {
            instance = new FenetreUtilisateur(_client);
        }
        return instance;
    }

    public static FenetreUtilisateur getInstance() throws Exception {
        if (instance == null) {
            throw new Exception();
        }
        return instance;
    }


    public static void killInstance() {
        if (instance != null)
            instance.setVisible(false);
        instance = null;
    }
}

class ButtonRendererInfo extends JButton implements TableCellRenderer {

    private static final long serialVersionUID = -4008051814163131780L;

    public ButtonRendererInfo() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Info");
        return this;
    }
}

class ButtonEditorInfo extends DefaultCellEditor {

    private static final long serialVersionUID = 8910047697105888195L;

    protected JButton button;

    public ButtonEditorInfo() {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        button.setText("Info");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("row :" + row + " column : " + column);
                try {
                    FenetreInfoLivre.killInstance();
                    Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
                    ResultSet res = stmt.executeQuery("SELECT * FROM livre WHERE ID_Livre = '" + table.getValueAt(row, 0) + "'");
                    res.next();
                    String titre = res.getString("Titre");
                    String auteur = res.getString("Auteur");
                    String resume = res.getString("Resume");
                    String sujet = res.getString("Sujet");
                    FenetreInfoLivre fenetreInfoLivre = FenetreInfoLivre.getInstance(titre, auteur, resume, sujet);
                    fenetreInfoLivre.setVisible(true);
                    fenetreInfoLivre.setLocationRelativeTo(FenetreUtilisateur.getInstance().getContentPane());
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(new JButton(), "Erreur livre non disponible");
                    try {
                        FenetreUtilisateur.getInstance().afficherTableauLivre();
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        table.getColumn("Info").setCellEditor(new ButtonEditorInfo());
        return button;

    }
}


class ButtonRendererEmprunter extends JButton implements TableCellRenderer {

    private static final long serialVersionUID = -3515681451726103450L;

    public ButtonRendererEmprunter() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Emprunter");
        return this;
    }
}

class ButtonEditorEmprunter extends DefaultCellEditor {

    private static final long serialVersionUID = 6420764142408657226L;

    protected JButton button;

    public ButtonEditorEmprunter() {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        button.setText("Emprunter");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("row :" + row + " column : " + column);
                try {
                    Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
                    int id = FenetreUtilisateur.getInstance().getClient().getID();
                    stmt.executeUpdate("UPDATE Livre SET ID_client = '" + id + "', EstDisponible = 0 WHERE ID_livre ='" + table.getValueAt(row, 0) + "';");
                    JOptionPane.showMessageDialog(new JButton(), "Le livre a bien été ajouté à vos emprunts");
                    FenetreUtilisateur.getInstance().afficherTableauLivre();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(new JButton(), "Erreur livre non disponible");
                    try {
                        FenetreUtilisateur.getInstance().afficherTableauLivre();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        table.getColumn("Emprunter").setCellEditor(new ButtonEditorEmprunter());
        return button;

    }
}

class ButtonRendererReserver extends JButton implements TableCellRenderer {


    private static final long serialVersionUID = 8676284455189980734L;

    public ButtonRendererReserver() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText("Reserver");
        return this;
    }
}

class ButtonEditorReserver extends DefaultCellEditor {

    private static final long serialVersionUID = 2581127369293255795L;

    protected JButton button;

    public ButtonEditorReserver() {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        button.setText("Reserver");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("row :" + row + " column : " + column);
                try {
                    Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
                    int id = FenetreUtilisateur.getInstance().getClient().getID();
                    stmt.executeUpdate("UPDATE salle SET ID_client = '" + id + "', EstDisponible = 0 WHERE ID_salle ='" + table.getValueAt(row, 0) + "';");
                    JOptionPane.showMessageDialog(new JButton(), "La salle a bien ete ajoute a vos reservations");
                    FenetreUtilisateur.getInstance().afficherTableauSalle();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(new JButton(), "Erreur salle non disponible");
                    try {
                        FenetreUtilisateur.getInstance().afficherTableauSalle();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        table.getColumn("Reserver").setCellEditor(new ButtonEditorReserver());
        return button;

    }
}

