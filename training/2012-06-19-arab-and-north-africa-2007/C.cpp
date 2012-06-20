// Problem C -- Rotating Rings
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

typedef unsigned long long Hash;

const int N = 1111;
const Hash MAGIC = 1289571;

int n, a[N][N], b[N][N];

bool check(vector <int> &a, vector <int> &b) {
    int n = a.size();
    vector <Hash> h(2 * n + 1);
    h[2 * n] = 0;
    for (int i = 2 * n - 1; i >= 0; -- i) {
        h[i] = h[i + 1] * MAGIC + a[i % n];
    }
    Hash tmp = 0;
    Hash pow = 1;
    for (int i = n - 1; i >= 0; -- i) {
        pow *= MAGIC;
        tmp = tmp * MAGIC + b[i];
    }
    for (int i = 0; i < (int)a.size(); ++ i) {
        if (h[i] - h[i + n] * pow == tmp) {
            return true;
        }
    }
    return false;
}

int main() {
    int test_count = 0;
    while (scanf("%d", &n) == 1 && n > 0) {
        //vector <int> values;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                scanf("%d", &a[i][j]);
                //values.push_back(a[i][j]);
            }
        }
        //sort(values.begin(), values.end());
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                b[i][j] = i * n + j + 1;
                //b[i][j] = values[i * n + j];
            }
        }
        bool yes = true;
        for (int k = 0; k < (n + 1) / 2 && yes; ++ k) {
            vector <int> va, vb;
            if (k == n - 1 - k) {
                va.push_back(a[k][k]);
                vb.push_back(b[k][k]);
            } else {
                int l = n - (2 * k) - 1;
                for (int i = 0; i < l; ++ i) {
                    va.push_back(a[k][k + i]);
                    vb.push_back(b[k][k + i]);
                }
                for (int i = 0; i < l; ++ i) {
                    va.push_back(a[k + i][k + l]);
                    vb.push_back(b[k + i][k + l]);
                }
                for (int i = 0; i < l; ++ i) {
                    va.push_back(a[k + l][k + l - i]);
                    vb.push_back(b[k + l][k + l - i]);
                }
                for (int i = 0; i < l; ++ i) {
                    va.push_back(a[k + l - i][k]);
                    vb.push_back(b[k + l - i][k]);
                }
            }
            yes &= check(va, vb);
        }
        printf("%d. %s\n", ++ test_count, yes? "YES": "NO");
    }
    return 0;
}
