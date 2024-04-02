listaDeDados = []

def insereUsuario():
    while True:
        nome = input("Insira o nome (Ou digite 'x' para sair):  ")
        
     # Verifica se o usuário deseja sair do loop
        if nome.lower() == 'x':
            break
    
        sobrenome = input("Sobrenome: ")
    
        ano_nascimento = int(input("Ano de nascimento: "))
    
        rg = input("RG: ")
    
        ano_admissao = int(input("Ano de admissão: "))
    
        s = float(input("Informe o seu salário: "))
        salario = "{:.2f}".format(s) #Formata um tipo float para 2 casas decimais.
    
        funcionario = {'nome': nome,
                    'sobrenome': sobrenome,
                    'ano_nascimento': ano_nascimento,
                    'rg': rg,
                     'ano_admissao': ano_admissao,
                     'salario':salario}
        
        novo_funcionario = str(funcionario)
        
        with open('dados.txt', 'a') as arquivo:
            arquivo.write(novo_funcionario + "\n")
            
    arquivo.close()
    
    with open('dados.txt', 'r') as arquivo:
        
        for linha in arquivo:
            linha.strip()
            funcionario = eval(linha)
            listaDeDados.append(funcionario)
            

        