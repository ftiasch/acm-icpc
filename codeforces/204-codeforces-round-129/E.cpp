// Codeforces Round #129
// Problem E -- Little Elephant and Strings
#include <cstdio>
#include <cstring>
#include <deque>
#include <queue>
#include <utility>
#include <iostream>
#include <algorithm>

const int N = 200000 + 10;

int n, m, length, text[N], belongs[N], extend[N];
char buffer[N];

int array[N], rank[N], height[N], hash[N];

void construct() {
    static int count[N], new_array[N], new_rank[2][N];
    memset(count, 0, sizeof(count));
    for (int i = 0; i < length; ++ i) {
        count[text[i]] ++;
    }
    for (int i = 1; i < N; ++ i) {
        count[i] += count[i - 1];
    }
    for (int i = 0; i < length; ++ i) {
        rank[i] = count[text[i]] - 1;
    }
    for (int delta = 1; delta <= length; delta <<= 1) {
        for (int i = 0; i < length; ++ i) {
            new_rank[0][i] = rank[i];
            new_rank[1][i] = i + delta < length ? rank[i + delta] + 1 : 0;
        }
        memset(count, 0, sizeof(count));
        for (int i = 0; i < length; ++ i) { 
            count[new_rank[1][i]] ++;
        }
        for (int i = 1; i <= length; ++ i) {
            count[i] += count[i - 1];
        }
        for (int i = length - 1; i >= 0; -- i) {
            new_array[-- count[new_rank[1][i]]] = i;
        }
        memset(count, 0, sizeof(count));
        for (int i = 0; i < length; ++ i) { 
            count[new_rank[0][i]] ++;
        }
        for (int i = 1; i <= length; ++ i) {
            count[i] += count[i - 1];
        }
        for (int i = length - 1; i >= 0; -- i) {
            array[-- count[new_rank[0][new_array[i]]]] = new_array[i];
        }
        rank[array[0]] = 0;
        for (int i = 1; i < length; ++ i) {
            rank[array[i]] = rank[array[i - 1]];
            if (new_rank[0][array[i]] != new_rank[0][array[i - 1]] || new_rank[1][array[i]] != new_rank[1][array[i - 1]]) {
                rank[array[i]] ++;
            }
        }
    }
    for (int i = 0, delta = 0; i < length; ++ i) {
        if (rank[i]) {
            int j = array[rank[i] - 1];
            while (text[i + delta] == text[j + delta]) {
                delta ++;
            }
            height[rank[i]] = delta;
            if (delta) {
                delta --;
            }
        }
    }
}

long long answer[N];

int main() {
    scanf("%d%d", &n, &m);
    length = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%s", buffer);
        int l = strlen(buffer);
        for (int j = 0; buffer[j]; ++ j) {
            extend[length] = l - j;
            belongs[length] = i;
            text[length ++] = buffer[j] - 'a';
        }
        extend[length] = 0;
        belongs[length] = -1;
        text[length ++] = 26 + i;
    }
    construct();
    std::deque <int> queue;
    int count = 0;
    memset(hash, 0, sizeof(hash));
    std::vector <std::pair <std::pair <int, int>, int> > events;
    for (int i = 0, j = 0; belongs[array[i]] != -1; ++ i) {
        hash[belongs[array[i]]] ++;
        if (hash[belongs[array[i]]] == 1) {
            count ++;
        }
        if (i) {
            while (!queue.empty() && height[queue.back()] >= height[i]) {
                queue.pop_back();
            }
            queue.push_back(i);
        }
        if (count >= m) {
            int lcp = i == j ? extend[array[i]] : height[queue.front()];
            if (lcp) {
                events.push_back(std::make_pair(std::make_pair(j, i), lcp));
            }
        }
        while (count > m || (count == m && hash[belongs[array[j]]] > 1)) {
            hash[belongs[array[j]]] --;
            if (hash[belongs[array[j]]] == 0) {
                count --;
            }
            j ++;
            while (!queue.empty() && queue.front() <= j) {
                queue.pop_front();
            }
            if (count >= m) {
                int lcp = i == j ? extend[array[i]] : height[queue.front()];
                if (lcp) {
                    events.push_back(std::make_pair(std::make_pair(j, i), lcp));
                }
            }
        }
    }
    std::sort(events.begin(), events.end());
    std::priority_queue <std::pair <int, int> > heap;
    memset(answer, 0, sizeof(answer));
    for (int i = 0, j = 0; belongs[array[i]] != -1; ++ i) {
        while (j < (int)events.size() && events[j].first.first <= i) {
            heap.push(std::make_pair(events[j].second, events[j].first.second));
            j ++;
        }
        while (!heap.empty() && heap.top().second < i) {
            heap.pop();
        }
        if (!heap.empty()) {
            answer[belongs[array[i]]] += heap.top().first;
        }
    }
    for (int i = 0; i < n; ++ i) {
        std::cout << answer[i] << " ";
    }
    std::cout << std::endl;
    return 0;
}
