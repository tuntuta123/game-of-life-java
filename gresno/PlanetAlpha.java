class PlanetAlpha(Grid) :

    NORTH,EAST,SOUTH,WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST = (-1, 0), (0, 1), (1, 0), (0, -1),(-1, 1), (1, 1), (1, -1), (-1, -1)

    def __init__ (self, name, latitude_cells_count, longitude_cells_count,ground) :
        # Initialise une grille avec l'aide de la classe Grid.
        Grid.__init__(self, [[ground for i in range(longitude_cells_count)] for j in range(latitude_cells_count)])
        self.__name = name
        self.__ground = ground

