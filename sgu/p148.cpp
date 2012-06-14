// SGU 148 -- B-station
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <queue>
using namespace std;

const int N = 15001;

int n, weight[N], limit[N], cost[N], sum[N];

int main() {
    scanf("%d", &n);
    sum[0] = 0;
    for (int i = 1; i <= n; ++ i) {
        scanf("%d%d%d", weight + i, limit + i, cost + i);
        sum[i] = sum[i - 1] + weight[i];
    }
    priority_queue <pair <int, int> > heap;
    int total_cost = 0;
    int minimum_cost = INT_MAX;
    int choice = -1;
    for (int i = n; i >= 1; -- i) {
        total_cost += cost[i];
        heap.push(make_pair(sum[i] - limit[i], cost[i]));
        while (!heap.empty() && heap.top().first > sum[i - 1]) {
            total_cost -= heap.top().second;
            heap.pop();
        }
        if (total_cost < minimum_cost) {
            choice = i;
            minimum_cost = total_cost;
        }
    }
    for (int i = choice, j = choice; j <= n; ++ j) {
        if (sum[j] - sum[i - 1] <= limit[j]) {
            printf("%d\n", j);
        }
    }
    return 0;
}
