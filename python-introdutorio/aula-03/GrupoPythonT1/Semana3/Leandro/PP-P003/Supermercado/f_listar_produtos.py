from Supermercado.f_inserir_produto import listaDeProdutos

def imprimir_em_lotes(lista):
    tamanho_lote = 10  # Define o tamanho do lote

    for i in range(0, len(lista), tamanho_lote):
        lote = lista[i:i + tamanho_lote]  # Fatia a lista em lotes de tamanho_lote
        print("Lote de produtos:")
        print("----------------------------------------")
        for produto in lote:
            print("Código: " + produto['codigo'])
            print("Nome: " + produto['nome'])
            print("Preço: " + produto['preco'])
            print(" ")
        input("Pressione Enter para mostrar o próximo lote de produtos...")
        print("\n")

def listaProdutos():
    listaDeProdutos.sort(key=lambda x: float(x['preco']))
    imprimir_em_lotes(listaDeProdutos)
