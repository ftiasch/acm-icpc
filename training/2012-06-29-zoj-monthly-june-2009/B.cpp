// Problem B -- Bussiness Rules Management System
#include <cstdio>
#include <cstring>
#include <map>
#include <string>
#include <queue>
using namespace std;

const int N = 11111;

int level[N];
bool inHeap[N];
string name[N];

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int ruleCount;
        scanf("%d", &ruleCount);
        map <string, int> idMap;
        for (int i = 0; i < ruleCount; ++ i) {
            char buffer[22];
            scanf("%s", buffer);
            name[i] = buffer;
            idMap[name[i]] = i;
            scanf("%d", level + i);
        }
        int commandCount;
        scanf("%d", &commandCount);
        priority_queue <pair <pair <int, int>, int> > heap;
        memset(inHeap, 0, sizeof(false));
        for (int i = 0; i < commandCount; ++ i) {
            char type[2], buffer[22];
            scanf("%s", type);
            if (*type == 'a') {
                scanf("%s", buffer);
                int id = idMap[buffer];
                inHeap[id] = true;
                heap.push(make_pair(make_pair(level[id], i), id));
            } else if (*type == 'd') {
                scanf("%s", buffer);
                int id = idMap[buffer];
                inHeap[id] = false;
            } else if (*type == 'g') {
                while (!heap.empty()) {
                    pair <pair <int, int>, int> ret = heap.top();
                    if (inHeap[ret.second]) {
                        break;
                    }
                    heap.pop();
                }
                if (heap.empty()) {
                    puts("<empty>");
                } else {
                    pair <pair <int, int>, int> ret = heap.top();
                    inHeap[ret.second] = false;
                    heap.pop();
                    printf("%s\n", name[ret.second].c_str());
                }
            }
        }
    }
    return 0;
}
