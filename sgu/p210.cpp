// SGU 210 -- Beloved Sons
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 400;

int n, weight[N], order[N], match[N], answer[N];
bool visit[N], graph[N][N];

bool compare(int i, int j) {
    return weight[i] > weight[j];
}

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
        scanf("%d", weight + i);
        order[i] = i;
    }
    for (int i = 0; i < n; ++ i) {
        int size;
        scanf("%d", &size);
        while (size --) {
            int j;
            scanf("%d", &j);
            graph[i][j - 1] = true;
        }
    }
    std::sort(order, order + n, compare);
    memset(match, -1, sizeof(match));
    memset(visit, 0, sizeof(visit));
    for (int i = 0; i < n; ++ i) {
        if (find(order[i])) {
            memset(visit, 0, sizeof(visit));
        }
    }
    memset(answer, -1, sizeof(answer));
    for (int i = 0; i < n; ++ i) {
        if (match[i] != -1) {
            answer[match[i]] = i;
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", answer[i] + 1, i == n - 1 ? '\n' : ' ');
    }
    return 0;
}
