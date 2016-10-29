#include <cstdio>
#include <cstring>

//  0  1  2  3
//  4  5  6  7
//  8  9 10 11
// 12 13 14 15

const int ORDER[] = {0, 1, 2, 3, 4, 8, 12, 5, 6, 7, 9, 13, 10, 11, 14, 15};

int cnt[10], sum[10], result, dist[16];

void modify(int* arr, int pos, int delta)
{
    arr[pos % 4] += delta;
    arr[4 + pos / 4] += delta;
    if (pos == 0 || pos == 5 || pos == 10 || pos == 15) {
        arr[8] += delta;
    }
    if (pos == 3 || pos == 6 || pos == 9 || pos == 12) {
        arr[9] += delta;
    }
}

void dfs(int pos_)
{
    if (pos_ >= 16) {
        result ++;
    } else {
        auto pos = ORDER[pos_];
        modify(cnt, pos, 1);
        int q = 0;
        while (q < 10 && cnt[q] != 4) {
            q ++;
        }
        for (int d = 0; d < 10; ++ d) {
            dist[pos] = d;
            bool check = true;
            if (q < 10) {
                for (int i = 0; i < 10; ++ i) {
                    if (cnt[i] == 4) {
                        check &= sum[q] == sum[i];
                    }
                }
            }
            if (check) {
                dfs(pos_ + 1);
            }
            modify(sum, pos, 1);
        }
        dist[pos] = -1;
        modify(sum, pos, -10);
        modify(cnt, pos, -1);
    }
}

int main()
{
    dfs(0);
    printf("%d\n", result);
}
