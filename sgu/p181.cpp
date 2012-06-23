// SGU 181 -- X-Sequence
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

int main() {
    int init, alpha, beta, gamma, mod, length;
    scanf("%d%d%d%d%d%d", &init, &alpha, &beta, &gamma, &mod, &length);
    if (length == 0) {
        printf("%d\n", init);
        return 0;
    }
    init %= mod;
    int hash[mod];
    memset(hash, -1, sizeof(hash));
    vector <int> sequence;
    sequence.push_back(init);
    hash[init] = 0;
    for (int i = 1; i <= length; ++ i) {
        int next = (alpha * init % mod * init % mod + beta * init % mod + gamma) % mod;
        if (hash[next] == -1) {
            hash[next] = i;
        } else {
            int cycle = i - hash[next];
            printf("%d\n", sequence[hash[next] + (length - hash[next]) % cycle]);
            return 0;
        }
        sequence.push_back(next);
        init = next;
    }
    printf("%d\n", init);
    return 0;
}
