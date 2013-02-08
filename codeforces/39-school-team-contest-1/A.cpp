// School Team Contest #1
// Problem A -- C*++ Calculations
#include <cctype>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <string>
#include <utility>
#include <vector>
#include <algorithm>

typedef std::pair <int, int> Pair;

int init;
char expr[10000];
std::vector <Pair> terms;

int main() {
    scanf("%d%s", &init, expr);
    int start = 0;
    bool negative = false;
    int length = strlen(expr);
    while (start < length) {
        int coef = 1;
        if (isdigit(expr[start])) {
            int times = start;
            while (expr[times] != '*') {
                times ++;
            }
            expr[times] = '\0';
            sscanf(expr + start, "%d", &coef);
            start = times + 1;
        }
        if (negative) {
            coef *= -1;
        }
        terms.push_back(std::make_pair(coef, expr[start] == '+'));
        if (start + 3 >= length) {
            break;
        }
        negative = expr[start + 3] == '-';
        start += 4;
    }
    std::sort(terms.begin(), terms.end());
    int answer = 0;
    for (int i = 0; i < (int)terms.size(); ++ i) {
        if (terms[i].second) {
            init ++;
            answer += terms[i].first * init;
        } else {
            answer += terms[i].first * init;
            init ++;
        }
    }
    printf("%d\n", answer);
    return 0;
}
