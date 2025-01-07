package io;
import java.util.ArrayList;


public class TextSplitter {
    private String texte;
    private ArrayList<String> lignes;
    public ArrayList<ArrayList<String>> mots;


    public TextSplitter(String texte) {
        this.texte = texte;
        lignes = decoupesLignes();
    }
    /* 
     
    Renvoie une liste qui contient tous les lignes de notre texte,
    qui sont séparée avec des saut-ligne "\n"*/
    public ArrayList<String> decoupesLignes() {
        String[] lignesArray = texte.split("\n");
        ArrayList<String> lignesListe = new ArrayList<>();

        for(int i = 0; i < lignesArray.length ; i++) {
            lignesListe.add(lignesArray[i]);
        }

        return lignesListe;
    }

    /* Renvoie la liste des lignes.*/
    public ArrayList<String> getLignes() {
        return lignes;
    }


    /*public ArrayList<Commande> textProcessor() {
        AnalyseurSyntaxique analyseurSyntaxique = new AnalyseurSyntaxique();
        Commande commande;


        Instructions instructions = new Instructions();

        for (String e : lignes) {
            commande = analyseurSyntaxique.getCommande(e);
            instructions.add(commande);
        }

        return instructions.getCommandeListe();
    }*/

}