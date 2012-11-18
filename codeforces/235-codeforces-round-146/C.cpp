// Codeforces Round #146
// Problem C -- Cyclical Quest
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int C = 26;
const int N = 1000000;

int n, m, fail[N];
char text[N + 1], pattern[N + 1];

struct State {
    static int size;
    int id;

    bool visit;
    int length, count;
    State *parent;
    State *go[C];

    State(int length) : visit(false), length(length), count(0), parent(NULL) {
        id = size ++;
        memset(go, 0, sizeof(go));
    }

    State* extend(State *start, int token) {
        State *p = this;
        State *np = new State(length + 1);
        while (p != NULL && p->go[token] == NULL) {
            p->go[token] = np;
            p = p->parent;
        }
        if (p == NULL) {
            np->parent = start;
        } else {
            State *q = p->go[token];
            if (p->length + 1 == q->length) {
                np->parent = q;
            } else {
                State *nq = new State(p->length + 1);
                memcpy(nq->go, q->go, sizeof(q->go));
                nq->parent = q->parent;
                np->parent = q->parent = nq;
                while (p != NULL && p->go[token] == q) {
                    p->go[token] = nq;
                    p = p->parent;
                }
            }
        }
        return np;
    }
};

int State::size = 0;

void dfs(State *state) {
    if (!state->visit) {
        state->visit = true;
        for (int i = 0; i < C; ++ i) {
            if (state->go[i] != NULL) {
                dfs(state->go[i]);
                state->count += state->go[i]->count;
            }
        }
    }
}

int main() {
    scanf("%s", text);
    n = strlen(text);

    State *start = new State(0);
    State *end = start;
    for (int i = 0; i < n; ++ i) {
        end = end->extend(start, text[i] - 'a');
    }
    while (end != start) {
        end->count ++;
        end = end->parent;
    }
    dfs(start);

    int q;
    scanf("%d", &q);
    while (q --) {
        scanf("%s", pattern);
        m = strlen(pattern);

        fail[0] = -1;
        for (int i = 1; i < m; ++ i) {
            fail[i] = -1;
            for (int j = i - 1; j != -1 && fail[i] == -1; ) {
                j = fail[j];
                if (pattern[j + 1] == pattern[i]) {
                    fail[i] = j + 1;
                }
            }
        }
        int cycle = m - 1 - fail[m - 1];

        int answer = 0;
        State *p = start;
        int match = 0;
        for (int i = 0; i + 1 < m << 1; ++ i) {
            int token = pattern[i % m] - 'a';
            while (p->go[token] == NULL && p != start) {
                p = p->parent;
                match = min(match, p->length);
            }
            if (p->go[token] != NULL) {
                match ++;
                p = p->go[token];
            }
            match = min(match, m);
            while (p->parent != NULL && p->parent->length >= match) {
                p = p->parent;
            }
            if (match >= m) {
                answer += p->count;
            }
        }
        if (m % cycle == 0) {
            answer /= (m / cycle);
        }
        printf("%d\n", answer);
    }
    return 0;
}
