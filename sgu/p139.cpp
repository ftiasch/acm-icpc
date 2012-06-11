// SGU 139 -- Help Needed!
#include <cstdio>
#include <cstring>
using namespace std;

int map[16];

int main() {
    for (int i = 0; i < 16; ++ i) {
        scanf("%d", map + i);
    }
    int result = 0;
    for (int i = 0; i < 16; ++ i) {
        if (map[i] == 0) {
            result += (3 - (i / 4)) + (3 - (i % 4));
        }
        for (int j = i + 1; j < 16; ++ j) {
            if (map[i] > map[j]) {
                result ++;
            }
        }
    }
    puts(result % 2 == 1? "YES": "NO");
    return 0;
}
