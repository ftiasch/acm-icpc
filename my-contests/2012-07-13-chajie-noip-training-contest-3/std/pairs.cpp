#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 50000;

int n, m;
char text[N][5], buffer[111];
long long result[6], hashes[N], choose[6][6];

int main() {
    memset(choose, 0, sizeof(choose));
    for (int i = 0; i <= 5; ++ i) {
        choose[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            choose[i][j] = choose[i - 1][j] + choose[i - 1][j - 1];
        }
    }
    scanf("%d", &n);
    fgets(buffer, 111, stdin);
    for (int i = 0; i < n; ++ i) {
        fgets(buffer, 111, stdin);
        for (int j = 0; j < 5; ++ j) {
            text[i][j] = buffer[j];
        }
    }
    memset(result, 0, sizeof(result));
    for (int mask = 0; mask < (1 << 5); ++ mask) {
        m = 0;
        for (int i = 0; i < n; ++ i) {
            long long newHash = 0;
            for (int j = 0; j < 5; ++ j) {
                if (mask >> j & 1) {
                    newHash *= 256;
                    newHash += text[i][j];
                }
            }
            hashes[m ++] = newHash;
        }
        sort(hashes, hashes + m);
        int one = 0;
        for (int j = 0; j < 5; ++ j) {
            one += (mask >> j & 1);
        }
        long long &ret = result[one];
        for (int begin = 0; begin < m; ++ begin) {
            int end = begin;
            while (end < m && hashes[begin] == hashes[end]) {
                end ++;
            }
            long long total = end - begin;
            ret += total * (total - 1) / 2;
            begin = end - 1;
        }
    }
    for (int i = 4; i >= 0; -- i) {
        for (int j = i + 1; j <= 5; ++ j) {
            result[i] -= result[j] * choose[j][i];
        }
    }
    for (int i = 5; i >= 0; -- i) {
        cout << result[i] << (i == 0? '\n': ' ');
    }
    return 0;
}
