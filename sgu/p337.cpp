// SGU 337 -- Keven
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 2000;

int n, m, sum[(N << 1) + 1];
char string[N + 1];

bool compare(int i, int j) {
    int k = 0;
    while (k < n && string[(i + k) % n] == string[(j + k) % n]) {
        k ++;
    }
    return k < n && string[(i + k) % n] < string[(j + k) % n];
}

int main() {
    scanf("%d%s", &m, string);
    n = strlen(string);
    int j;
    for (j = n >> 1; j; -- j) {
        sum[n << 1] = 0;
        for (int i = (n << 1) - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + (string[i % n] != string[(i + j) % n]);
        }
        bool found = false;
        for (int i = 0; i < n; ++ i) {
            found |= sum[i] - sum[i + j] <= m;
        }
        if (found) {
            break;
        }
    }
    if (j) {
        std::vector <int> order;
        for (int i = 0; i < n; ++ i) {
            if (sum[i] - sum[i + j] <= m) {
                order.push_back(i);
            }
        }
        std::sort(order.begin(), order.end(), compare);
        for (int k = 0; k < j << 1; ++ k) {
            putchar(string[(order.front() + k) % n]);
        }
    } 
    puts("");
    return 0;
}
