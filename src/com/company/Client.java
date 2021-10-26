package com.company;

import java.util.ArrayList;

public class Client {
    private ArrayList<Reservation> reservations;

    private String identifiant;
    private String motDePasse;
    private boolean estAbonne;

    public Client(String _identifiant, String _motDePasse) {

        this.identifiant = _identifiant;
        this.motDePasse = _motDePasse;
        this.estAbonne = false;
        this.reservations = new ArrayList<Reservation>();
    }

    //ajouter exception client deja abonné
    public void abonner() throws Exception{
        if (estAbonne == true)
            throw new Exception();
        else
            this.estAbonne = true;
    }

    public void reserver(String date, ArrayList<PC> pcAReserves, ArrayList<Salle> salleAReservees) {

        Reservation newReservation = new Reservation(date,pcAReserves,salleAReservees);
        reservations.add(newReservation);

    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}
