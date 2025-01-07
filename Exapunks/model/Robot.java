package model;
import view.*;
import java.util.ArrayList;

public class Robot implements Cloneable
{
    private String robotName;
    public boolean holdFile;
    private ArrayList<Commande> commandes = new ArrayList<>();
    private Piece pieceAct;
    private int X;
    private int T;
    private Fichier F;
    public static int M; 
    public static ArrayList<Robot> robots = new ArrayList<>();
    private String texte;

    private RobotIG robotGraphique;
    


    /////////////////////////////////
            //Constructors//
    /////////////////////////////////
    public Robot(String name, Piece piece)
    {
        if(name == null)
        {
            throw new NullPointerException("nom == null!!\n");
        }
        this.robotName = name;
        this.holdFile = false;
        
        this.X = 0;
        this.T = 0;
        this.F = null;
        M = 0;

        this.pieceAct =  piece;
        // Liaison avec le robot graphique
        robotGraphique = new RobotIG();
        robotGraphique.myRobot = this;
        ////////////////////////////////////
        robots.add(this);// Ajouter le robot à la liste statique lors de sa création
    }

    /*public Robot(String name,ArrayList<Instructions> commandes2,Piece pieceA,int x,int t,Ficheir f,int m,boolean globalMode)
    {
        if(name == null || pieceA == null || hold == null || pos == null || commandes2 == null)
        {
            throw new NullPointerException("Arguments null !!\n");
        }
        this.robotName = name;
        commandes = new ArrayList<Instructions>(commandes2);
        this.pieceAct = pieceA;
        this.X = x;
        this.F = f;
        this.M = m;
        this.T = t;
        if(f != null)
        {
            this.holdFile = true;
        }
        else
        {
            this.holdFile = false;
        }

        this.mGlobalMode = globalMode;
        // Liaison avec le robot graphique
        robotGraphique = new RobotIG();
        this.robotGraphique.myRobot = this;
        ////////////////////////////////////
        robots.add(this);// Ajouter le robot à la liste statique lors de sa création
    }*/

    ///////////////////////////////
            //GETTERS//
    ///////////////////////////////        

    public String getName()
    {
        return robotName;
    }

    public boolean getholdFile()
    {
        return holdFile;
    }   

    public ArrayList<Commande> getCommandes()
    {
        return commandes;
    }
    
    public int getM()
    {
        return M;
    }
    public void setM(int nvM)
    {
        M = nvM;
    }

    public int getX()
    {
        return X;
    }
    public void setX(int nvX)
    {
        X = nvX;
    }

    public int getT()
    {
        return T;
    }
    public void setT(int nvT)
    {
        T = nvT;
    }

    public Fichier getF()
    {
        return F;
    }


    public Piece getPieceAct()
    {
        return pieceAct;
    }
    public String getTexte()
    {
        return texte;
    }
    public void setTexte(String nvTexte){
        texte = nvTexte;
    }

    public static ArrayList<Robot> getRobots() {
        return robots;
    }
    public RobotIG getRobotGraphique(){
        return robotGraphique;
    }
    
    //////////////////////////////////////////
            //Clone,HashCode,Equals//
    //////////////////////////////////////////        
    /*public Robot clone()
    {
        Robot leClone = null;
        try{
            leClone = (Robot) super.clone();
        }catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
        leClone.robotName = this.robotName;
        leClone.holdFile = this.holdFile;
        leClone.commandes = (ArrayList<Instructions>) commandes.clone();
        leClone.pieceAct = this.pieceAct.clone();
        leClone.X = this.X;
        leClone.F = this.F.clone();
        leClone.T = this.T;
        leClone.M = this.M;
        return leClone;
    }*/

    /*public int hashCode()
    {
        return robotName.hashCode() + position.hashCode() + commandes.hashCode();
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Robot))
        {
            return false;
        }
        Robot neR = (Robot) obj;
        if(!(this.pieceAct.equals(neR.pieceAct)) || !(this.position.equals(neR.position)) || !(this.F.equals(neR.F)))
        {
            return false;
        }
        ArrayList<Instructions> l = new ArrayList<Instructions>(neR);
        for(Instructions elt : this)
        {
            if(!l.remove(elt))
            {
                return false;
            }
        }
        return (l.isEmpty() && this.robotName.equals(neR.robotName) &&
                this.holdFile.equals(neR.holdFile) && this.X.equals(neR.X) 
                 && this.T.equals(neR.T) && this.M.equals(neR.M));
    }*/

    ////////////////////////////////////////
            //INSTRUCTIONS//
    ////////////////////////////////////////
    
    public void link(int nPiece)
    { 
        for (Piece p : Piece.listePiece){
            if (nPiece == Integer.parseInt(p.getNomPiece())) {
                pieceAct = p;
                p.removeRobot(this);
                getRobotGraphique().linkG(nPiece);
            }
        }
    }

    public void grab(int nFichier)
    {
        if(this.holdFile == true)
        {
            System.out.println("impossible de prendre le fichier !!\n");
            return;
        }
        System.out.println(getPieceAct().fichiers);
        for(Fichier f : getPieceAct().fichiers){
            if(f.getName() == nFichier){
                if (f.getRobot() != null) {
                    System.err.println("impossible de prendre le fichier !!\n");
                    return;
                }
                
                F = f;
                this.holdFile = true;
                f.setRobot(this);
                System.out.println("cbn ?");
                getPieceAct().removeFichier(f);
                getRobotGraphique().grabG(nFichier);
                return;
            }
        }
       
        if(this.holdFile == false)
        {
            System.err.println("Fichier introuvable !!\n");
        }
    }

