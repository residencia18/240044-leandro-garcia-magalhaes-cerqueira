from AnaliseDados import AnaliseDados
from Idade import Idade

class ListaIdades(AnaliseDados):
    
    def __init__(self):
        super().__init__(int)
        self.__lista = []        
    
    def entradaDeDados(self):
        qtdItens,contador,idade = 0,0,0
        
        print("Informe a quantidade de itens na lista: ")
        qtdItens = int(input())

        if qtdItens > 0:
            while contador < qtdItens:
                print("\nInserindo Idades na lista:")
                
                print("\nIdade", contador + 1, ": ")
                
                idade = int(input("Digite a idade: "))
                
                try:
                    self.__lista.append(idade)

                    contador += 1
                except ValueError as e:
                    print(f"Erro: {e}. Idade inválida. Por favor, insira uma idade válida.")
                    
        print("Idade adicionada com sucesso!")
    
    def mostraMediana(self):

        self.listarEmOrdem()
        n = len(self.__lista)
        if n % 2 == 0:
            mediana = (self.__lista[n//2 - 1] + self.__lista[n//2]) / 2
        else:
            mediana = self.__lista[n//2]
        print(f"Mediana dos salários: {mediana}")
        
    def mostraMenor(self):
        
        menor_idade = min(self.__lista)
        print(f"Menor idade: {menor_idade}")

    def mostraMaior(self):
        
        maior_idade = max(self.__lista)
        print(f"Maior idade: {maior_idade}")

    def listarEmOrdem(self):
        self.__lista.sort()
        print("Idades em ordem crescente:", self.__lista)



    def __str__(self):
        pass