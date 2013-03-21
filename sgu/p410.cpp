// SGU 410 -- Galaxy in danger
#include <cstdio>
#include <cstring>
#include <queue>
#include <utility>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;

typedef long long LL;

int n;
LL a[N];

LL solve() {
    LL ret = 0;
    LL top = *std::max_element(a, a + n);
    for (int i = 0; i < n; ++ i) {
        LL x = a[i];
        LL y = top;
        while (x < y) {
            if (x * 2 <= y) {
                x *= 2;
                ret ++;
            } else {
                LL d = x * 2 - y;
                x -= d;
                y -= d;
            }
        }
    }
    return ret + top;
}

void construct() {
    std::priority_queue <std::pair <LL, int> > heap;
    for (int i = 0; i < n; ++ i) {
        heap.push(std::make_pair(-a[i], i));
    }
    LL top = *std::max_element(a, a + n);
    LL delta = 0;
    while (delta < top) {
        int id = heap.top().second;
        heap.pop();
        if (a[id] * 2 <= top + delta) {
            a[id] *= 2;
            std::cout << "science mission to the planet " << id + 1 << std::endl;
        } else {
            delta ++;
            std::cout << "flying mission" << std::endl;
        }
        heap.push(std::make_pair(-a[id], id));
    }
}

int main() {
    std::cin >> n;
    for (int i = 0; i < n; ++ i) {
        std::cin >> a[i];
    }
    LL answer = solve();
    std::cout << answer << std::endl;
    if (answer <= 1000) {
        construct();
    }
    return 0;
}
