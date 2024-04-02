#include "ctrl_RedeSocial.h"
#include <ctime>


Ctrl_RedeSocial::Ctrl_RedeSocial(/* args */){}


//Função que limita a quantidade de caracteres inseridos pelo usuário.
bool Ctrl_RedeSocial::verificaTamanhoMaximo(const std::string& texto) {
    if (texto.size() > 255) {
        return false; // Retorna falso se o tamanho for maior que 255.
    }
    return true; // Retorna verdadeiro se o tamanho estiver dentro do limite.
}

// Função para obter a data atual no formato "dd/mm/aaaa"
string Ctrl_RedeSocial::obterDataAtual() {
    // Obtém o tempo atual
    std::time_t tempo_atual = std::time(nullptr);

    // Converte o tempo atual em uma estrutura tm (que contém informações de data e hora)
    std::tm* data_hora = std::localtime(&tempo_atual);

    // Formata a data como "dd/mm/aaaa"
    char dataFormatada[11];
    std::strftime(dataFormatada, sizeof(dataFormatada), "%d/%m/%Y", data_hora);

    return std::string(dataFormatada);
}

Usuario Ctrl_RedeSocial:: acessarConta(string username, bool &request){

    int _token;
    request = token(username, _token);
    return usuarios_do_twitter[_token];
}

bool Ctrl_RedeSocial::token(string username, int &token_acesso){
    string username_usuario;
    for (int i =0; i < usuarios_do_twitter.size();i++){
        username_usuario = usuarios_do_twitter[i].getUsername();
      if (username_usuario == username){
         token_acesso = i;
         return true;
      }
      
    }
    token_acesso = -1;
    return false;
}

void Ctrl_RedeSocial:: criarConta(Usuario &novo){
    usuarios_do_twitter.push_back(novo);
}

void Ctrl_RedeSocial:: adicionarPoster(Usuario &usuario){
    cin.ignore();
    string descricao, data;
    
    cout<<"Descreva (máx. 255 caracteres):" <<endl;
    getline(cin, descricao);

       if (verificaTamanhoMaximo(descricao)) {

        string data = obterDataAtual();

    Twitter poster(usuario, descricao, data);
    postagens_do_twitter.push_back(poster);
 
    } else { 
        std::cerr << "Erro: Seu tweet possui mais de 255 caracteres." << std::endl;
        return;
    }

}

void Ctrl_RedeSocial::seguirPerfil(Usuario &usuario){
    int token_acesso;
    token(usuario.getUsername(), token_acesso);
    usuarios_do_twitter[token_acesso] = usuario;
}

void Ctrl_RedeSocial::timeline(Usuario &usuario) {
    vector<Usuario> amigos = usuario.getSeguindo();
    vector<Twitter> postagem_dos_amigos;

    for (size_t i = 0; i < amigos.size(); i++) {
        for (Twitter &post : postagens_do_twitter) {
            if (post.getAutor() == amigos[i]) {
                postagem_dos_amigos.push_back(post);
            }
        }
    }

    // Sort the posts in chronological order based on the creation date.
    sort(postagem_dos_amigos.begin(), postagem_dos_amigos.end(), 
        [](Twitter &a, Twitter &b) {
            return a.getData_criacao() > b.getData_criacao();
        });

    // Now, postagem_dos_amigos contains the combined posts of all friends sorted by date.
    // You can display these posts in chronological order.
    for (Twitter &post : postagem_dos_amigos) {
        cout << "@" << post.getAutor().getUsername() << " tweeted on " << post.getData_criacao() << ":\n";
        cout << post.getConteudo() << "\n";
        cout << "------------------------------------\n";
    }
}


Usuario  Ctrl_RedeSocial::  getUsuario(int &token_acesso){

    return usuarios_do_twitter[token_acesso];
}

Ctrl_RedeSocial::~Ctrl_RedeSocial(){}

// ...
vector<Usuario> Ctrl_RedeSocial::usuarios_do_twitter = {
    Usuario("oliveira4552", "Daniel Oliveira da silva",  Usuario("oliveira4552", "Daniel Oliveira da silva")),
    Usuario("lorena", "Lorena Andrade", Usuario("lorena", "Lorena Andrade")),
    Usuario("diascarlos", "Caros Dias", Usuario("diascarlos", "Caros Dias"))
};

vector<Twitter> Ctrl_RedeSocial::postagens_do_twitter = {
    Twitter(usuarios_do_twitter[0], "Eu amo morango!", "20/03/2023"),
    Twitter(usuarios_do_twitter[0], "Adotei um pet!", "28/05/2023"),
    Twitter(usuarios_do_twitter[0], "Virei vegano :)!", "20/03/2023"),
    Twitter(usuarios_do_twitter[1], "Sou mãe de dois filhos!", "20/05/2015"),
    Twitter(usuarios_do_twitter[1], "Casei aos 19 anos, e sou feliz!", "16/07/2017"),
    Twitter(usuarios_do_twitter[2], "Os impostos acabam com meu salário de 9.5k ;-;", "16/10/2023")
};

