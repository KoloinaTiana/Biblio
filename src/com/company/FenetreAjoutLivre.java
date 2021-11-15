package com.company;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAjoutLivre extends JFrame{
    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)


    private JTextField txtTitre;
    private JTextField txtAuteur;
    private JTextArea txtResume;
    private JTextField txtEditeur;
    private JTextField txtPage;


    public FenetreAjoutLivre() {

        super("Ajout livre - B'ook la bibliotheque 2.0");

        String[] optionsToChoose = {"Aucun", "Sport", "Histoire", "Informatique", "Geographie"};

        getContentPane().setFont(new Font("Yu Gothic", Font.PLAIN, 11));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // termine le processus si la derniere fenetre a ete ferme
        this.setResizable(false);// empeche toutes modifications de la taille de la fenetre
        // centralise la fenetre et definit sa taille
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        JLabel lbTitre = new JLabel("Titre :");
        lbTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbTitre.setBounds(243, 20, 150, 20);
        contentPane.add(lbTitre);

        txtTitre = new JTextField();
        txtTitre.setBounds(243, 50, 193, 20);
        getContentPane().add(txtTitre);
        txtTitre.setColumns(10);

        JLabel lbAuteur = new JLabel("Auteur :");
        lbAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbAuteur.setSize(150, 20);
        lbAuteur.setLocation(243, 80);
        contentPane.add(lbAuteur);

        txtAuteur = new JTextField();
        txtAuteur.setBounds(243, 110, 193, 20);
        getContentPane().add(txtAuteur);
        txtAuteur.setColumns(10);

        JLabel lbSujet = new JLabel("Sujet :");
        lbSujet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbSujet.setSize(150, 20);
        lbSujet.setLocation(243, 140);
        contentPane.add(lbSujet);

        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(243, 170, 150, 20);
        contentPane.add(jComboBox);

        JLabel lbResume = new JLabel("Resume :");
        lbResume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbResume.setSize(150, 20);
        lbResume.setLocation(243, 200);
        contentPane.add(lbResume);

        txtResume= new JTextArea();
        txtResume.setBounds(243, 230, 193, 60);
        getContentPane().add(txtResume);
        txtResume.setColumns(10);

        JLabel lbEditeur = new JLabel("Editeur :");
        lbEditeur.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbEditeur.setSize(150, 20);
        lbEditeur.setLocation(243, 300);
        contentPane.add(lbEditeur);

        txtEditeur= new JTextField();
        txtEditeur.setBounds(243, 330, 193, 20);
        getContentPane().add(txtEditeur);
        txtEditeur.setColumns(10);

        JButton btnAjout = new JButton("Ajouter");
        btnAjout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAjout.setBounds(230, 400, 100, 30);
        getContentPane().add(btnAjout);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAnnuler.setBounds(340, 400, 100, 30);
        getContentPane().add(btnAnnuler);

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
}
