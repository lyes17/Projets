package model;
import java.util.ArrayList;


public class Piece implements Cloneable
{   
    public static ArrayList<Piece> listePiece = new ArrayList<>();
    public String nomPiece;
    private int idPiece;
    public ArrayList<Robot> robots = new ArrayList<>();
    public ArrayList<Fichier> fichiers = new ArrayList<>();
    private String pieceSuiv;
    public int uniqPiece;
    public static int nbPieceTotal = 0;
    //////////////////////////////
            //Constructors//
    //////////////////////////////        
    public Piece(String n)
    {
        /*if(Integer.valueOf(n) != 800 || Integer.valueOf(n) != 799)
        {
            throw new IllegalArgumentException("Arguments illegaux !!\n");
        }*/
        this.nomPiece = n;
        robots = new ArrayList<Robot>();
        fichiers = new ArrayList<Fichier>();
        this.pieceSuiv = null;
        listePiece.add(this);
        idPiece = listePiece.size();
        nbPieceTotal++;
        uniqPiece = nbPieceTotal;
    }

    public Piece(String n,String pS)
    {
        if(Integer.valueOf(n) != 800 || Integer.valueOf(n) != 799)
        {
            throw new IllegalArgumentException("Arguments illegaux !!\n");
        }
        this.nomPiece = n;
        this.pieceSuiv = pS;
        listePiece.add(this);
        idPiece = listePiece.size();
    }
        ///////////////////////
            //Getters//
        ///////////////////////
    public String getNomPiece()
    {
        return nomPiece;
    }        

    public int getIdPiece()
    {
        return idPiece;
    }

    public String getPieceSuiv()
    {
        return pieceSuiv;
    }

    public ArrayList<Fichier> getFichiers()
    {
        return fichiers;
    }

    public ArrayList<Robot> getRobots()
    {
        return robots;
    }
    public void addRobot(Robot nvRobot){
        this.robots.add(nvRobot);
    }
    public boolean removeRobot(Robot robot){
        return robots.remove(robot);
    }
    public void addFichier(Fichier nvFichier){
        this.fichiers.add(nvFichier);
    }
    public boolean removeFichier(Fichier fichier){
        return fichiers.remove(fichier);
    }
    

            ///////////////////////////////////
                //Clone,Equals,HashCode//
            //////////////////////////////////
    
    public Piece Clone()
    {
        Piece leClone = null;
        try
        {
            leClone = (Piece) super.clone();
        }catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
        leClone.nomPiece = this.nomPiece;
        leClone.idPiece = this.idPiece;
        leClone.pieceSuiv = this.pieceSuiv;
        return leClone;
    }        

    /*public boolean equals(Object obj)
    {
        if(!(obj instanceof Piece))
        {
            return false;
        }
        Piece neP = (Piece) obj;
        if(!this.nPiece.equals(neP.nPiece) || !this.pieceSuiv.equals(neP.pieceSuiv))
        {
            return false;
        }
        ArrayList<Fichier> l = new ArrayList<Fichier>(neP);
        for(Fichier elt : this)
        {
            if(!l.remove(elt))
            {
                return false;
            }
        }
        return (l.isEmpty() && this.idPiece == neP.idPiece
                && this.nbRobot == neP.nbRobot && this.nbFichier == neP.nbFichier);
    }*/

   /*public int hashCode()
    {
        return (nPiece.hashCode() + 31*idPiece + 31*nbRobot 
        + 31*nbFichier + pieceSuiv.hashCode() + fichiers.hashCode());
    }*/

    public void halt(String name)
    {
        for(Robot r:robots)
        {
            if(r.getName().equals(name))
            {
                robots.remove(r);
            }
        } 
    } 
}
