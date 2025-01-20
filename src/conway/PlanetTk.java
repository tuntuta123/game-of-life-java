
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


