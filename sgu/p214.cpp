// SGU 214 -- Weird Dissimilarity
#include <cstdio>
#include <cstring>
#include <climits>
#include <string>
#include <iostream>
#include <algorithm>

const int N = 2000;
const int M = 200;

const int D = 256;

char alphabet[M + 1], a[N + 1], b[N + 1];
int cost[M][M], min_column[M], min_row[M], minimum[N + 1][N + 1], choice[N + 1][N + 1], id[512];


int main() {
    scanf("%s%s%s", alphabet, a, b);
    int m = strlen(alphabet);
    memset(id, -1, sizeof(id));
    for (int i = 0; i < m; ++ i) {
        id[D + alphabet[i] ] = i;
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &cost[i][j]);
        }
    }
    for (int i = 0; i < m; ++ i) {
        min_column[i] = std::min_element(cost[i], cost[i] + m) - cost[i];
    }
    for (int j = 0; j < m; ++ j) {
        min_row[j] = 0;
        for (int i = 0; i < m; ++ i) {
            if (cost[i][j] < cost[min_row[j]][j]) {
                min_row[j] = i;
            }
        }
    }
    int na = strlen(a);
    int nb = strlen(b);
    minimum[na][nb] = 0;
    for (int i = na; i >= 0; -- i) {
        for (int j = nb; j >= 0; -- j) {
            minimum[i][j] = i == na && j == nb ? 0 : INT_MAX;
            if (i < na && minimum[i + 1][j] + cost[id[D + a[i]]][min_column[id[D + a[i]]]] < minimum[i][j]) {
                minimum[i][j] = minimum[i + 1][j] + cost[id[D + a[i]]][min_column[id[D + a[i]]]];
                choice[i][j] = 0;
            }
            if (j < nb && minimum[i][j + 1] + cost[min_row[id[D + b[j]]]][id[D + b[j]]] < minimum[i][j]) {
                minimum[i][j] = minimum[i][j + 1] + cost[min_row[id[D + b[j]]]][id[D + b[j]]];
                choice[i][j] = 1;
            }
            if (i < na && j < nb && minimum[i + 1][j + 1] + cost[id[D + a[i]]][id[D + b[j]]] < minimum[i][j]) {
                minimum[i][j] = minimum[i + 1][j + 1] + cost[id[D + a[i]]][id[D + b[j]]];
                choice[i][j] = 2;
            }
        }
    }
    std::cout << minimum[0][0] << std::endl;
    std::string sa, sb;
    for (int i = 0, j = 0; i < na || j < nb; ++ i, ++ j) {
        if (choice[i][j] == 0) {
            sa += a[i];
            sb += alphabet[min_column[id[D + a[i]]]];
            j --;
        } else if (choice[i][j] == 1) {
            sa += alphabet[min_row[id[D + b[j]]]];
            sb += b[j];
            i --;
        } else {
            sa += a[i];
            sb += b[j];
        }
    }
    std::cout << sa << "\n" << sb << std::endl;
    return 0;
}
