// Codeforces Round #142
// Problem A -- Dragons
#include <cstdio>
#include <cstring>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 1000;

int s, n;
pair <int, int> dragons[N];

int main() {
    scanf("%d%d", &s, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &dragons[i].first, &dragons[i].second);
    }
    sort(dragons, dragons + n);
    for (int i = 0; i < n; ++ i) {
        if (s <= dragons[i].first) {
            puts("NO");
            return 0;
        }
        s += dragons[i].second;
    }
    puts("YES");
    return 0;
}
