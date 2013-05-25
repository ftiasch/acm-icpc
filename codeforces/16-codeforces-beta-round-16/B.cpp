// Codeforces Beta Round #16
// Problem B -- Burglar and Matches
#include <cstdio>
#include <utility>
#include <functional>
#include <iostream>
#include <algorithm>

typedef long long LL;

const int M = 20;

LL n;
int m;
std::pair <int, int> boxes[M];

int main() {
    std::cin >> n >> m;
    for (int i = 0; i < m; ++ i) {
        std::cin >> boxes[i].second >> boxes[i].first;
    }
    std::sort(boxes, boxes + m, std::greater <std::pair <int, int> >());
    LL answer = 0;
    for (int i = 0; i < m && n; ++ i) {
        LL take = std::min(n, (LL)boxes[i].second);
        n -= take;
        answer += take * boxes[i].first;
    }
    std::cout << answer << std::endl;
    return 0;
}
