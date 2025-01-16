# Importation des bibliothèques nécessaires : tkinter pour les composants de l'interface graphique et random pour générer des états aléatoires.
import tkinter as tk
import random
from interface import Element, Grid, PlanetAlpha, PlanetTk

# Définition de la classe `Human` pour représenter chaque cellule avec un emoji dans le jeu de la vie de Conway.
class Human(Element) :
    def __init__(self) :
        Element.__init__(self, "\U0001F600")

# Définition de la classe `Conway`, héritant de `PlanetTk` pour utiliser les fonctionnalités personnalisées de la grille et de tkinter.        
class Conway(PlanetTk):
    def __init__(self, master, width=20, height=20,cell_size=40, gutter_size = 0, margin_size = 1):
         # Initialisation des attributs principaux du jeu, y compris le maître tkinter, les dimensions de la grille et la taille des cellules.
        self.__master = master
        self.__width = width  #Largeur de la grille
        self.__height = height #Hauteur de la grille
        self.__cell_size = cell_size # Taille visuelle de chaque cellule dans la grille.
        # Création d'un canvas de grille comme objet PlanetTk pour afficher le jeu.
        self.__grid_canvas = PlanetTk(self.__master, height, width, cell_size, 0, 1)
        self.__grid_canvas.pack() # Affiche le canvas à l'écran.
        # Ajout des boutons de contrôle pour démarrer le jeu, passer à la génération suivante et quitter le jeu.
        self.__start_button = tk.Button(self.__master, text='Commencer', command=self.start_game)
        self.__start_button.place(x=25, y=100)
        self.__start_button.pack()
        self.__next_button = tk.Button(self.__master, text='Suivant', command=self.next_move)
        self.__next_button.place(x=50, y=100)
        self.__next_button.pack()
        self.__quit_button = tk.Button(self.__master, text='Quitter', command=quit)
        self.__quit_button.place(x=50, y=100)
        self.__quit_button.pack()
        #Initialisation de la grille avec des états aléatoires (vivant ou mort) pour chaque cellule.
        self.__cells = [[random.choice([0, 1]) for _ in range(width)] for _ in range(height)]
        self.__running = False
        # Place un élément graphique représentant un 'Human' dans chaque cellule de la grille.
        for cell_number in range(width * height):
            i, j = PlanetTk.get_coordinates_from_cell_number(self.__grid_canvas,cell_number)
            x = j * (cell_size + gutter_size) + margin_size
            y = i * (cell_size + gutter_size) + margin_size
            self.__grid_canvas.create_text(x + cell_size // 2, y + cell_size // 2, text=Human(), fill="white", font=('Arial', cell_size // 2, 'bold'), tags=(f't_{i}_{j}', f't_{cell_number}'))
    
    # Dessine l'état actuel de la grille, en colorant chaque cellule en fonction de son état (vivant ou mort).
    def draw_board(self):
        for y, row in enumerate(self.__cells):
            for x, cell in enumerate(row):
                filled = True if cell else False
                cell_number = y * self.__width + x
                self.__grid_canvas.set_cell_color_conway(cell_number, filled)
                
    # Fonction pour compter les voisins vivants autour d'une cellule donnée, cruciale pour la logique du jeu.                
    def count_neighbors(self, x, y):
        count = 0
        for dy in [-1, 0, 1]:
            for dx in [-1, 0, 1]:
                if dx == 0 and dy == 0:
                    continue
                if 0 <= x + dx < self.__width and 0 <= y + dy < self.__height:
                    count += self.__cells[y + dy][x + dx]
        return count


    # Fonction pour mettre à jour l'état du plateau en appliquant les règles du jeu de la vie de Conway à chaque cellule.
    def update_board(self):
        new_cells = [[0 for _ in range(self.__width)] for _ in range(self.__height)]
        for y, row in enumerate(self.__cells):
            for x, cell in enumerate(row):
                neighbors = self.count_neighbors(x, y)
                if cell == 1 and (neighbors < 2 or neighbors > 3):
                    new_cells[y][x] = 0  # La cellule meurt.
                elif cell == 0 and neighbors == 3:
                    new_cells[y][x] = 1  # Une nouvelle cellule naît.
                else:
                    new_cells[y][x] = cell
        self.__cells = new_cells
        self.draw_board() # Redessine la grille après mise à jour.

    #Fonction pour démarrer la progression automatique du jeu.
    def start_game(self):
        if not self.__running:
            self.__running = True
            self.run_game()
    #Fonction récursive qui met continuellement à jour le plateau tandis que le jeu est en cours.
    def run_game(self):
        if self.__running:
            self.update_board()
            self.__master.after(500, self.run_game)
    #Fonction pour progresser manuellement vers la génération suivante, utilisée quand le jeu n'est pas en mode d'exécution automatique.
    def next_move(self):
        if not self.__running:
            self.update_board()
