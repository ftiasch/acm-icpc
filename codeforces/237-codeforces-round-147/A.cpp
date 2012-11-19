// Codeforces Round #147
// Problem A -- Free Cash
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int counter[60 * 24];

int main() {
    memset(counter, 0, sizeof(counter));
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int h, m;
        scanf("%d%d", &h, &m);
        counter[h * 60 + m] ++;
    }
    printf("%d\n", *max_element(counter, counter + 60 * 24));
    return 0;
}
