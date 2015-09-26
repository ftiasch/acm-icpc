#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>

const int N = 100000;
const int C = 26;

char s[N + 1];

struct State {
    int    length;
    int    first;
    State* parent;
    State* go[C];

    State* extend(State*, int);
};

int    state_count;
State  states[N << 1];

State* new_state(int length) {
    State* state = states + (state_count ++);
    state->length = length;
    state->first  = -1;
    state->parent = NULL;
    memset(state->go, 0, sizeof(state->go));
    return state;
}

State* State::extend(State* start, int token) {
    State *p = this;
    State *np = new_state(length + 1);
    while (p && !p->go[token]) {
        p->go[token] = np;
        p = p->parent;
    }
    if (!p) {
        np->parent = start;
    } else {
        State *q = p->go[token];
        if (p->length + 1 == q->length) {
            np->parent = q;
        } else {
            State *nq = new_state(p->length + 1);
            memcpy(nq->go, q->go, sizeof(q->go));
            nq->parent = q->parent;
            np->parent = q->parent = nq;
            while (p && p->go[token] == q) {
                p->go[token] = nq;
                p = p->parent;
            }
        }
    }
    return np;
}

int       cost[C], a, b, match[N + 1], queue[N + 1];
State*    end[N + 1];
long long minimum[N + 1];

int main() {
    int T;
    scanf("%d", &T);
    for (int t = 1; t <= T; ++ t) {
        scanf("%s", s);
        for (int i = 0; i < C; ++ i) {
            scanf("%d", cost + i);
        }
        scanf("%d%d", &a, &b);
        int n = strlen(s);
        state_count = 0;
        end[0] = new_state(0);
        for (int i = 0; i < n; ++ i) {
            end[i + 1] = end[i]->extend(end[0], s[i]- 'a');
        }
        for (int i = 0; i < n; ++ i) {
            for (State* p = end[i + 1]; p && !~p->first; p = p->parent) {
                p->first = i;
            }
        }
        State* p = end[0];
        for (int i = 0, l = 0; i < n; ++ i) {
            int c = s[i] - 'a';
            while (!p->go[c]) {
                p = p->parent;
                l = std::min(l, p->length);
            }
            p = p->go[c];
            l ++;
            while (p->first > i - l) {
                l --;
                if (p->parent->length == l) {
                    p = p->parent;
                }
            }
            match[i + 1] = l;
        }
        minimum[0] = 0;
        int head = 0;
        int tail = 0;
        queue[tail ++] = 0;
        for (int i = 1; i <= n; ++ i) {
            minimum[i] = minimum[i - 1] + cost[s[i - 1] - 'a'];
            while (head < tail && queue[head] < i - match[i]) {
                head ++;
            }
            if (head < tail) {
                int j = queue[head];
                minimum[i] = std::min(minimum[i], minimum[j] + (long long)a * (i - j) + b + b);
            }
#define VALUE(i) (minimum[i] - (long long)a * (i))
            while (head < tail && VALUE(queue[tail - 1]) > VALUE(i)) {
                tail --;
            }
#undef VALUE
            queue[tail ++] = i;
        }
        std::cout << "Case #" << t << ": " << minimum[n] << std::endl;;
    }
    return 0;
}
