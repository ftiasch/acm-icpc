// Codeforces Beta Round #94
// Problem D -- String
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

using std::vector;

const int C = 26;

struct State {
    static vector <State*> states;

    int id, length, size;
    long long ways;
    State* parent;
    State* go[C];
    vector <State*> sons;

    State(int length) : id((int)states.size()), 
                        length(length), 
                        size(0),
                        ways(0),
                        parent(NULL) {
        memset(go, 0, sizeof(go));
        states.push_back(this);
    }

    State* extend(State* start, int token) {
        State *p = this;
        State *np = new State(length + 1);
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
                State *nq = new State(p->length + 1);
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
};

vector <State*> State::states;

const int N = 100000;

int n;
char text[N + 1];

void construct(State* u, int k) {
    if (u->size < k) {
        k -= u->size;
        for (int i = 0; i < C; ++ i) {
            if (u->go[i]) {
                State* v = u->go[i];
                if (v->ways < k) {
                    k -= v->ways;
                } else {
                    putchar('a' + i);
                    construct(v, k);
                    break;
                }
            }
        }
    }
}

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); \
        i != (v).end(); ++ i)

void dfs(State* u) {
    foreach(iter, u->sons) {
        State* v = *iter;
        dfs(v);
        u->size += v->size;
        u->ways += v->ways;
    }
}

int main() {
    scanf("%s", text);
    n = strlen(text);
    State* start = new State(0);
    State* now = start;
    for (int i = 0; i < n; ++ i) {
        now = now->extend(start, text[i] - 'a');
        now->size ++;
        now->ways += n - i;
    }
    foreach (iter, State::states) {
        State* u = *iter;
        if (u->parent) {
            u->parent->sons.push_back(u);
        }
    }
    dfs(start);
    start->size = 0;
    int k;
    scanf("%d", &k);
    if (k > start->ways) {
        puts("No such line.");
    } else {
        construct(start, k);
        puts("");
    }
    return 0;
}
