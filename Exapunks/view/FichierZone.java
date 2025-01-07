package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

public class FichierZone extends JPanel{ 

    public static ArrayList<FichierZone> listeFZ = new ArrayList<>();
    public BufferedImage fichierSprite;
    

    int id;
    FichierIG myFichier;
    int nomFichier;
    JLabel nomBouteille;
    JLabel contenuBouteille = new JLabel("0");

    // Constructeur de la zone texte de Robot
    public FichierZone(int nomFichier,FichierIG monParent) {
        myFichier = monParent;
        add(contenuBouteille);
        this.nomFichier = nomFichier;
        nomBouteille = new JLabel(String.valueOf(nomFichier));

        try {
            // Charger l'image du sprite du fichier depuis le fichier
            fichierSprite = ImageIO.read(getClass().getResource("img/bouteillezone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
        listeFZ.add(this);
        SidePanel.listeFZ.add(this);
        Fenetre.sidePanel.add(this,BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(SidePanel.width,99));
        this.setBackground(null);        
    }
    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(myFichier.getRobot() != null){
            try {
                // Charger l'image du sprite du fichier depuis le fichier
                fichierSprite = ImageIO.read(getClass().getResource("img/bouteillezonegrab.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            nomBouteille.setForeground(Color.decode("#5A2029"));
            nomBouteille.setFont(new Font("Arial", Font.BOLD, 24));
            nomBouteille.setBounds(15,35,50,20);
            add(nomBouteille);
        }
        else{
            try {
                // Charger l'image du sprite du fichier depuis le fichier
                fichierSprite = ImageIO.read(getClass().getResource("img/bouteillezone.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            nomBouteille.setForeground(Color.black);
            nomBouteille.setFont(new Font("Arial", Font.BOLD, 12));
            nomBouteille.setBounds(15,50,50,20);
            add(nomBouteille);
        }
        
        g.drawImage(fichierSprite, 0, 10, getWidth(), 89, this);

        /*String nombres = "";
        for(int i = 0;i<myFichier.myFichier.contenu.size();i++){
            if(i != myFichier.myFichier.contenu.size()-1){
                nombres += myFichier.myFichier.get(i) + ",  ";
            }
            nombres += myFichier.myFichier.get(i);
        }*/
        //JLabel contenuBouteille = new JLabel(nombres);

        String texte ="";
        if (myFichier.myFichier.getTexte() != null) {
            for(int i=0;i<myFichier.myFichier.getTexte().size();i++){
                if (i<myFichier.myFichier.getTexte().size() - 1) {
                    texte += myFichier.myFichier.getTexte().get(i) + ",   ";
                }else{
                    texte += myFichier.myFichier.getTexte().get(i);
                }
                
            }
        }
        contenuBouteille.setText(texte);
        
        contenuBouteille.setForeground(Color.black);
        
        contenuBouteille.setBounds(100,50,130,20);
        
    }
    public int getId(){
        return id;
    }



}