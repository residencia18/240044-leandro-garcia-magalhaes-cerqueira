import f_insere_usuario
import f_reajusta_dez_porcento
import f_lista_funcionarios
from f_insere_usuario import listaDeDados

def main():
        
        while True:

            print("========== GERENCIAMENTO DE FUNCIONÁRIOS ==========")
            print(" ")
            print("[1] - Cadastrar funcionário: ")
            print("[2] - Reajustar salários: ")
            print("[3] - Listar funcionários: ")
            print("[0] - Sair. ")

            opcao = int(input("Escolha uma opção: "))
            print(" ")
            
            if opcao == 1:
                f_insere_usuario.insereUsuario()
                print(" ")
                        
            elif opcao == 2:
                f_reajusta_dez_porcento.Reajusta_dez_porcento(listaDeDados)
    
            elif opcao == 3:
                f_lista_funcionarios.listaFuncionarios()
                print(" ")
                
            elif opcao == 0:
                print("Programa finalizado. Até a próxima!")
                exit(0)
            else:
                print("Opcão inválida. Tente novamente.")
                print(" ")
                

if __name__ == "__main__":
    main()


