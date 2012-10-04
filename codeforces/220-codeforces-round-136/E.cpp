// Codeforces Round #136
// Problem E -- Little Elephant and Inversions
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

struct Node;

typedef long long LL;
typedef Node* Tree;

const int N = 100000;

int n, a[N];
LL limit;

Tree null;

struct Node {

    int count;
    Tree left, right;

    Node(int count, Tree left, Tree right): count(count), left(left), right(right) {}

    Tree insert(int l, int r, int k) {
        if (k < l || r <= k) {
            return this;
        }
        if (l == k && k + 1 == r) {
            return new Node(count + 1, null, null);
        }
        int m = (l + r) >> 1;
        return new Node(count + 1,
                        left->insert(l, m, k),
                        right->insert(m, r, k));
    }

    int query(int l, int r, int a, int b) {
        if (b <= l || r <= a) {
            return 0;
        }
        if (a <= l && r <= b) {
            return count;
        }
        int m = (l + r) >> 1;
        return left->query(l, m, a, b) + right->query(m, r, a, b);
    }
};

Tree prefix[N + 1], suffix[N + 1];

int main() {
    null = new Node(0, NULL, NULL);
    null->left = null->right = null;
    cin >> n >> limit;
    vector <int> values;
    for (int i = 0; i < n; ++ i) {
        cin >> a[i];
        values.push_back(a[i]);
    }
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());
    for (int i = 0; i < n; ++ i) {
        a[i] = lower_bound(values.begin(), values.end(), a[i]) - values.begin();
    }
    prefix[0] = suffix[0] = null;
    for (int i = 0; i < n; ++ i) {
        prefix[i + 1] = prefix[i]->insert(0, n, a[i]);
        suffix[i + 1] = suffix[i]->insert(0, n, a[n - 1 - i]);
    }
    LL current = 0;
    for (int i = 0; i < n; ++ i) {
        current += prefix[i]->query(0, n, a[i] + 1, n);
    }
    current -= suffix[n - 1]->query(0, n, 0, a[0]);
    LL result = (LL)n * (n - 1) / 2;
    limit ++;
    for (int i = 0, j = 1; i + 1 < n; ++ i) {
        current += prefix[i]->query(0, n, a[i] + 1, n);
        current += suffix[n - j]->query(0, n, 0, a[i]);
        while (j + 1 < n) {
            LL tmp = current;
            tmp -= prefix[i + 1]->query(0, n, a[j] + 1, n);
            tmp -= suffix[n - j - 1]->query(0, n, 0, a[j]);
            if (tmp < limit) {
                break;
            }
            current = tmp;
            j ++;
        }
        if (current >= limit) {
            result -= j - i;
        }
        if (j == i + 1) {
            current -= prefix[i + 1]->query(0, n, a[j] + 1, n);
            current -= suffix[n - j - 1]->query(0, n, 0, a[j]);
            j ++;
        }
    }
    cout << result << endl;
    return 0;
}
