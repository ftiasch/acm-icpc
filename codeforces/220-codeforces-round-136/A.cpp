// Codeforces Round #136
// Problem A -- Little Elephant and Problem
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, a[N], b[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        b[i] = a[i];
    }
    sort(b, b + n);
    int counter = 0;
    for (int i = 0; i < n; ++ i) {
        counter += a[i] != b[i];
    }
    puts(counter <= 2? "YES": "NO");
    return 0;
}
