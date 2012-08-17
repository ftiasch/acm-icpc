// SGU 354 -- Just Matrix
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 600;
const int L = 10;

int n, top[N][N], left[N][N], order[N], sum[N];
int greater[N][N][2], degree[N][N], queue[N * N], result[N][N];

void reconstruct(int* greater) {
    for (int i = 0; i < n; ++ i) {
        sum[i] = ~i & i + 1;
    }
    for (int i = n - 1; i >= 0; -- i) {
        int need = i + 1 - greater[i];
        int start = 0;
        for (int k = L - 1; k >= 0; -- k) {
            if (start + (1 << k) <= n && sum[start + (1 << k) - 1] < need) {
                start += 1 << k;
                need -= sum[start - 1];
            }
        }
        order[start] = i;
        for (int k = start; k < n; k += ~k & k + 1) {
            sum[k] --;
        }
    }
}

int id(int x, int y) {
    return x * n + y;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &top[j][i]);
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &left[i][j]);
        }
    }
    memset(greater, -1, sizeof(greater));
    bool valid = true;
    for (int j = 0; j < n; ++ j) {
        for (int i = 0; i < n; ++ i) {
            valid &= top[j][i] <= i;
        }
        if (!valid) {
            break;
        }
        reconstruct(top[j]);
        for (int i = 0; i + 1 < n; ++ i) {
            greater[order[i]][j][0] = id(order[i + 1], j);
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            valid &= left[i][j] <= j;
        }
        if (!valid) {
            break;
        }
        reconstruct(left[i]);
        for (int j = 0; j + 1 < n; ++ j) {
            greater[i][order[j]][1] = id(i, order[j + 1]);
        }
    }
    memset(degree, 0, sizeof(degree));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            for (int k = 0; k < 2; ++ k) {
                int &reg = greater[i][j][k];
                if (reg != -1) {
                    int x = reg / n;
                    int y = reg % n;
                    degree[x][y] ++;
                }
            }
        }
    }
    int tail = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (degree[i][j] == 0) {
                queue[tail ++] = i * n + j;
            }
        }
    }
    int head = 0;
    while (head != tail) {
        int i = queue[head] / n;
        int j = queue[head ++] % n;
        result[i][j] = head;
        for (int k = 0; k < 2; ++ k) {
            int &reg = greater[i][j][k];
            if (reg != -1) {
                int x = reg / n;
                int y = reg % n;
                degree[x][y] --;
                if (degree[x][y] == 0) {
                    queue[tail ++] = x * n + y;
                }
            }
        }
    }
    if (valid && head == n * n) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                printf("%d%c", result[i][j], j == n - 1? '\n': ' ');
            }
        }
    } else {
        puts("0");
    }
    return 0;
}
