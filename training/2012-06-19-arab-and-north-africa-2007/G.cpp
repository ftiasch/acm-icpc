// Problem G -- Let's Go to the Movies
#include <cctype>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int BUFFER_LENGTH = 2222222;
char buffer[BUFFER_LENGTH];

const int N = 111111;
const int MOD = 3214567;
const unsigned MAGIC = 173;

int n, s, f, stamp_count, stamp[MOD], hash[MOD], first_child[N], next_child[N], queue[N];
bool has_parent[N];

struct Data {
    int s_count, f_count;

    Data(int a = 0, int b = 0): s_count(a), f_count(b) {}

    int count() const {
        return s_count + f_count;
    }

    int cost() const {
        return s_count * s + f_count * f;
    }

    Data &operator +=(const Data &other) {
        return *this = Data(s_count + other.s_count, f_count + other.f_count);
    }
};

Data operator +(Data a, const Data &b) {
    return a += b;
}

bool operator <(const Data &a, const Data &b) {
    if (a.cost() == b.cost()) {
        return a.count() < b.count();
    }
    return a.cost() < b.cost();
}

Data minimum[N][2];

int main() {
    int test_count = 0;
    stamp_count = 0;
    memset(stamp, 0, sizeof(stamp));
    int next_s, next_f;
    scanf("%d%d", &next_s, &next_f);
    fgets(buffer, BUFFER_LENGTH, stdin);
    while (next_s + next_f > 0) {
        n = 0;
        s = next_s;
        f = next_f;
        stamp_count ++;
        while (true) {
            fgets(buffer, BUFFER_LENGTH, stdin);
            int i = 0;
            while (buffer[i] == ' ') {
                i ++;
            }
            if (isdigit(buffer[i])) {
                sscanf(buffer, "%d%d", &next_s, &next_f);
                break;
            }
            int length = strlen(buffer) - 1;
            int parent_id = -1;
            for (int lower = 0; lower < length; ++ lower) {
                if (buffer[lower] != ' ') {
                    int upper = lower;
                    unsigned code = 0;
                    while (upper < length && buffer[upper] != ' ') {
                        code = code * MAGIC + buffer[upper];
                        upper ++;
                    }
                    lower = upper;
                    code %= MOD;
                    if (stamp[code] != stamp_count) {
                        has_parent[n] = false;
                        first_child[n] = -1;
                        hash[code] = n ++;
                        stamp[code] = stamp_count;
                    }
                    int id = hash[code];
                    if (parent_id == -1) {
                        parent_id = id;
                    } else {
//printf("%d, %d\n", parent_id, id);
                        has_parent[id] = true;
                        next_child[id] = first_child[parent_id];
                        first_child[parent_id] = id;
                    }
                }
            }
        }
        int head = 0;
        int tail = 0;
        for (int i = 0; i < n; ++ i) {
            if (!has_parent[i]) {
                queue[tail ++] = i;
            }
        }
        while (head != tail) {
            int u = queue[head ++];
            for (int v = first_child[u]; v != -1; v = next_child[v]) {
                queue[tail ++ ] = v;
            }
        }
        for (int i = n - 1; i >= 0; -- i) {
            int u = queue[i];
            minimum[u][0] = Data(0, 0);
            minimum[u][1] = Data(0, 1);
            for (int v = first_child[u]; v != -1; v = next_child[v]) {
                minimum[u][0] += minimum[v][1];
                minimum[u][1] += min(minimum[v][0], minimum[v][1]);
            }
            minimum[u][1] = min(minimum[u][1], minimum[u][0] + Data(1, 0));
        }
        Data result;
        for (int i = 0; i < n; ++ i) {
            if (!has_parent[i]) {
                result += minimum[i][1];
            }
        }
        printf("%d. %d %d %d\n", ++ test_count, result.s_count, result.f_count, result.cost());
    }
    return 0;
}
