package model;
import view.*;
import java.util.ArrayList;
import java.util.List;  

// Classe FichierAbstrait
public class Fichier {

    public ArrayList<String> contenu;
    public ArrayList<String> contenuDefaut;
    protected int nom ; 
    private int curseur;
    private FichierIG fichierGraphique;
    private Piece pieceActuelle;
    private Robot robotActuel;

    public static ArrayList<Fichier> listeFichier = new ArrayList<>();


    public Fichier(int nom,Piece piece,ArrayList<String> listeNombres) {
        contenu = listeNombres;
        contenuDefaut = new ArrayList<>(listeNombres);
        this.nom=nom; 
        this.curseur = 0;
        this.pieceActuelle = piece;
        fichierGraphique  = new FichierIG(nom,pieceActuelle.getIdPiece());
        fichierGraphique.myFichier = this;
        listeFichier.add(this);
    }

    public int getName(){
        return nom; 
    }
    public List<String> getTexte(){
        return contenu;
    }
    public int getPositionCurseur() {
        return curseur;
    }
    public void incrCurseur(){
        curseur++;
    }
    public void decrCurseur(){
        curseur--;
    }
    public void zeroCurseur(){
        curseur= 0;
    }
    public Robot getRobot(){
        return robotActuel;
    }
    public void setRobot(Robot nvRobot){
        robotActuel = nvRobot;
    }

    public void restartDonnees(){
        System.out.println(contenu);
        System.out.println(contenuDefaut);
        contenu = new ArrayList<>(contenuDefaut);
        System.out.println(contenu);
        System.out.println(contenuDefaut);
        
    }

    
    public boolean ecrire(String val){
        if (val == null) {
            throw new IllegalArgumentException("L'élément ne peut pas être null");
            }
        return contenu.add(val);
    }

    public  boolean rechercher(String contenu){
        return this.contenu.contains(contenu);
    }

    public boolean testOEF(){

        return this.contenu.isEmpty();
    } 
    public String lire() {
        if (testOEF()) {
            return "fichier est vide";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String element : contenu) {
                sb.append(element).append(",");
            }
            return sb.substring(0, sb.length() - 1);
        }
    }
    public String lireMot() {
        if (curseur < contenu.size()) {
            String mot = contenu.get(curseur);
            curseur++;
            return mot;
        } else {
            return null; 
        }
    }


    public String lirePartie(int nombreDeMots) {
        StringBuilder partie = new StringBuilder();
        for (int i = 0; i < nombreDeMots; i++) {
            String mot = lireMot();
            if (mot != null) {
                partie.append(mot).append(",");
            } else {
                break; 
            }
        }
        return partie.toString().trim(); 
    }

    public void clear() {
        contenu.clear();
    }
   
}
