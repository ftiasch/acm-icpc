// Codeforces Round #120
// Problem D -- Non-Secret Cypher
#include <cstdio>
#include <cstring>
#include <iostream>
#include <map>
using namespace std;

const int N = 400000;

int n, m, a[N];
map <int, int> frequency;

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    long long result = (long long)n * (n + 1) / 2;
    int j = n;
    for (int i = n - 1; i >= 0; -- i) {
        frequency[a[i]] ++;
        while (frequency[a[i]] >= m) {
            frequency[a[-- j]] --;
        }
        result -= j - i;
    }
    cout << result << endl;
    return 0;
}
