# ChâTop
Développez le back-end en utilisant Java et Spring

### 🔄 Téléchargement 🔄
1. Téléchargez ou clonez le projet.
2. Nécessite les éléments suivants :
    - Angular 14
    - Apache Maven
    - Java 11 ou 17
    - NodeJS
    - PHP
    - PHPMyAdmin

### 💻 Installation 💻
1. Effectuez la commande : `npm install` à la racine
2. Effectuez la commande : `maven clean install` dans le répertoire back-end/Chatop

### ⚙️ Configuration ⚙️
1. Créez vos variables d'environnements :
1.1 `P3_CHATOP_API__JWT` ayant pour valeur une clé de cryptage de 256bits
1.2 `SPRING_DATASOURCE_USERNAME` ayant pour valeur l'identifiant d'accès à votre base de données
1.3 `SPRING_DATASOURCE_PASSWORD` ayant pour valeur le mot de passe d'accès à votre base de données
2. Configurez le fichier `application.properties`:
2.1 Mettez vos informations de base de données
2.2 Choississez le répertoire où seront stockés vos photos (vous pouvez laissez tel quel)
3. Importez sur votre base de données le fichier SQL présent dans `ressources/sql/script.sql`

### ✅ Démarrage ✅
1. Effectuez la commande : `npm run start` à la racine
2. Effectuez la commande `mvn spring-boot:run` dans le répertoire back-end/Chatop