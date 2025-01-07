package view;
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;


import model.*;

public class EcranJeu extends JPanel {
    public static int n;
    public EcranJeu(){
        
    }
    public void lancerFeur(){
        // Création des elements de la fenetre en leger /////
        Container conteneur = InterfaceGraphique.fenetre.getContentPane();
        conteneur.setLayout(new BorderLayout());
        conteneur.setBackground(Color.white);

        Container conteneurDroit = new Container();
        conteneurDroit.setLayout(new BorderLayout());
        conteneurDroit.add(Fenetre.gameArenaPanel, BorderLayout.CENTER);
        conteneurDroit.add(Fenetre.downPanel, BorderLayout.SOUTH);

        conteneur.add(conteneurDroit,BorderLayout.CENTER);
        conteneur.add(Fenetre.sidePanel,BorderLayout.WEST);

        
        
        // Niveaux
        new Piece("200");
        new Piece("400");
        new Piece("600");
        
        
        Fenetre.niveauActuel = new Niveau(n);
        
        
        Fenetre.niveau = n;

        /*ArrayList<String> nbF1 = new ArrayList<>();
        for(int i=0;i<5;i++){
            if (i<4) {
                nbF1.add(String.valueOf(i)+",  ");
            }
            else{
                nbF1.add(String.valueOf(i));
            }
            
        }
        p1.addFichier(new Fichier(50,p1,nbF1));
        p2.addFichier(new Fichier(70,p2,nbF1));
        p3.addFichier(new Fichier(536,p3,nbF1));

        
    
        ////////////////////////////////////////////////////*/
    }
}
