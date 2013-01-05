// Codeforces Round #155
// Problem C -- Anagram
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 100000;

int n;
char source[N + 1], target[N + 1];

int difference[26], total[26];

int count() {
    int ret = 0;
    for (int i = 0; i < 26; ++ i) {
        ret += std::max(difference[i], 0);
    }
    return ret;
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%s%s", source, target);
    int n = strlen(source);
    memset(difference, 0, sizeof(difference));
    memset(total, 0, sizeof(total));
    for (int i = 0; i < n; ++ i) {
        difference[source[i] - 'A'] ++;
        difference[target[i] - 'A'] --;
        total[source[i] - 'A'] ++;
    }
    int answer = count();
    printf("%d\n", answer);
    for (int i = 0; i < n; ++ i) {
        total[source[i] - 'A'] --;
        for (int c = 0; c < 26; ++ c) {
            difference[source[i] - 'A'] --;
            difference[c] ++;
            if ((source[i] - 'A' != c) + count() == answer) {
                bool valid = true;
                for (int k = 0; k < 26; ++ k) {
                    valid &= total[k] >= difference[k];
                }
                if (valid) {
                    answer -= source[i] - 'A' != c;
                    source[i] = 'A' + c;
                    break;
                }
            }
            difference[c] --;
            difference[source[i] - 'A'] ++;
        }
    }
    puts(source);
    return 0;
}
