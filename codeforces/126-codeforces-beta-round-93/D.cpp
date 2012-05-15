// Codeforces Beta Round #93
// Problem D -- Fibonacci Sums
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

const int N = 87;
const long long INF = 1000000000000000000LL;

long long fibonacci[N], ways[N][3];

int main() {
    int m = 2;
    fibonacci[1] = 1;
    fibonacci[2] = 2;
    while (fibonacci[m - 1] + fibonacci[m] <= INF) {
        fibonacci[m + 1] = fibonacci[m - 1] + fibonacci[m];
        m ++;
    }
    //for (int i = 1; i <= m; ++ i) {
    //    cout << fibonacci[i] << endl;
    //}
    int test_count;
    cin >> test_count;
    while (test_count > 0) {
        test_count --;
        long long n;
        cin >> n;
        memset(ways, 0, sizeof(ways));
        ways[N - 1][0] = 1;
        for (int i = N - 1; i >= 1; -- i) {
            bool has = n >= fibonacci[i];
            if (has) {
                n -= fibonacci[i];
                ways[i - 1][2] += ways[i][1];

                ways[i - 1][2] += ways[i][0];
                ways[i - 1][0] += ways[i][0];
            } else {
                ways[i - 1][1] += ways[i][2];
                
                ways[i - 1][2] += ways[i][1];
                ways[i - 1][0] += ways[i][1];

                ways[i - 1][0] += ways[i][0];
            }
        }
        cout << ways[0][0] << endl;
    }
    return 0;
}
