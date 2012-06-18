// Problem H -- Persistent Bits 
#include <cstdio>
#include <cstring>
#include <iostream>
using namespace std;

typedef long long LL;

int main() {
    while (true) {
        bool visit[65536];
        bool bit[16][2];
        memset(visit, 0, sizeof(visit));
        memset(bit, 0, sizeof(bit));
        LL s, a, b, c;
        cin >> a;
        if (a == 0) {
            break;
        }
        cin >> b >> c >> s;
        LL x = s;
        while (!visit[x]) {
            for (int i = 0; i < 16; ++ i) {
                bit[i][x >> i & 1] = true;
            }
            visit[x] = true;
            x = (x * a % c + b) % c;
        }
        for (int i = 15; i >= 0; -- i) {
            if (bit[i][0] && bit[i][1]) {
                printf("?");
            } else if (bit[i][0]) {
                printf("0");
            } else {
                printf("1");
            }
        }
        puts("");
    }
    return 0;
}
