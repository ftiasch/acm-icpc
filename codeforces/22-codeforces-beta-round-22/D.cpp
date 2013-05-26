// Codeforces Beta Round #22
// Problem D -- Segments
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>
#include <algorithm>

const int N = 1000;

int n;
std::pair <int, int> intervals[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &intervals[i].first, &intervals[i].second);
        if (intervals[i].first > intervals[i].second) {
            std::swap(intervals[i].first, intervals[i].second);
        }
    }
    std::sort(intervals, intervals + n);
    std::vector <std::pair <int, int> > concern;
    for (int i = 0; i < n; ++ i) {
        while (!concern.empty() && intervals[i].second <= concern.back().second) {
            concern.pop_back();
        }
        concern.push_back(intervals[i]);
    }
    std::vector <int> answer;
    int last = concern.front().first - 1;
    for (int i = 0; i < (int)concern.size(); ++ i) {
        if (last < concern[i].first) {
            last = concern[i].second;
            answer.push_back(last);
        }
    }
    printf("%d\n", (int)answer.size());
    for (int i = 0; i < (int)answer.size(); ++ i) {
        printf("%d\n", answer[i]);
    }
    return 0;
}
