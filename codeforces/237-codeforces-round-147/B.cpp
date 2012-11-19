// Codeforces Round #147
// Problem B -- Yound Table
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
using namespace std;

const int N = 50;

int n, c[N];
vector <int> a[N];

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", c + i);
        a[i].resize(c[i]);
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < c[i]; ++ j) {
            scanf("%d", &a[i][j]);
        }
    }
    vector <pair <pair <int, int>, pair <int, int> > > swaps;
    int total = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < c[i]; ++ j) {
            total ++;
            if (a[i][j] != total) {
                for (int x = 0; x < n; ++ x) {
                    for (int y = 0; y < c[x]; ++ y) {
                        if (a[x][y] == total) {
                            swap(a[i][j], a[x][y]);
                            swaps.push_back(make_pair(make_pair(i + 1, j + 1),
                                make_pair(x + 1, y + 1)));
                            break;
                        }
                    }
                }
            }
        }
    }
    printf("%d\n", (int)swaps.size());
    foreach (iter, swaps) {
        printf("%d %d %d %d\n", iter->first.first, iter->first.second,
                iter->second.first, iter->second.second);
    }
    return 0;
}
