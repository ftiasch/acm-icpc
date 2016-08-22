#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

const int N = 300000;

bool read[N];
std::vector<int> app[N];

int main()
{
    int n, q;
    scanf("%d%d", &n, &q);
    int m = 0;
    int unread = 0;
    memset(read, 0, sizeof(read));
    int max_t = 0;
    while (q --) {
        int type, x;
        scanf("%d%d", &type, &x);
        x --;
        if (type == 1) {
            unread ++;
            app[x].push_back(m ++);
        } else if (type == 2) {
            for (auto&& msg : app[x]) {
                if (!read[msg]) {
                    unread --;
                    read[msg] = true;
                }
            }
            app[x].clear();
        } else {
            for (int i = max_t; i <= x; ++ i) {
                if (!read[i]) {
                    unread --;
                    read[i] = true;
                }
            }
            max_t = std::max(max_t, x + 1);
        }
        printf("%d\n", unread);
    }
}
