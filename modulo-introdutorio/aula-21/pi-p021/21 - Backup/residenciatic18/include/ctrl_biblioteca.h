#pragma once

#include <vector>
#include "livro.h"
#include "usuario.h"
#include "emprestimo.h"
#include "interface.h"

using namespace std; // Adicionado o using namespace std;

class ctrl_biblioteca
{
private:
    static vector<Livro> acervo;
    static vector<Usuario> usuarios;

public:
    ctrl_biblioteca(vector<Livro> acervo, vector<Usuario> usuarios);


    static void insere_livro();
    static bool localizarLivro(string titulo, int &indice);
    static bool verificarDisponibilidadeDelivro(string &livro);
    static bool localizarUsuarioPorNome(string nome, int &indice);
    static void registrarEmprestimo();
   
    static void verificarDisponibilidadeDelivroBiblioteca();
    static void exibirInfoEmprestimoDoLivro(string titulo);
    
    static void exibirTodosEmprestimosDoUsuario();
    ~ctrl_biblioteca();
};
