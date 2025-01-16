# -*- coding: utf-8 -*-

import tkinter as tk 
from snake import SnakeGame
from conway import Conway
from langton import Turmites

class SnakeGameWindow(tk.Toplevel):

    # Initialisation des constantes
    __LINES_COUNT = 21
    __COLUMNS_COUNT = 31
    __CELL_SIZE = 30
    __GUTTER_SIZE = 0
    __MARGIN_SIZE = 30
    __SNAKE_LENGTH = 3
    __BACKGROUND = "00FF00"

    def __init__(self,master,**kw):

        tk.Toplevel.__init__(self, master, **kw)
        self.__master = master
        self.title("Snake Game")
        
        self.__snake_game = SnakeGame(self, self.__LINES_COUNT, self.__COLUMNS_COUNT, self.__CELL_SIZE, self.__GUTTER_SIZE, self.__MARGIN_SIZE, self.__SNAKE_LENGTH, bg=self.__BACKGROUND)
        self.__snake_game.pack(side='left')

        # Des événements de touches fléchées à des méthodes de l'instance SnakeGame pour contrôler la direction du serpent.
        self.bind('<Left>', lambda event: self.__snake_game.set_direction('left'))
        self.bind('<Right>', lambda event: self.__snake_game.set_direction('right'))
        self.bind('<Up>', lambda event: self.__snake_game.set_direction('up'))
        self.bind('<Down>', lambda event: self.__snake_game.set_direction('down'))

        self.__control_frame = tk.Frame(self)
        self.__control_frame.pack(side='right', padx=10, pady=10) 

        # Bouton pour démarrer le jeu.
        self.__start_btn = tk.Button(self.__control_frame, text="Commencer le jeu", command=self.__snake_game.start_game)
        self.__start_btn.pack()

        # Bouton pour quitter le jeu.
        self.__quit_btn = tk.Button(self.__control_frame, text="Quitter", command=self.destroy)
        self.__quit_btn.pack()

        # Affiche les significations des couleurs.
        self.__color_meanings_label = tk.Label(self.__control_frame, text="Significations des couleurs :\n\nVert - Le serpent\n\nRouge - Nourriture\n\nNoir- Obstacle", justify='center')
        self.__color_meanings_label.pack()

        # Étiquette pour le message 'Fin de jeu'.
        self.__game_over_label = tk.Label(self.__control_frame, text="", font=('Arial', 30), bg='white')
        self.__game_over_label.pack(fill='both', expand=True)

        # Étiquette pour afficher le score.
        self.__score_label = tk.Label(self.__control_frame, text="Score: 0", font=('Arial', 20))
        self.__score_label.pack()

    def show_game_over(self):
        """
        Affiche le message 'Fin de jeu'.
        """
        self.__game_over_label.config(text="Game Over")

    def update_score(self, score):
        """
        Met à jour le texte du score.
        """
        self.__score_label.config(text=f"Score: {score}")

class ConwayWindow(tk.Toplevel):

    def __init__(self, master, **kw):
        # Initialisation de la fenêtre pour le jeu de Conway avec 'tk.Toplevel'.
        tk.Toplevel.__init__(self, master, **kw)
        self.__master = master
        # Création du jeu de Conway avec une grille de 30x30 et une taille de cellule spécifique.
        self.__game = Conway(self, 30, 30, cell_size=20)
        self.title("Conway")

class TurmitesWindow(tk.Toplevel) :

    def __init__(self,master,**kw) :
        # Initialisation de la fenêtre pour les Turmites avec 'tk.Toplevel'.
        tk.Toplevel.__init__(self, master, **kw)
        self.__master = master
        num_ants = 5
        self.__game = Turmites(self, 100, 100, 5, 0, 0, num_ants)
        self.__game.pack()
        tk.Button(self, text='Commencer', command=self.start_ants).pack()
        tk.Button(self, text='Quitter', command=self.quit).pack()

    def start_ants(self):
        # Function pour commencer le jeu
        self.__game.step() 

class MyApp(tk.Tk):

    def __init__(self):
        tk.Tk.__init__(self)
        self.title("Projet fil rouge")  
        # Création de trois boutons connectés aux trois jeux différents : SnakeGame, Conway Game et Turmites Game    
        tk.Button(self, text='Snake game', command=lambda: SnakeGameWindow(self)).pack(side=tk.LEFT)
        tk.Button(self, text='Conway game', command=lambda: ConwayWindow(self)).pack(side=tk.LEFT)
        tk.Button(self, text='Turmites game', command=lambda: TurmitesWindow(self)).pack(side=tk.LEFT)
        # Création d'un bouton pour quitter la fenêtre
        tk.Button(self, text='Quitter', command=self.quit).pack(side=tk.RIGHT)

if __name__ == '__main__':

    MyApp().mainloop()

