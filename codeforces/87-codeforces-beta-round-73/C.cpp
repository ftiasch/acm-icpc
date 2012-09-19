// Codeforces Beta Round #73
// Problem C -- Interesting Game
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100000;

int n, sg[N + 1], stamp[N];
vector <pair <int, int> > choices[N + 1];

int main() {
    scanf("%d", &n);
    for (int init = 1; init <= n; ++ init) {
        int sum = 0;
        int length = 0;
        while (true) {
            sum += init + (length ++);
            if (sum > n) {
                break;
            }
            if (length >= 2) {
                choices[sum].push_back(make_pair(init, init + length - 1));
            }
        }
    }
    sg[0] = 0;
    memset(stamp, 0, sizeof(stamp));
    for (int i = 1; i <= n; ++ i) {
        int m = choices[i].size() + 1;
        for (vector <pair <int, int> > :: iterator iter = choices[i].begin(); iter != choices[i].end(); ++ iter) {
            int ret = sg[iter->second] ^ sg[iter->first - 1];
            if (ret < m) {
                stamp[ret] = i;
            }
        }
        sg[i] = 0;
        while (stamp[sg[i]] == i) {
            sg[i] ++;
        }
        sg[i] ^= sg[i - 1];
    }
    if ((sg[n] ^ sg[n - 1]) == 0) {
        puts("-1");
    } else {
        int result = INT_MAX;
        for (vector <pair <int, int> > :: iterator iter = choices[n].begin(); iter != choices[n].end(); ++ iter) {
            if ((sg[iter->second] ^ sg[iter->first - 1]) == 0) {
                result = min(result, iter->second - iter->first + 1);
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
