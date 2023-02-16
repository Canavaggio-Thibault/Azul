Je vous conseille de télécharger le document car il est afficher sans saut de ligne sur github



iteration04.md groupe c azul

BILAN DE L'ITÉRATION

En terme de fonctionnalités livrées et issues faites :
Issues réalisées (se référer au git) : #51 #58 #64 #65 #67 #69 #70 #71 #73 #77 #79 #80 #81 #82 #83 #84 #85 #86 #87   
Issues non réalisées : #63 #66 #72 #74 #75 #76 #78

Concernant les fonctionalités :
• Il a désormais les joueurs, le jeu peut se jouer à plusieurs joueurs et les joueurs ont un niveau d'IA qui leur est assigné (joueur 1 joue avec l'IA random et le joueur 2 joue avec l'IA plus de couleurs)
• Il y a plusieurs fabriques qui se vident et qui remplissent le centre lorsque le joueur pioche une couleur à l'interieur.
• l'IA random est capable de piocher aléatoirement entre le centre ou bien les fabriques(sauf si un des deux membres et vide, il piochera forcément dans l'autre). Lorsque le centre et toutes les fabriques sont vide, la manche se termine et la partie aussi.
• l'IA plus de couleur pioche entre une des fabrique ou bien le centre là où est-ce qu'il y a le plus de tuiles d'une même couleur. L'IA choisit aussi une ligne de taille adaptée au nombre de tuile piochée
• Le client peut visualiser le déroulement d'une manche entière dans le terminal de façon clair et précise, avec le déroulement d'un tour, l'affichage du mur des joueurs, des fabriques, du centre et du plancher.
• Le joueur est capable de piocher dans le centre ou bien dans une des fabriques une ou plusieurs tuiles, et les place  sur une des lignes en fonction du niveau d'IA . Si une ou plusieurs tuiles dépassent de la ligne, les tuiles dépassants iront sur le plancher.
• Lorsque l'ensemble des fabriques et le centre de la table sont vides, les fabriques se remplissent avec des tuiles provenants de la pioche (100 tuiles, 20 de chaques couleur)
• Il y a désormais un affichage des tuiles en couleurs dans pour le jeu, de plus nous affichons les lignes de motifs
• Il est possible maintenant de calculer le nombre des points pour chaque joueur à la fin de chaque manche et de la partie, ainsi que les malus du au remplissage du plancher


NOTE : Nous sommes au courant de bugs liés au plancher qui ne se remplit plus lors de la partie et l'IA plus de couleur qui décide parfois de tirer une couleur qui n'existe pas dans uen fabrique, nous y travaillerons dessus lors de l'itération 5. 
De plus, les IAs ne prennent pas en compte l'état du mur pour faire ses choix, idem, ce sera pour l'itération 5. 

En terme de tests :
• CompterPointTest : 
- test des différentes méthodes permettant de compter le nombre de point obtenu via les lignes de motif.
- test des malusPlancher méthodes permettant de retirer les points en fonction des tuiles dans le plancher en fin de chaque manche
- test des points en fin de partie permettant de compter les points bonus en fonction des lignes verticales et honrizontales
• IArandomTest : Test les différents choix de l'IA random, notamment :
- testChoixPiocheAleatoireDansUneFabrique : Vérifie que l'IA pioche aléatoirement dans UNE fabrique : une fabrique est créer avec 3 couleurs différentes et les tests vérifient que l'IA a bien sélectioner une couleur présente dans la fabrique.
- testChoixCouleurDansCentre : Vérifie que l'IA sélectione une tuile de couleur aléatoire présente dans le centre (le centre est initialisé avec des tuiles de couleurs VIDE de base, le test vérifie donc que l'IA ne pioche pas une tuile VIDE ni d'une couleur qui n'est pas présente dans le centre)
- testChoixPlacementAleatoireSurUneLigne : Vérifie que l'IA pose les tuiles sur une ligne de motif aléatoire, et sur une ligne contenant déjà une tuile de couleur (ex: si il y a 2 tuiles rouges sur la ligne 3 et que l'IA pioche du rouge, la tuile doit être placée sur la ligne 3)
• MilieuDeTableTest (présente lors de l'itération 2, mais on était en avance et nous ne l'avons pas expliqué ni utiliser):
- remplirCentreVide : Vérifie qu'il est possible de placer une tuile de couleur si l'entièreté du centre est vide
- remplirCentreNonVide : Vérifie qu'il est possible de placer une tuile de couleur si des tuiles de couleur sont déjà placées au centre
- recupTuileMemeCouleur : Vérifie s'il est bien possible de récupérer des tuiles de même couleur du centre et de les retirer ensuite du centre
• PiocheTest : Vérifie que la pioche est bien générée et qu'elle possède 20 tuiles de chaque couleur
• CouvercleTest : Vérifie si on crée bien un tableau de 100 cases vide et qu'on arrive à mettre dedans les tuiles "perdues"
  •IAintermediaireTest : Test sur les differents cas lors du piochage entre les fabriques ou le centre, avec plusieurs états du centre et de fabriques. Test sur les différents cas avec plusieurs état des lignes de motif pour choisir un ligne de motif


