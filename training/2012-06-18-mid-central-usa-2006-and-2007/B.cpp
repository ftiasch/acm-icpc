// Problem B -- Linear Pachinko
#include <cstdio>
#include <cstring>
using namespace std;

int main() {
    char map[222];
    while (true) {
        scanf("%s", map);
        if (*map == '#') {
            break;
        }
        int length = strlen(map);
        double result = 0;
        for (int i = 0; i < length; ++ i) {
            if (map[i] == '.') {
                result += 1.0 / length;
            } 
            if (map[i] == '/' || map[i] == '|') {
                int j = i - 1;
                while (j >= 0 && map[j] == '_') {
                    j --;
                }
                if (j == -1 || map[j] == '.') {
                    if (map[i] == '|') {
                        result += 0.5 / length;
                    } else {
                        result += 1.0 / length;
                    }
                }
            }
            if (map[i] == '\\' || map[i] == '|') {
                int j = i + 1;
                while (j < length && map[j] == '_') {
                    j ++;
                }
                if (j == length || map[j] == '.') {
                    if (map[i] == '|') {
                        result += 0.5 / length;
                    } else {
                        result += 1.0 / length;
                    }
                }
            }
        }
        printf("%d\n", (int)(result * 100));
    }
    return 0;
}
