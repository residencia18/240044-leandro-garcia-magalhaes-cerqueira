#include "ctrl_biblioteca.h"
#include <ctime>
#include <iostream>
#include <string>
#include <iomanip> // Para std::get_time

using namespace std;

//Função de validar a data (Náthalie).



// Função insere_livro
void ctrl_biblioteca::insere_livro()
{
    string titulo, autor;
    int copias;
    cin.ignore();

    cout << "Titulo do livro:";
    getline(cin, titulo);

    cout << "Nome do autor:";
    getline(cin, autor);

    cout << "Copias do livro:";
    cin >> copias;

    Livro novoLivro(titulo, autor, copias);
    
    
    int indice;
    if (localizarLivro(titulo, indice))
    {
        acervo[indice].setCopias(copias);

        string confirma;
        cin.ignore();
        cout << "Deseja adicionar um novo livro s/n:";
        getline(cin, confirma);

        if (confirma == "s" || confirma == "S")
        {
            insere_livro();
            cout << "Livro inserido com sucesso!" <<endl;
        }
    }else{
        acervo.push_back(novoLivro);
        cout << "Livro inserido com sucesso!" <<endl;

    }

    
}

// Função registrarEmprestimo
void ctrl_biblioteca::registrarEmprestimo()
{
    string titulo, nome;

    cin.ignore();
    cout <<endl<<"Nome do usuario:";
    getline(cin, nome);
    
    int indiceUsuario;
    if (localizarUsuarioPorNome(nome, indiceUsuario))
    {
        cout << "Titulo do livro:";
        getline(cin, titulo);

        int indiceLivro;
        if (localizarLivro(titulo, indiceLivro) && verificarDisponibilidadeDelivro(titulo))
        {
            string data;
            cout << "Data (Formato dd/mm/aaaa): ";
            getline(cin, data);

            string autor = acervo[indiceLivro].getAutor();
            int copias = 1;
            Livro livro(titulo, autor, copias);
            Emprestimo emprestimo(nome, data, livro);

            string confimar;
            acervo[indiceLivro].setCopias(-1);

            while (true)
            {
                cout << "Deseja adicionar um novo livro ao empréstimo: s/n" << endl;
                getline(cin, confimar);
                if (confimar == "s" || confimar == "S")
                {
                    cout << "Titulo do livro:";
                    getline(cin, titulo);
                    if (localizarLivro(titulo, indiceLivro) && verificarDisponibilidadeDelivro(titulo))
                    {
                        autor = acervo[indiceLivro].getAutor();
                        int copias = 1;
                        Livro livro(titulo, autor, copias);
                        emprestimo.setRegistro(livro);
                        acervo[indiceLivro].setCopias(-1);
                    }
                    else
                    {
                        cout << "Não foi possível realizar o empréstimo." << endl;
                    }
                }
                else
                {
                    break;
                }
            }

            usuarios[indiceUsuario].setEmprestimos(emprestimo);
        }
        else
        {
            cout << "Livro não encontrado ou não disponível." << endl;
        }
    }
    else
    {
        cout << "Usuário não encontrado." << endl;
    }
}

void ctrl_biblioteca::exibirTodosEmprestimosDoUsuario()
{
    cout <<"Digite o nome:";
    cin.ignore();
    string nome;
    getline(cin, nome);
    int indiceUsuario;
    if (localizarUsuarioPorNome(nome, indiceUsuario))
    {
        Usuario usuario = usuarios[indiceUsuario];
        vector<Emprestimo> emprestimosUsuario = usuario.getEmprestimos();

        if (emprestimosUsuario.empty())
        {
            cout << "Nenhum empréstimo encontrado para o usuário '" << nome << "'" << endl;
        }
        else
        {
            cout <<endl<< "Empréstimos de " << nome << ":" << endl;

            for (Emprestimo &emprestimo : emprestimosUsuario)
            {
                cout << "Data do empréstimo: " << emprestimo.getData() << endl;
                for (Livro &livro : emprestimo.getRegistro())
                {
                    livro.exibirInformacoes();
                }
            }
        }
    }
    else
    {
        cout << "Usuário não encontrado." << endl;
    }
}

bool ctrl_biblioteca::localizarLivro(string _titulo, int &indice)
{
    string titulo;
    for (int i = 0; i < acervo.size(); ++i)
    {
        titulo = acervo[i].getTitulo();
        if (titulo == _titulo)
        {
            indice = i;  // Armazena o índice do livro
            return true; // O livro foi encontrado
        }
    }
    indice = -1;
    return false; // O livro não foi encontrado
}

bool ctrl_biblioteca::verificarDisponibilidadeDelivro(string &titulo)
{

    int indice;
    int copias;
    if (localizarLivro(titulo, indice))
    {
        copias = acervo[indice].getCopias();
        if ( copias > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    else
    {
        return false;
    }
}

void ctrl_biblioteca::verificarDisponibilidadeDelivroBiblioteca()
{
    
    string titulo;
    cout << "Titulo do livro:";
    cin.ignore();
    getline(cin, titulo);

    int indice;
    if (localizarLivro(titulo, indice))
    {   
        if (acervo[indice].getCopias() > 0 && indice != -1)
        {
            cout << "Livro disponivel! Número de cópias restantes: " << acervo[indice].getCopias() << endl;
        }
        else
        {
            cout << "Livro Indisponivel" << endl <<endl;
        }
    }else{
        cout << "Livro Indisponivel" << endl <<endl;
    }
}

bool ctrl_biblioteca::localizarUsuarioPorNome(string nome, int &indice)
{
    for (int i = 0; i < usuarios.size(); ++i)
    {
        if (usuarios[i].getNome() == nome)
        {
            indice = i;  // Armazena o índice do usuário
            return true; // O usuário foi encontrado
        }
    }
    return false; // O usuário não foi encontrado
}


void ctrl_biblioteca::exibirInfoEmprestimoDoLivro(string titulo)
{
    bool emprestimosEncontrados = false; // Variável para rastrear se foram encontrados empréstimos

    for ( Usuario &usuario : usuarios)
    {
        for (Emprestimo &emprestimo : usuario.getEmprestimos())
        {
            for (Livro &livro : emprestimo.getRegistro())
            {
                if (livro.getTitulo() == titulo)
                {
                    cout << "Usuário: " << usuario.getNome() << endl;
                    cout << "Data do empréstimo: " << emprestimo.getData() << endl;
                    livro.exibirInformacoes();
                    cout << "---------------------" << std::endl;
                    emprestimosEncontrados = true;
                }
            }
        }
    }

    if (!emprestimosEncontrados)
    {
        cout << "Nenhum empréstimo encontrado para o livro '" << titulo << "'" << endl;
    }
}

ctrl_biblioteca::~ctrl_biblioteca(){};

vector<Livro> ctrl_biblioteca::acervo = {
    Livro("amor", "Autor 1", 2),
    Livro("deus", "Autor 2", 3),
    Livro("lar", "Autor 3", 7)
};

vector<Usuario> ctrl_biblioteca::usuarios = {
    Usuario("daniel"),
    Usuario("lorena"),
    Usuario("leo")
};
