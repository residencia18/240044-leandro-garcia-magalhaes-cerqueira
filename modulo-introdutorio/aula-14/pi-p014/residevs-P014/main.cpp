#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#define TRACO "---------------------------------------------------------------------------------------------------"
using namespace std;

// ESPAÇO PARA AS STRUCTS ---------------

struct Livro
{
    string titulo = "";
    string autor;
    string genero;
    int disponibilidade = 0; //O tipo pode ser alterado depois!
};

struct Biblioteca
{
    string nome;
    string endereco;
    Livro livro;
  //Leitor leitor;
};

// ESPAÇO PARA AS STRUCTS ---------------

void adicionaLivro(vector<Livro> &livros);// Insere um aluno e suas notas

int main()
{

char resposta;
bool continuar = false;

    vector<Livro> livros;

    do
    {
            adicionaLivro(livros);
            cout << "Deseja adicionar mais um livro? (s/n): " << endl;
            cin >> resposta;

            if (resposta == 's' || resposta == 'S')
                continuar = true;
            else
            {
                continuar = false;
            }
        
    } while (continuar);

    return 0; 
}

void adicionaLivro(vector<Livro> &livros){

    Biblioteca biblioteca;
    string nome;
    
    do
    {
        cout << "Informe o título do livro: ";
        cin.ignore();
        getline(cin, nome);
    } while (nome == "");

    cout << "Informe o nome do autor: ";
    cin >> biblioteca.livro.autor;

    cout << "Informe o gênero do livro: ";
    cin >> biblioteca.livro.genero;

    biblioteca.livro.disponibilidade += 1; // Talvez haja a necessidade de alterar.

    biblioteca.livro.titulo = nome; 

}