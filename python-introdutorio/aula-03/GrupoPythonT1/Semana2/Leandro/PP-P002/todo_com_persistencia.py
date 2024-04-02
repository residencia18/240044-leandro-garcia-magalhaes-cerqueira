# Função para carregar as tarefas do arquivo
def carregar_tarefas():
    try:
        with open("lista.txt", "r") as arquivo:
            linhas = arquivo.readlines()
            for linha in linhas:
                descricao, concluida = linha.strip().split(",")
                tarefas.append({'descricao': descricao, 'concluida': concluida == 'True'})
    except FileNotFoundError:
        # Se o arquivo não existir, não há tarefas para carregar
        pass

# Função para salvar as tarefas no arquivo
def salvar_tarefas():
    with open("lista.txt", "w") as arquivo:
        for tarefa in tarefas:
            linha = f"{tarefa['descricao']},{tarefa['concluida']}\n"
            arquivo.write(linha)

# Inicialização da lista de tarefas
tarefas = []
carregar_tarefas()  # Carregar tarefas do arquivo, se existirem

# Função para listar as tarefas
def listar_tarefas():
    if not tarefas:
        print("Não há tarefas registradas.")
    else:
        print("Lista de Tarefas:")
        for idx, tarefa in enumerate(tarefas, start=1):
            status = "[x]" if tarefa['concluida'] else "[ ]"
            print(f"{idx}.{tarefa['descricao']} {status}")

# Função para registrar uma nova tarefa
def adicionar_tarefa():
    nova_tarefa = input("Digite a descrição da nova tarefa: ").capitalize()
    tarefas.append({'descricao': nova_tarefa, 'concluida': False})
    print("Tarefa registrada!!!")

# Função para marcar uma tarefa como realizada
def concluir_tarefa():
    listar_tarefas()
    id_tarefa = int(input("Digite o ID da tarefa a ser marcada como concluída: ")) - 1
    if 0 <= id_tarefa < len(tarefas):
        if not tarefas[id_tarefa]['concluida']:
            tarefas.insert(0, tarefas.pop(id_tarefa))
            tarefas[0]['concluida'] = True
            print("Tarefa marcada como realizada!")
        else:
            print("Esta tarefa já foi concluída.")
    else:
        print("ID de tarefa inválido.")

# Função para editar uma tarefa
def editar_tarefa():
    listar_tarefas()
    id_tarefa = int(input("Digite o ID da tarefa a ser editada: ")) - 1
    if 0 <= id_tarefa < len(tarefas):
        nova_descricao = input("Digite a nova descrição da tarefa: ").capitalize()
        tarefas[id_tarefa]['descricao'] = nova_descricao
        print("Tarefa editada com sucesso!")
    else:
        print("ID de tarefa inválido.")

# Menu principal
while True:
    print("\n------------ TO-DO LIST ------------")
    print("|       O que deseja fazer?        |")
    print("------------------------------------")
    print("[1] - Listar tarefas")
    print("[2] - Registrar uma nova tarefa")
    print("[3] - Marcar uma tarefa como feita")
    print("[4] - Editar uma tarefa")
    print("[0] - Sair.")

    escolha = input("Escolha uma opção: ")

    if escolha == '1':
        listar_tarefas()
    elif escolha == '2':
        adicionar_tarefa()
    elif escolha == '3':
        concluir_tarefa()
    elif escolha == '4':
        editar_tarefa()
    elif escolha == '0':
        print("Saindo da Lista de Tarefas. Até mais!")
        salvar_tarefas()  # Salvar tarefas antes de sair do programa
        break
    else:
        print("Opção inválida. Escolha novamente.")
