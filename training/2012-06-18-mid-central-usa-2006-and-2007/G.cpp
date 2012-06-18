// Problem G -- Typesetting
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 50;

int n, width[N], right[N];
char figure[N][N][N];
bool result[N][2 * N * N];
char buffer[1111];

int main() {
    int test_count = 0;
    while (scanf("%d", &n) == 1 && n != 0) {
        int m = 0;
        fgets(buffer, 1111, stdin);
        for (int i = 0; i < n; ++ i) {
            fgets(buffer, 1111, stdin);
            int length = strlen(buffer) - 1;
            int total = 0;
            for (int lower = 0; lower < length; ++ lower) {
                int upper = lower;
                while (upper < length && buffer[upper] != ' ') {
                    upper ++;
                }
                width[total] = upper - lower;
                for (int j = lower; j < upper; ++ j) {
                    figure[total][i][j - lower] = buffer[j];
                }
                total ++;
                lower = upper;
            }
            m = total;
        }
        for (int i = 0; i < n; ++ i) {
            right[i] = -1;
        }
        memset(result, 0, sizeof(result));
        for (int i = 0; i < m; ++ i) {
            int position = 0;
            for (int j = 0; j < n; ++ j) {
                int first = 0;
                while (first < width[i] && figure[i][j][first] == '.') {
                    first ++;
                }
                if (first == width[i]) {
                    continue;
                }
                position = max(position, right[j] + 1 - first);
            }
            if (position == 0) {
                position = 500;
            }
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < width[i]; ++ k) {
                    if (figure[i][j][k] == '.') {
                        continue;
                    }
                    right[j] = max(right[j], position + k + 1);
                    if (figure[i][j][k] == '#') {
                        result[j][position + k] = true;
                    }
                }
            }
        }
        printf("%d\n", ++ test_count);
        int first_column = 0;
        while (true) {
            bool found = false;
            for (int i = 0; i < n; ++ i) {
                if (result[i][first_column]) {
                    found = true;
                }
            }
            if (found) {
                break;
            }
            first_column ++;
        }
        int last_column = *max_element(right, right + n);
        while (true) {
            bool found = false;
            for (int i = 0; i < n; ++ i) {
                if (result[i][last_column - 1]) {
                    found = true;
                }
            }
            if (found) {
                break;
            }
            last_column --;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = first_column; j < last_column; ++ j) {
                printf("%c", result[i][j]? '#': '.');
            }
            puts("");
        }
    }
    return 0;
}
