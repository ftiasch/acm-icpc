#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

const int M = 60;

double f(double p)
{
    return .5 + .5 * p;
}

int main()
{
    std::vector<double> leaf_probability(M);
    for (int i = 1; i < M; ++ i) {
        leaf_probability[i] = 1.;
    }
    int q;
    scanf("%d", &q);
    std::vector<int> parent;
    std::vector<std::vector<double>> probability;
    parent.push_back(-1);
    probability.push_back(leaf_probability);
    while (q --) {
        int t, v;
        scanf("%d%d", &t, &v);
        v --;
        if (t == 1) {
            int u = parent.size();
            parent.push_back(v);
            probability.push_back(leaf_probability);
            std::vector<int> changes;
            changes.push_back(u);
            for (int i = 1; i < M && changes[i - 1]; ++ i) {
                changes.push_back(parent[changes[i - 1]]);
            }
            for (int i = (int)changes.size() - 1; i >= 2; -- i) {
                probability[changes[i]][i] /= f(probability[changes[i - 1]][i - 1]);
            }
            for (int i = 1; i < (int)changes.size(); ++ i) {
                probability[changes[i]][i] *= f(probability[changes[i - 1]][i - 1]);
            }
        } else {
            double result = 0.;
            for (int i = 1; i < M; ++ i) {
                result += (1. - probability[v][i]);
            }
            printf("%.9f\n", result);
        }
    }
}
