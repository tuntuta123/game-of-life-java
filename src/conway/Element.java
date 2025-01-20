class Element :

    def __init__(self,char_repr):
        self.__char_repr = char_repr

    def __repr__(self):
        """
        Retourne une representation avec un caractere.
        """
        return self.__char_repr
