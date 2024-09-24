# 🌟 Projet de Gestion de Rénovation de Cuisine 🌟

Bienvenue dans l'application de gestion de projets de rénovation de cuisine, qui permet de gérer les **clients**, les **projets**, et de générer des **devis**. Ce projet utilise **Java** et **PostgreSQL** pour la gestion des données.

---

## 📂 Structure du projet

- **`utils/`** : Outils pour le parsing et les validations (ex: conversion des chaînes en types numériques).
- **`main/`** : Contient la classe principale `Main.java`, qui lance l'application et affiche le menu.

---

## 📊 Modèles de données

### 👤 Client
Un client possède les attributs suivants :
- `id` : Identifiant unique.
- `nom` : Nom du client.
- `adresse` : Adresse.
- `telephone` : Numéro de téléphone.

### 🏗️ Projet
Un projet de rénovation avec ces attributs :
- `id` : Identifiant unique.
- `nomProjet` : Nom du projet.
- `surfaceCuisine` : Surface de la cuisine en m².
- `margeBeneficiaire` : Marge bénéficiaire.
- `coutTotal` : Coût total estimé.
- `etatProjet` : État du projet (`En_cours`, `Terminé`).
- `clientId` : Référence au client associé.

### 📝 Devis
Un devis pour un projet spécifique :
- `id` : Identifiant unique.
- `montantEstime` : Montant estimé.
- `dateEmission` : Date d'émission.
- `dateValidite` : Date de validité.
- `estApprouve` : Approuvé ou non.

---

## 🔧 Services

### `ClientService`
- Gère les clients : Ajout, mise à jour, suppression, récupération.

### `ProjectService`
- Gère les projets : Création, ajout de matériaux et main-d'œuvre, génération de devis.

### `DevisService`
- Gère les devis : Ajout, récupération et association avec les projets.

---

## 💡 Exemples d'utilisation

### ➕ Ajouter un client
1. Saisissez les informations du client (nom, adresse, téléphone).
2. Le programme valide l'entrée et enregistre le client dans la base de données.

### 🏗️ Créer un projet
1. Fournissez les informations suivantes :
    - Nom du projet
    - Surface de la cuisine en m²
    - Marge bénéficiaire
    - Coût total
    - État du projet (`En_cours` ou `Terminé`)
2. Le projet sera ajouté et lié au client correspondant.

### 📝 Générer un devis
1. Fournissez :
    - Date d'émission
    - Date de validité
    - Montant estimé
2. Confirmez l'enregistrement du devis dans la base de données.

---

## 📦 Dépendances

Le projet utilise les bibliothèques suivantes :
- **PostgreSQL JDBC Driver** : Pour connecter la base de données.
- **Maven** : Pour la gestion des dépendances et la compilation.

---


## 🛠️ Fichier Exécutable

Le fichier exécutable JAR se trouve dans le dossier `out`. Vous pouvez exécuter l'application en utilisant la commande suivante dans votre terminal :

```bash
java -jar out/artifacts/Bati_Cuisine_jar/Bati-Cuisine.jar
```
 ---
## 🚀 Améliorations futures

- 🎨 Intégration d'une interface graphique (GUI) pour une meilleure expérience utilisateur.
- 🔐 Gestion avancée des rôles (administrateurs, clients).
- 🖨️ Exportation des devis au format PDF.
- 🔔 Système de notifications pour les clients.

---

## 👩‍💻 Auteurs

- **Oumaima** - Développeuse principale.

---

## 📜 Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

