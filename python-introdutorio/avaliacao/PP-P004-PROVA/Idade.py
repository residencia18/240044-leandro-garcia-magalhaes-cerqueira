class Idade:
    
    def __init__(self,  idade = 4):
        if idade < 4 or idade > 80:
            raise ValueError("Idade inválida")
        self.__idade = idade

    @property
    def idade(self):
        return self.__idade
    
    @idade.setter
    def idade(self, idade):
        if idade < 4 or idade > 80:
            raise ValueError("Idade inválida")
        self.__idade = idade

    