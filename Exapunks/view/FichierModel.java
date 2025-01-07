package view;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



public class FichierModel extends JPanel {
    public  BufferedImage fichierSprite;
    public double x,y;
    public FichierIG myFichier;
    public int caseActuelle;
    public boolean isMoving;


    public FichierModel(int x,int y){
        try {
            // Charger l'image du sprite du fichier depuis le fichier
            fichierSprite = ImageIO.read(getClass().getResource("img/bouteille.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }
    public double getXpos(){
        return x;
    }

    public double getYpos(){
        return y;
    }
    public void tpTo(Plateforme p, int carré){
        p.etatCases.set(myFichier.getCell()-1, false);
        if(carré == 10){carré = 8;}
        if(p.etatCases.get(carré-1)){
            Random random = new Random();
            int nombreAleatoire = random.nextInt(9) + 1;
            while(p.etatCases.get(nombreAleatoire-1)){
                nombreAleatoire = random.nextInt(9) + 1;
            }
            carré = nombreAleatoire;
        }
        

        x = p.getX();
        y = p.getY();
        if(carré == 10){carré = 9;}
        // Sert à placer le fichier dans la bonne case //
        if(carré<=3){
            for(int j=1; j<carré;j++){
                x += p.getXC()/2;
                y += p.getYC()/2;
            }
        }
        else if(carré <= 6){
            x += p.getXC()/2;
            y -= p.getYC()/2;
            
            for(int j=4; j<carré;j++){
                x += p.getXC()/2;
                y += p.getYC()/2;
            }
        }
        else if(carré <= 9){
            x += p.getXC();
            y -= p.getYC();
            for(int j=7; j<carré;j++){
                x += p.getXC()/2;
                y += p.getYC()/2;
            }
        }
        p.etatCases.set(carré-1, true);
        myFichier.setCell(carré);
    }
    
    
    public void drawMe(Graphics g){
        int offsetX = (int) (Plateforme.XC/4); 
        int offsetY = (int) (Plateforme.YC*0.8);
        int taille = (int) (Plateforme.XC *0.6);
        int xReel = (int) x+offsetX;
        int yReel = (int) y-offsetY;
        if (myFichier.getRobot() == null){
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                fichierSprite = ImageIO.read(getClass().getResource("img/bouteille.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            fichierSprite = null;
        }
        g.drawImage(fichierSprite, xReel, yReel,taille,taille, this);
    }
    public void drawMyName(Graphics g){
        int offsetX = (int) (Plateforme.XC/5); 
        int offsetY = (int) (Plateforme.YC*1.05);
        int xReel = (int) x+offsetX;
        int yReel = (int) y-offsetY;
        // Dessinez du texte à côté du rectangle
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 20);
        g.setFont(font);
        //g.setColor(Color.decode("#5A2029"));

        // Créer un tracé (outline) de la police
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setFont(font);
       
        if (myFichier.getRobot() == null) {
            xReel = xReel + (int) (0.07*Plateforme.tailleX); 
            yReel = yReel + (int) (0.03*Plateforme.tailleX);
            g2d.setColor(Color.decode("#004A18"));
            g2d.drawString(myFichier.getNomString(), (int) (xReel - 2), (int) (yReel - 2));
            g2d.drawString(myFichier.getNomString(), (int) (xReel + 2), (int) (yReel - 2));
            g2d.drawString(myFichier.getNomString(), (int) (xReel - 2), (int) (yReel + 2));
            g2d.drawString(myFichier.getNomString(), (int) (xReel + 2), (int) (yReel + 2));
            g.setColor(Color.white);
            g.drawString(myFichier.getNomString(), xReel, yReel );
            
        }
    }

}   
