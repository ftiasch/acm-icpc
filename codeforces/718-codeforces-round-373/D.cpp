#include <algorithm>
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>

const int N = 100000;

int head[N], to[N << 1], next[N << 1], tree_hash[N << 1];
std::map<std::vector<int>, int> hashes;

int get_hash(const std::vector<int>& hash)
{
    if (!hashes.count(hash)) {
        auto count = hashes.size();
        hashes[hash] = count;
    }
    return hashes[hash];
}

int get_tree_hash(int edge)
{
    int& ref = tree_hash[edge];
    if (ref == -1) {
        std::vector<int> hashes;
        for (int iterator = head[to[edge]]; ~iterator; iterator = next[iterator]) {
            if (to[iterator] != to[edge ^ 1]) {
                hashes.push_back(get_tree_hash(iterator));
            }
        }
        std::sort(hashes.begin(), hashes.end());
        ref = get_hash(hashes);
    }
    return ref;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("D.in", "r", stdin);
#endif
    int n;
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n - 1 << 1; ++ i) {
            scanf("%d", to + i);
            to[i] --;
        }
        memset(head, -1, sizeof(*head) * n);
        for (int i = 0; i < n - 1 << 1; ++ i) {
            auto& h = head[to[i ^ 1]];
            next[i] = h;
            h = i;
        }
        memset(tree_hash, -1, sizeof(*tree_hash) * (n << 1));
        std::vector<std::vector<int>> hashes;
        for (int u = 0; u < n; ++ u) {
            std::vector<int> hash;
            for (int iterator = head[u]; ~iterator; iterator = next[iterator]) {
                hash.push_back(get_tree_hash(iterator));
            }
            if (static_cast<int>(hash.size()) < 4) {
                std::sort(hash.begin(), hash.end());
                hashes.push_back(hash);
            }
        }
        std::sort(hashes.begin(), hashes.end());
        hashes.erase(std::unique(hashes.begin(), hashes.end()), hashes.end());
        printf("%d\n", static_cast<int>(hashes.size()));
    }
}
