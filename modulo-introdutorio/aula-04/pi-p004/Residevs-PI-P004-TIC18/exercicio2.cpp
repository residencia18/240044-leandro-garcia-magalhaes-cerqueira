#include <iostream>

/**#include <locale>
// A biblioteca <locale> serve para lidar com configurações de localização e codificação de caracteres.**/

using namespace std;

int main (){

 /**Configurar o locale para usar UTF-8.
    std::locale utf8_locale("en_US.utf8");
    std::locale::global(utf8_locale);**/

// Neste for é feito o loop de caracteres que vai do 0 até o 9.
    for (char i = '0'; i <= '9'; ++i){

    // a função "static_cast<int> serve para converter o caractere em seu valor numérico."
    cout << "O caractere " << i << " está na posição: " <<  static_cast<int>(i) << ", seu código octal é " << std::oct <<  static_cast<int>(i) << " e o hexadecimal é: " << std::hex  << static_cast<int>(i) << endl;   
    }

cout << endl;

char c;
char32_t cedilha = 'ç';
char32_t til = 'ã';

int pontoCodigoCedilha = (char32_t)cedilha;
int pontoCodigoTil = (char32_t)til;

    cout << "Agora insira um caractere qualquer: ";
    cin >> c;

    cout << "O caractere " << c << " está na posição: " << std::dec <<  static_cast<int>(c) << ", seu código octal é " << std::oct <<  static_cast<int>(c) << " e o hexadecimal é: " << std::hex  << static_cast<int>(c) << endl;

    cout << "O caractere " << cedilha << " está na posição: " << std::dec << pontoCodigoCedilha << ", seu código octal é " << std::oct << pontoCodigoCedilha << " e o hexadecimal é: " << std::hex  << pontoCodigoCedilha << endl;
    cout << "O caractere " << til << " está na posição: " << std::dec << pontoCodigoTil << ", seu código octal é " << std::oct << pontoCodigoTil << " e o hexadecimal é: " << std::hex  << pontoCodigoTil << endl;

    return 0;
}