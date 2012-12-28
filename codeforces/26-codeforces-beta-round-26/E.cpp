// Codeforces Beta Round #26
// Problem E -- Multithreading
#include <cstdio>
#include <cstring>
#include <vector>
#include <numeric>
#include <algorithm>

const int N = 100;

int n, m, a[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    std::vector <int> answer;
    if (m >= 1) {
        if (n == 1) {
            if (a[0] == m) {
                while (a[0] --) {
                    answer.push_back(0);
                    answer.push_back(0);
                }
            }
        } else if (m == 1) {
            int id = std::min_element(a, a + n) - a;
            if (a[id] == 1) {
                answer.push_back(id);
                for (int i = 0; i < n; ++ i) {
                    if (i != id) {
                        while (a[i] --) {
                            answer.push_back(i);
                            answer.push_back(i);
                        }
                    }
                }
                answer.push_back(id);
            }
        } else {
            int exceed = std::accumulate(a, a + n, 0) - m;
            if (exceed >= 0) {
                int id = std::min_element(a, a + n) - a;
                if (a[id] > m) {
                    exceed = std::accumulate(a, a + n, 0) - a[id] - 1;
                }
                answer.push_back(id);
                for (int i = 0; i < n; ++ i) {
                    if (i != id) {
                        while (a[i] && exceed) {
                            a[i] --;
                            exceed --;
                            answer.push_back(i);
                            answer.push_back(i);
                        }
                    }
                }
                answer.push_back(id);
                a[id] --;
                if (a[id] > m - 1) {
                    int jd = (id + 1) % n;
                    while (!a[jd]) {
                        jd = (jd + 1) % n;
                    }
                    a[jd] --;
                    answer.push_back(jd);
                    while (a[id] > m - 2) {
                        a[id] --;
                        answer.push_back(id);
                        answer.push_back(id);
                    }
                    answer.push_back(jd);
                }
                for (int i = 0; i < n; ++ i) {
                    while (a[i] --) {
                        answer.push_back(i);
                        answer.push_back(i);
                    }
                }
            }
        }
    }
    if (answer.empty()) {
        puts("No");
    } else {
        puts("Yes");
        for (int i = 0; i < (int)answer.size(); ++ i) {
            printf("%d%c", 1 + answer[i], i == (int)answer.size() - 1 ? '\n' : ' ');
        }
    }
    return 0;
}
