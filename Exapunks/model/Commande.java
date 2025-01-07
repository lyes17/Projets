package model;
import view.*;
import java.util.ArrayList;



public class Commande
{
    private String first; //mot commande
    private String second; //premier argument
    private String third;   //deuxieme arg
    private String fourth;  // troisieme arg
    //j'evite d'utiliser une liste vu qu'on a que 4 arguments maximum

    public Commande(String commande)
    {
        // Diviser la ligne en mots en utilisant les espaces comme délimiteurs
        String[] mots = commande.split("\\s+");
        // Créer une ArrayList pour stocker les mots de cette ligne
        ArrayList<String> motsDeCommande = new ArrayList<>();
        // Ajouter chaque mot à la liste des mots de cette ligne
        for (String mot : mots) {
             motsDeCommande.add(mot);
        }

        if (motsDeCommande.size()>0) {
            this.first = motsDeCommande.get(0);
            if (motsDeCommande.size()>1) {
                this.second = motsDeCommande.get(1);
                if (motsDeCommande.size()>2) {
                    this.third = motsDeCommande.get(2);
                    if (motsDeCommande.size()>3) {
                        this.fourth = motsDeCommande.get(3);
                    }
                }
            }
        }
        
    
        
        
        
        
        if(first == null )
        {
            System.err.println("commande invalide null\n");
        }
        if (!(VerifieCommande.commandeValide(first))) {
            System.err.println("commande invalide verifie commande\n" + first);
        }
        
    }

    public String getFirst() // le mot commande
    {
        return first; 
    }

    public String getSecond()
    {
        return second;
    }

    public String getThird()
    {
        return third;
    }

    public String getFourth()
    {
        return fourth;
    }

    public boolean hasSecond()
    {
        return (second != null);
    }

    public boolean hasThird()
    {
        return (third != null);
    }
    
    public boolean hasFourth()
    {
        return (fourth != null);
    }

    public String toString() {
		if (!VerifieCommande.commandeValide(getFirst())) {
			return " Commande inconnue !";
		}

		String result = "Commande: " + getFirst();
		if (hasSecond()) {
			result += " ,arg1: " + getSecond();
		}

		if (hasThird()) {
			result += " ,arg2: " + getThird();
		}
	
		if (hasFourth()) {
			result += " ,arg3: " + getFourth();
		}

		return result;
	}

}