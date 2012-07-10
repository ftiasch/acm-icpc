#include <cstdio>
#include <cstring>
#include <vector>
#include <functional>
#include <algorithm>
using namespace std;

const int N = 50;
const int M = 111;

int n, sg[N][M][M], toLeft[M], toRight[M], newSG[N][M];
pair <int, int> order[M];
bool myHash[M];
vector <int> chains[N];

int find(int parent[M], int i) {
    if (parent[i] != i) {
        parent[i] = find(parent, parent[i]);
    }
    return parent[i];
}

void solveSG(const vector <int> &chain, int sg[M][M], int newSG[M]) {
    int n = (int)chain.size();
    vector <pair <int, int> > allOrder;
    for (int i = 0; i < n; ++ i) {
        allOrder.push_back(make_pair(chain[i], i));
    }
    sort(allOrder.begin(), allOrder.end(), greater <pair <int, int> >());
    for (int lower = n; lower >= 0; -- lower) {
        sg[lower][lower] = 0;
        for (int upper = lower + 1; upper <= n; ++ upper) {
            memset(myHash, 0, sizeof(myHash));
            int orderSize = 0;
            for (int i = 0; i < n; ++ i) {
                if (lower <= allOrder[i].second && allOrder[i].second < upper) {
                    order[orderSize ++] = allOrder[i];
                }
            }
            for (int i = lower; i <= upper; ++ i) {
                toLeft[i] = toRight[i] = i;
            }
            int currentSG = 0;
            for (int begin = 0; begin < orderSize; ++ begin) {
                myHash[currentSG] = true;
                int end = begin;
                while (end < orderSize && order[begin].first == order[end].first) {
                    end ++;
                }
                for (int i = begin; i < end; ++ i) {
                    int p = order[i].second;
//printf("%d, %d, %d, %d\n", lower, upper, i, p);
                    newSG[p] = currentSG;
                }
                for (int i = begin; i < end; ++ i) {
                    int p = order[i].second;
                    currentSG ^= sg[find(toLeft, p)][p];
                    currentSG ^= sg[p + 1][find(toRight, p + 1)];
                    currentSG ^= sg[find(toLeft, p)][find(toRight, p + 1)];
                    toLeft[p + 1] = p;
                    toRight[p] = p + 1;
                }
                begin = end - 1;
            }
            int &ret = sg[lower][upper] = 0;
            while (myHash[ret]) {
                ret ++;
            }
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int m;
        scanf("%d", &m);
        chains[i].resize(m);
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &chains[i][j]);
        }
        solveSG(chains[i], sg[i], newSG[i]);
    }
    int sgSum = 0;
    for (int i = 0; i < n; ++ i) {
        sgSum ^= sg[i][0][(int)chains[i].size()];
    }
    if (sgSum == 0) {
        puts("S");
    } else {
        puts("G");
        bool found = false;
        for (int i = 0; i < n && !found; ++ i) {
            for (int j = 0; j < (int)chains[i].size() && !found; ++ j) {
                if ((sgSum ^ sg[i][0][(int)chains[i].size()] ^ newSG[i][j]) == 0) {
                    printf("%d %d\n", i + 1, j + 1);
                    found = true;
                }
            }
        }
    }
    return 0;
}
