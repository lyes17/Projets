package view;
import model.*;
import javax.imageio.ImageIO;
import java.lang.Thread;
import java.util.ArrayList;
import java.io.IOException;

public class RobotIG {
    public static final Object lock = new Object();
    public static ArrayList<RobotIG> listeRobot = new ArrayList<>();
    public static int nbRobotTotal = 0;
    private int X;
    private int T;
    private int F;
    public static int M;

    private int id;
    
    private String nom;
    private RobotZone rz;
    private RobotModel rm;
    public Robot myRobot;

    
    private Plateforme plateformeActuelle;
    public Plateforme plateformeDefaut;
    public int cell;
    public int cellDefaut;
    private FichierIG myFichier;

    public String texteActuel = "";

    public RobotIG() {
        nbRobotTotal++;
        id = nbRobotTotal;
        

        nom = "Octopunk " + id;
        rz = new RobotZone(nom,id);
        rz.myRobot = this;
        plateformeActuelle = Plateforme.listePlateforme.get(0);
        plateformeDefaut = Plateforme.listePlateforme.get(0);
        
        int i = 0;
        while(GameArenaPanel.plateforme.etatCases.get(i)){
            i++;
        }
        int carré = i+1;
        int x = plateformeActuelle.getX();
        int y = plateformeActuelle.getY();
        // Sert à placer le fichier dans la bonne case //
        if(carré<=3){
            for(int j=1; j<carré;j++){
                x += plateformeActuelle.getXC()/2;
                y += plateformeActuelle.getYC()/2;
            }
        }
        else if(carré <= 6){
            x += plateformeActuelle.getXC()/2;
            y -= plateformeActuelle.getYC()/2;
                
            for(int j=4; j<carré;j++){
                x += plateformeActuelle.getXC()/2;
                y += plateformeActuelle.getYC()/2;
            }
        }
        else if(carré <= 9){
            x += plateformeActuelle.getXC();
            y -= plateformeActuelle.getYC();
            for(int j=7; j<carré;j++){
                x += plateformeActuelle.getXC()/2;
                y += plateformeActuelle.getYC()/2;
            }
        }
        ////////////////////////////////////////////
        rm = new RobotModel(x ,y);
        cell = carré;
        cellDefaut = carré;
        //rm = new RobotModel(plateformeActuelle.getX() + (int) (0.5*Plateforme.XC)*i , plateformeActuelle.getY() + (int) (0.5*Plateforme.YC)*i);
        plateformeActuelle.etatCases.set(cell-1, true);
            
        
        
        rm.myRobot = this;
        listeRobot.add(this);
        
    }

    public RobotZone getRobotZone(){
        return rz;
    }

    public RobotModel getRobotModel(){
        return rm;
    }
    public int getCell() {
        return cell;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nvNom){
        nom = nvNom;
    }
    public int getId(){
        return id;
    }

    public Plateforme getPlateforme() {
        return plateformeActuelle;
    }
    public void setPlateforme(Plateforme nvPlateforme) {
        plateformeActuelle = nvPlateforme;
    }

    public int getX() {
        return X;
    }
    public void setX(int nvX){
        this.X = nvX;
    }
    public int getT() {
        return T;
    }
    public void setT(int nvT){
        this.T = nvT;
    }
    public int getF() {
        return F;
    }
    public int getM(){
        return M;
    }
    public static RobotIG getById(int idRecherche){
        for(RobotIG r : listeRobot){
            if (idRecherche == r.id) {
                return r;
            }
        }
        return null;
    }
    public FichierIG getFichier(){
        return myFichier;
    }
    public void setFichier(FichierIG fichier){
        this.myFichier = fichier;
    }
    public void linkG(int nombre){

        //Verification des places libres dans la plateforme
        int carré = 0;
        for(Plateforme p : Plateforme.listePlateforme){
            if (p.getNom() == nombre) {
                for(int i=1 ; i< 9 +1 ; i++){
                    if(!p.etatCases.get(((i % 9 == 0) ? 9 : i % 9)-1)){
                        carré = ((i % 9 == 0) ? 9 : i % 9);
                        getRobotModel().moveTo(p, carré);
                        return;
                    } 
                }
                System.err.println("Pas de place libre : LinkG RobotIG");
            }
        }
        
    }
    public void grabG(int nombre){
        
        for(FichierIG f : FichierIG.listeFichier){
            
            if(f.getNom() == nombre){
                
                if (f.getRobot() != null) {
                    System.out.println("deja pris");
                    return;
                }
                
                rm.moveTo(f.getPlateforme(),f.getCell());
                
                Thread thread = new Thread(() -> {
                    synchronized (lock) {
                        try {
                            lock.wait();
                            // Changement de sprite quand on termine le mouvement//
                            f.getFichierModel().fichierSprite = null;
                            f.setRobot(this);
                            setFichier(f);
                            myRobot.holdFile = true;
                            if (getFichier().getFichierZone() != null) {
                                try {
                                    getFichier().getFichierZone().fichierSprite = ImageIO.read(getClass().getResource("img/bouteillezonegrab.png"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            ///////////////////////////////////////////////////////
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start(); 
            }
            
        }
        
    }
    public void dropG(){
        /*while (rm.isMoving()) {
            rm.repaint();
        }*/

        if(myFichier == null){
            System.err.println("Pas de fichier à drop");
            return;
        }
        myFichier.getFichierModel().tpTo(plateformeActuelle,cell+1);
        myRobot.holdFile = false;
        myFichier.setRobot(null);
        myFichier = null;
        
    }
}