#include <cstdio>
#include <cstring>
#include <chrono>
#include <iostream>
#include <map>
#include <thread>
#include <utility>
#include <vector>

const int M = 2816;
const int MOD = (int)1e9 + 7;

struct Matrix
{
    const int* operator[](int i) const
    {
        return mat[i];
    }

    int* operator[](int i)
    {
        return mat[i];
    }

    int mat[M][M];
};

static int CORES = 4;

void multiply(Matrix& a, Matrix& b, Matrix& c_)
{
    // static int buffer[M];
    static Matrix c;
    std::vector<std::thread> threads;
    for (int t = 0; t < CORES; ++ t) {
        threads.emplace_back([t, &a, &b]()
        {
            int buffer[M];
            for (int j = t; j < M; j += CORES) {
                for (int k = 0; k < M; ++ k) {
                    buffer[k] = b[k][j];
                }
                for (int i = 0; i < M; ++ i) {
                    unsigned long long tmp = 0;
                    for (int k = 0; k < M; ++ k) {
                        tmp += 1ULL * a[i][k] * buffer[k];
                        if (!(k & 15)) {
                            tmp %= MOD;
                        }
                    }
                    c[i][j] = tmp % MOD;
                }
            }
        });
    }
    for (int t = 0; t < CORES; ++ t) {
        threads.at(t).join();
    }
    memcpy(&c_, &c, sizeof(c));
}

void power(const Matrix& a_, long long n, Matrix& result)
{
    static Matrix a;
    memset(&result, 0, sizeof(result));
    for (int i = 0; i < M; ++ i) {
        result[i][i] = 1;
    }
    memcpy(&a, &a_, sizeof(a));
    auto tp = std::chrono::system_clock::now();
    while (n) {
        if (n & 1) {
            multiply(a, result, result);
        }
        multiply(a, a, a);
        auto now = std::chrono::system_clock::now();
        std::cerr << n << " " << std::chrono::duration_cast<std::chrono::milliseconds>(now - tp).count() << std::endl;
        tp = now;
        n >>= 1;
    }
}

Matrix transfer, result;

int main()
{
    using State = std::pair<int, int>;
    std::vector<State> states;
    std::map<State, int> states_map;
    for (int mask = 0; mask < 1 << 9; ++ mask) {
        for (int count = 0; count < 10; ++ count) {
            if (!count || (mask >> count - 1 & 1)) {
                states_map[{mask, count}] = states.size();
                states.emplace_back(mask, count);
            }
        }
    }
    for (int i = 0; i < M; ++ i) {
        auto&& mask = states.at(i).first;
        auto&& count = states.at(i).second;
        transfer[i][i] ++;
        for (int digit = 1; digit < 10; ++ digit) {
            auto new_mask = ((mask << digit) & 511) | 1 << digit - 1;
            auto new_count = count + digit;
            if (new_count <= 10) {
                if (mask >> 9 - digit & 1) {
                    new_count = 0;
                }
                transfer[i][states_map[{new_mask, new_count}]] ++;
            }
        }
    }
    // power(transfer, 5, result);
    power(transfer, 1000000000000000000LL, result);
    int answer = MOD - 1;
    for (int i = 0; i < M; ++ i) {
        if (!states[i].second) {
            (answer += result[0][i]) %= MOD;
        }
    }
    printf("%d\n", answer);
}
