listaDeProdutos = [] #OBS: Para a lista poder ser acessada por outros arquivos, é necessário que ela esteja sendo declarada fora de uma função.

def inserirProduto():
            
    while True:
        
        codigo = input("Digite o código do produto (Formato de 13 dígitos numéricos: xxxxxxxxxxxxx) ou digite 'x' para encerrar: ")
        
         # Verifica se o usuário deseja sair do loop
        if codigo.lower() == 'x':
            break
        
        # Verifica se o usuário digitou menos que 13 caracteres
        
        if len(codigo) != 13:
            print("Código inválido! Você precisa inserir 13 digitos númericos. Ex: 0000000000001")
            break
        print(" ")
        
        nome = input("Digite o nome do produto: ")
        novo_nome = nome.capitalize() #Converte a primeira letra de uma string para maiúscula.
        
        preco = float(input("Informe o preço do produto: "))
        novo_preco = "{:.2f}".format(preco) #Formata um tipo float para 2 casas decimais.
        
        produto = {'codigo':codigo, 'nome':novo_nome, 'preco': novo_preco}
        
        listaDeProdutos.append(produto)
        
        print("Produto adicionado com sucesso!")
    print(" ")
    
        


  
        
    
    
    
    