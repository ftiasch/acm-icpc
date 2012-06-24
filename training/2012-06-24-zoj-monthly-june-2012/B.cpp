// Problem B -- Median  
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 111111;

int treap_count, key[N], frequency[N], priority[N], children[N][2], size[N];
bool fail;

void update(int &x) {
    size[x] = size[children[x][0]] + frequency[x] + size[children[x][1]];
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, int k) {
    if (x == 0) {
        x = ++ treap_count;
        key[x] = k;
        priority[x] = rand();
        frequency[x] = 1;
        children[x][0] = children[x][1] = 0;
    } else if (key[x] == k) {
        frequency[x] ++;
    } else {
        int t = key[x] < k;
        insert(children[x][t], k);
        if (priority[children[x][t]] < priority[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

void erase(int &x, int k) {
    if (x == 0) {
        fail = true;
        return;
    } else if (key[x] != k) {
        erase(children[x][key[x] < k], k);
    } else if (frequency[x] > 1) {
        frequency[x] --;
    } else if (children[x][0] + children[x][1] == 0) {
        x = 0;
        return;
    } else {
        rotate(x, priority[children[x][0]] > priority[children[x][1]]);
        erase(x, k);
    }
    update(x);
}

int find(int &x, int k) {
    if (size[children[x][0]] >= k) {
        return find(children[x][0], k);
    }
    if (k <= size[children[x][0]] + frequency[x]) {
        return key[x];
    }
    return find(children[x][1], k - (size[children[x][0]] + frequency[x]));
}

int main() {
    size[0] = 0;
    priority[0] = INT_MAX;
    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        int operation_count;
        scanf("%d", &operation_count);
        treap_count = 0;
        int root = 0;
        while (operation_count > 0) {
            operation_count --;
            char buffer[22];
            int key;
            scanf("%s%d", buffer, &key);
            fail = false;
            if (*buffer == 'a') {
                insert(root, key);
            } else {
                erase(root, key);
            }
            if (fail) {
                puts("Wrong!");
            } else if (size[root] == 0) {
                puts("Empty!");
            } else {
                int s = size[root];
                if (s & 1) {
                    printf("%d\n", find(root, (s + 1) / 2));
                } else {
                    long long sum = (long long)find(root, s / 2) + find(root, s / 2 + 1);
                    if (sum & 1) {
                        if (sum / 2 == 0 && sum < 0) {
                            printf("-");
                        }
                        printf("%d.5\n", (int)(sum / 2));
                    } else { 
                        printf("%d\n", (int)(sum / 2));
                    }
                }
            }
        }
    }
    return 0;
}
