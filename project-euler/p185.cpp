#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <climits>
#include <functional>
#include <utility>
#include <vector>

const int N = 22;
const int M = 16;

const char* GUESS[N] =
{ "5616185650518293"
, "3847439647293047"
, "5855462940810587"
, "9742855507068353"
, "4296849643607543"
, "3174248439465858"
, "4513559094146117"
, "7890971548908067"
, "8157356344118483"
, "2615250744386899"
, "8690095851526254"
, "6375711915077050"
, "6913859173121360"
, "6442889055042768"
, "2321386104303845"
, "2326509471271448"
, "5251583379644322"
, "1748270476758276"
, "4895722652190306"
, "3041631117224635"
, "1841236454324589"
, "2659862637316867" };

// M * 10 sets
// N + M elements

int count[N + M] = { 2 , 1 , 3 , 3 , 3 , 1 , 2 , 3 , 1 , 2 , 3 , 1 , 1 , 2 , 0 , 2 , 2 , 3 , 1 , 3 , 3 , 2 };

int removed[N * 10];
char sequence[M + 1];
std::vector<int> set[M * 10];
std::vector<int> included[N + M];

void dfs(int depth)
{
    std::pair<int, int> best(INT_MAX, -1);
    for (int i = 0; i < N + M; ++ i) {
        if (count[i] > 0) {
            int count = 0;
            for (auto&& s : included[i]) {
                count += !removed[s];
            }
            best = std::min(best, {count, i});
        }
    }
    if (~best.second) {
        auto i = best.second;
        for (auto&& s : included[i]) {
            if (!removed[s]) {
                for (auto&& x : set[s]) {
                    count[x] --;
                    if (count[x] == 0) {
                        for (auto&& y : included[x]) {
                            removed[y] ++;
                        }
                    }
                }
                sequence[s / 10] = '0' + s % 10;
                dfs(depth + 1);
                for (auto&& x : set[s]) {
                    if (count[x] == 0) {
                        for (auto&& y : included[x]) {
                            removed[y] --;
                        }
                    }
                    count[x] ++;
                }
            }
        }
    } else {
        // for (int i = 0; i < N + M; ++ i) {
        //     printf("%d,", count[i]);
        // }
        // puts("");
        puts(sequence);
        exit(0);
    }
}

int main()
{
    for (auto i = 0; i < M; ++ i) {
        count[N + i] = 1;
    }
    for (auto position = 0; position < M; ++ position) {
        for (auto digit = 0; digit < 10; ++ digit) {
            auto id = position * 10 + digit;
            for (int i = 0; i < N; ++ i) {
                if (GUESS[i][position] - '0' == digit) {
                    set[id].push_back(i);
                    included[i].push_back(id);
                }
            }
            set[id].push_back(N + position);
            included[N + position].push_back(id);
        }
    }
    for (int i = 0; i < N + M; ++ i) {
        if (count[i] == 0) {
            for (auto&& y : included[i]) {
                removed[y] ++;
            }
        }
    }
    dfs(0);
}
