// SGU 187 -- Twist and whirl - want to cheat
#include <cstdio>
#include <cstring>
#include <list>
#include <utility>
#include <algorithm>
using namespace std;

typedef list <pair <int, int> > List;

int sgn(int x) {
    return x < 0? -1: x > 0;
}

List :: iterator cut(List &sequence, int p) {
    int s = 0;
    for (List :: iterator iter = sequence.begin(); iter != sequence.end(); ++ iter) {
        if (s == p) {
            return iter;
        }
        if (s < p && p < s + abs(iter->first - iter->second)) {
            int a = iter->first;
            int b = iter->second;
            int c = a + sgn(b - a) * (p - s);
            sequence.insert(iter, make_pair(a, c));
            iter->first = c;
            return iter;
        }
        s += abs(iter->first - iter->second);
    }
    return sequence.end();
}

void show(List &sequence) {
    for (List :: iterator iter = sequence.begin(); iter != sequence.end(); ++ iter) {
        printf("(%d, %d), ", iter->first, iter->second);
    }
    puts("");
}

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    List sequence(1, make_pair(0, n));
    while (m > 0) {
        m --;
        int a, b;
        scanf("%d%d", &a, &b);
        cut(sequence, a - 1);
        cut(sequence, b);
        List :: iterator begin = cut(sequence, a - 1);
        List :: iterator end = cut(sequence, b);
        for (List :: iterator iter = begin; iter != end; ++ iter) {
            swap(iter->first, iter->second);
        }
        reverse(begin, end);
    }
    for (List :: iterator iter = sequence.begin(); iter != sequence.end(); ++ iter) {
        if (iter->first < iter->second) {
            for (int i = iter->first + 1; i <= iter->second; ++ i) {
                printf("%d ", i);
            }
        } else {
            for (int i = iter->first; i >= iter->second + 1; -- i) {
                printf("%d ", i);
            }
        }
    }
    puts("");
    return 0;
}
