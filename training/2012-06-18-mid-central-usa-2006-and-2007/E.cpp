// Problem E -- Go Go Gorelians
#include <cmath>
#include <cstdio>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1111;
const double EPS = 1e-9;

int n, x[N], y[N], z[N], id[N];
int maximum[N];
vector <int> adjacent[N];

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

double my_sqrt(double x) {
    return sqrt(max(x, 0.0));
}

double sqr(double x) {
    return x * x;
}

double get_dist(int i, int j) {
    return my_sqrt(sqr(x[i] - x[j]) + sqr(y[i] - y[j]) + sqr(z[i] - z[j]));
}

int dfs(int p, int u) {
    int result = 0;
    for (vector <int> :: iterator iter = adjacent[u].begin(); iter != adjacent[u].end(); ++ iter) {
        int v = *iter;
        if (v != p) {
            result = max(result, dfs(u, v) + 1);
        }
    }
    return result;
}

int main() {
    while (scanf("%d", &n) == 1 && n != 0) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d%d%d", id + i, x + i, y + i, z + i);
        }
        for (int i = 0; i < n; ++ i) {
            adjacent[i].clear();
        }
        for (int i = 1; i < n; ++ i) {
            int choice = -1;
            double minimum = 1e9;
            for (int j = 0; j < i; ++ j) {
                double tmp = get_dist(i, j);
                if (tmp < minimum) {
                //if (sgn(tmp - minimum) < 0) {
                    choice = j;
                    minimum = tmp;
                }
            }
            adjacent[i].push_back(choice);
            adjacent[choice].push_back(i);
        }
        for (int i = 0; i < n; ++ i) {
            maximum[i] = dfs(-1, i);
        }
        int minimum = *min_element(maximum, maximum + n);
        vector <int> result;
        for (int i = 0; i < n; ++ i) {
            if (maximum[i] == minimum) {
                result.push_back(id[i]);
            }
        }
        sort(result.begin(), result.end());
        for (int i = 0; i < (int)result.size(); ++ i) {
            printf("%d%c", result[i], i == (int)result.size() - 1? '\n': ' ');
        }
    }
    return 0;
}
