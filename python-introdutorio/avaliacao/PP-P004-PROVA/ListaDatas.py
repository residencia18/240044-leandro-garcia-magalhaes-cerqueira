# Importe a classe AnaliseDados e Data 
from AnaliseDados import AnaliseDados
from Data import Data

class ListaDatas(AnaliseDados):
        
    def __init__(self):
        super().__init__(type(Data))
        self.__lista = []
                
    def entradaDeDados(self):
        
        qtdItens = 0
        contador = 0
        dataValida = True
        dia = mes = ano = ''

        print("Informe a quantidade de itens na lista: ")
        qtdItens = int(input())

        if qtdItens > 0:
            while contador < qtdItens or not dataValida:
                print("\nInserindo Datas na lista:")
                
                print("\nData", contador + 1, ": ")
                
                dia = int(input("Digite o dia: "))
                mes = int(input("Digite o mês: "))
                ano = int(input("Digite o ano: "))
                
                try:
                    data = Data(dia, mes, ano)
                    self.__lista.append(str(data))
                    contador += 1
                except ValueError as e:
                    print(f"Erro: {e}. Data inválida. Por favor, insira uma data válida.")
                    
            self.ordenar_datas()
                
    def ordenar_datas(self):
        # Converter a lista de strings de datas de volta para objetos Data
        datas_objeto = [Data(int(data.split('/')[0]), int(data.split('/')[1]), int(data.split('/')[2])) for data in self.__lista]

        # Ordenar os objetos Data com base em suas propriedades de data
        datas_objeto.sort()

        # Atualizar a lista com as datas ordenadas em forma de strings
        self.__lista = [str(data) for data in datas_objeto]
                
                               
    
    # Se a lista de itens for de tamanho par, mostra o item menor (Data mais antiga)
    def mostraMediana(self):
       lista = self.__lista
       mediana = Data
       tamanho = len(lista)
        
       if (tamanho % 2 == 0):
           index = ((tamanho // 2 - 1) + (tamanho // 2)) // 2
           mediana = lista[index]
       else:
           mediana = lista[tamanho // 2]
           
       print("Mediana:" + mediana)
     
    def mostraMenor(self):
        lista = self.__lista
        print("Menor data: " + lista[0])
        
    
    def mostraMaior(self):
        index = len(self.__lista)
        print("Maior data: " + self.__lista[index - 1])
        
        
    def listarEmOrdem(self):
        print("Lista de datas em ordem crescente (Da mais antiga pra mais atual):")
        counter = 1
        for i in self.__lista:
            if counter < len(self.__lista):
                print(counter,"-",i, "\n")   
            else:
                print(counter,"-",i,"\n")
            counter += 1
    
    #Verifica na lista todas as datas que forem inferiores a 2019 e caso encontre, muda o dia do mês para 1.
    def modificar_dia_para_1(self, data):
        if data.ano < 2019:
            data.dia = 1
        return data

    #incrementa a função "modificar_dia_para_1" e muda a lista original.
    def modificaData(self):
        print("Modificando datas: ")

        datas_objeto = [Data(int(data.split('/')[0]), int(data.split('/')[1]), int(data.split('/')[2])) for data in self.__lista]

        # Usando filter para aplicar a função modificar_dia_para_1 aos objetos Data
        datas_filtradas = filter(self.modificar_dia_para_1, datas_objeto)
        
        # Convertendo o resultado de filter de volta para uma lista
        datas_modificadas = list(datas_filtradas)
       
        # Atualizar a lista com as datas ordenadas em forma de strings
        self.__lista = [str(data) for data in datas_modificadas]
        
        print("Datas modificadas com sucesso!")
          

    def __str__(self):
        pass
