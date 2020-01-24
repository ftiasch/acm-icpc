#include <cstdio>
#include <cstring>

// x^7 - 4*x^6 + 6*x^5 - 6*x^4 + 6*x^3 - 6*x^2 + 5*x - 2
const int C[] = {2, -5, 6, -6, 6, -6, 4};
const int A[] = {42, 138, 362, 842, 1832, 3844, 7906};

template<int MOD>
struct Solver
{
    void go(int& p, int* a)
    {
        long long r = 0;
        for (int i = 0; i < 7; ++ i) {
            r += C[i] * a[p + i & 7];
        }
        r %= MOD;
        if (r < 0) {
            r += MOD;
        }
        a[p + 7 & 7] = r;
        p ++;
        p &= 7;
    }

    bool cmp(int p, int q)
    {
        for (int i = 0; i < 7; ++ i) {
            if (a[p + i & 7] != b[q + i & 7]) {
                return false;
            }
        }
        return true;
    }

    Solver()
    {
        int p = 0;
        int q = 0;
        memcpy(a, A, sizeof(A));
        memcpy(b, A, sizeof(A));
        int cyc = 0;
        while (cyc == 0 || !cmp(p, q)) {
            if (a[p] == 0) {
                printf("%d ", cyc);
            }
            cyc ++;
            go(p, a);
            go(q, b); go(q, b);
        }
        printf("\n%d\n", cyc);
    }

    int a[8], b[8];
};



int main()
{
    Solver<9>();
    Solver<1997>();
    Solver<4877>();
}
