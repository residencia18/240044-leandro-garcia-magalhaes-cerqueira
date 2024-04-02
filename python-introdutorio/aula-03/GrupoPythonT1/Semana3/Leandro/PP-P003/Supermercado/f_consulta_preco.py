from Supermercado.f_inserir_produto import listaDeProdutos

def consultaPreco():
    
    codigo = input("Insira o código do produto que deseja consultar: ")    
    chaveProcurada = 'codigo'
    index = 0
    
    for produto in listaDeProdutos:
        achou = False
        if chaveProcurada in produto:
            if produto[chaveProcurada] == codigo:
                print("\n")
                print("Produto encontrado!")
                print("-----------------------------")
                print("Código: " + produto['codigo'])
                print("Nome: " + produto['nome'])
                print("Preço: " + produto['preco'])
                print("-----------------------------")
                print(" ")
                achou = True
                break 
        index += 1
        
    if not achou:  # Verifica se achou é False
        print("Produto não encontrado. Certifique-se de que digitou o código corretamente.")
        print("\n")
        
 
   