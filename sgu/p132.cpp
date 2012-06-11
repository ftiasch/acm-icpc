// SGU 132 -- Another Chocolate Maniac
#include <cstdio>
#include <cstring>
#include <map>
#include <algorithm>
using namespace std;

const int INF = 1000000000;

struct Int {
    int data;

    Int(int data = 0): data(data) {}

    int get(int i, int j) {
        return data >> ((i << 1) | j) & 1;
    }

    Int set(int i, int j, int v) {
        int k = (i << 1) | j;
        return Int((data & ~(1 << k)) | (v << k));
    }
};

const int N = 70;
const int M = 7;

int n, m, current, previous, state_count[2], values[2][1 << (M << 1)], hash[1 << (M << 1)];
Int states[2][1 << (M << 1)];
char cake[N][M + 1];

void update(Int key, int value) {
    if (hash[key.data] == -1) {
        int &reg = state_count[current];
        hash[key.data] = reg;
        states[current][reg] = key;
        values[current][reg] = value;
        reg ++;
    } else {
        int &reg = hash[key.data];
        values[current][reg] = min(values[current][reg], value);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", cake[i]);
    }
    current = 0;
    previous = 1;
    state_count[current] = 1;
    states[current][0] = Int(0);
    values[current][0] = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            swap(previous, current);
            state_count[current] = 0;
            memset(hash, -1, sizeof(hash));
            bool candle = cake[i][j] == '*';
            for (int iter = 0; iter < state_count[previous]; ++ iter) {
                Int mask = states[previous][iter];
                int value = values[previous][iter];
                bool up = mask.get(j, 0);
                bool upup = mask.get(j, 1);
                bool left = j > 0 && mask.get(j - 1, 0);
                bool upleft = j > 0 && mask.get(j - 1, 1);
                if (!up || (!upup && !upleft)) {
                    update(mask.set(j, 0, !candle).set(j, 1, up), value);
                    if (!candle && left) {
                        update(mask.set(j - 1, 0, 0).set(j, 0, 0).set(j, 1, up), value + 1);
                    }
                }
                if (!candle && up) {
                    update(mask.set(j, 0, 0).set(j, 1, 0), value + 1);
                }
            }
        }
    }
    int result = INF;
    for (int iter = 0; iter < state_count[current]; ++ iter) {
        Int mask = states[current][iter];
        bool check = true;
        for (int j = 0; j < m; ++ j) {
            check &= !mask.get(j, 0) || !mask.get(j, 1);
            if (j > 0) {
                check &= !mask.get(j, 0) || !mask.get(j - 1, 0);
            }
        }
        if (check) {
            result = min(result, values[current][iter]);
        }
    }
    printf("%d\n", result);
    return 0;
}
