#include "testlib.h"

#include <algorithm>
#include <functional>
#include <vector>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    int b = rnd.next(0, m);
    b += (m - b) % n;
    std::vector <int> pattern, buffer;
    for (int i = 0; i < n; ++ i) {
        pattern.push_back(rnd.next(0, 25));
    }
    for (int i = 0; i < b; ++ i) {
        buffer.push_back(rnd.next(0, 25));
    }
    for (int i = 0; i < n; ++ i) {
        putchar('a' + pattern[i]);
    }
    puts("");
    std::vector <int> positions;
    for (int i = 0; i <= m; ++ i) {
        positions.push_back(i);
    }
    shuffle(positions.begin(), positions.end());
    positions.resize((m - b) / n);
    std::sort(positions.begin(), positions.end(), std::greater <int>());
    int count = 0;
    while (true) {
        if (!positions.empty() && positions.back() == count) {
            positions.pop_back();
            for (int i = n - 1; i >= 0; -- i) {
                buffer.push_back(pattern[i]);
            }
        } else if (buffer.empty()) {
            break;
        } else {
            count ++;
            putchar('a' + buffer.back());
            buffer.pop_back();
        }
    }
    puts("");
    return 0;
}
