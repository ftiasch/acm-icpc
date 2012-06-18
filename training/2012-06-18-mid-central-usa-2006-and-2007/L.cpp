// Problem L -- Rock Skipping
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

struct Throw {
    int i, d, count, length;

    Throw(int i = 0, int d = 0, int count = 0, int length = 0): i(i), d(d), count(count), length(length) {}
};

bool operator <(const Throw &a, const Throw &b) {
    if (a.count == b.count) {
        if (a.length == b.length) {
            if (a.i == b.i) {
                return a.d < b.d;
            }
            return a.i > b.i;
        }
        return a.length > b.length;
    }
    return a.count > b.count;
}

int main() {
    char map[222];
    while (true) {
        scanf("%s", map);
        if (strcmp(map, "END") == 0) {
            break;
        }
        int length = strlen(map);
        vector <Throw> throws;
        for (int i = 0; i < length; ++ i) {
            for (int d = 1; d < length; ++ d) {
                int c = 0;
                int j = i;
                while (j < length && map[j] == '.') {
                    c ++;
                    j += d;
                }
                if (j >= length) {
                    j -= d;
                }
                throws.push_back(Throw(i, d, c, j));
            }
        }
        sort(throws.begin(), throws.end());
        printf("%d %d\n", throws.front().i, throws.front().d);
    }
    return 0;
}
