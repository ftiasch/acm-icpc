// Codeforces Beta Round #29 
// Problem C -- Mail Stamps
#include <cstdio>
#include <cstring>
#include <map>
#include <set>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)
int n;
std::set <int> visit;
std::map <int, std::vector <int> > graph;


int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    int begin = 0;
    foreach (iter, graph) {
        if (iter->second.size() == 1) {
            begin = iter->first;
        }
    }
    visit.insert(begin);
    printf("%d", begin);
    while (true) {
        bool found = false;
        foreach (iter, graph[begin]) {
            if (!visit.count(*iter)) {
                found = true;
                visit.insert(*iter);
                begin = *iter;
                printf(" %d", begin);
                break;
            }
        }
        if (!found) {
            break;
        }
    }
    puts("");
    return 0;
}
