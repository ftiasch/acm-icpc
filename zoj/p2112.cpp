#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <cstdlib>
#include <vector>

const int N = 60000;
const int M = 1000000;

int a[N], l[N], r[N], c[N], key[M], weight[M], size[M], children[M][2];
char type[N];

int update(int x)
{
    size[x] = size[children[x][0]] + 1 + size[children[x][1]];
    return x;
}

void rotate(int &x, int t)
{
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, int k)
{
    if (x) {
        int t = key[x] < key[k];
        insert(children[x][t], k);
        if (weight[children[x][t]] < weight[x]) {
            rotate(x, t);
        }
    } else {
        x = k;
    }
    update(x);
}

int erase(int &x, int k) {
    int ret = -1;
    if (key[x] != k) {
        ret = erase(children[x][key[x] < k], k);
    } else if (!children[x][0] && !children[x][1]) {
        ret = x;
        x = 0;
        return ret;
    } else {
        rotate(x, weight[children[x][0]] > weight[children[x][1]]);
        ret = erase(x, k);
    }
    update(x);
    return ret;
}

int count(int &x, int k)
{
    if (x) {
        if (key[x] >= k) {
            return count(children[x][0], k);
        }
        return size[children[x][0]] + 1 + count(children[x][1], k);
    }
    return 0;
}

std::vector <int> buffer;

int new_node(int k)
{
    int n = buffer.back();
    buffer.pop_back();
    key[n] = k;
    weight[n] = rand();
    children[n][0] = children[n][1] = 0;
    return n;
}

std::vector <int> values;

int get_id(int a)
{
    return std::lower_bound(values.begin(), values.end(), a) - values.begin();
}

int tree[1 << 16];

int main()
{
    size[0] = 0;
    weight[0] = RAND_MAX;
    int tests;
    scanf("%d", &tests);
    while (tests --) {
        int n, q;
        scanf("%d%d", &n, &q);
        values.clear();
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
            values.push_back(a[i]);
        }
        for (int i = 0; i < q; ++ i) {
            char buffer[2];
            scanf("%s", buffer);
            type[i] = *buffer;
            scanf("%d", l + i);
            l[i] --;
            if (type[i] == 'Q') {
                scanf("%d", r + i);
            }
            scanf("%d", c + i);
            if (type[i] == 'C') {
                values.push_back(c[i]);
            }
        }
        std::sort(values.begin(), values.end());
        values.erase(std::unique(values.begin(), values.end()), values.end());
        buffer.resize(M - 1);
        for (int i = 1; i < M; ++ i) {
            buffer[i - 1] = i;
        }
        int mb = 32 - __builtin_clz(values.size());
        int m = 1 << mb;
        memset(tree, 0, sizeof(tree));
        for (int i = 0; i < n; ++ i) {
            for (int k = get_id(a[i]); k < m; k += ~k & k + 1) {
                insert(tree[k], new_node(i));
            }
        }
        for (int i = 0; i < q; ++ i) {
            if (type[i] == 'Q') {
                int result = 0;
                for (int k = mb - 1; k >= 0; -- k) {
                    int rr = tree[result + (1 << k) - 1];
                    int cc = count(rr, r[i]) - count(rr, l[i]);
                    if (cc < c[i]) {
                        c[i] -= cc;
                        result += 1 << k;
                    }
                }
                printf("%d\n", values[result]);
            } else {
                for (int k = get_id(a[l[i]]); k < m; k += ~k & k + 1) {
                    buffer.push_back(erase(tree[k], l[i]));
                }
                a[l[i]] = c[i];
                for (int k = get_id(c[i]); k < m; k += ~k & k + 1) {
                    insert(tree[k], new_node(l[i]));
                }
            }
        }
    }
    return 0;
}
