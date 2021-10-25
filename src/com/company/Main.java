package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        Connection con = db.connexion();
        String requete = "SELECT * FROM mytable";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(requete);
            res.next();

            while (res.next()) {
                int id = res.getInt("id");
                String nom = res.getString("name");
                String phone = res.getString("phone");
                String email = res.getString("email");
                System.out.println(id+" "+nom+" "+phone+" "+email);
            }


        } catch (SQLException e) {
            throw new SQLException();
        }

    }
}
