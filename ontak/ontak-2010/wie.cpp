// ONTAK 2010 Day 4 -- Tower(wie)
#include <cstdio>
#include <cstring>
#include <deque>
using namespace std;

const int N = 100000 + 1;

int n, width[N], highest[N];
long long sum[N], shortest[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", width + i);
    }
    sum[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        sum[i] = sum[i + 1] + width[i];
    }
    highest[n] = 0;
    shortest[n] = 0;
    // >= 
    // sum[i] >= sum[j] + shortest[j]
    deque <int> queue;
    queue.push_back(n);
    for (int i = n - 1; i >= 0; -- i) {
        while ((int)queue.size() > 1 && sum[i] >= sum[queue[1]] + shortest[queue[1]]) {
            queue.pop_front();
        }
        highest[i] = highest[queue.front()] + 1;
        shortest[i] = sum[i] - sum[queue.front()];
        while (!queue.empty() && sum[i] + shortest[i] <= sum[queue.back()] + shortest[queue.back()]) {
            queue.pop_back();
        }
        queue.push_back(i);
    }
    printf("%d\n", highest[0]);
    return 0;
}
