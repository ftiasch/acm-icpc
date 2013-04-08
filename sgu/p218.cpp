// SGU 218 -- Unstable Systems
#include <cstdio>
#include <cstring>

const int N = 500;

int n, limit, weight[N][N], match[N];
bool visit[N];

bool find(int i) {
    if (visit[i]) {
        return false;
    }
    visit[i] = true;
    for (int j = 0; j < n; ++ j) {
        if (weight[i][j] <= limit && match[j] == -1) {
            match[j] = i;
            return true;
        }
    }
    for (int j = 0; j < n; ++ j) {
        if (weight[i][j] <= limit && find(match[j])) {
            match[j] = i;
            return true;
        }
    }
    return false;
}

bool check(int limit) {
    ::limit = limit;
    memset(match, -1, sizeof(match));
    for (int i = 0; i < n; ++ i) {
        memset(visit, 0, sizeof(visit));
        if (!find(i)) {
            return false;
        }
    }
    return true;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &weight[i][j]);
        }
    }
    int low = -1000000;
    int high = 1000000;
    while (low < high) {
        int middle = low + high >> 1;
        if (check(middle)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    check(high);
    printf("%d\n", high);
    for (int i = 0; i < n; ++ i) {
        printf("%d %d\n", 1 + match[i], 1 + i);
    }
    return 0;
}
