// Codeforces Beta Round #25
// Problem D -- Roads not only in Berland
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>

const int N = 1000;

int n, parent[N];
std::vector <std::pair <int, int> > more;

int find(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i] = find(parent[i]);
}

int main() {
    scanf("%d", &n);
    memset(parent, -1, sizeof(parent));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        if (find(a) != find(b)) {
            parent[find(a)] = find(b);
        } else {
            more.push_back(std::make_pair(a, b));
        }
    }
    printf("%d\n", (int)more.size());
    int total = 0;
    for (int i = 1; i < n; ++ i) {
        if (find(i) == i && find(0) != find(i)) {
            printf("%d %d %d %d\n", more[total].first + 1, more[total].second + 1, 1, i + 1);
            total ++;
        }
    }
    return 0;
}
