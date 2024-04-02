#include <iostream>

int main() {
    unsigned char informacaoGenetica;

    // Passo A: Ler a informação genética da planta
    std::cout << "Informe a informação genética da planta (um valor entre 0 e 255): ";
    std::cin >> informacaoGenetica;

    // Passo B: Contar quantos genes estão presentes usando operadores bit a bit
    int genesPresentes = 0;
    for (int i = 0; i < 8; i++) {
        if (informacaoGenetica & (1 << i)) {
            genesPresentes++;
        }
    }

    std::cout << "Número de genes presentes nesta planta: " << genesPresentes << std::endl;

    // Passo C: Solicitar ao usuário um número de gene específico
    int geneEspecifico;
    std::cout << "Informe o número de gene que você deseja verificar (entre 1 e 8): ";
    std::cin >> geneEspecifico;

    // Verificar se o gene específico está presente
    if (geneEspecifico >= 1 && geneEspecifico <= 8) {
        bool presente = informacaoGenetica & (1 << (geneEspecifico - 1));
        if (presente) {
            std::cout << "O gene " << geneEspecifico << " está presente nesta planta." << std::endl;
        } else {
            std::cout << "O gene " << geneEspecifico << " não está presente nesta planta." << std::endl;
        }
    } else {
        std::cout << "Número de gene inválido. Por favor, informe um número entre 1 e 8." << std::endl;
    }

    return 0;
}
