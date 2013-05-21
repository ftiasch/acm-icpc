// Codeforces Beta Round #55
// Problem D -- Team Arrangement
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 100000;

int n, position[N * 3], teams[N][3];
bool selected[N * 3];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n * 3; ++ i) {
        int o;
        scanf("%d", &o);
        position[-- o] = i;
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 3; ++ j) {
            scanf("%d", &teams[i][j]);
            teams[i][j] --;
        }
    }
    int target;
    scanf("%d", &target);
    target --;
    memset(selected, 0, sizeof(selected));
    for (int i = 0; i < n; ++ i) {
        bool found = false;
        for (int j = 0; j < 3; ++ j) {
            found |= teams[i][j] == target;
        }
        if (found) {
            bool leader = true;
            for (int j = 0; j < 3; ++ j) {
                leader &= position[teams[i][j]] >= position[target];
            }
            std::vector <int> order;
            if (leader) {
                std::vector <int> former;
                for (int j = 0; j < n * 3; ++ j) {
                    if (selected[j]) {
                        former.push_back(j);
                    }
                }
                std::vector <int> latter;
                for (int j = 0; j < 3; ++ j) {
                    selected[teams[i][j]] = true;
                    if (teams[i][j] != target) { 
                        latter.push_back(teams[i][j]);
                    }
                }
                std::sort(latter.begin(), latter.end());
                for (int j = 0; j < n * 3; ++ j) {
                    if (!selected[j]) {
                        latter.push_back(j);
                    }
                }
                for (int j = 0, k = 0; j < (int)former.size() || k < (int)latter.size(); ) {
                    if (j < (int)former.size() && (k == (int)latter.size() || former[j] < latter[k])) {
                        order.push_back(former[j ++]);
                    } else {
                        order.push_back(latter[k ++]);
                    }
                }
            } else {
                for (int j = 0; j < n * 3; ++ j) {
                    if (j != target) {
                        order.push_back(j);
                    }
                }
            }
            for (int j = 0; j < (int)order.size(); ++ j) {
                printf("%d%c", order[j] + 1, " \n"[j == (int)order.size() - 1]);
            }
            return 0;
        }
        for (int j = 0; j < 3; ++ j) {
            selected[teams[i][j]] = true;
        }
    }
    return 0;
}
