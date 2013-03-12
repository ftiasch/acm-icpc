// SGU 347 -- Join the Strings
#include <iostream>
#include <string>
#include <algorithm>

const int N = 100;

std::string strings[N];

bool compare(const std::string &a, const std::string &b) {
    return a + b < b + a;
}

int main() {
    int n;
    std::cin >> n;
    for (int i = 0; i < n; ++ i) {
        std::cin >> strings[i];
    }
    std::sort(strings, strings + n, compare);
    for (int i = 0; i < n; ++ i) {
        std::cout << strings[i];
    }
    std::cout << std::endl;
    return 0;
}
