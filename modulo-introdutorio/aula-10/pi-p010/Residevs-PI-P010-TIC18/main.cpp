#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#define TRACO "---------------------------------------------------------------------------------------------------"
using namespace std;

// Estrutura https://drive.google.com/file/d/1DOSEwaityLckkWh3SqNXxnqVoSlRgQBL/view?usp=sharing
struct Aluno
{
    string nome;
    double nota1;
    double nota2;
    double media;
};
void inserirAluno(vector<Aluno> &alunos, int nAluno);// Insere um aluno e suas notas
void listarAlunos(vector<Aluno> alunos);              // lista nome, nota1, nota2. Mais pode ser implementado para listar media.
int buscarAluno(vector<Aluno> &alunos);               // retona o indice do aluno
void alterarAluno(vector<Aluno> &alunos);             // Precisa atualizar a media quando atualizar a nota? Devo criar uma função para isso?
void excluirAluno(vector<Aluno> &alunos);             // Precisa reordenar? Mesmo que eu exluia uma valor de uma lista ordenada ela continua ordenada. A esta está ordenada antes de excluir?
void bubblesort(vector<Aluno> &alunos);               // ordenar em ordem lexografica.
double calcularMediaIndividual(Aluno aluno);          // A ideia era usar para inseri a medias de todos os alunos.Mais ṕode ser implementado ou criado outra função para media individual por exemplo.
void calcularMediaGeral(vector<Aluno> &alunos);       // calcular e alterar a média de todos os alunos
int main()
{
    // Fluxograma do algoritmo  https://drive.google.com/file/d/1hmIH267o7IIGC-jx0_fUVAD5jitPDvvV/view
    char resposta;
    int quantidadeMaxima;
    int quantidadeAlunos = 0;
    bool continuar;
    vector<Aluno> alunos;

    cout << "Defina a quantidade máxima de alunos: " << endl;
    cin >> quantidadeMaxima;
    // inserir aluno
    do
    {
        if (quantidadeAlunos < quantidadeMaxima)
        {
            quantidadeAlunos++;
            inserirAluno(alunos, quantidadeAlunos);
            cout << "Deseja incluir mais alunos? (s/n): " << endl;
            cin >> resposta;

            if (resposta == 's' || resposta == 'S')
                continuar = true;
            else
            {
                continuar = false;
            }
        }
        else
        {
            cout << "Ops! Não será possivel continuar. O limite máximo de alunos cadastrados foi atingido." << endl;
            continuar = false;
            break;
        }


    } while (continuar);

    // Excluir aluno
    continuar = true;

    do
    {

        cout << "Deseja excluir algum alunos? (s/n)?: " << endl;
        cin >> resposta;
        if (resposta == 's' || resposta == 'S')
        {
            cout << "Insira o nome do aluno que Excluir: ";
            excluirAluno(alunos);
        }
        else
        {
            continuar = false;
        }

    } while (continuar);

    // listar
    calcularMediaGeral(alunos);
    listarAlunos(alunos);

    // alterar
    continuar = true;
    do
    {
        cout << "\n\nDeseja alterar alguma nota? (s/n): ";
        cin >> resposta;
        if (resposta == 's' || resposta == 'S')
        {
            alterarAluno(alunos);
        }else{
            continuar = false;
        }


    } while (continuar);

    // Excluir aluno
    continuar = true;

    // listar
    listarAlunos(alunos);

   

    return 0;
    
}

void inserirAluno(vector<Aluno> &alunos, int nAluno){
    Aluno novoAluno;
    string nome;
    double nota1;
    double nota2;

    do
    {
        cout << "Digite o nome do aluno " << nAluno << ":" << endl;
        cin.ignore();
        getline(cin, nome);
    } while (nome == "");

    cout << "Digite a nota 1: " << endl;
    cin >> nota1;
    cout << "Digite a nota 2: " << endl;
    cin >> nota2;

    novoAluno.nome = nome;
    novoAluno.nota1 = nota1;
    novoAluno.nota2 = nota2;
    novoAluno.media = calcularMediaIndividual(novoAluno);
    alunos.push_back(novoAluno);
}

