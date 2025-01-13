# Estate

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

## Start the project

Git clone:

> git clone https://github.com/OpenClassrooms-Student-Center/P3-Full-Stack-portail-locataire

Go inside folder:

> cd P3-Full-Stack-portail-locataire

Install dependencies:

> npm install

Launch Front-end:

> npm run start;


## Ressources

### Mockoon env

Download Mockoon here: https://mockoon.com/download/

After installing you could load the environement

> ressources/mockoon/rental-oc.json

directly inside Mockoon 

> File > Open environmement

For launching the Mockoon server click on play bouton

Mockoon documentation: https://mockoon.com/docs/latest/about/

### Postman collection

For Postman import the collection

> ressources/postman/rental.postman_collection.json 

by following the documentation: 

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman


### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`


# ChâTop
Développez le back-end en utilisant Java et Spring

### Téléchargement
1. Téléchargez ou clonez le projet.
2. Nécessite les éléments suivants :
    - Angular 14
    - Apache Maven
    - Java 11 ou 17
    - NodeJS
    - PHP
    - PHPMyAdmin

### Installation
1. Effectuez la commande : `npm install` à la racine
2. Effectuez la commande : `npm run build` à la racine
3. Effectuez la commande : `php bin/console doctrine:database:create` à la racine du projet
4. Effectuez la commande : `php bin/console make:migration` à la racine du projet
5. Effectuez la commande : `php bin/console doctrine:migrations:migrate` à la racine du projet
6. Effectuez la commande : `php bin/console doctrine:fixtures:load` à la racine du projet
7. Lancez le projet avec la commande : `symfony serve`

### Configuration
1. Configurez le fichier .env
2. Effectuez la commande : `composer install` à la racine
3. Effectuez la commande : `npm install` à la racine
4. Effectuez la commande : `npm run build` à la racine
5. Effectuez la commande : `php bin/console doctrine:database:create` à la racine du projet
6. Effectuez la commande : `php bin/console make:migration` à la racine du projet
7. Effectuez la commande : `php bin/console doctrine:migrations:migrate` à la racine du projet
8. Effectuez la commande : `php bin/console doctrine:fixtures:load` à la racine du projet
9. Lancez le projet avec la commande : `symfony serve`