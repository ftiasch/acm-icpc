// Codeforces Round #142
// Problem E -- Gifts
#include <cstdio>
#include <cstring>
#include <vector>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, m;
vector <int> wish, prices[N];
double choose[N + 1][N + 1], probability[N + 1][N + 1];

int main() {
    for (int i = 0; i <= N; ++ i) {
        choose[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            choose[i][j] = choose[i - 1][j - 1] + choose[i - 1][j];
        }
    }
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        int k;
        scanf("%d", &k);
        while (k --) {
            int x;
            scanf("%d", &x);
            wish.push_back(x);
            prices[i].push_back(x);
        }
        sort(prices[i].begin(), prices[i].end(), greater <int>());
    }
    sort(wish.begin(), wish.end(), greater <int>());
    int special = wish[n - 1];
    int counter = 0;
    for (int i = 0; i < n; ++ i) {
        if (wish[i] == special) {
            counter ++;
        }
    }
    int total = 0;
    for (int i = 0; i < (int)wish.size(); ++ i) {
        if (wish[i] == special) {
            total ++;
        }
    }
    probability[0][0] = 1;
    for (int i = 0; i < m; ++ i) {
        int n = (int)prices[i].size();
        bool found = false;
        int k = 0;
        while (k < n && prices[i][k] >= special) {
            if (prices[i][k] == special) {
                found = true;
            }
            k ++;
        }
        for (int j = 0; j <= counter; ++ j) {
            probability[i + 1][j] += probability[i][j] / choose[n][k - found];
            if (found) {
                probability[i + 1][j + 1] += probability[i][j] / choose[n][k];
            }
        }
    }
    printf("%.9f\n", probability[m][counter] / choose[total][counter]);
    return 0;
}
