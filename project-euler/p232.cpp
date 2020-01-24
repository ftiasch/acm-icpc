#include <algorithm>
#include <cstdio>

const auto N = 100;

double prob[N + 1][N + 1][2];

int main()
{
    for (auto a = 0; a <= N; ++ a) {
        for (auto b = 0; b <= N; ++ b) {
            if (!a) {
                prob[a][b][0] = prob[a][b][1] = 0.;
            } else if (!b) {
                prob[a][b][0] = prob[a][b][1] = 1.;
            } else {
                double result = 0.;
                for (auto t = 1; ; ++ t) {
                    result = std::max(result, (double)(2 * prob[a][std::max(b - (1 << t - 1), 0)][0] + ((1 << t) - 1) * prob[a - 1][b][1]) / ((1 << t) + 1));
                    if (1 << t - 1 > b) {
                        break;
                    }
                }
                prob[a][b][1] = result;
                prob[a][b][0] = .5 * (prob[a - 1][b][1] + prob[a][b][1]);
            }
        }
    }
    printf("%.8f\n", prob[N][N][0]);
}
