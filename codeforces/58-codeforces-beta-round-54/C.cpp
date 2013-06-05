// Codeforces Beta Round #54
// Problem C -- Trees
#include <cstdio>
#include <cstring>
#include <map>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

int n;
std::map <int, int> count;

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int a;
        scanf("%d", &a);
        int ret = a - std::min(i, n - 1 - i);
        if (ret > 0) {
            count[ret] ++;
        }
    }
    int answer = 0;
    foreach (iter, count) {
        answer = std::max(answer, iter->second);
    }
    printf("%d\n", n - answer);
    return 0;
}
