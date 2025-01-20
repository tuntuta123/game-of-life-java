from conway import Conway

class ConwayWindow(tk.Toplevel):

    def __init__(self, master, **kw):
        # Initialisation de la fenêtre pour le jeu de Conway avec 'tk.Toplevel'.
        tk.Toplevel.__init__(self, master, **kw)
        self.__master = master
        # Création du jeu de Conway avec une grille de 30x30 et une taille de cellule spécifique.
        self.__game = Conway(self, 30, 30, cell_size=20)
        self.title("Conway")
        
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

