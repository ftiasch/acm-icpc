// Codeforces Round #138
// Problem A -- Bracket Sequence
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100000 + 1;

char string[N];
int match[N], sum[N];

bool is_match(char a, char b) {
    if (a == '(' && b == ')') {
        return true;
    }
    if (a == '[' && b == ']') {
        return true;
    }
    return false;
}

int main() {
    scanf("%s", string);
    int n = strlen(string);
    int best = -1, best_i, best_j;
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        match[i] = i;
        if (i + 1 < n && is_match(string[i], string[match[i + 1]])) {
            match[i] = match[i + 1] + 1;
        }
        if (i < match[i] && match[i] < n) {
            match[i] = match[match[i]];
        }
        sum[i] = sum[i + 1] + (string[i] == '[');
        if (sum[i] - sum[match[i]] > best) {
            best = sum[i] - sum[match[i]];
            best_i = i;
            best_j = match[i];
        }
    }
    string[best_j] = '\0';
    printf("%d\n%s\n", best, string + best_i);
    return 0;
}
