// Codeforces Beta Round #19
// Problem C -- Deletion of Repeats
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

typedef unsigned long long Hash;

const int N = 100000;
const Hash MAGIC = (int)1e9 + 7;

int n, a[N];
Hash hash[N + 1], power[N + 1];

std::map <int, std::vector <int> > positions;

struct Repeat {
    int start, length;

    Repeat(int start, int length) : start(start), length(length) {}
};

bool operator < (const Repeat &a, const Repeat &b) {
    if (a.length != b.length) {
        return a.length < b.length;
    }
    return a.start < b.start;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        positions[a[i]].push_back(i);
    }
    hash[n] = 0;
    for (int i = n - 1; i >= 0; -- i) {
        hash[i] = hash[i + 1] * MAGIC + a[i];
    }
    power[0] = 1;
    for (int i = 0; i < n; ++ i) {
        power[i + 1] = power[i] * MAGIC;
    }
    std::vector <Repeat> repeats;
    for (int i = 0; i < n; ++ i) {
        foreach (iter, positions[a[i]]) {
            int j = *iter;
            if (j >= i) {
                break;
            }
            int low = 0;
            int high = n - i;
            while (low < high) {
                int middle = low + high + 1 >> 1;
                if (hash[i] - hash[i + middle] * power[middle] == hash[j] - hash[j + middle] * power[middle]) {
                    low = middle;
                } else {
                    high = middle - 1;
                }
            }
            if (j + low >= i) {
                repeats.push_back(Repeat(i, i - j));
            }
        }
    }
    std::sort(repeats.begin(), repeats.end());
    int now = 0;
    foreach (iter, repeats) {
        if (now <= iter->start - iter->length) {
            now = iter->start; 
        }
    }
    printf("%d\n", n - now);
    for (int i = now; i < n; ++ i) {
        printf("%d%c", a[i], " \n"[i == n - 1]);
    }
    return 0;
}
