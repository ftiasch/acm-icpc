#include <cassert>
#include <algorithm>
#include <iostream>

const int DEBUG_LEVEL = -1;

#define DEBUG(id, _) if (DEBUG_LEVEL == -1 || DEBUG_LEVEL == id) _

typedef long long Long;

const int  N   = 12345;
const int  M   = 6789;
const Long MOD = 135707531;

short gcd[N + 1][M + 1];

Long inverse(Long a)
{
    return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
}

Long countQuadrangle(Long n, Long m)
{
    Long all = (n + 1) * (m + 1);
    Long result = all * (all - 1) % MOD * (all - 2) % MOD * (all - 3) % MOD * inverse(24) % MOD;
    for (Long i = 0; i <= n; ++ i) {
        for (Long j = !i; j <= m; ++ j) {
            Long copies = (i && j ? 2 : 1) * (n - i + 1) * (m - j + 1);
            Long line = gcd[i][j] - 1;
            (result += MOD - line * (all - 3) % MOD * copies % MOD) %= MOD;
            (result += 3 * line * (line - 1) / 2 * copies) %= MOD;
        }
    }
    return result;
}


Long countPointInsideTriangle(Long a, Long b)
{
    return ((a - 1) * (b - 1) - gcd[a][b] + 1) / 2;
}


Long xCount[N + 1], yCount[N + 1];

void add(Long* count, int n, int k, int v)
{
    for (; k <= n; k += ~k & k + 1) {
        count[k] += v;
    }
}

Long ask(Long* count, int k)
{
    Long result = 0;
    for (; k >= 0; k -= ~k & k + 1) {
        result += count[k];
    }
    return result;
}

Long dfs(Long n, Long m, int q1, int p1, int q2, int p2)
{
    Long q = q1 + q2;
    Long p = p1 + p2;
    if (p <= n && q <= m) {
        Long result = 0;
        (result += dfs(n, m, q1, p1, q, p)) %= MOD;
        for (Long x = p, y = q; x <= n && y <= m; x += p, y += q) {
            Long xSum = ask(xCount, x);
            Long ySum = ask(yCount, x);
            (result += MOD - 2 * (xSum * y - ySum * x) % MOD * (n - x + 1) * (m - y + 1) % MOD) %= MOD;
            add(xCount, n, x, x);
            add(yCount, n, x, y);
        }
        (result += dfs(n, m, q, p, q2, p2)) %= MOD;
        return result;
    } else {
        return 0;
    }
}

Long countConvexQuadrangle(Long n, Long m)
{
    Long result = 0;
    for (Long i = 1; i <= n; ++ i) {
        for (Long j = 1; j <= m; ++ j) {
            Long copies = (n - i + 1) * (m - j + 1);

            DEBUG(0, (result += (i - 1) * (i - 1) * (j - 1) * (j - 1) % MOD * copies) %= MOD);

            DEBUG(21, (result += 2 * ((i - 1) * (i - 2) / 2 + (j - 1) * (j - 2) / 2) * copies) %= MOD);
            Long triangle = countPointInsideTriangle(i, j) + i + j - 2;
            DEBUG(22, (result += 2 * triangle * triangle % MOD * copies) %= MOD);
            DEBUG(22, (result += 2 * (triangle + 1) * triangle % MOD * copies) %= MOD);
            DEBUG(22, (result += 2 * (triangle + 1) * (gcd[i][j] + 2) % MOD * copies % MOD) %= MOD);

            DEBUG(3, (result += 4 * triangle * copies) %= MOD);

            DEBUG(4, (result += 1 * copies) %= MOD);
        }
    }
    for (Long i = 1; i <= n; ++ i) {
        for (Long j = 1; j < m; ++ j) {
            Long triangle = i - 1 + countPointInsideTriangle(i, j);
            DEBUG(1, (result += 4 * triangle * (i - 1) * (n - i + 1) % MOD * (m - j) * (m - j + 1) / 2) %= MOD);
            DEBUG(21, (result += 4 * triangle * (n - i + 1) % MOD * (m - j) * (m - j + 1) / 2) %= MOD);
        }
    }
    for (Long j = 1; j <= m; ++ j) {
        for (Long i = 1; i < n; ++ i) {
            Long triangle = j - 1 + countPointInsideTriangle(i, j);
            DEBUG(1, (result += 4 * triangle * (j - 1) * (m - j + 1) % MOD * (n - i) * (n - i + 1) / 2) %= MOD);
            DEBUG(21, (result += 4 * triangle * (m - j + 1) % MOD * (n - i) * (n - i + 1) / 2) %= MOD);
        }
    }
    {
        for (Long i = 1; i < n; ++ i) {
            for (Long j = 1; j < m; ++ j) {
                Long copies = 4 * ((n - i) * (n - i + 1) / 2) * ((m - j) * (m - j + 1) / 2) % MOD;
                Long triangle = countPointInsideTriangle(i, j) + i + j - 2;
                DEBUG(1, (result += triangle * copies) %= MOD);
            }
        }
    }
    {
        static Long sum[2][M + 1];
        memset(sum, 0, sizeof(sum));
        for (int i = 0; i <= n; ++ i) {
            for (int j = !i; j <= m; ++ j) {
                Long copies = (n - i + 1) * (m - j + 1);
                sum[i & 1][j] = (sum[i + 1 & 1][j] + gcd[i][j]) % MOD;
                if (j) {
                    (sum[i & 1][j] += sum[i & 1][j - 1] + MOD - sum[i + 1 & 1][j - 1]) %= MOD;
                }
                if (i && j) {
                    Long line = gcd[i][j];
                    DEBUG(22, (result += 2 * (MOD - sum[i & 1][j] + (line + 1) * line / 2) * copies) %= MOD);
                }
            }
        }
    }
    memset(xCount, 0, sizeof(xCount));
    memset(yCount, 0, sizeof(yCount));
    for (int i = 1; i <= n; ++ i) {
        add(xCount, n, i, i);
    }
    DEBUG(22, (result += dfs(n, m, 0, 1, 1, 0)) %= MOD);
    return result;
}

Long solve(Long n, Long m)
{
    if ((n + 1) * (m + 1) < 4) {
        return 0;
    } else {
        return (3 * countQuadrangle(n, m) % MOD + MOD - 2 * countConvexQuadrangle(n, m) % MOD) % MOD;
    }
}

int main()
{
    memset(gcd, 0, sizeof(gcd));
    for (int i = 0; i <= N; ++ i) {
        for (int j = 0; j <= M; ++ j) {
            gcd[i][j] = [&]() -> short {
                if (i && j) {
                    return i < j ? gcd[i][j - i] : gcd[i - j][j];
                } else {
                    return i | j;
                }
            }();
        }
    }
    std::cout << solve(2, 2) << std::endl;
    std::cout << solve(3, 7) << std::endl;
    std::cout << solve(12, 3) << std::endl;
    std::cout << solve(123, 45) << std::endl;
    std::cout << solve(12345, 6789) << std::endl;
    return 0;
}
