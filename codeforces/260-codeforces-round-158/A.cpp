// Codeforces Round #158
// Problem A -- Adding Digits
#include <cstdio>
#include <cstring>
#include <sstream>
#include <iostream>
#include <string>

int a, b, n;

int main() {
    scanf("%d%d%d", &a, &b, &n);
    std::stringstream sout;
    sout << a;
    while (n --) {
        int d = 9;
        while (d >= 0 && (a * 10 + d) % b != 0) {
            d --;
        }
        if (d == -1) {
            std::cout << -1 << std::endl;
            return 0;
        }
        sout << d;
        a = (a * 10 + d) % b;
    }
    std::string s;
    sout >> s;
    std::cout << s << std::endl;
    return 0;
}
