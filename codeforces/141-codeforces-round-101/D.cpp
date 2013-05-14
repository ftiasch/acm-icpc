// Codeforces Round #101
// Problem D --  Take-off Ramps
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <climits>
#include <queue>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 100000;
const long long INF = 1000000000000000000LL;

int n, l, x[N], d[N], t[N], p[N];

const int V = N + 1 << 1;

std::vector <std::pair <int, int> > edges[V];
std::vector <std::pair <int, int> > backup[V];

long long distance[V];
std::vector <std::pair <int, int> > :: iterator back[V];

int main() {
    scanf("%d%d", &n, &l);
    std::vector <int> events;
    events.push_back(0);
    events.push_back(l);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d%d", x + i, d + i, t + i, p + i);
        if (x[i] - p[i] >= 0) {
            events.push_back(x[i] - p[i]);
        }
        events.push_back(x[i] + d[i]);
    }
    std::sort(events.begin(), events.end());
    events.erase(std::unique(events.begin(), events.end()), events.end());
    for (int i = 0; i + 1 < (int)events.size(); ++ i) {
        edges[i].push_back(std::make_pair(i + 1, events[i + 1] - events[i]));
        backup[i].push_back(std::make_pair(i, -1));
        edges[i + 1].push_back(std::make_pair(i, events[i + 1] - events[i]));
        backup[i + 1].push_back(std::make_pair(i + 1, -1));
    }
    for (int i = 0; i < n; ++ i) {
        if (x[i] - p[i] >= 0) {
            int a = std::lower_bound(events.begin(), events.end(), x[i] - p[i]) - events.begin();
            int b = std::lower_bound(events.begin(), events.end(), x[i] + d[i]) - events.begin();
            edges[a].push_back(std::make_pair(b, t[i] + p[i]));
            backup[a].push_back(std::make_pair(a, i));
        }
    }
    std::fill(distance, distance + V, INF);
    distance[0] = 0;
    std::priority_queue <std::pair <long long, int> > heap;
    heap.push(std::make_pair(0, 0));
    while (!heap.empty()) {
        std::pair <long long, int> ret = heap.top();
        heap.pop();
        int u = ret.second;
        if (-ret.first != distance[u]) {
            continue;
        }
        for (int i = 0; i < (int)edges[u].size(); ++ i) {
            std::vector <std::pair <int, int> >::iterator iter = edges[u].begin() + i;
            if (distance[u] + iter->second < distance[iter->first]) {
                distance[iter->first] = distance[u] + iter->second;
                back[iter->first] = backup[u].begin() + i;
                heap.push(std::make_pair(-distance[iter->first], iter->first));
            }
        }
    }
    std::vector <int> answer;
    std::cout << distance[(int)events.size() - 1] << std::endl;
    for (int i = (int)events.size() - 1; i != 0; i = back[i]->first) {
        if (back[i]->second != -1) {
            answer.push_back(back[i]->second);
        }
    }
    std::reverse(answer.begin(), answer.end());
    printf("%d\n", (int)answer.size());
    for (int i = 0; i < (int)answer.size(); ++ i) {
        printf("%d\n", answer[i] + 1);
    }
    return 0;
}
