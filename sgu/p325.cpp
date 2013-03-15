// SGU 325 -- Palindrome
#include <cstdio>
#include <cstring>
#include <deque>
#include <iostream>

const int N = 1000000;

int n;
char string[N + 1];
std::deque <int> letters[26];

int count[N];
bool present[N];

void modify(int k, int v) {
    for(; k < n; k += ~k & k + 1) {
        count[k] += v;
    }
}

int query(int k) {
    int ret = 0;
    for (; k >= 0; k -= ~k & k + 1) {
        ret += count[k];
    }
    return ret;
}

void remove(int i) {
    present[i] = false;
    modify(i, -1);
}

int main() {
    scanf("%s", string);
    n = strlen(string);
    for (int i = 0; i < n; ++ i) {
        letters[(int)(string[i] -= 'A')].push_back(i);
    }
    int odd_count = 0;
    for (int i = 0; i < 26; ++ i) {
        odd_count += letters[i].size() & 1;
    }
    if (odd_count != (n & 1)) {
        puts("-1");
        return 0;
    }
    memset(present, true, sizeof(present));
    for (int i = 0; i < n; ++ i) {
        modify(i, 1);
    }
    long long answer = 0;
    for (int l = 0, r = n - 1; l < r; ) {
        if (!present[l]) {
            l ++;
        } else if (!present[r]) {
            r --;
        } else {
            int left_cost = query(n - 1) - query(letters[(int)string[l]].back());
            int right_cost = query(letters[(int)string[r]].front()) - 1;
            answer += std::min(left_cost, right_cost);
            int token = left_cost < right_cost ? string[l] : string[r];
            remove(letters[token].front());
            remove(letters[token].back());
            letters[token].pop_front();
            letters[token].pop_back();
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
