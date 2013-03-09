// SGU 411 -- Petya the Hero
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 4001;

int n, m, order[N];
char string[N + 1];

char palindrome[N][N];

bool compare(int i, int j) {
    int k = 0;
    while (string[i + k] == string[j + k]) {
        k ++;
    }
    return string[i + k] < string[j + k];
}

bool is_palindrome(int i, int j) {
    if (i >= j) {
        return true;
    }
    if (palindrome[i][j] == -1) {
        palindrome[i][j] = string[i] == string[j] && is_palindrome(i + 1, j - 1);
    }
    return palindrome[i][j];
}

int main() {
    scanf("%s", string);
    n = strlen(string);
    string[n] = '#';
    scanf("%s", string + (n + 1));
    m = strlen(string + (n + 1));
    for (int i = 0; i < n + m + 1; ++ i) {
        order[i] = i;
    }
    std::sort(order, order + (n + m + 1), compare);
    memset(palindrome, -1, sizeof(palindrome));
    int start = -1;
    int length = 0;
    for (int k = 0; k < n + m; ++ k) {
        int i = order[k];
        int j = order[k + 1];
        if ((i < n) ^ (j < n)) {
            int x = 0;
            while (string[i + x] == string[j + x]) {
                x ++;
                if (is_palindrome(i, i + x - 1) && x > length) {
                    start = i;
                    length = x;
                }
            }
        }
    }
    for (int i = 0; i < length; ++ i) {
        putchar(string[start + i]);
    }
    puts("");
    return 0;
}
