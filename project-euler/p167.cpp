#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>

using LL = long long;

/*
 * NOTE
 *
 * [Paper] On the Regularity of Certain 1-Additive Sequences
 *
 * ulam(2, 2 * n + 1) is regular for n >= 2
 * i.e. ulam.diff() is periodic
 *
 * Conjecture: a_1 = 2, a_{n + 4} = 4 * n + 4 are the only two evens
 *
 * (count_{2 * s + 1}, count_{2 * s + 2}, ..., count_{2 * s + 2 * n + 2}) \in F_2^{2 n + 2}
 * should be periodic
 */

auto brute(int v, int k)
{
    v = 2 * v + 1;
    std::vector<LL> sequence { 2, v };
    auto next = v;
    while ((int)sequence.size() < k) {
        next ++;
        auto count = 0;
        for (int i = 0, j = (int)sequence.size() - 1; i < j && count < 2; ++ i) {
            while (i < j && sequence[i] + sequence[j] > next) {
                j --;
            }
            count += i < j && sequence[i] + sequence[j] == next;
        }
        if (count == 1) {
            sequence.push_back(next);
        }
    }
    return sequence.back();
}

auto solve(int v, LL k)
{
    v = 2 * v + 1;
    std::vector<LL> sequence { 2, v };
    auto next = (LL)v;
    while ((int)sequence.size() < (v + 7) / 2) {
        next ++;
        auto count = 0;
        for (int i = 0, j = (int)sequence.size() - 1; i < j && count < 2; ++ i) {
            while (i < j && sequence[i] + sequence[j] > next) {
                j --;
            }
            count += i < j && sequence[i] + sequence[j] == next;
        }
        if (count == 1) {
            sequence.push_back(next);
        }
    }
    auto state = 0LL;
    for (auto n = 1; n < (int)sequence.size() - 1; ++ n) {
        state |= 1LL << (next - sequence[n] >> 1);
    }
    auto init_state = state;
    auto m = 0;
    // std::map<ULL, int> states;
    // while (!states.count(state)) {
    //     states.emplace(state, m ++);
    std::vector<int> occurs;
    do {
        state <<= 1;
        state |= ((state >> 1) ^ (state >> (next >> 1))) & 1;
        state &= (1 << (next >> 1)) - 1;
        m ++;
        if (state & 1) {
            occurs.push_back(m);
        }
    } while (state != init_state);
    auto need = k - (int)sequence.size();
    auto l = occurs.size();
    next += 2LL * m * ((need - 1) / occurs.size());
    auto rem = need % occurs.size();
    if (!rem) {
        rem += l;
    }
    assert(rem);
    return next + 2 * occurs.at(rem - 1) - 1;
}

int main()
{
    auto result = 0LL;
    for (int n = 2; n <= 10; ++ n) {
        result += solve(n, 100000000000LL);
    }
    printf("%lld\n", result);
}
