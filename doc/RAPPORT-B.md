## 1. Point d'avancement
### Un résumé des **fonctionnalités** réalisées sur tout le jeu (couverture des règles du jeu indépendamment de ce qui était demandée cette semaine), et éventuellement la liste de ce qui n'a pas été fait
> Toute les fonctionnalitées ont été réalisé, pour le jeu de base, les joueurs sont obligés de réaliser la méteo, ils n'ont pas le choix.

### Un résumé de ce qui a été fait pour les logs (en quelques lignes max, quels choix ont été faits pour les réaliser)
> Pour les logs nous nous y sommes pris au début de la semaine nous avons adopté les sorties sur la console que nous avions afin de les affichers avec un `Logger`. 

> Tous les messages sont affichés sur la sortie standart notement quand on utilise l'argument `--demo` de notre programme.

> Pour les autres arguments aucun logs n'est affiché, a l'exception des logs de fin de partie qui sont affiche pour connaitre les résultats des parties.

### Un résumé de ce qui a été fait pour les statistiques en **CSV** (en quelques lignes max, quels choix ont été faits pour les réaliser)
> Tout d'abord, on récupère données si le fichier CSV exists pour créer un CSVStats.La classe CSVStats est utilisée pour stocker et mettre à jour les données avec les nouveaux données des parties. Les données consistent des informations sur les types de joueurs, les victoires, les défaites, les matchs nuls, le score moyen et le nombre moyen d'objectifs de chaque joueur.

### Un résumé de ce qui a été fait pour le bot spécifique demandé, et éventuellement une comparaison avec votre meilleur bot et une analyse de pourquoi celui qui gagne est le meilleur

> Pour le bot qui nous a été demandé, nous nous sommes basés sur les fonctionnalités que nous proposaient déja le bot Moyen.

> Pour la fonctionnalité du choix de la metéo, nous avons decidé de lui faire choisir la météo nuage afin qu'il puisse prendre un bassin dans les aménagements. Et ce tant que nous sommes avant/pendant le tour 5.

> Pour nuir aux autres joueurs, le bot MVP va regarder quel type d'objectif est le plus présent parmis ceux que possède tous les autres joueurs et jouer en fonction :
> - S'il y a plus d'objectif panda : il va déplacer le panda sur une parcelle avec peu de bambou. Il va de plus ne pas déplacer le jardinier.
> - S'il y a plus d'objectif jardinier, il va déplacer le jardinier sur des parcelles avec 3 bambous ou plus.
> - S'il y a plus d'objectif parcelle : il va poser des parcelles qui vont lui permettre de valider ses objectifs plus rapidement. Comme les objectifs parcelles sont unique, jouer pour lui va lui permettre d'empecher les autres joueurs de valider leurs objectifs.

> Le bot MVP est meilleur, car lorsqu'il doit prendre des décisions, il va éviter d'aider ses adversaires, contrairement au bot Moyen qui lui va seulement joue pour realiser ses objectifs sans prendre en compte les autres joueurs. De plus dans le pire des cas il va prendre les decisions que ferait le bot Moyen

> Nous avons par ailleur realisé 3bots similaire au bot moyen dans l'execution de leurs actions, mais qui vont cependant donnée la priorite un certain type d'objectif.

## 2. Architecture et qualité
### Comment est faite l'architecture du projet ? Et quels choix vous ont amené à la réaliser ainsi ?
> Notre classe `Jeu` distribue des ordres au plateau et aux joueurs. On n'a pas créé d'objet irrigation ou bambous, car elles auraient été des classes vides : aucune méthode ne leur appartiennes.

### Où trouver les infos (de la java doc, de la doc sur les points et les classes importants ?)
> *\*On verra\**

### Etat de la base de code : quelles parties sont bien faites ? Quelles parties sont à refactor et pourquoi ? Comment la sortie SONAR le montre-t-elle (ou pas) ?
> Les parties que nous estimons bien développer sont par exemple :
> - Plateau : Notre implémentation avec un tableau et un enum de directions nous permet de **facilement** et **instantanement** trouver les cases adjacentes **sans avoir à parcourir de listes**.
> - Bot Moyen: optimise et réalise souvent de bon choix afin de remporter la partie *(1bot moyen vs 1bot random => >90% de win)*.
> - Grace a notre implémentation des differents éléments de jeu nos parties s'execute rapidements *(0.7 sec/parties avec logs) (2000 parties en - de 15sec)*
> - 
> - 
> - 
> - 
Cependant :
> - Joueur (Bots) trops responsabilités, car ils choisissent et accomplissent les actions par eux-mêmes, ils gèrent aussi leur inventaire.
Il ne fait **aucun choix aleatoire** sauf s'il n'y a pas de bon choix.
> - Les classes qui ne sont pas bien développer sont les classes `jeu` et `jeuLanceur` car c'est sur ces classes que nous manquons de tests. Cela est dû au fait que les fonctions sont trop complexes ce qui rend la creation de tests complique. La sortie Sonar nous montre bien que ce sont les classes avec le plus de lignes non-couverte par les tests. Pour remédier a cela nous devrions séparer les classes et diminue la complexité des fonctions.
> - Nos méthodes n'ont pas des noms assez explicites
> - 
> - 

## 3. Processus
###  Qui est responsable de quoi / qui a fait quoi ?
> 
> - Timothée : ce qui concerne le plateau et le positionnement des parcelles, les personnages. Il a développé les bases du bot Moyen. Il s'est occupé de la gestion des arguments grace a `JCommandeur` ainsi que le `main` et le `lanceur`. Il s'est occupé de faire les tests manquants notement ceux avec les `Mocks`.
>   - Plateau 
>   - Parcelle 
>   - Panda 
>   - Jardinier 
>   - Args 
>   - Tests 
>   - Dokker

 >- Yael : Amélioration du bot moyen suite à implementation des nouveaux elements du jeu jusqu'à la version finale du botMoyen à partir du model de Timothée. Il a travaillé sur le bot MVP. Ainsi que revoir les codes qui ont ete produis.

>- Tu : 
>     - Jeu 
>     - CSV/Stats 
>     - LOGGER 
>     - METEO 
>     - OBJPanda 
>     - Bamboo 
>     - Bot MVP 
>     - Bot Random 
>     - Amenagements

>- Marco : S'est occupé de l'implementations des objectifs, notement parcelles et Jardiniers. Ainsi que des tests et l'implementation des irrigations.

### Quel est le process de l'équipe (comment git est utilisé, branching strategy)
> Branche Dev sur laquelle nous avons merge nos codes avant de pousser sur master une version stable du projet. Pour chaque issue nous avons cree une branche en rapport avec celle-ci.