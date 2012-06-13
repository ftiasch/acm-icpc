#include <cstdio>
#include <cstring>
#include <queue>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 11111;

int n, size[N];

int main() {
    int test_count = 0;
    while (scanf("%d", &n) == 1 && n > 0) {
        test_count ++;
        if (test_count > 1) {
            puts("");
        }
        for (int i = 0; i < n; ++ i) {
            scanf("%d", size + i);
        }
        sort(size, size + n);
        int piece_count = 0;
        priority_queue <pair <int, int> > heap;
        for (int lower = 0; lower < n; ++ lower) {
            int upper = lower;
            while (upper < n && size[lower] == size[upper]) {
                upper ++;
            }
            heap.push(make_pair(upper - lower, size[lower]));
            piece_count = max(piece_count, upper - lower);
            lower = upper - 1;
        }
        printf("%d\n", piece_count);
        int limit = (n + piece_count - 1) / piece_count;
        while (!heap.empty()) {
            vector <pair <int, int> > choice;
            for (int i = 0; i < limit && !heap.empty(); ++ i) {
                choice.push_back(heap.top());
                heap.pop();
            }
            for (int i = 0; i < (int)choice.size(); ++ i) {
                printf("%d%c", choice[i].second, i + 1 == (int)choice.size()? '\n': ' ');
                if (choice[i].first > 1) {
                    heap.push(make_pair(choice[i].first - 1, choice[i].second));
                }
            }
        }
    }
    return 0;
}
