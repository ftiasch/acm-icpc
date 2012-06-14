// SGU 153 -- Playing with matches
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

int hash[1 << 9];

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        int n, m;
        scanf("%d%d", &n, &m);
        int mask = 1;
        for (int i = 0; i < m; ++ i) {
            int p_i;
            scanf("%d", &p_i);
            mask |= 1 << (p_i - 1);
        }
        memset(hash, -1, sizeof(hash));
        vector <int> states;
        states.push_back(0);
        hash[0] = 0;
        int final_state = -1;
        for (int i = 1; i <= n; ++ i) {
            int next = (states.back() & mask) == 0;
            states.push_back(((states.back() << 1) | next) & ((1 << 9) - 1));
            if (hash[states.back()] == -1) {
                hash[states.back()] = i;
            } else {
                int start_position = hash[states.back()];
                int cycle_length = i - start_position;
                final_state = states[start_position + (n - start_position) % cycle_length];
                break;
            }
        }
        if (final_state == -1) {
            final_state = states.back();
        }
        puts((final_state & 1) == 0? "FIRST PLAYER MUST WIN": "SECOND PLAYER MUST WIN");
    }
    return 0;
}
