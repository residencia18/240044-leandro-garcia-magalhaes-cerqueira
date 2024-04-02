class Nome:
    def __init__(self, primeiro_nome, sobrenome):
        self.primeiro_nome = primeiro_nome
        self.sobrenome = sobrenome

    def obter_nome_completo(self):
        return f"{self.primeiro_nome} {self.sobrenome}"

    def obter_primeiro_nome(self):
        return self.primeiro_nome

    def obter_sobrenome(self):
        return self.sobrenome