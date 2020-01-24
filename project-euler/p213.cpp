#include <cassert>
#include <cstdio>
#include <cstring>

const int N = 30;

const int DELTA_X[] = {-1, 0, 0, 1};
const int DELTA_Y[] = {0, -1, 1, 0};

int deg[N][N];
double prob[N][N][N][N], tmp[N][N], tmp2[N][N];

int main()
{
    for (auto x = 0; x < N; ++ x) {
        for (auto y = 0; y < N; ++ y) {
            for (auto k = 0; k < 4; ++ k) {
                auto xx = x + DELTA_X[k];
                auto yy = y + DELTA_Y[k];
                deg[x][y] += 0 <= xx && xx < N && 0 <= yy && yy < N;
            }
        }
    }
    for (auto x0 = 0; x0 < N; ++ x0) {
        for (auto y0 = 0; y0 < N; ++ y0) {
            memset(tmp, 0, sizeof(tmp));
            tmp[x0][y0] = 1.;
            for (auto _ = 0; _ < 50; ++ _) {
                memset(tmp2, 0, sizeof(tmp2));
                for (auto x = 0; x < N; ++ x) {
                    for (auto y = 0; y < N; ++ y) {
                        for (auto k = 0; k < 4; ++ k) {
                            auto xx = x + DELTA_X[k];
                            auto yy = y + DELTA_Y[k];
                            if (0 <= xx && xx < N && 0 <= yy && yy < N) {
                                tmp2[xx][yy] += tmp[x][y] / deg[x][y];
                            }
                        }
                    }
                }
                memcpy(tmp, tmp2, sizeof(tmp));
            }
            memcpy(prob[x0][y0], tmp, sizeof(tmp));
        }
    }
    double result = 0.;
    for (auto x = 0; x < N; ++ x) {
        for (auto y = 0; y < N; ++ y) {
            double p = 1.;
            for (auto x0 = 0; x0 < N; ++ x0) {
                for (auto y0 = 0; y0 < N; ++ y0) {
                    p *= 1. - prob[x0][y0][x][y];
                }
            }
            result += p;
        }
    }
    printf("%.6f\n", result);
}
