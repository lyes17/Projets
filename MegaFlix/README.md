# 🎥 MegaFlix

**MegaFlix** est une application de gestion de films avec une interface graphique intuitive, permettant de gérer une collection de films, de rechercher des titres, de trier par catégories et d'afficher des informations détaillées sur chaque film.

## 🛠️ Fonctionnalités

- Ajout, suppression et modification de films dans la collection.
- Recherche par titre, genre ou année de sortie.
- Classement et tri des films par critères (date, nom, etc.).
- Interface graphique ergonomique et facile à utiliser (développée avec **PyQt5** en Python).
- Sauvegarde des données dans une base de données locale.

## ⚙️ Technologies utilisées

- **Langage de programmation** : Python
- **Interface graphique** : PyQt5
- **Base de données** : Postgresql
- **Gestion des données** : psycopg2

## 🌟 Instructions pour exécuter le projet

1. **Cloner le dépôt Git :**
   ```bash
   git clone https://github.com/lyes17/Projets.git
2. **Configurer la base de données :**
    Avant d'exécuter le projet, configurez la connexion à la base de données PostgreSQL
        
        psycopg2.connect(
        dbname="",       
        user="",         
        password="",   
        host="localhost",
        port="5432"
        )
        
        dbname : Le nom de votre base de données.
        user : Votre nom d'utilisateur PostgreSQL.
        password : Votre mot de passe PostgreSQL.
        host : L'adresse de votre serveur PostgreSQL (par défaut localhost).
        port : Le port PostgreSQL (par défaut 5432).
3. **Créer la base de données :**
    -Connectez-vous à PostgreSQL :
        psql -U <votre_nom_utilisateur>
    -Créez la base de données:
        \i MegaFlix-schema-data.sql
        \i MegaFlix-data.sql
4. **Exécuter le projet :**  
    python3 interface.py

## ⚠️ Notes importantes
    -Assurez-vous que PostgreSQL est installé et en cours d'exécution sur votre machine.
    -Installez les dépendances nécessaires (psycopg2, pyqt5 ...) avec pip install 

## 📝 Auteur
    Développé par Lyes SI DJILLALI, Nassim AMROUCHE, Lounes SAMAH.




