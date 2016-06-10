#include <iostream>
#include <string>

const int PRIMES[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 4, 9, 25, 49};

int main()
{
    int count = 0;
    for (auto&& p : PRIMES) {

        std::cout << p << std::endl;
        std::string result;
        std::cin >> result;
        count += result == "yes";
    }
    std::cout << (count >= 2 ? "composite" : "prime") << std::endl;
}
