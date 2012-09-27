// Codeforces Round #136
// Problem C -- Little Elephant and Shifts
#include <cstdio>
#include <cstring>
#include <climits>
#include <queue>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, init[N], goal[N], a[N], b[N];

struct Heap {
    bool exists[N];
    int delta, current[N];
    priority_queue <pair <int, int> > heap;

    Heap() {
        delta = 0;
        memset(exists, 0, sizeof(exists));
    }

    void insert(int key, int value) {
        exists[key] = true;
        current[key] = value - delta;
        heap.push(make_pair(-current[key], key));
    }

    void erase(int key) {
        exists[key] = false;
    }

    int getMin() {
        while (!heap.empty() && 
                (!exists[heap.top().second] || current[heap.top().second] != -heap.top().first)) {
            heap.pop();
        }
        return heap.empty()? INT_MAX: (-heap.top().first + delta);
    }
};

Heap down, up;
vector <int> events[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        goal[-- a[i]] = i;
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", b + i);
        init[-- b[i]] = i;
    }
    for (int i = 0; i < n; ++ i) {
        events[(goal[i] - init[i] + n) % n].push_back(i);
    }
    for (int i = 0; i < n; ++ i) {
        if (init[i] < goal[i]) {
            down.insert(i, abs(init[i] - goal[i]));
        } else {
            up.insert(i, abs(init[i] - goal[i]));
        }
    }
    vector <int> result;
    for (int d = 0; d < n; ++ d) {
        result.push_back(min(down.getMin(), up.getMin()));
        for (vector <int> :: iterator iter = events[d].begin();
                iter != events[d].end(); ++ iter) {
            down.erase(*iter);
            up.insert(*iter, 0);
        }
        down.delta -= 1;
        up.delta += 1;
        int i = b[n - 1 - d];
        up.erase(i);
        down.insert(i, goal[i]);
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", result[(n - i) % n]);
    }
    return 0;
}
