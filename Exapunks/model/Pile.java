package model;
import java.util.ArrayList;
// Classe Pile
public class Pile extends Fichier {

    public Pile(int nom,Piece piece,ArrayList<String> contenu){
        super(nom,piece,contenu);
    }
    public boolean push(String val) {
        return ecrire(val);
    }

    public String pop() {
        if (testOEF()) {
            throw new IllegalStateException("Pile vide");
        }
        return contenu.remove(contenu.size() - 1);
    }

    

    
    public void voidF() {
        if (testOEF()) {
            throw new IllegalStateException("Pile vide");
        }
        contenu.remove(contenu.size() - 1);
    }

}
