// Codeforces Round #144
// Problem D -- Fence
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 100000;
const int M = (N << 1) - 1;

int n, h[N];

int m, text[M], array[M], rank[M], newArray[M], newRank[M][2], counter[M + 1], height[M];

const int D = 20;

int log[M], table[D][M];

int lcp(int i, int j) {
    if (i == j) {
        return INT_MAX;
    }
    if (i > j) {
        swap(i, j);
    }
    i ++;
    int k = log[j - i + 1];
    return min(table[k][i], table[k][j - (1 << k) + 1]);
}

struct Node {
    int count;
    Node *left, *right;

    Node(int count, Node *left, Node *right):count(count), left(left), right(right) {}

    Node* insert(int, int, int); 

    int query(int l, int r, int a, int b) {
        if (a >= b || b <= l || r <= a) {
            return 0;
        }
        if (a <= l && r <= b) {
            return count;
        }
        int m = (l + r) >> 1;
        return left->query(l, m, a, b) + right->query(m, r, a, b);
    }
};

Node* null;

Node* Node::insert(int l, int r, int k) {
    if (k < l || r <= k) {
        return this;
    }
    if (l + 1 == r) {
        return new Node(count + 1, null, null);
    }
    int m = (l + r) >> 1;
    return new Node(count + 1, left->insert(l, m, k), right->insert(m, r, k));
}

Node* trees[M + 1];

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", h + i);
    }
    
    m = 0;
    for (int i = 0; i + 1 < n; ++ i) {
        text[m ++] = h[i + 1] - h[i];
    }
    text[m ++] = INT_MAX;
    for (int i = 0; i + 1 < n; ++ i) {
        text[m ++] = h[i] - h[i + 1];
    }

    {
        vector <pair <int, int> > sorted;
        for (int i = 0; i < m; ++ i) {
            sorted.push_back(make_pair(text[i], i));
        }
        sort(sorted.begin(), sorted.end());
        for (int i = 0; i < m; ++ i) {
            array[i] = sorted[i].second;
        }
        rank[array[0]] = 0;
        for (int i = 1; i < m; ++ i) {
            rank[array[i]] = rank[array[i - 1]] + (text[array[i]] != text[array[i - 1]]);
        }
    }
    for (int k = 1; k < m; k <<= 1) {
        for (int i = 0; i < m; ++ i) {
            newRank[i][0] = rank[i] + 1;
            newRank[i][1] = (i + k < m ? rank[i + k] : -1) + 1;
        }
        memset(counter, 0, sizeof(counter));
        for (int i = 0; i < m; ++ i) {
            counter[newRank[i][1]] ++;
        }
        for (int i = 1; i <= m; ++ i) {
            counter[i] += counter[i - 1];
        }
        for (int i = m - 1; i >= 0; -- i) {
            newArray[-- counter[newRank[i][1]]] = i;
        }
        memset(counter, 0, sizeof(counter));
        for (int i = 0; i < m; ++ i) {
            counter[newRank[i][0]] ++;
        }
        for (int i = 1; i <= m; ++ i) {
            counter[i] += counter[i - 1];
        }
        for (int i = m - 1; i >= 0; -- i) {
            array[-- counter[newRank[newArray[i]][0]]] = newArray[i];
        }
        rank[array[0]] = 0;
        for (int i = 1; i < m; ++ i) {
            rank[array[i]] = rank[array[i - 1]] + (newRank[array[i]][0] != newRank[array[i - 1]][0] || newRank[array[i]][1] != newRank[array[i - 1]][1]);
        }
    }
    for (int i = 0, k = 0; i < m; ++ i) {
        if (rank[i]) {
            int j = array[rank[i] - 1];
            while (i + k < m && j + k < m && text[i + k] == text[j + k]) {
                k ++;
            }
            height[rank[i]] = k;
            if (k) {
                k --;
            }
        }
    }

//for (int i = 0; i < m; ++ i) {
//    if (i) {
//        printf("%d\n", height[i]);
//    }
//    for (int j = array[i]; j < m; ++ j) {
//        printf("%d, ", text[j]);
//    }
//    puts("");
//}

    for (int i = 1; i < m; ++ i) {
        table[0][i] = height[i];
    }
    for (int k = 1; k < D; ++ k) {
        for (int i = 0; i + (1 << k) <= m; ++ i) {
            table[k][i] = min(table[k - 1][i], table[k - 1][i + (1 << k - 1)]);
        }
    }
    log[1] = 0;
    for (int i = 2; i < m; ++ i) {
        log[i] = log[i - 1];
        if (1 << log[i] + 1 <= i) {
            log[i] ++;
        }
    }

    trees[m] = null;
    for (int i = m - 1; i >= 0; -- i) {
        trees[i] = trees[i + 1];
        if (array[i] >= n) {
            trees[i] = trees[i]->insert(0, n, array[i] - n);
        }
    }
    int q;
    scanf("%d", &q);
    while (q --) {
        int a, b;
        scanf("%d%d", &a, &b);
        if (a == b) {
            printf("%d\n", n - 1);
        } else {
            a --;
            b --;
            int base = rank[a];
            int length = b - a;
            int left = -1;
            {
                int low = 0;
                int high = base;
                while (low < high) {
                    int middle = (low + high) >> 1;
                    if (lcp(middle, base) >= length) {
                        high = middle;
                    } else {
                        low = middle + 1;
                    }
                }
                left = high;
            }
            int right = -1;
            {
                int low = base;
                int high = m - 1;
                while (low < high) {
                    int middle = (low + high + 1) >> 1;
                    if (lcp(base, middle) >= length) {
                        low = middle;
                    } else {
                        high = middle - 1;
                    }
                }
                right = low + 1;
            }
            int result = 0;
#define QUERY(a, b) trees[left]->query(0, n, a, b) - trees[right]->query(0, n, a, b)
            result += QUERY(0, a - length);
            result += QUERY(b + 1, n);
#undef QUERY
            printf("%d\n", result);
        }
    }
    return 0;
}
