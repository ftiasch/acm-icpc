// July Challenge 2012
// Gift Rift
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100;

int n, m, a[N][N], b[N][N], row_minimum[N], colume_maximum[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &a[i][j]);
            b[j][i] = a[i][j];
        }
    }
    for (int i = 0; i < n; ++ i) {
        row_minimum[i] = *min_element(a[i], a[i] + m);
    }
    for (int j = 0; j < m; ++ j) {
        colume_maximum[j] = *max_element(b[j], b[j] + n);
    }
    vector <int> choices;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (row_minimum[i] == a[i][j] && colume_maximum[j] == a[i][j]) {
                choices.push_back(a[i][j]);
            }
        }
    }
    sort(choices.begin(), choices.end());
    choices.erase(unique(choices.begin(), choices.end()), choices.end());
    if (choices.empty()) {
        puts("GUESS");
    } else if (choices.size() == 1) {
        printf("%d\n", choices.front());
    } else {
        puts("GUESS");
    }
    return 0;
}
