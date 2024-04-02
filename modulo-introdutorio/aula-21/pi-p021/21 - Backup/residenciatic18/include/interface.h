#pragma once

#include <string>
#include <vector>
#include "livro.h"
#include "usuario.h"
#include "ctrl_biblioteca.h"

void aguarde();
int monta_menu(std::vector<std::string> itens, std::string titulo);
void repitirOperacaoLivro(std::string nome_da_operação, Livro livro);
void repitirOperacaoEmprestimo(std::string nome_da_operacao, Usuario usuario, Livro livro);
