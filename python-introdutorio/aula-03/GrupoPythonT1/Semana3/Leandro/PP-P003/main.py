from Supermercado import f_inserir_produto
from Supermercado import f_excluir_produto
from Supermercado import f_listar_produtos
from Supermercado import f_consulta_preco

def main():
    while True:

        print("========== Supermercado Bem Melhor ==========")
        print(" ")
        print("[1] - Inserir novo produto: ")
        print("[2] - Excluir um produto: ")
        print("[3] - Listar produtos: ")
        print("[4] - Consultar preço (através do código do produto): ")
        print("[0] - Sair. ")

        opcao = int(input("Escolha uma opção: "))
        print(" ")
        
        if opcao == 1:
            f_inserir_produto.inserirProduto()
            print(" ")
                    
        elif opcao == 2:
            f_excluir_produto.excluirProduto()
            print(" ")  
                     
        elif opcao == 3:
            f_listar_produtos.listaProdutos()
            print(" ")
            
        elif opcao == 4:
            f_consulta_preco.consultaPreco()
            print(" ")
            
        elif opcao == 0:
            print("Programa finalizado. Até a próxima!")
            exit(0)
        else:
            print("Opcão inválida. Tente novamente.")
            print(" ")
            

if __name__ == "__main__":
    main()


