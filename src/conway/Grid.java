package conway;

public class Grid {

    def __init__( grid_init):
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

}
