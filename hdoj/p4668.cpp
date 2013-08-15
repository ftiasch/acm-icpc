// HDOJ 4668 -- Finding string
#include <cctype>
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <string>
#include <iostream>
#include <algorithm>

#define SIZE(v) ((int)(v).size())

typedef std::string String;

const int N = 500;

char buffer[N + 1];

int m, fail[N];
String pattern;

int count(const String &text) {
    int ret = 0;
    int n = SIZE(text);
    for (int i = 0, p = -1; i < n; ++ i) {
        if (p + 1 < m && pattern[p + 1] == text[i]) {
            p ++;
        } else {
            while (p != -1) {
                p = fail[p];
                if (pattern[p + 1] == text[i]) {
                    p ++;
                    break;
                }
            }
        }
        ret += p == m - 1;
    }
    return ret;
}

std::pair <String, long long> repeat(const String &seed, int times) {
    String s;
    int used = 0;
    for (used = 0; used < times && SIZE(s) < 2 * SIZE(pattern); ++ used) {
        s += seed;
    }
    long long ret = count(s);
    if (used < times) {
        int extra = count(s + seed) - ret;
        ret  += (long long)extra * (times - used);
    }
    return std::make_pair(s, ret);
}

int main() {
//freopen("B.in", "r", stdin);
    while (scanf("%s", buffer) == 1) {
        std::vector <std::pair <String, int> > fragments;
        for (int i = 0; buffer[i]; ) {
            if (buffer[i] == '[') {
                int j = i;
                while (buffer[j] != ']') {
                    j ++;
                }
                buffer[j] = 0;
                fragments.push_back(std::make_pair(buffer + (i + 1), 0));
                sscanf(buffer + (j + 1), "%d", &fragments.back().second);
                i = j + 1;
                while (isdigit(buffer[i])) {
                    i ++;
                }
            } else {
                fragments.push_back(std::make_pair(String(1, buffer[i]), 1));
                i ++;
            }
        }
        scanf("%s", buffer);
        pattern = buffer;
        m = SIZE(pattern);
        std::fill(fail, fail + m, -1);
        for (int i = 0; i + 1 < m; ++ i) {
            int j = i;
            while (j != -1 && fail[i + 1] == -1) {
                j = fail[j];
                if (pattern[j + 1] == pattern[i + 1]) {
                    fail[i + 1] = j + 1;
                }
            }
        }
        String s;
        long long answer = 0;
        for (int i = 0; i < SIZE(fragments); ++ i) {
//printf("(%s, %d)\n", fragments[i].first.c_str(), fragments[i].second);
            std::pair <String, long long> ret = repeat(fragments[i].first, fragments[i].second);
            s += ret.first;
            answer += ret.second - count(ret.first);
        }
        answer += count(s);
        std::cout << answer << std::endl;
    }
    return 0;
}
