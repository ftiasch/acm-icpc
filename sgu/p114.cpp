// SGU 114 -- Telecasting station
#include <cstdio>
#include <utility>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 15000;
const long long INF = 1000000000000000000LL;

int n, sum[N];
long long prefix[N], suffix[N];
pair <int, int> point[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &point[i].first, &point[i].second);
    }
    sort(point, point + n);
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + point[i].second;
    }
    prefix[0] = (long long)point[0].first * point[0].second;
    for (int i = 1; i < n; ++ i) {
        prefix[i] = prefix[i - 1] + (long long)point[i].first * point[i].second;
    }
    suffix[n - 1] = (long long)point[n - 1].first * point[n - 1].second;
    for (int i = n - 2; i >= 0; -- i) {
        suffix[i] = suffix[i + 1] + (long long)point[i].first * point[i].second;
    }
    int choice = 0;
    long long result = INF;
    for (int i = 0; i < n; ++ i) {
        long long tmp = (long long)point[i].first * (sum[0] - sum[i + 1]) - prefix[i]
            + suffix[i] - (long long)point[i].first * sum[i];
        if (tmp < result) {
            choice = point[i].first;
            result = tmp;
        }
    }
    cout << choice << ".00000" << endl;
    return 0;
}
