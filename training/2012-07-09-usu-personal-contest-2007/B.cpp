#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

const int N = 100;

int n, graph[N][N], match[N], result[N];
bool visit[N];

bool find(int i) {
    if (visit[i]) {
        return false;
    }
    visit[i] = true;
    for (int j = 0; j < n; ++ j) {
        if (graph[i][j]) {
            if (match[j] == -1 || find(match[j])) {
                match[j] = i;
                return true;
            }
        }
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &graph[i][j]);
        }
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] |= graph[i][k] && graph[k][j];
            }
        }
    }
    memset(visit, 0, sizeof(visit));
    memset(match, -1, sizeof(match));
    for (int i = 0; i < n; ++ i) {
        if (find(i)) {
            memset(visit, 0, sizeof(visit));
        }
    }
    int m = 0;
    for (int i = 0; i < n; ++ i) {
        bool found = false;
        for (int j = 0; j < n; ++ j) {
            found |= match[j] == i;
        }
        if (!found) {
            result[m ++]= i;
        }
    }
    while (true) {
        bool found = false;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                while (graph[result[i]][result[j]]) {
                    result[j] = match[result[j]];
if (result[j] == -1) { vector <int> v; printf("%d\n", v[0]); }
                    found = true;
                }
            }
        }
        if (!found) {
            break;
        }
    }
    printf("%d\n", m);
    for (int i = 0; i < m; ++ i) {
        printf("%d ", result[i] + 1);
    }
    puts("");
    return 0;
}
