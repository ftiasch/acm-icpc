#include <cstdio>
#include <cstring>
#include <stack>
#include <utility>
#include <algorithm>

const int N = 1000;

int n, m, q, a[4][N][N], height[4][N][N], left[N];

void transform(int &i, int &j) {
    std::swap(n, m);
    std::swap(i, j);
    i *= -1;
    i += n - 1;
}

void set(int i, int j, int na) {
    for (int k = 0; k < 4; ++ k) {
        a[k][i][j] = na;
        transform(i, j);
    }
}

void update(int i, int j) {
    for (int _ = 0; _ < 4; ++ _) {
        for (int k = 0; k < n; ++ k) {
            height[_][k][j] = a[_][k][j] ? ((k ? height[_][k - 1][j] : 0) + 1) : 0;
        }
        transform(i, j);
    }
}

int solve(int i, int j) {
    int result = 0;
    for (int _ = 0; _ < 4; ++ _) {
        int *h = height[_][i];
        {
            std::stack <int> stack;
            stack.push(-1);
            for (int k = 0; k < m; ++ k) {
                while (stack.top() != -1 && h[stack.top()] >= h[k]) {
                    stack.pop();
                }
                left[k] = stack.top();
                stack.push(k);
            }
        }
        {
            std::stack <int> stack;
            stack.push(m);
            for (int k = m - 1; k >= 0; -- k) {
                while (stack.top() != m && h[stack.top()] >= h[k]) {
                    stack.pop();
                }
                int right = stack.top();
                if (left[k] < j && j < right) {
                    result = std::max(result, (right - left[k] - 1) * h[k]);
                }
                stack.push(k);
            }
        }
        transform(i, j);
    }
    return result;
}

int main() {
    scanf("%d%d%d", &n, &m, &q);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            int a;
            scanf("%d", &a);
            set(i, j, a);
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (!i || !j) {
                update(i, j);
            }
        }
    }
    while (q --) {
        int t, x, y;
        scanf("%d%d%d", &t, &x, &y);
        x --, y --;
        if (t == 1) {
            int na = a[0][x][y] ^ 1;
            set(x, y, na);
            update(x, y);
        } else if (t == 2) {
            printf("%d\n", solve(x, y));
        }
    }
    return 0;
}
