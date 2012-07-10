#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 100;

int d, n;
long long fraction[N];
char buffer[22];

int main() {
    scanf("%d%d", &d, &n);
    int base = 1;
    for (int i = 0; i < d; ++ i) {
        base *= 10;
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        int k = strlen(buffer) - d;
        buffer[k - 1] = '\0';
        sscanf(buffer, "%d", fraction + i);
        fraction[i] *= base;
        int delta;
        sscanf(buffer + k, "%d", &delta);
        fraction[i] += delta;
        fraction[i] *= 10;
    }
    base *= 10;
    long long denominator = 1;
    while (true) {
        bool check = true;
        for (int i = 0; i < n; ++ i) {
            long long minRange = ((fraction[i] - 5) * denominator + base - 1) / base;
            long long maxRange = ((fraction[i] + 5) * denominator - 1) / base;
            check &= minRange <= maxRange;
        }
        if (check) {
            break;
        }
        denominator ++;
    }
    cout << denominator << endl;
    return 0;
}