    public void drop()
    {
        if(this.holdFile == false)
        {
            System.out.println("aucun fichier a deposer !! \n");
            return;
        }
        getPieceAct().addFichier(F);
        F.setRobot(null);
        this.F = null;
        this.holdFile = false;
        robotGraphique.dropG();
        
    }
                    
    public void copy(String copié,String copieur) 
    {
        int entrée = 0;
        switch (copié) {
            case "X":
                entrée = X;
                break;
            case "T":
                entrée = T;
                break;
            case "M":
                entrée = M;
                break;
            case "F":
                entrée = Integer.parseInt(F.contenu.get(F.getPositionCurseur()));
                break;
            default:
                break;
        }
        switch (copieur) {
            case "X":
                X = entrée;
                break;
            case "T":
                T = entrée;
                break;
            case "M":
                M = entrée;
                break;
            case "F":
                try{
                    F.contenu.set(F.getPositionCurseur(), String.valueOf(entrée));
                }catch (IndexOutOfBoundsException e) {
                    F.contenu.add(String.valueOf(entrée));
                    F.decrCurseur();
                }
                
                F.incrCurseur();
                break;
            default:
                break;
        }
        
    }

    public void wipe()
    {
        System.out.println("bien effacé ce feur là avec un chien");
        if(F.testOEF() != true)
        {
            F.clear();
            System.out.println("AVEC UN CHIEN");
        }
    }
    
    public void addi(String r1,String r2,String r3)
    {
        int ajouté1 = 0;
        int ajouté2  = 0;
        switch (r2) {
            case "X":
                ajouté1 = X;
                break;
            case "T":
                ajouté1 = T;
                break;
            case "M":
                ajouté1 = M;
                break;
            case "F":
                ajouté1 = Integer.parseInt(F.contenu.get(F.getPositionCurseur()));
                break;
            default:
                break;
        }
        switch (r3) {
            case "X":
                ajouté2 = X;
                break;
            case "T":
                ajouté2 = T;
                break;
            case "M":
                ajouté2 = M;
                break;
            case "F":
            try {
                ajouté2 = Integer.parseInt(F.contenu.get(F.getPositionCurseur()));
            } catch (IndexOutOfBoundsException e) {
                ajouté2 = Integer.parseInt(F.contenu.get(F.contenu.size()-1));
            }
                
                break;
            default:
                break;
        }
        switch (r1) {
            case "X":
                X = ajouté1 + ajouté2;
                break;
            case "T":
                T = ajouté1 + ajouté2;
                break;
            case "M":
                M = ajouté1 + ajouté2;
                break;
            case "F":
                int res = ajouté1 + ajouté2;
                try{
                    F.contenu.set(F.getPositionCurseur(),String.valueOf(res));
                }catch (IndexOutOfBoundsException e) {
                    F.contenu.add(String.valueOf(res));
                }
            
                
                break;
            default:
                break;
        }
    }

    public void muli(String r1,String r2,String r3)
    {
        copy(r2, r1);
        int multiplieur=1;
        switch (r3) {
            case "X":
                multiplieur = X;
                break;
            case "T":
                multiplieur = T;
                break;
            case "M":
                multiplieur = M;
                break;
            case "F":
                multiplieur = Integer.parseInt(F.contenu.get(F.getPositionCurseur()));
                break;
            default:
                break;
        }
        for(int i = 1; i < multiplieur;i++){
            addi(r1, r1, r2);
        }
    }

    public void subi(String r1,String r2, String r3)
    {
        copy(r2, r1);
        int enlevé = 0;
        switch (r3) {
            case "X":
                enlevé = X;
                break;
            case "T":
                enlevé = T;
                break;
            case "M":
                enlevé = M;
                break;
            case "F":
                enlevé = Integer.parseInt(F.contenu.get(F.getPositionCurseur()));
                break;
            default:
                break;
        }
        switch (r1) {
            case "X":
                X -= enlevé;
                break;
            case "T":
                T -= enlevé;
                break;
            case "M":
                M -= enlevé;
                break;
            case "F":
                int res = Integer.parseInt(F.contenu.get(F.getPositionCurseur())) - enlevé;
                F.contenu.set(F.getPositionCurseur(),String.valueOf(res));
                break;
            default:
                break;
        }
    }

    public void halt()
    {   
        System.out.println(Fenetre.niveauActuel.testNiveau(Fenetre.niveau));
        System.out.println(Fenetre.niveau);
        if(Fenetre.niveauActuel.testNiveau(Fenetre.niveau)){
            Fenetre.gameArenaPanel.afficherVictoire();
            System.out.println("Gagné !!!!!!!!!!!!!!!!!!");
            return;
        }
    }

    
    // Méthode pour effectuer le test MRD

    /*public void testMRD() {
        // Vérifier si ce EXA pourrait lire d'un autre EXA sans pause
        // Pour l'instant, on suppose que si le registre M est en mode local, alors le test échoue (T = 0)
        // Sinon, le test réussit (T = 1)
        if (!mGlobalMode) {
            T = 0; // Registre M en mode local, donc le test échoue
        } else {
            T = 1; // Registre M en mode global, donc le test réussit
        }*/

        public void kill(Robot target) {
            // Vérifier si le robot cible est null ou s'il est égal à lui-même
            if (target == null || target == this) {
                System.out.println("Impossible de tuer le robot spécifié.");
                return;
            }
    
            // Supprimer le robot cible de la liste des robots
            if (robots.contains(target)) {
                target.drop();
                robots.remove(target);
                System.out.println("Le robot " + target.getName() + " a été tué.");
            } else {
                System.out.println("Le robot spécifié n'est pas présent dans la liste.");
            }
        }
        
}



    


