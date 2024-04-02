from f_insere_usuario import listaDeDados

def listaFuncionarios():
    for funcionario in listaDeDados:
        print("Nome completo: " + funcionario['nome'] + " " + funcionario['sobrenome'])
        print("Ano de nascimento: " + str(funcionario['ano_nascimento']))
        print("RG: " + str(funcionario['rg']))
        print("Ano de admissão: " + str(funcionario['ano_admissao']))
        print("Salário: " + str(funcionario['salario']))
        
        