void listarAlunos(const vector<Aluno> alunos)
{
    string situacao = "";

    cout << TRACO << endl;
    cout << left << setw(40) << "NOME" << setw(16) << "NOTA 1" << setw(16) << "NOTA 2" << setw(16) << "MEDIA" << setw(16) << "SITUAÇÃO" << endl;
    cout << TRACO << endl;

    for (const Aluno &aluno : alunos)
    {
        situacao = aluno.media < 7 ? "REPROVADO" : "APROVADO";
        cout << left << setw(40) << aluno.nome << setw(16) << setprecision(2) << aluno.nota1 << setw(16) << setprecision(2) << aluno.nota2 << setw(16) << setprecision(2) << aluno.media << setw(16) << situacao << endl;
    }
    cout << TRACO << endl;

}

void bubblesort(std::vector<Aluno> &alunos)
{
    int resultado;
    Aluno aux;

    for (auto i = alunos.begin(); i != alunos.end(); ++i)
    {
        for (auto j = alunos.begin(); j != alunos.end() - 1; ++j)
        {
            resultado = (j + 1)->nome.compare(j->nome);

            if (resultado < 0)
            {
                // If aluno at j+1 comes before aluno at j in lexicographic order, swap them
                aux = *j;
                *j = *(j + 1);
                *(j + 1) = aux;
            }
        }
    }
}

int buscarAluno(vector<Aluno> &alunos)
{

    string nome_aluno;
    int qtd_alunos = alunos.size();
    cin.ignore();
    getline(cin, nome_aluno);

    for (int i = 0; i < qtd_alunos; i++)
    {
        if (nome_aluno == alunos[i].nome)
        {
            cout << "Aluno encontrado!" << endl;
            return i;
        }
    }
    cout << "Aluno não encontrado." << endl;
    return -1;
}

void excluirAluno(vector<Aluno> &alunos)
{

    bool para = false;
    char escolha;
    do
    {

        int indice = buscarAluno(alunos);

        if (indice != -1)
        {
            alunos.erase(alunos.begin() + indice);
            cout << "Aluno removido com sucesso!" << endl;
        }
        else
        {
            cout << "Aluno não encontrado, portanto nenhum aluno foi removido." << endl;
        }
        cout << "Deseja excluir mais algum aluno? (s/n): ";
        cin >> escolha;

        if (escolha != 's' && escolha != 'S')
        {
            cout << "\n\nInforme o nome do aluno que deseja Excluir: " << endl;
            para = true;
        }
    } while (para == false);
}

void alterarAluno(vector<Aluno> &alunos)
{
    bool sair = true;
    int opcao;
    string nome;
    Aluno aluno;
    int index;

    cout << "Insira o nome do aluno que deseja alterar: ";
    index = buscarAluno(alunos);
    if (index >= 0)
    {
        aluno = alunos[index];

        do
        {
            cout << "\n\nAluno: " << aluno.nome << endl;
            cout << "\nNota 1: " << aluno.nota1 << endl;
            cout << "\nNota 2: " << aluno.nota2 << endl;

        
            cout << "\n\n[ 1 ] Alterar primeira nota\n";
            cout << "\n[ 2 ] Alterar segunda nota\n";
            cout << "\n[ 0 ] Nenhuma nota\n";

            cout << "\nOpção: ";
            cin >> opcao;

            switch (opcao){
            case 1:
                cout << "\n\nNota 1: ";
                cin >> aluno.nota1;

                alunos[index].nota1 = aluno.nota1;


                break;

            case 2:
                cout << "\n\nNota 2: ";
                cin >> aluno.nota2;

                alunos[index].nota2 = aluno.nota2;


                
                break;

            default:
               sair = false;
               break;
            }
        } while (sair);


        alunos[index].media = calcularMediaIndividual(aluno);   


    }
    else
    {
        cout << "Aluno não encontrado." << endl;
    }
}

double calcularMediaIndividual(Aluno aluno) 
{
    aluno.media = (aluno.nota1 + aluno.nota2) / 2;

    return aluno.media;
};

void calcularMediaGeral(vector<Aluno> &alunos) 
{
    for ( Aluno aluno: alunos ) {
        aluno.media = (aluno.nota1 + aluno.nota2) / 2;
    }
};