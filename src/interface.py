import tkinter as tk
import random

class Grid:

    def __init__(self, grid_init):
        self.__grid = grid_init
        self.__lines_count = len(grid_init)  # Nombre de lignes dans la grille.
        self.__columns_count = len(grid_init[0]) # Nombre de colonnes dans la grille.

    def get_grid(self):
        return self.__grid

    def get_lines_count(self):
        """
        Retourne le nombre de lignes dans la grille.
        """
        return self.__lines_count

    def get_columns_count(self):
        """
        Retourne le nombre de colonnes dans la grille. 
        """
        return self.__columns_count

    def get_coordinates_from_cell_number(self, cell_number):
        """ 
        Converti un numéro de case 'cell_number' de la grille vers les coordonnées (ligne, colonne)
        correspondants.
        """
        return cell_number // self.__columns_count, cell_number % self.__columns_count

    def get_cell_number_from_coordinates(self, line_number, column_number):
        """ 
        Converti les coordonnées ('line_number', 'column_number') de la grille vers le numéro de case
        correspondant.
        """
        return line_number * self.__columns_count + column_number

class PlanetAlpha(Grid) :

    NORTH,EAST,SOUTH,WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST = (-1, 0), (0, 1), (1, 0), (0, -1),(-1, 1), (1, 1), (1, -1), (-1, -1)

    def __init__ (self, name, latitude_cells_count, longitude_cells_count,ground) :
        # Initialise une grille avec l'aide de la classe Grid.
        Grid.__init__(self, [[ground for i in range(longitude_cells_count)] for j in range(latitude_cells_count)])
        self.__name = name
        self.__ground = ground

class PlanetTk(PlanetAlpha, tk.Canvas):

    def __init__(self, master, lines_count, columns_count, cell_size, gutter_size, margin_size, background='white', cell_fill='grey', cell_foreground='white', **kw):

        PlanetAlpha.__init__(self,"Projet fils rouge", lines_count,columns_count,"")
        kw['width'] = cell_size * columns_count + 2 * margin_size + (columns_count - 1) * gutter_size
        kw['height'] = cell_size * lines_count + 2 * margin_size + (lines_count - 1) * gutter_size
        kw['bg'] = background
        tk.Canvas.__init__(self, master, **kw)

        # Création d'une grille en utilisant tkinter
        for cell_number in range(self.get_lines_count() * self.get_columns_count()):
            i, j = Grid.get_coordinates_from_cell_number(self,cell_number)
            x = j * (cell_size + gutter_size) + margin_size
            y = i * (cell_size + gutter_size) + margin_size
            self.create_rectangle(x, y, x + cell_size, y + cell_size, fill=background, tags=(f'c_{i}_{j}', f'c_{cell_number}'))

        self.__master = master
        self.__cell_size = cell_size
        self.__gutter_size = gutter_size
        self.__margin_size = margin_size
        self.__background = background
        self.__cell_fill = cell_fill
        self.__cell_foreground = cell_foreground

    def get_master(self):
        return self.__master

    def set_cell_colors_langton(self, cell_number, filled=True):
        """
        Cette function est utilisée pour mettre à jour la couleur d'une cellule 
        spécifique sur le canevas, en fonction de son état dans le programme Turmites.
        """
        if filled:
            self.itemconfigure(f'c_{cell_number}', fill=self.__cell_fill)
            self.itemconfigure(f't_{cell_number}', fill=self.__cell_foreground)
        else:
            self.itemconfigure(f'c_{cell_number}', fill=self.__background)
            self.itemconfigure(f't_{cell_number}', fill=self.__background)

        
    def set_cell_color_conway(self, cell_number, filled=True):

        """
        Cette function est utilisée pour mettre à jour la couleur d'une cellule spécifique 
        sur le canevas, en fonction de son état dans le Jeu de la vie de Conway.
        """

        if filled:
            self.itemconfigure(f'c_{cell_number}', fill=self.__cell_fill)
            self.itemconfigure(f't_{cell_number}', fill=self.__cell_foreground)
            self.get_grid()[cell_number // Grid.get_columns_count(self)][cell_number % self.get_columns_count()] = 1

        else:

            self.itemconfigure(f'c_{cell_number}', fill=self.__background)
            self.itemconfigure(f't_{cell_number}', fill=self.__background)
            self.get_grid()[cell_number // Grid.get_columns_count(self)][cell_number % Grid.get_columns_count(self)] = 0

    def set_cell_color(self, cell_number, background_color):
        """
        Function pour changer la couleur d'une cellule spécifique.
        """
        self.itemconfigure(f'c_{cell_number}', fill=background_color)

    def get_cell_colors(self, cell_number):
        """
        Récupère les couleurs actuelles d'une cellule.
        """
        return self.itemcget(f'c_{cell_number}', 'fill'), self.itemcget(f't_{cell_number}', 'fill')

class Element :

    def __init__(self,char_repr):
        self.__char_repr = char_repr

    def __repr__(self):
        """
        Retourne une representation avec un caractere.
        """
        return self.__char_repr
