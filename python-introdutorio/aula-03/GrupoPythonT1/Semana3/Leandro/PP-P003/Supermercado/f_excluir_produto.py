from Supermercado.f_inserir_produto import listaDeProdutos

def excluirProduto():
    
    chaveProcurada = 'codigo'
    codigoProcurado = input("Insira o código do produto que deseja excluir:")
    
    index = 0
    for produto in listaDeProdutos:
        achou = False
        if chaveProcurada in produto:
            if produto[chaveProcurada] == codigoProcurado:
                del listaDeProdutos[index]
                print("Produto removido com sucesso!")
                print("\n")
                achou = True
                break
        
        index += 1
        
    if not achou:  # Verifica se achou é False
        print("Produto não encontrado. Certifique-se de que digitou o código corretamente.")
        print("\n")
        
    