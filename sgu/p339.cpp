// SGU 339 -- Segments
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

std::vector <std::pair <int, int> > segments;

int main() {
    char operation[2];
    int l, r;
    while (scanf("%s%d%d", operation, &l, &r) == 3) {
        if (*operation == '+') {
            int total = 0;
            foreach (iter, segments) {
                total += l <= iter->first && iter->second <= r;
            }
            segments.push_back(std::make_pair(l, r));
            printf("%d\n", total);
        } else {
            segments.erase(std::find(segments.begin(), segments.end(), std::make_pair(l, r)));
        }
    }
    return 0;
}
