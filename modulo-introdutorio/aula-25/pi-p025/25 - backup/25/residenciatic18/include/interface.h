#pragma once

#include <iostream>
#include <chrono>
#include <thread>
#include "ctrl_RedeSocial.h"

using namespace std;

// Protótipo da função de animação de carregamento
void loadingAnimation();

// Protótipo da função para limpar o terminal
void clearScreen();

// Protótipo da função para a página de Twitter após o login
void PageTwitterConected(bool &loggedIn, Usuario &usuario);

// Protótipo da função de login
void login();

// Protótipo da função para a página principal do Twitter
void PageTwitter();

void registerUsuario();

int main();

