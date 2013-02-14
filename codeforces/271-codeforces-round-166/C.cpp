// Codeforces Round #166
// Problem C -- Secret
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

int k, n;

int main() {
    scanf("%d%d", &n, &k);
    if (n < 3 * k) {
        puts("-1");
        return 0;
    }
    std::vector <int> answer;
    for (int i = 1; i <= k; ++ i) {
        answer.push_back(i);
        answer.push_back(i);
    }
    for (int i = 1; i <= k; ++ i) {
        answer.push_back(i);
    }
    for (int i = 3 * k; i < n; ++ i) {
        answer.push_back(rand() % k + 1);
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d ", answer[i]);
    }
    puts("");
    return 0;
}
