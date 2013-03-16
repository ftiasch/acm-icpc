// SGU 321 -- The Spy Network
#include <cstdio>
#include <cstring>
#include <deque>
#include <vector>
#include <utility>
#include <algorithm>

const int N = 200000;

int n;

int first_edge[N], to[N], next_edge[N], type[N];

int current;
std::deque <int> edges;

std::vector <int> choice;

void dfs(int u) {
    if (current < 0) {
        current += 2;
        type[edges.front()] *= -1;
        choice.push_back(edges.front());
        edges.pop_front();
    }
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        current += type[iter];
        if (type[iter] == -1) {
            edges.push_back(iter);
        }
        dfs(to[iter]);
        if (type[iter] == -1) {
            edges.pop_back();
        }
        current -= type[iter];
    }
}

int main() {
    scanf("%d", &n);
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0, a, b; i < n - 1; ++ i) {
        char buffer[10];
        scanf("%d%d%s", &a, &b, buffer);
        a --;
        b --;
        to[i] = a;
        type[i] = *buffer == 'p' ? 1 : -1;
        next_edge[i] = first_edge[b];
        first_edge[b] = i;
        if (*buffer == 'a') {
            scanf("%s", buffer);
        }
    }
    dfs(0);
    printf("%d\n", (int)choice.size());
    for (int i = 0; i < (int)choice.size(); ++ i) {
        printf("%d%c", choice[i] + 1, i == (int)choice.size() - 1 ? '\n' : ' ');
    }
    return 0;
}
