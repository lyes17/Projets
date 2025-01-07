package view;
import javax.swing.*;
import java.awt.*;
import java.lang.Thread;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RobotModel extends JPanel {
    public BufferedImage characterSprite;
    public double x,y;
    public int destinationX,destinationY;
    public RobotIG myRobot;
    public int caseActuelle;
    private boolean isMoving;


    public RobotModel(int x,int y){
        try {
            // Charger l'image du sprite du personnage depuis un fichier
            characterSprite = ImageIO.read(getClass().getResource("img/octo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.destinationX = x;
        this.destinationY = y;
    }
    public double getXpos(){
        return x;
    }

    public double getYpos(){
        return y;
    }

    public void tpTo(Plateforme plateforme, int carré){
        // La case qu'on quitte devient libre
        myRobot.getPlateforme().etatCases.set(myRobot.cell - 1, false);
        // Par defaut on prends la plateforme()
        x = plateforme.getX();
        y = plateforme.getY();
        destinationX = (int) x;
        destinationY = (int)y;

        // Sert à placer le robot dans les cases //
        if(carré<=3){
            for(int j=1; j<carré;j++){
                x += plateforme.getXC()/2;
                y += plateforme.getYC()/2;
            }
        }
        else if(carré <= 6){
            x += plateforme.getXC()/2;
            y -= plateforme.getYC()/2;
            
            for(int j=4; j<carré;j++){
                x += plateforme.getXC()/2;
                y += plateforme.getYC()/2;
            }
        }
        else if(carré <= 9){
            x += plateforme.getXC();
            y -= plateforme.getYC();
            for(int j=7; j<carré;j++){
                x += plateforme.getXC()/2;
                y += plateforme.getYC()/2;
            }
        }
        ////////////////////////////////////////////
        destinationX = (int) x;
        destinationY = (int)y;
        plateforme.etatCases.set(carré - 1, true);
        myRobot.setPlateforme(plateforme);
        myRobot.cell = carré;
        setIsMoving(false);
    }
    public void tpTo(int nvX, int nvY){
        this.x = nvX;
        this.y = nvY;
        
        synchronized(RobotIG.lock){
            RobotIG.lock.notify();
        }
    }
    public void moveTo(Plateforme plateforme,int carré){
        // La case qu'on quitte devient libre
        myRobot.getPlateforme().etatCases.set(myRobot.cell - 1, false);
        // Par defaut on prends la plateforme
        destinationX = plateforme.getX();
        destinationY = plateforme.getY();
            
        // Sert à placer le robot dans les cases //
        if(carré<=3){
            for(int j=1; j<carré;j++){
                destinationX += plateforme.getXC()/2;
                destinationY += plateforme.getYC()/2;
            }
        }
        else if(carré <= 6){
            destinationX += plateforme.getXC()/2;
            destinationY -= plateforme.getYC()/2;
            
            for(int j=4; j<carré;j++){
                destinationX += plateforme.getXC()/2;
                destinationY += plateforme.getYC()/2;
            }
        }
        else if(carré <= 9){
            destinationX += plateforme.getXC();
            destinationY -= plateforme.getYC();
            for(int j=7; j<carré;j++){
                destinationX += plateforme.getXC()/2;
                destinationY += plateforme.getYC()/2;
            }
        }
        ////////////////////////////////////////////

        
        plateforme.etatCases.set(carré - 1, true);
        myRobot.setPlateforme(plateforme);
        myRobot.cell = carré;
        setIsMoving(true);
        
    }

    public boolean isMoving(){
        return isMoving;
    }
    // Méthode pour mettre à jour le booléen isMoving
    public synchronized void setIsMoving(boolean moving) {
        this.isMoving = moving;
        /*if (!isMoving) {
            synchronized (this) {
                isMoving = false;
                RobotIG.lock.notify(); // Notifier le thread en attente
            }
        }*/
    }

    // Méthode pour attendre que le mouvement s'arrête
    public synchronized void attendreFinMouvement() throws InterruptedException {
        try {
            while (isMoving) {
                // Tant que le mouvement est en cours, attendre
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Rétablir le statut d'interruption du thread
            // Gérer l'exception ou propager à un niveau supérieur si nécessaire
            System.err.println("L'interruption du thread a été capturée : " + e.getMessage());
        }
    }
    
    
    public void update() {
        // Calculer le vecteur de déplacement
        double dx = destinationX - x;
        double dy = destinationY - y;
    
        // Calculer la distance totale à parcourir
        double distance = Math.sqrt(dx * dx + dy * dy);
    
        // Calculer le pas de déplacement pour chaque axe
        double stepX = 1 * Math.sqrt(3);
        double stepY = 1; 
    
         
        if (distance == 0) {
            return;
        }

        //System.out.println(x + " " + dx + " " + destinationX + " " + y + " " + dy + " " + destinationY + " ");
        if (Math.abs(dx) > stepX || Math.abs(dy) > stepY) {
            
            if(dx < 0){
                stepX = -stepX;
            }
            if(dy > 0){
                stepY = -stepY;
            }
    
            // Calculer les nouveaux coordonnées en fonction du rapport de progression
            x += stepX;
            y -= stepY;
            
            setIsMoving(true);
            // On reverifie
            dx = destinationX - x;
            dy = destinationY - y;
            
            ////////////////////////////////////////

            // Vérifier si le robot est proche de sa destination
            if (Math.abs(dx) <= stepX) {
                x = destinationX;
                stepX = 0;
            }
            if (Math.abs(dy) <= stepY) {
                y = destinationY;
                stepY = 0;
            }
            // On reverifie
            dx = destinationX - x;
            dy = destinationY - y;
            distance = Math.sqrt(dx * dx + dy * dy);
            if (distance == 0) {
                setIsMoving(false);
                synchronized(RobotIG.lock){
                    RobotIG.lock.notify();
                }
                return;
            }
        }
        // Vérifier si le robot est proche de sa destination
        if (Math.abs(dx) <= stepX) {
            x = destinationX;
            stepX = 0;
        }
        
        if (Math.abs(dy) <= stepY) {
            y = destinationY;
            stepY = 0;
        }
        
    }
    

    public void drawMe(Graphics g){
        int offsetX = (int) (- Plateforme.XC/20); 
        int offsetY = (int) (Plateforme.YC*1.25);
        int taille = (int) (Plateforme.XC *1.1);
        int xReel = (int) x+offsetX;
        int yReel = (int) y-offsetY;
        if (myRobot.getFichier() != null) {
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                characterSprite = ImageIO.read(getClass().getResource("img/octograb.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                // Charger l'image du sprite du personnage depuis un fichier
                characterSprite = ImageIO.read(getClass().getResource("img/octo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(characterSprite, xReel, yReel,taille,taille, this);
        
    }
    public void drawMyName(Graphics g){
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 18);
        int offsetX = (int) (- Plateforme.XC/20) + (int) (0.04*Plateforme.tailleX); 
        int offsetY = (int) (Plateforme.YC*1.25)- (int) (0.03*Plateforme.tailleX);
        int xReel = (int) x+offsetX;
        int yReel = (int) y-offsetY;
        // Création de l'outline
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        // Dessiner l'outline
        g2d.setColor(Color.decode("#D187DC"));
        g2d.drawString(myRobot.getNom(), (int) (xReel - 2), (int) (yReel - 2));
        g2d.drawString(myRobot.getNom(), (int) (xReel + 2), (int) (yReel - 2));
        g2d.drawString(myRobot.getNom(), (int) (xReel - 2), (int) (yReel + 2));
        g2d.drawString(myRobot.getNom(), (int) (xReel + 2), (int) (yReel + 2));
        // Dessiner le nom
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(myRobot.getNom(),xReel,yReel );

        
    }
    public void drawMyFichierName(Graphics g){
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 16);
        int offsetX = (int) (Plateforme.XC/20); 
        int offsetY = (int) (Plateforme.YC*0.6);
        int xReel = (int) x+offsetX;
        int yReel = (int) y - offsetY;

        
        
        
        g.setFont(font);
        
        if (myRobot.getFichier() != null) {
            // Création de l'outline
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setFont(font);
        // Dessiner l'outline
        g2d.setColor(Color.decode("#004A18"));
        g2d.drawString(myRobot.getFichier().getNomString(), (int) (xReel - 2), (int) (yReel - 2));
        g2d.drawString(myRobot.getFichier().getNomString(), (int) (xReel + 2), (int) (yReel - 2));
        g2d.drawString(myRobot.getFichier().getNomString(), (int) (xReel - 2), (int) (yReel + 2));
        g2d.drawString(myRobot.getFichier().getNomString(), (int) (xReel + 2), (int) (yReel + 2));
        g.setColor(Color.white);
        g.drawString(myRobot.getFichier().getNomString(), xReel , yReel );
        }
       
    }

    
}