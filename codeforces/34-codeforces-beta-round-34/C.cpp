// Codeforces Beta Round #34
// Problem C -- Page Numbers
#include <cstdio>
#include <sstream>
#include <vector>
#include <iostream>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) ((int)(v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

int main() {
    std::string buffer;
    std::getline(std::cin, buffer);
    foreach (iter, buffer) {
        if (*iter == ',') {
            (*iter) = ' ';
        }
    }
    std::istringstream sin(buffer);
    std::vector <int> numbers;
    {
        int tmp;
        while (sin >> tmp) {
            numbers.push_back(tmp);
        }
    }
    std::sort(ALL(numbers));
    numbers.erase(std::unique(ALL(numbers)), numbers.end());
    bool first = true;
    for (int i = 0; i < SIZE(numbers); ) {
        int j = i;
        while (j < SIZE(numbers) && numbers[j] - numbers[i] == j - i) {
            j ++;
        }
        if (first) {
            first = false;
        } else {
            putchar(',');
        }
        if (i + 1 == j) {
            printf("%d", numbers[i]);
        } else {
            printf("%d-%d", numbers[i], numbers[j - 1]);
        }
        i = j;
    }
    puts("");
    return 0;
}
