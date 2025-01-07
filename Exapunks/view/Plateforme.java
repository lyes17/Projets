package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Plateforme extends JPanel {
    
    public static ArrayList<Plateforme> listePlateforme = new ArrayList<>();
    public ArrayList<FichierIG> listeFichier = new ArrayList<>();
    private int x,y;
    private int nom;
    public BufferedImage sprite;

    public static double ratio = Math.sqrt(3.0);
    public ArrayList<Boolean> etatCases = new ArrayList<>(9);
    public static int tailleX = 300;
    public static int tailleY = (int) (((1/ratio)*tailleX));
    public static int XC  = (int) (tailleX/3);
    public static int YC  = (int) (tailleY/3);
    
    public Plateforme(int x, int y, int nom){
        if (listePlateforme.size()==0) {
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                sprite = ImageIO.read(getClass().getResource("img/plateformetest.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (listePlateforme.size()==1) {
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                sprite = ImageIO.read(getClass().getResource("img/plateformedeuxt.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (listePlateforme.size()==2) {
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                sprite = ImageIO.read(getClass().getResource("img/fplateformet.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        this.x = x;
        this.y = y;
        this.nom = nom;
        listePlateforme.add(this);
        initialiseCells();
    }
    public void initialiseCells(){
        for(int i = 0; i<9; i++){
            etatCases.add(false);
            
        }
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public int getNom(){
        return nom;
    }
    public int getXC(){
        return XC;
    }
    public int getYC(){
        return YC;
    }
    public void changeSize(int newSize){
        tailleX = newSize;
        refresh(); 
    }
    public void refresh(){
        tailleY = (int) (((1/ratio)*tailleX));
        XC  = (int) (tailleX/3);
        YC  = (int) (tailleY/3);
    }
    public void drawMe(Graphics g){
        g.drawImage(sprite, (int) (x-tailleX/2), (int) (y-tailleY/1.75),(int) (tailleX*2),(int) (tailleY*4.92), this);
    }

    
}