// Codeforces Round #173
// Problem E -- Sausage Maximization
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

const int N = 100000;
const int L = 40;

struct Node {
    Node* to[2];

    Node() {
        to[0] = to[1] = NULL;
    }
};

int n;
long long a[N];

int main() {
    std::cin >> n;
    for (int i = 0; i < n; ++ i) {
        std::cin >> a[i];
    }
    long long prefix = 0;
    long long suffix = 0;
    for (int i = 0; i < n; ++ i) {
        prefix ^= a[i];
    }
    long long answer = 0;
    Node* root = new Node();
    for (int i = n - 1; i >= -1; -- i) {
        Node* p = root;
        for (int k = L - 1; k >= 0; -- k) {
            int t = suffix >> k & 1;
            if (!p->to[t]) {
                p->to[t] = new Node();
            }
            p = p->to[t];
        }
        long long sum = 0;
        p = root;
        for (int k = L - 1; k >= 0; -- k) {
            int t = prefix >> k & 1;
            if (p->to[t ^ 1]) {
                t ^= 1;
                sum |= 1LL << k;
            }
            p = p->to[t];
        }
        answer = std::max(answer, sum);
        if (i >= 0) {
            prefix ^= a[i];
            suffix ^= a[i];
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
