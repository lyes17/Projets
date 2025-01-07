package model;
import java.util.ArrayList;

public class File extends Fichier{

    public File(int nom,Piece piece,ArrayList<String> contenu){
        super(nom,piece,contenu); 
    }
    public boolean enqueue(String val) {
        return ecrire(val);  
    }
    public String dequeue() {
        if (testOEF()) {
            return null;
        }
        return contenu.remove(0);
    }
    
    public String front() {
        if (testOEF()) {
            return null;
        }
        return contenu.get(0);
    }
    








}