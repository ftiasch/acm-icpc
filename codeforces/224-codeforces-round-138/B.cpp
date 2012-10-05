// Codeforces Round #138
// Problem B -- Array
#include <cstdio>
#include <cstring>
#include <map>
using namespace std;

const int N = 100000;

int n, m, a[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    map <int, int> hash;
    for (int i = 0; i < n; ++ i) {
        hash[a[i]] ++;
        if (hash[a[i]] == 1) {
            m --;
        }
        if (m == 0) {
            int j = 0;
            while (hash[a[j]] > 1) {
                hash[a[j ++]] --;
            }
            printf("%d %d\n", j + 1, i + 1);
            return 0;
        }
    }
    puts("-1 -1");
    return 0;
}
