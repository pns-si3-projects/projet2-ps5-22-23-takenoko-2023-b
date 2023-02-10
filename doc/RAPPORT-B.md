# Rapport d'équipe B "Les Gourmands"
## 1. Point d'avancement
### Un résumé des **fonctionnalités** réalisées
> Toutes les fonctionnalités ont été réalisées pour le jeu de base. Cependant, les joueurs sont obligés de réaliser la météo, ils n'ont pas le choix.

### Un résumé de ce qui a été fait pour les logs
> Pour les logs nous nous y sommes pris au début de la semaine nous avons adopté les sorties sur la console que nous avions afin de les afficher avec un `Logger`. 

> Tous les messages sont affichés sur la sortie standard notamment quand on utilise l'argument `--demo` de notre programme.

> Pour les autres arguments aucun log n'est affiché, a l'exception des logs récapitulatifs qui sont affichés pour connaitre les résultats de l'ensemble des parties.

### Un résumé de ce qui a été fait pour les statistiques en **CSV**
> Tout d'abord, on récupère les données pour savoir si le fichier CSV existe pour créer un `CSVStats`. La classe `CSVStats` est utilisée pour stocker et mettre à jour les données avec les nouvelles données des parties et le merger avec les anciennes données s'il y en a. Les données contiennent des informations sur les types de joueurs, les victoires, les défaites, les matchs nuls, le score moyen et le nombre moyen d'objectifs de chaque joueur.

### Un résumé de ce qui a été fait pour les bots

> Pour le bot qui nous a été demandé, nous nous sommes basés sur les fonctionnalités que nous proposaient déjà le `BotMoyen`.

> Pour la fonctionnalité du choix de la météo, nous avons décidé de lui faire choisir la météo nuage afin qu'il puisse prendre un bassin dans les aménagements. Et ce tant que nous sommes avant le tour 5.

> Pour nuire aux autres joueurs, le `BotMVP` va regarder quel type d'objectif est le plus présent parmi ceux que possède tous les autres joueurs et jouer en fonction :
> - S'il y a plus d'objectif panda : il va déplacer le panda sur une parcelle avec peu de bambou. Il va de plus ne pas déplacer le jardinier.
> - S'il y a plus d'objectif jardinier, il va déplacer le jardinier sur des parcelles avec 3 bambous ou plus.
> - S'il y a plus d'objectif parcelle : il va poser des parcelles qui vont lui permettre de valider ses objectifs plus rapidement. Comme les objectifs parcelles sont uniques, jouer pour lui va lui permettre d'empêcher les autres joueurs de valider leurs objectifs.

> Le `BotMVP` est meilleur, car lorsqu'il doit prendre des décisions, il va éviter d'aider ses adversaires, contrairement au `BotMoyen` qui lui va seulement jouer pour réaliser ses objectifs sans prendre en compte les autres joueurs. De plus dans le pire des cas il va prendre les décisions que ferait le `BotMoyen`.

> Nous avons par ailleurs réalisé 3 bots similaires au bot moyen dans l'exécution de leurs actions, mais qui vont cependant donner la priorité à un certain type d'objectif.

## 2. Architecture et qualité
### Comment est faite l'architecture du projet ? Et quels choix nous ont amené à la réaliser ainsi ?
> Notre classe `Jeu` distribue des ordres au plateau et aux joueurs. Elle s'occupe du déroulement de la partie

> Pour le positionnement des parcelles, on a décidé de leur donner des coordonnées sur 2 valeurs respectivement le numéro de ligne et de colonne. 
> - Nous stockons les positions des parcelles présentes sur le plateau dans une `ArrayList`.
> - Les parcelles quant à elles sont contenues dans un double tableau pour y faciliter l'accès.

> Le positionnement des irrigations est réalisé à l'aide des tuples de position qui donne la bordure entre les deux positions. On stocke ces bordures dans une `ArrayList`.

> On n'a pas créé de classe irrigation ou bambou, car elles auraient été vides, on a choisi d'utiliser des compteurs.

### Où trouver les infos ?
> Nous n'avons pas documenté la plupart de notre code, la meilleure façon est de relire le fonctionnement des tests pour comprendre les différentes méthodes.

### État de la base de code 
> Les parties que nous estimons bien développer sont par exemple :
> - Plateau : Notre implémentation avec un tableau et un enum de directions nous permet de **facilement** et **instantanément** trouver les cases adjacentes **sans avoir à parcourir de listes**.
> - BotMoyen : optimise et réalise souvent de bon choix afin de remporter la partie *(1bot moyen vs 1bot random => >95% de victoires)*.
Il ne fait **aucun choix aléatoire** sauf s'il n'y a pas de bon choix.
> - Grâce à notre implémentation des différents éléments de jeu nos parties s'exécute rapidement *(0.7 sec/parties avec logs) (2000 parties en - de 15sec).*

Cependant :
> - Les bots ont trop de responsabilités, car ils choisissent et accomplissent les actions par eux-mêmes, ils gèrent aussi leur inventaire.
> - Les classes qui ne sont pas bien développer sont les classes `Jeu` et `JeuLanceur` car c'est sur ces classes que nous manquons de tests. Cela est dû au fait que les fonctions sont trop complexes ce qui rend la creation de tests complique. La sortie Sonar nous montre bien que ce sont les classes avec le plus de lignes non-couverte par les tests. Pour remédier à cela nous devrions séparer les classes et diminue la complexité des fonctions.
> - Nos méthodes n'ont pas des noms assez explicites


## 3. Processus
###  Qui est responsable de quoi / qui a fait quoi ?

> - **Timothée :** ce qui concerne le plateau et le positionnement des parcelles, les personnages. Il a développé les bases du `BotMoyen`. Il s'est occupé de la gestion des arguments grâce à `JCommandeur` ainsi que le `Main` et le `JeuLanceur`. Il s'est occupé de faire les tests manquants notamment ceux avec les `Mocks`.

 >- **Yael :** Amélioration du `BotMoyen` suite à implementation des nouveaux elements du jeu jusqu'à la version définitive du botMoyen à partir du model de Timothée. Il a travaillé sur le `BotMVP`. Ainsi que revoir les codes qui ont ete produis.

>- **Tu :** Création et évolution de la classe Jeu qui gère le déroulement de la partie. Elle a travaillé sur l'implémentation des bambous avec les objectifs associés, de la météo ainsi que des aménagements. Elle a mis à jour le `BotRandom` à chaque nouvel élément implémenté. Elle a créé un logger et à remplacer les affichages de la console par celui-ci. Elle s'est occupé de la création des Stats et de leur écriture dans un fichier `.csv`. Elle a participé à la réalisation du `BotMVP`

>- **Marco :** S'est occupé de l'implementations des objectifs, notamment parcelles et Jardiniers. Ainsi que de l'implémentation des irrigations. Il a passé une grande partie du projet à réaliser des tests et à refactor des méthodes mal écrites.

### Quel est le process de l'équipe

> On a une branche `Dev` sur laquelle nous avons merge les codes de nos autres branches avant de pousser sur `master` une version stable du projet. Pour chaque issue nous avons cree une branche en rapport avec celle-ci et pour le merge vers `master` nous nous sommes assurés d'avoir un jeu bien complet qui fonctionne.
