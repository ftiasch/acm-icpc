#include <cstdio>
#include <cstring>
#include <queue>

const int N = 200;

int pred[N + 1], bound[N + 1], answer[N + 1];
std::vector<int> states;

void search(int target, int count)
{
    if (count + 1 >= answer[target]) {
        return;
    }
    for (int i = 0; i < (int)(states.size()); ++ i) {
        for (int j = (int)(states.size()) - 1; j >= i; -- j) {
            if (states[i] + states[j] == target) {
                answer[target] = std::min(answer[target], count + 1);
                return;
            }
        }
    }
    for (int i = 0; i < (int)(states.size()); ++ i) {
        for (int j = (int)(states.size()) - 1; j >= i; -- j) {
            if (states[i] + states[j] <= states.back()) {
                break;
            }
            states.push_back(states[i] + states[j]);
            search(target, count + 1);
            states.pop_back();
        }
    }
}

int main()
{
    pred[1] = 0;
    bound[1] = 0;
    {
        std::queue<int> queue;
        queue.push(1);
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            for (int v = u; v; v = pred[v]) {
                int w = u + v;
                if (w <= N && !bound[w]) {
                    pred[w] = u;
                    bound[w] = bound[u] + 1;
                    queue.push(w);
                }
            }
        }
    }
    memcpy(answer, bound, sizeof(bound));
    states = {1};
    int result = 0;
    for (int target = N; target > 1; -- target) {
        printf("%d...\n", target);
        search(target, 0);
        result += answer[target];
    }
    printf("%d\n", result);
}