En terme d'organisation du code et de refactoring :
• Organisation du code :
- Package azul :
• MoteurDeJeu : permet de lancer une partie avec le nombre de joueur souhaité avec le constructeur Manager(nbDeJoueur)
//
- Package controller.joueur : contient tous les attributs que les joueurs possèdent
• Joueur : Classe qui définit les differents attribut d'un joueur comme son mur, ses lignes de motifs, son nom, son plancher et surtout son niveau d'IA
• LignesMotif : permet de créer des lignes de motif en escalier et de les remplir
• Mur : Permet de créer un mur de couleur pour un joueur. Un mur de couleur est créer et un mur de "true/false" est aussi créer pour permettre de savoir si une case a été remplie ou non.
• Plancher : Créer le plancher de 7 tuiles pour un joueur
//
- Package controler.manager :
• Manager : classe principal du projet, elle permet de faire en sorte qu'une manche se déroule. Elle instencie les joueurs, place des tuiles sur les lignes et sur le mur, vérifie si les fabriques et le centre et vide et termine la manche.
• CentreDeTable : Creer le centre de la table, est capable de se remplir avec des tuiles de couleur et d'y extraire des tuiles d'une même couleur
• Fabrique : Classe permettant de creer plusieurs fabriques qui seront contenue dans une liste. Le nombre de fabrique dépend du nombre de joueur. Les fabriques peuvent se vider et remplir le centre.
• Pioche : Créer une pioche contentant 20 tuiles de chaque couleur et mélange la pioche
• Couvercle : Classe permettant de stocker les tuiles "perdues" et remet les tuiles dans la pioche quand elle est vide
- Package plancher :
//
- Package ia : contient les différents types d'IA et leurs classes abstraite
• IA : classe abstraite permettant ensuite au joueur de récupérer les méthodes de son niveau d'IA et pas d'un autre niveau
• TypeIa : énumération des différents type d'IA
//
- Package ia.typeIA : ce package contient les differents niveaux d'IA et leurs décision dans le choix de placement dans les lignes de motif et de piochage
• IArandom : simule le comportement d'une IA qui pioche et pose des tuiles aléatoirement
• IAintermediaire : 
• IAsuperieur : ne sont pas encore codé
//
- Package utilitaire :
• CompterPoint : Permet de compter les points issues des lignes de motif (n'est pas encore implémenter dans le jeu)
• Utilitaire : Peut généré un nombre aléatoire et une couleur aléatoire
• AfficherElement : Permet la gestion de tout les affichages 
//
- Package tuile :
• Tuile : Créer une tuile de couleur ou une tuile vide
• Couleurs : énumération de couleur et permet de set la valeur d'une couleur a true/false, utile pour la classe Mur




• Refactoring : Nous avons procédé a plusieurs refactoring, notamment pour mettre en place le systeme de joueur, le moteur de jeu et pour le code de L'IA random. Nous avons aussi optimiser certaines partie du code en changeant leurs fonctionnement.

En terme d'organisation du travail :
• Nicolas : IAintermediaire, IAintermediaireTest, implémentation de l'IA intermédiaire sur joueur 2

• Gando : refactoring pour mettre en place les joueurs et leur attribuer un niveau d'IA , tâches correctives, instencier les élements d'un joueur (mur, lignes, plancher), gestion des manches, gestion des joueurs sur une manche, AfficheElement

• Thomas : classe compterPoint, compterPointTest, implémentation du comptage de points liées aux lignes de motif, au malus du plancher et en fin de partie, itération05, itération04.md

• Thibault : classe Couvercle, testCouvercle

• Ralph : Affichage des couleurs dans le terminal 

• Matthéo : Tâches corrective, rajouts de tests










