from AnaliseDados import AnaliseDados
from typing import List
from Nome import Nome

class ListaNomes(AnaliseDados):
    def __init__(self):
        super().__init__(type(Nome))
        self.__lista = []        


    def entradaDeDados(self):
        primeiro_nome = input("Digite o primeiro nome: ")
        sobrenome = input("Digite o sobrenome: ")
        novo_nome = Nome(primeiro_nome, sobrenome)
        self.__lista.append(novo_nome)
        print("Nome adicionado com sucesso.")

    def mostraMediana(self):
        if not self.__lista:
            print("A lista de nomes está vazia.")
            return

        # Ordenar os nomes antes de calcular a mediana
        nomes_ordenados = sorted(self.__lista, key=lambda x: x.obter_nome_completo())
        tamanho = len(nomes_ordenados)

        if tamanho % 2 == 0:
            # Média dos dois valores do meio se a lista tiver um número par de elementos
            mediana_idx_1 = tamanho // 2 - 1
            mediana_idx_2 = tamanho // 2

            mediana_1 = nomes_ordenados[mediana_idx_1].obter_nome_completo()
            mediana_2 = nomes_ordenados[mediana_idx_2].obter_nome_completo()

            # Se o primeiro nome do par for o último na ordem alfabética,
            # a mediana será o primeiro nome em ordem alfabética
            if mediana_1 > mediana_2:
                mediana_1, mediana_2 = nomes_ordenados[0].obter_nome_completo(), mediana_1
            
            mediana = mediana_1
        else:
            # Valor do meio se a lista tiver um número ímpar de elementos
            mediana_idx = tamanho // 2
            mediana = nomes_ordenados[mediana_idx].obter_nome_completo()

        print("Mediana dos nomes:", mediana)



    def mostraMenor(self):
        if not self.__lista:
            print("A lista de nomes está vazia.")
            return

        menor_nome = min(self.__lista, key=lambda x: x.obter_nome_completo())
        print("Menor nome:", menor_nome.obter_nome_completo())

    def mostraMaior(self):
        if not self.__lista:
            print("A lista de nomes está vazia.")
            return

        maior_nome = max(self.__lista, key=lambda x: x.obter_nome_completo())
        print("Maior nome:", maior_nome.obter_nome_completo())

    def listarEmOrdem(self):
        if not self.__lista:
            print("A lista de nomes está vazia.")
            return

        nomes_ordenados = sorted(self.__lista, key=lambda x: x.obter_nome_completo())
        print("Nomes em ordem alfabética:")
        for nome in nomes_ordenados:
            print(nome.obter_nome_completo())

    def excluirNome(self):
        if not self.__lista:
            print("A lista de nomes está vazia.")
            return

        nome_para_excluir = input("Digite o nome que deseja excluir: ")
        nome_encontrado = None

        for nome in self.__lista:
            if nome_para_excluir.lower() == nome.obter_nome_completo().lower():
                nome_encontrado = nome
                break

        if nome_encontrado:
            self.__lista.remove(nome_encontrado)
            print("Nome excluído com sucesso.")
        else:
            print("Nome não encontrado na lista.")

    def editarNome(self):
        if not self.__listas:
            print("A lista de nomes está vazia.")
            return

        nome_para_editar = input("Digite o nome que deseja editar: ")
        nome_encontrado = None

        for nome in self.__lista:
            if nome_para_editar.lower() == nome.obter_nome_completo().lower():
                nome_encontrado = nome
                break

        if nome_encontrado:
            novo_primeiro_nome = input("Digite o novo primeiro nome: ")
            novo_sobrenome = input("Digite o novo sobrenome: ")
            nome_encontrado.primeiro_nome = novo_primeiro_nome
            nome_encontrado.sobrenome = novo_sobrenome
            print("Nome editado com sucesso.")
        else:
            print("Nome não encontrado na lista.")
            
            
    
