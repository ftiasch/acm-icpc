#include <cassert>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

const int N = 1000000;

int m, min_div[N + 1];
std::vector <int> primes;

int delta[N];
long long count[N], new_count[N];

bool factor(int n)
{
    bool result = false;
    while (n > 1) {
        int id = min_div[n];
        result |= count[id] == 0;
        count[id] ++;
        n /= primes[id];
    }
    return result;
}

int main()
{
    memset(min_div, -1, sizeof(min_div));
    for (int p = 2; p <= N; ++ p) {
        if (min_div[p] == -1) {
            min_div[p] = primes.size();
            primes.push_back(p);
        }
        for (int i = 0; i < (int)primes.size() && p * primes[i] <= N; ++ i) {
            min_div[p * primes[i]] = i;
            if (p % primes[i] == 0) {
                break;
            }
        }
    }
    std::cin >> m;
    memset(count, 0, sizeof(count));
    for (int i = 0; i < m; ++ i) {
        static int p;
        static long long a;
        std::cin >> p >> a;
        count[std::lower_bound(primes.begin(), primes.end(), p) - primes.begin()] += a;
    }
    long long k;
    std::cin >> k;
    m = primes.size();
    while (k > 0) {
        k --;
        memcpy(new_count, count, sizeof(count));
        for (int i = 0; i < m; ++ i) {
            if (count[i]) {
                new_count[i] --;
                for (int x = primes[i] - 1; x > 1; x /= primes[min_div[x]]) {
                    new_count[min_div[x]] ++;
                }
            }
        }
        bool changed = false;
        for (int i = 0; i < m; ++ i) {
            if (count[i] == 0 && new_count[i] > 0) {
                changed = true;
            }
        }
        memcpy(count, new_count, sizeof(count));
        if (!changed) {
            break;
        }
    }
    memset(delta, 0, sizeof(delta));
    for (int i = 0; i < m; ++ i) {
        if (count[i] > 0) {
            delta[i] --;
            for (int x = primes[i] - 1; x > 1; x /= primes[min_div[x]]) {
                delta[min_div[x]] ++;
            }
        }
    }
    std::priority_queue <std::pair <long long, int> > queue;
    for (int i = 0; i < m; ++ i) {
        if (delta[i] < 0) {
            assert(delta[i] == -1);
            queue.push(std::make_pair(-count[i], i));
        }
    }
    while (!queue.empty()) {
        int i = queue.top().second;
        queue.pop();
        if (count[i] >= k) {
            break;
        }
        long long need = count[i];
        count[i] -= need;
        delta[i] ++;
        for (int x = primes[i] - 1; x > 1; x /= primes[min_div[x]]) {
            int k = min_div[x];
            count[k] += need;
            delta[k] --;
            if (delta[k] < 0) {
                queue.push(std::make_pair(-count[k], k));
            }
        }
    }
    int number = 0;
    for (int i = 0; i < m; ++ i) {
        count[i] += delta[i] * k;
        number += count[i] > 0;
    }
    std::cout << number << std::endl;
    for (int i = 0; i < m; ++ i) {
        if (count[i] > 0) {
            std::cout << primes[i] << " " << count[i] << std::endl;
        }
    }
    return 0;
}
