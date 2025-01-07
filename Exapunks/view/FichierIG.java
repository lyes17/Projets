package view;
import model.*;
import java.util.ArrayList;
import java.util.Random;



public class FichierIG {
    public static ArrayList<FichierIG> listeFichier = new ArrayList<>();
    public static int nbFichierTotal = 0;
    private int nom;
    public Fichier myFichier;

    private RobotIG robotGrabbingMe;
    
    public FichierZone fz;
    private FichierModel fm;

    private Plateforme plateformeActuelle;
    public Plateforme plateformeDefaut;
    private int cell;
    public int cellDefaut;

    public FichierIG(int nom,int id){
        Plateforme plateforme = Plateforme.listePlateforme.get(id-1);
        nbFichierTotal++;
        this.nom = nom;
        plateforme.listeFichier.add(this);


        
        plateformeActuelle = plateforme;
        plateformeDefaut = plateforme;
        
        Random random = new Random();
        int nombreAleatoire = random.nextInt(9) + 1;
        while(plateforme.etatCases.get(nombreAleatoire-1)){
            nombreAleatoire = random.nextInt(9) + 1;
        }
        int carré = nombreAleatoire;
        int x = plateforme.getX();
        int y = plateforme.getY();
        // Sert à placer le fichier dans la bonne case //
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
        fm = new FichierModel(x, y);
        fz = new FichierZone(nom,this);
        cell = carré;
        cellDefaut = cell;
        plateforme.etatCases.set(cell-1, true);
        fm.myFichier = this;
        fz.myFichier = this;
        listeFichier.add(this);
        
    }

    public String getNomString(){
        return String.valueOf(nom);
    }
    public int getNom(){
        return nom;
    }

    public FichierModel getFichierModel(){
        return fm;
    }
    public FichierZone getFichierZone(){
        return fz;
    }
    public int getCell(){
        return cell;
    }
    public Plateforme getPlateforme(){
        return plateformeActuelle;
    }
    public RobotIG getRobot(){
        return robotGrabbingMe;
    } 
    public void setRobot(RobotIG robot){
        robotGrabbingMe =  robot;
    }
    public void setCell(int nvCell){
        cell = nvCell;
    }
}   
