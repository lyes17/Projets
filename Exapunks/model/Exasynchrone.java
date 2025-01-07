package model;
import java.util.ArrayList;
import java.util.Random;

public class Exasynchrone {
    private Random random;

    public Exasynchrone(long seed) {
        // Initialiser la graine pour la génération pseudo-aléatoire
        random = new Random(seed);
    }

    public ArrayList<Robot> modeAsynchroneDiscret() {
        // Création d'une nouvelle liste pour stocker la liste mélangée
        ArrayList<Robot> robotsCopie = new ArrayList<>(Robot.robots);
        ArrayList<Robot> robotsOrdre = new ArrayList<>();

        // Mélanger la liste mélangée
        while (robotsOrdre.size() != Robot.robots.size()) {
            int index = random.nextInt(robotsCopie.size());
            robotsOrdre.add(robotsCopie.get(index));
            robotsCopie.remove(index);
        }

        // Retourner la liste mélangée
        return robotsOrdre;
    }

    public ArrayList<Robot> modeAsynchroneDiscretSimultane() {
        // Création d'une nouvelle liste pour stocker la liste mélangée
        ArrayList<Robot> robotsCopie = new ArrayList<>(Robot.robots);
        ArrayList<Robot> robotsOrdre = new ArrayList<>();

        // Création d'un objet Random
        Random random = new Random();

        // Mélanger la liste mélangée
        while (robotsCopie.size() != 0) {
            int index = random.nextInt(robotsCopie.size());
            if(random.nextDouble() < 0.5){
                robotsOrdre.add(robotsCopie.get(index));
            }
            robotsCopie.remove(index);
        }

        // Retourner la liste mélangée
        return robotsOrdre;
    }

       

    
    }
