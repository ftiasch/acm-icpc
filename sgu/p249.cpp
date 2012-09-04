// SGU 249 -- Matrix
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

vector <int> construct(int n) {
    vector <int> result(1, 0);
    for (int i = 1; i < 1 << n; ++ i) {
        result.push_back(result.back() ^ (i & -i));
    }
    return result;
}

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    vector <int> row = construct(n);
    vector <int> colume = construct(m);
    for (int i = 0; i < 1 << n; ++ i) {
        for (int j = 0; j < 1 << m; ++ j) {
            printf("%d%c", (row[i] << m) | colume[j], j == (1 << m) - 1? '\n': ' ');
        }
    }
    return 0;
}
