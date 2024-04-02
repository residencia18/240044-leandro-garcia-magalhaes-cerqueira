from AnaliseDados import AnaliseDados

class ListaSalarios(AnaliseDados):

    def __init__(self):
        super().__init__(float)
        self.__lista = []        

    def entradaDeDados(self):
        n = int(input("Quantos salários deseja incluir na lista? "))
        for i in range(n):
            salario = float(input(f"Informe o {i+1}º salário: "))
            self.__lista.append(salario)
        print("Salário(s) adicionado(s) com sucesso!")

    def mostraMediana(self):
        self.listarEmOrdem()
        n = len(self.__lista)
        if n % 2 == 0:
            mediana = (self.__lista[n//2 - 1] + self.__lista[n//2]) / 2
        else:
            mediana = self.__lista[n//2]
        print(f"Mediana dos salários: {mediana}")

    def mostraMenor(self):
        menor_salario = min(self.__lista)
        print(f"Menor salário: {menor_salario}")

    def mostraMaior(self):
        maior_salario = max(self.__lista)
        print(f"Maior salário: {maior_salario}")
    
    def listarEmOrdem(self):
        self.__lista.sort()
        print("Salários em ordem crescente:", self.__lista)
        
    def reajusteSalarios(self):
        self.__lista = list(map(lambda x: round(x * 1.1, 2), self.__lista))

    def __str__(self):
        return str(self.__lista)