package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        Connection con = db.connexion();
        String requete = "SELECT * FROM livre";

        try {
            System.out.println("Liste livres :");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            res.next();

            while (res.next()) {
                int id = res.getInt("ID_livre");
                String titre = res.getString("Titre");
                String auteur = res.getString("Auteur");
                String resume = res.getString("Resume");
                String sujet = res.getString("Sujet");
                System.out.println(id+" "+titre+" "+auteur+" "+resume+" "+sujet);
            }


        } catch (SQLException e) {
            throw new SQLException();
        }

    }
}
