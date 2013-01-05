// Codeforces Round #157
// Problem B -- Little Elephant and Elections
#include <cstdio>
#include <cstring>


int m, length;
int digit[11];

int memory[2][11][11];

int count(bool first, bool less, int length, int size) {
    if (length == 0) {
        return less && !size;
    }
    if (less && memory[first][length][size] != -1) {
        return memory[first][length][size];
    }
    int ret = 0;
    for (int i = 0; i < 10; ++ i) {
        if (first && !i) {
            continue;
        }
        if (!less && digit[length - 1] < i) {
            continue;
        }
        ret += count(false, less || i < digit[length - 1], length - 1, size - (i == 4 || i == 7));
    }
    if (less) {
        memory[first][length][size] = ret;
    }
    return ret;
}

int size[11];

const int MOD = 1000000000 + 7;

int answer;
int ways[7];

void dfs(int i, int way) {
    if (i < 7) {
        for (int k = 0; k <= 10; ++ k) {
            if (size[k] > 0) {
                ways[i] = k;
                dfs(i + 1, (long long)way * (size[k] --) % MOD);
                size[k] ++;
            }
        }
    } else {
        int sum = 0;
        for (int i = 0; i < 6; ++ i) {
            sum += ways[i];
        }
        sum -= ways[6];
        if (sum < 0) {
            answer += way;
            answer %= MOD;
        }
    }
}

int main() {
    scanf("%d", &m);
    m ++;
    length = 0;
    while (m) {
        digit[length ++] = m % 10;
        m /= 10;
    }
    memset(memory, -1, sizeof(memory));
    for (int k = 0; k <= 10; ++ k) {
        for (int i = 1; i <= length; ++ i) {
            size[k] += count(true, i < length, i, k);
        }
    }
    //for (int i = 0; i <= 10; ++ i) {
    //    printf("%d\n", size[i]);
    //}
    answer = 0;
    dfs(0, 1);
    printf("%d\n", answer);
    return 0;
}
