package com.company;

import java.util.ArrayList;

public class GestionnaireComptes {
    private ArrayList<Client> clients;

    public GestionnaireComptes() {
        clients = new ArrayList<Client>();
        Client admin = new Client("admin","admin");
        clients.add(admin);
    }

    // ajouter exception utilisateur non trouvé et mdp incorrect
    public Client connexion(String id, String mdp) throws Exception{
        Client openClient = null;
        for (Client c : clients) {
            if (c.getIdentifiant() == id ) {
                openClient = c;
                System.out.println("Utilisateur trouvé");
            }
        }
        if (openClient == null)
            throw new Exception();
        if (openClient.getMotDePasse() == mdp)
            return openClient;
        else
            throw new Exception();

    }

    public void inscription (String id, String mdp) {
        Client newClient = new Client(id,mdp);
        clients.add(newClient);
    }


    public void addClient(Client c) {
        clients.add(c);
    }
}
