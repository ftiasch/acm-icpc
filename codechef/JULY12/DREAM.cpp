// July Challenge 2012
// Chef's Dream 
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, m, need[N], order[N];

bool compare(int i, int j) {
    if (need[i] == need[j]) {
        return i < j;
    }
    return need[i] < need[j];
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", need + i);
        order[i] = i;
    }
    sort(order, order + n, compare);
    int result = 0;
    for (int begin = 0; begin < n; ++ begin) {
        int end = begin;
        while (end < n && need[order[begin]] == need[order[end]]) {
            end ++;
        }
        int last = 0;
        for (int i = begin; i < end; ++ i) {
            if (order[i] >= last) {
                result ++;
                last = order[i] + m;
            }
        }
        begin = end - 1;
    }
    printf("%d\n", result);
    return 0;
}
