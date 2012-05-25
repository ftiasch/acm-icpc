// Codeforces Beta Round #15
// Problem A -- Cottage Village
#include <cstdio>
#include <cstring>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, size;
pair <int, int> houses[N];

int main() {
    scanf("%d%d", &n, &size);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &houses[i].first, &houses[i].second);
    }
    int result = 2;
    sort(houses, houses + n);
    for (int i = 1; i < n; ++ i) {
        int space = (houses[i].first - houses[i - 1].first) * 2 - houses[i].second - houses[i - 1].second;
        if (space >= size * 2) {
            result ++;
        } 
        if (space > size * 2) {
            result ++;
        }
    }
    printf("%d\n", result);
    return 0;
}
