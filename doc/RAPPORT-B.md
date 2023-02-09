## 1. Point d'avancement
### Un résumé des **fonctionnalités** réalisées sur tout le jeu (couverture des règles du jeu indépendamment de ce qui était demandé cette semaine), et éventuellement la liste de ce qui n'a pas été fait.
> Toute les fonctionnalitées ont été réalisé, pour le jeu de base, les joueurs sont oblige de realise la meteo, ils n'ont pas le choix

### Un résumé de ce qui a été fait pour les logs (en quelques lignes max, quels choix ont été faits pour les réaliser)
> Pour les logs nous nous y sommes pris au debut de la semaine nous avons adopter les sorties sur la console que nous avions afin de les affichers avec un `Logger`. 

> Tout les messages sont affiche sur la sortie standart notement quand on utilise l'argument `--demo` de notre programme.

> Pour les autres arguments aucun logs n'est affiche, a l'exception des logs de fin de partie qui sont affiche pour connaitre les resultats de la parties

### Un résumé de ce qui a été fait pour les statistiques en **CSV** (en quelques lignes max, quels choix ont été faits pour les réaliser)

> Pour les statistique nous recuperons quels est le resultat de la partie: nous affichons alors une fois que l'ensemble des parties ont ete execute le % de victoire de defaire et d'egalite de nos bots. 

### Un résumé de ce qui a été fait pour le bot spécifique demandé, et éventuellement une comparaison avec votre meilleur bot et une analyse de pourquoi celui qui gagne est le meilleur

> Pour le bot qui nous a ete demande, nous nous sommes base sur les fonctionnalites que nous proposaient deja le bot Moyen.

> Pour la fonctionnalite du choix de la meteo, nous avons decide de lui faire choisir la meteo nuage afin qu'il puisse prendre un bassin dans les amenagements (car c'est op wtf). Et ce tant que nous sommes avant/pendant le tour 5.

> Pour nuir aux autres joueurs, le bot MVP va regarder quel type d'objectif est le plus present parmis ceux que possedes tout les autres joueurs et jouer en fonction:
> - Si il y a plus d'objectif panda: il va deplacer le panda sur une parcelle avec peu de bambou. Il va de plus ne pas deplacer le jardinier.
> - Si il y a plus d'objectif jardinier il va deplacer le jardinier sur des parcelles avec 3 bambous ou plus.
> - Si il y a plus d'objectif parcelle: il va poser des parcelles qui vont lui permettre de valider ses objectifs plus rapidement. Comme les objectifs parcelles sont unique, jouer pour lui va lui permettre d'empecher les autres joueurs de valider leurs objectifs.

> Le bot MVP est meilleur car lorsqu'il doit prendre des decisions il va eviter d'aider ses adversaires,contrairement au bot Moyen qui lui va seulement joue pour realiser ses objectifs sans prendre en compte les autres joueurs. De plus dans le pire des cas il va prendre les decisions que ferai le bot Moyen

> Nous avons par ailleur realisé 3bots similaire au bot moyen dans l'execution de leur actions mais qui vont cependant donnée la priorite un certain type d'objectif.

## 2. Architecture et qualité
### Comment est faite l'architecture du projet ? Et quels choix vous ont amené à la réaliser ainsi ?
> Notre classe `Jeu` distribue des ordres aux plateau et aux joueurs. On a pas cree d'objet irrigation ou bambous car elles auraient ete des classes vides: aucune methode ne leurs appartiennes.

### Où trouver les infos (de la java doc, de la doc sur les points et les classes importants ?)
> *\*On verra\**

### Etat de la base de code : quelles parties sont bien faites ? Quelles parties sont à refactor et pourquoi ? Comment la sortie SONAR le montre-elle (ou pas) ?
> Les parties que nous estimons bien développer sont par exemple:
> - Plateau : Notre implementation avec un tableau et un enum de directions nous permet de **facilement** et **instantanement** trouver les cases adjacentes **sans avoir a parcourir de listes**.
> - Bot Moyen: optimise et realise souvent de bon choix afin de remporter la partie *(1bot moyen vs 1bot random => >90% de win)*.
> - Grace a notre implementation des differents elements de jeu nos parties s'execute rapidements *(0.7 sec/parties avec logs) (2000 parties en - de 15sec)*
> - 
> - 
> - 
> - 
Cependant :
> - Joueur (Bots) trops responsabilites car ils choisissent et accomplissent les actions par eux meme, ils gerent aussi leur inventaire.
Il ne fais **aucun choix aleatoire** sauf si il n'y a pas de bon choix.
> - Les classes qui ne sont pas bien développer sont les classes `jeu` et `jeuLanceur` car c'est sur ces classes que nous manquons de tests. Cela est dues au fait que les fonctions sont trop complexe ce qui rend la creation de tests complique. La sortie Sonar nous montre bien que ce sont les classes avec le plus de lignes non-couverte par les tests. Pour remedier a cela nous devrions separe les classes et diminue la complexite des fonctions.
> - Nos methodes n'ont pas des noms assez explicites
> - 
> - 

## 3. Processus
###  Qui est responsable de quoi / qui a fait quoi ?
> 
> - Timothee : ce qui concerne le plateau et le positionnement des parcelles, les personnages. Il a développer les bases du bot Moyen. S'est occupe de la gestioin des arguments grace a `JCommandeur` ainsi que le `main` et le `lanceur`. Il s'est occupe de faire les tests manquants notement ceux avec les `Mocks`.
Plateau - Parcelle - Panda - Jardinier - Args - Tests - Dokker

> - Yael : Amelioration du bot moyen suite a l'implementation des nouveaux elements du jeu jusqu'a la version finale du botMoyen a partir du modele de Pipope. Il a travaille sur le bot MVP. Ainsi que revoir les codes qui ont ete produis.

> - Tu : Jeu - CSV/Stats - LOGGER - METEO - OBJPanda - Bamboo - Bot MVP - Joueur random - Amenagements

> - Marco : S'est occupe de l'implementations des objectifs, notement parcelles et Jardiniers. Ainsi que des tests et l'implementation des irrigations.

### Quel est le process de l'équipe (comment git est utilisé, branching strategy)
> branche Dev sur laquelle nous avons merge nos codes avant de pousser sur master une version stable du projet. Pour chaque issues nous avons cree une branche en rapport avec celle-ci.