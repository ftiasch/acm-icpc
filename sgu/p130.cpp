// SGU 130 -- Circle
#include <cstring>
#include <iostream>
using namespace std;

const int N = 33;

int n;
long long ways[N];

int main() {
    cin >> n;
    memset(ways, 0, sizeof(ways));
    ways[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        for (int j = 0; j <= i - 1; ++ j) {
            ways[i] += ways[j] * ways[i - 1 - j];
        }
    }
    cout << ways[n] << " " << n + 1 << endl;
}
