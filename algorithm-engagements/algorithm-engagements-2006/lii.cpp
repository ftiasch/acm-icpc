// Algorithm Engagements 2006 Round 6 -- Professor Laugh's Numbers
#include <cstdio>
#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

typedef unsigned long long Long;

const Long MAX_LONG = 18446744073709551615ULL;

vector <Long> factor(Long n) {
    vector <Long> result;
    for (Long i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            result.push_back(i);
        }
        while (n % i == 0) {
            n /= i;
        }
    }
    if (n > 1) {
        result.push_back(n);
    }
    return result;
}

Long pow_mod(Long a, Long n, Long m) {
    Long result = 1 % m;
    while (n > 0) {
        if ((n & 1) == 1) {
            result = (result * a) % m;
        }
        a = (a * a) % m;
        n >>= 1;
    }
    return result;
}

Long find_primitive_root(Long p) {
    vector <Long> prime_divisors = factor(p - 1);
    Long g = 2;
    while (true) {
        bool found = false;
        for (vector <Long> :: iterator iter = prime_divisors.begin(); \
                iter != prime_divisors.end() && !found; ++ iter) {
            Long o = (p - 1) / *iter;
            if (pow_mod(g, o, p) == 1LL) {
                found = true;
            }
        }
        if (!found) {
            break;
        }
        g ++;
    }
    return g;
}

template <class Key, class Value> 
struct HashMap {
    static const int SIZE = 100000;
    static const int MAGIC = 100003;

    int size;
    int hash[MAGIC], next_element[SIZE];
    Key keys[SIZE];
    Value values[SIZE];

    HashMap(): size(0) {
        memset(hash, -1, sizeof(hash));
    }

    void add(Key key, Value value) {
        for (int iter = hash[key % MAGIC]; iter != -1; iter = next_element[iter]) {
            if (keys[iter] == key) {
                return;
            }
        }
        keys[size] = key;
        values[size] = value;
        next_element[size] = hash[key % MAGIC];
        hash[key % MAGIC] = size ++;
    }

    Value get(Key key) {
        for (int iter = hash[key % MAGIC]; iter != -1; iter = next_element[iter]) {
            if (keys[iter] == key) {
                return values[iter];
            }
        }
        return MAX_LONG;
    }
};

struct LogarithmSolver {
    Long p, g, block_size, giant_step, baby_step;
    HashMap <Long, Long> giant_steps;

    LogarithmSolver(Long m): p(m) {
        block_size = 0;
        while (block_size * block_size <= p - 1) {
            block_size ++;
        }
        g = find_primitive_root(p);
        baby_step = pow_mod(g, p - 2, p);
        giant_step = pow_mod(g, block_size, p);
        Long current = 1 % p;
        for (Long i = 0; i < block_size; ++ i) {
            giant_steps.add(current, block_size * i);
            current = (current * giant_step) % p;
        }
    }

    Long log(Long a) {
        Long current = a % p;
        for (Long i = 0; i < block_size; ++ i) {
            Long result = giant_steps.get(current);
            if (result != MAX_LONG) {
                return result + i;
            }
            current = (current * baby_step) % p;
        }
        throw "Fail";
    }
};

int main() {
    Long p, e;
    cin >> p >> e;

    LogarithmSolver *solver = new LogarithmSolver(p);

    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        Long a;
        cin >> a;
//printf("= %lld\n", solver->log(a));
        cout << (solver->log(a) % __gcd(e, p - 1) == 0? "TAK": "NIE") << endl;
    }
    return 0;
}
