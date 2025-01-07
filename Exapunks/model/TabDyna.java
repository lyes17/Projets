package model;

import java.util.ArrayList;
import java.util.LinkedList; 
public class TabDyna extends Fichier{

    private LinkedList<String> copie; 
    public TabDyna(int nom ,Piece piece,ArrayList<String> contenu){
        super(nom,piece,contenu);
        copie=new LinkedList<>(contenu); 
    }


    public void F (){
        if(!testOEF())
        {
            copie.removeLast(); 
        }
    }

    public String supprime(){

        return copie.removeLast();

    }


}