#include "testlib.h"

#include <vector>
#include <utility>

int main(int argc, char* argv[])
{
    registerGen(argc, argv, 1);
    int n = std::atoi(argv[2]);
    int m = std::atoi(argv[3]);
    std::vector <std::pair <int, int>> blocks;
    for (int i = -m; i < m; ++ i) {
        for (int j = -m; j < m; ++ j) {
            if ((i + j) % 2) {
                blocks.push_back({i, j});
            }
        }
    }
    shuffle(blocks.begin(), blocks.end());
    blocks.resize(n);
    printf("%d\n", (int)blocks.size());
    for (const auto &it : blocks) {
        printf("%d %d\n", it.first, it.second);
    }
    return 0;
}
