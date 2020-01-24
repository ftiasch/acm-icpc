#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <queue>
#include <vector>

const auto MOD = (int)1e8;

using State = std::vector<int>;

#define ALL(v) (v).begin(), (v).end()

void update(std::map<State, int>& ways, State state, int value)
{
    std::map<int, int> name;
    for (auto& x : state) {
        if (x) {
            if (!name.count(x)) {
                name.emplace(x, 1 + name.size());
            }
            x = name.at(x);
        }
    }
    auto& ref = ways[state];
    ref += value;
    if (ref >= MOD) {
        ref -= MOD;
    }
}

void print(const State& state)
{
    for (auto&& x : state) {
        printf("%d", x);
    }
}

struct Matrix
{
    explicit Matrix(std::vector<std::vector<int>> mat) : mat(mat) {}

    explicit Matrix(int n, bool identity = false)
    : mat(n, std::vector<int>(n))
    {
        if (identity) {
            for (auto i = 0; i < n; ++ i) {
                mat.at(i).at(i) = 1;
            }
        }
    }

    int size() const
    {
        return mat.size();
    }

    std::vector<int>& operator[](int i)
    {
        return mat[i];
    }

    const std::vector<int>& operator[](int i) const
    {
        return mat[i];
    }

    std::vector<std::vector<int>> mat;
};

Matrix operator * (const Matrix& a, const Matrix& b)
{
    auto n = a.size();
    assert(a.size() == b.size());
    Matrix c(n);
    for (auto i = 0; i < n; ++ i) {
        for (auto j = 0; j < n; ++ j) {
            auto tmp = 0;
            for (auto k = 0; k < n; ++ k) {
                tmp += 1LL * a[i][k] * b[k][j] % MOD;
                if (tmp >= MOD) {
                    tmp -= MOD;
                }
            }
            c[i][j] = tmp;
        }
    }
    return c;
}

Matrix power(Matrix a, long long n)
{
    Matrix result(a.size(), true);
    while (n) {
        if (n & 1) {
            result = result * a;
        }
        n >>= 1;
        a = a * a;
    }
    return result;
}

int solve(long long n)
{
    std::vector<State> states;
    std::vector<std::vector<int>> transition;

    auto find = [&](const State& state) -> int
    {
        auto iterator = std::find(ALL(states), state);
        if (iterator != states.end()) {
            return iterator - states.begin();
        }
        states.push_back(state);
        int n = states.size();
        for (auto& row : transition) {
            row.push_back(0);
        }
        transition.emplace_back(n);
        return n - 1;
    };

    find(State { 1, 0, 0, 1 });
    for (auto head = 0; head < static_cast<int>(states.size()); ++ head) {
        std::map<State, int> ways;
        {
            auto state = states.at(head);
            if (!*std::max_element(ALL(state))) {
                continue;
            }
            state.insert(state.begin(), 0);
            ways.emplace(state, 1);
        }
        for (auto i = 0; i < 4; ++ i) {
            std::map<State, int> new_ways;
            for (auto&& iterator : ways) {
                auto state = iterator.first;
                auto count = !!state.at(i) + !!state.at(i + 1);
                if (count == 0) {
                    state.at(i) = state.at(i + 1) = 5;
                    update(new_ways, state, iterator.second);
                } else if (count == 1) {
                    auto value = state.at(i) | state.at(i + 1);
                    state.at(i) = 0, state.at(i + 1) = value;
                    update(new_ways, state, iterator.second);
                    state.at(i) = value, state.at(i + 1) = 0;
                    update(new_ways, state, iterator.second);
                } else if (state.at(i) != state.at(i + 1)) {
                    auto ov = state.at(i);
                    auto nv = state.at(i + 1);
                    std::replace(ALL(state), ov, nv);
                    state.at(i) = state.at(i + 1) = 0;
                    update(new_ways, state, iterator.second);
                }
            }
            ways.swap(new_ways);
            // for (auto&& iterator : ways) {
            //     auto state = iterator.first;
            //     print(state);
            //     printf(" %d,", iterator.second);
            // }
            // puts("");
        }
        // puts("---");
        for (auto&& iterator : ways) {
            auto state = iterator.first;
            if (state.back()) {
                continue;
            }
            state.pop_back();
            auto id = find(state);
            transition.at(head).at(id) = iterator.second;
        }
    }
    Matrix a(transition.size());
    Matrix t(transition);
    a[0][0] = 1;
    a = a * power(t, n);
    return a[0][find({0, 0, 1, 1})];
}

int main()
{
    printf("%d\n", solve(10));
    printf("%d\n", solve(1000000000000LL));
}
