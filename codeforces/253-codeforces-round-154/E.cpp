// Codeforces Round #154 
// Problem E -- Printer
#include <cstdio>
#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>

using std::vector;

const int N = 500000;

int n, start[N], volume[N], priority[N], order[N], left[N];
long long finish[N];

bool by_start(int i, int j) {
    return start[i] < start[j];
}

bool by_priority(int i, int j) {
    return priority[i] < priority[j];
}

void solve() {
    for (int i = 0; i < n; ++ i) {
        left[i] = volume[i];
    }
    memset(finish, -1, sizeof(finish));
    int id = 0;
    long long now = 0;
    vector <int> heap;
    while (!heap.empty() || id < n) {
        if (heap.empty()) {
            now = start[order[id]];
        }
        if (id < n && now == start[order[id]]) {
            heap.push_back(order[id ++]);
            std::push_heap(heap.begin(), heap.end(), by_priority);
        } else {
            long long end = now + left[heap.front()];
            if (id < n) {
                end = std::min(end, (long long)start[order[id]]);
            }
            left[heap.front()] -= end - now;
            if (!left[heap.front()]) {
                finish[heap.front()] = end;
                pop_heap(heap.begin(), heap.end(), by_priority);
                heap.pop_back();
            }
            now = end;
        }
    }
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::cin >> n;
    vector <int> priorities;
    int x = -1;
    for (int i = 0; i < n; ++ i) {
        std::cin >> start[i] >> volume[i] >> priority[i];
        if (priority[i] != -1) {
            priorities.push_back(priority[i]);
        } else {
            x = i;
        }
    }
    long long finish_x;
    std::cin >> finish_x;
    std::sort(priorities.begin(), priorities.end());
    vector <int> choices;
    if (priorities.front() != 1) {
        choices.push_back(1);
    }
    for (int i = 0; i < (int)priorities.size(); ++ i) {
        if (i + 1 == (int)priorities.size() || priorities[i] + 1 != priorities[i + 1]) {
            choices.push_back(priorities[i] + 1);
        }
    }
    for (int i = 0; i < n; ++ i) {
        order[i] = i;
    }
    std::sort(order, order + n, by_start);
    int low = 0;
    int high = (int)choices.size() - 1;
    while (low <= high) {
        int middle = low + high >> 1;
        priority[x] = choices[middle];
        solve();
        if (finish[x] == finish_x) {
            break;
        }
        if (finish[x] < finish_x) {
            high = middle - 1;
        } else {
            low = middle + 1;
        }
    }
    std::cout << priority[x] << std::endl;
    for (int i = 0; i < n; ++ i) {
        std::cout << finish[i] << (i == n - 1 ? '\n' : ' ');
    }
    return 0;
}
