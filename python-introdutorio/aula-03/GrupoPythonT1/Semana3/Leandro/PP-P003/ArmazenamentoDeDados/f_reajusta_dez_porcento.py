from f_insere_usuario import listaDeDados

def Reajusta_dez_porcento(listaDeDados):
    
    for funcionario in listaDeDados:
        salario_atual = float(funcionario['salario'])
        reajuste = salario_atual + (salario_atual * 0.1)
        funcionario['salario'] = "{:.2f}".format(reajuste)  # Formatando o salário com duas casas decimais novamente
        
print("Salários reajustados com sucesso!")
print(" ")