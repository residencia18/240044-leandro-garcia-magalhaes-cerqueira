from ListaDatas import ListaDatas
from ListaSalarios import ListaSalarios
from ListaIdades import ListaIdades
from ListaNomes import ListaNomes

#from AnaliseDados import AnaliseDados

def main():
    
    datas = ListaDatas()
    nomes = ListaNomes()
    salarios = ListaSalarios()
    idades = ListaIdades()
    
    
    while True:

            print("========== Menu de opções ==========")
            print(" ")
            print("[1] - Incluir um nome na lista de nomes: ")
            print("[2] - Incluir salário na lista de salários: ")
            print("[3] - Incluir data na lista de datas: ")
            print("[4] - Incluir idade na lista de idades: ")
            print("[5] - Percorrer as listas de nomes e salários: ")
            print("[6] - Calcular o valor da folha com um reajuste de 10% ")
            print("[7] - Modificar o dia das datas anteriores a 2019")
            print("[8] - Relatório completo")
            print("[0] - Sair. ")

            opcao = int(input("Escolha uma opção: "))
            print(" ")
            
            if opcao == 1:
                nomes.entradaDeDados()
                print(" ")
                        
            elif opcao == 2:
    
                salarios.entradaDeDados()
                print(" ")
                        
            elif opcao == 3:
                datas.entradaDeDados()
                print(" ")
                
            elif opcao == 4:
                idades.entradaDeDados()
                print(" ")
                
            elif opcao == 5:
                for nome, salario in zip(nomes._ListaNomes__lista, salarios._ListaSalarios__lista):
                    nome_completo = nome.obter_nome_completo()  # Obtém o nome completo usando o método da classe Nome
                    print(f"Nome: {nome_completo} - Salário: {salario}")

                print(" ")
                     
            elif opcao == 6:
                salarios.reajusteSalarios()
                print("Salários reajustados em 10% com sucesso.")
                
            elif opcao == 7:
                datas.modificaData()
                print(" ")
                
            elif opcao == 8:
                print("--------Lista Datas--------")
                datas.listarEmOrdem()
                print(" ")
                datas.mostraMenor()
                print(" ")
                datas.mostraMaior()
                print(" ")
                datas.mostraMediana()
                print(" ")
                print("--------Lista Nomes--------")
                nomes.listarEmOrdem()
                print(" ")
                nomes.mostraMenor()
                print(" ")
                nomes.mostraMaior()
                print(" ")
                nomes.mostraMediana()
                print(" ")
                print("--------Lista Idades--------")
                idades.listarEmOrdem()
                print(" ")
                idades.mostraMenor()
                print(" ")
                idades.mostraMaior()
                print(" ")
                idades.mostraMediana()
                print(" ")
                print("--------Lista Salarios--------")
                salarios.listarEmOrdem()
                print(" ")
                salarios.mostraMenor()
                print(" ")
                salarios.mostraMaior()
                print(" ")
                salarios.mostraMediana()
                print(" ") 
                
            elif opcao == 0:
                print("Programa finalizado. Até a próxima!")
                exit(0) 
                   
            else:
                print("Opcão inválida. Tente novamente.")
                print(" ")
                

    

if __name__ == "__main__":
    main()