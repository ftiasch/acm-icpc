// SGU 398 -- Friends of Friends
#include <cstdio>
#include <cstring>
#include <vector>

const int N = 50;

int n, x;
bool graph[N][N];

int main() {
    scanf("%d%d", &n, &x);
    x --;
    for (int i = 0; i < n; ++ i) {
        int d;
        scanf("%d", &d);
        while (d --) {
            int j;
            scanf("%d", &j);
            j --;
            graph[i][j] = graph[j][i] = true;
        }
    }
    std::vector <int> friends;
    for (int i = 0; i < n; ++ i) {
        if (i != x && !graph[x][i]) {
            bool found = false;
            for (int j = 0; j < n; ++ j) {
                found |= graph[x][j] && graph[j][i];
            }
            if (found) {
                friends.push_back(i);
            }
        }
    }
    printf("%d\n", (int)friends.size());
    for (int i = 0; i < (int)friends.size(); ++ i) {
        printf("%d%c", friends[i] + 1, i == (int)friends.size() - 1 ? '\n' : ' ');
    }
    return 0;
}
