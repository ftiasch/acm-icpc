#include <algorithm>
#include <cassert>
#include <cmath>
#include <functional>
#include <vector>
#include <string>

#define ALL(v) (v).begin(), (v).end()

struct Point
{
    Point(const double& x = 0., const double& y = 0.) : x(x), y(y) {}

    double det(const Point& o) const {
        return x * o.y - y * o.x;
    }

    double dot(const Point &o) const {
        return x * o.x + y * o.y;
    }

    Point add(const Point& o) const {
        return Point(x + o.x, y + o.y);
    }

    Point subtract(const Point& o) const {
        return Point(x - o.x, y - o.y);
    }

    Point multiply(const double& k) const {
        return Point(x * k, y * k);
    }

    bool operator < (const Point& o) const {
        return det(o) > .5;
    }

    double x, y;
};

class BichromeSky2
{
public:
    double expectationOfArea(const std::vector<int>& x, const std::vector<int>& y, const std::vector<int>& p_)
    {
        int n = x.size();
        std::vector<Point> points;
        for (int i = 0; i < n; ++ i) {
            points.emplace_back(x[i], y[i]);
        }
        std::vector<double> p;
        for (int i = 0; i < n; ++ i) {
            p.emplace_back(p_[i] / 1000.);
        }
        auto&& to_left = [&](int i, int j, int k) {
            return (points[j].subtract(points[i])).det(points[k].subtract(points[i])) > .5;
        };
        auto&& intercept = [&](int i, int j, int k, int l) {
            auto&& p = points[l].subtract(points[k]);
            double s = p.det(points[i].subtract(points[k]));
            return s / (s - p.det(points[j].subtract(points[k])));
        };
        double result = 0.;
        for (int _ = 0; _ < 2; ++ _) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (i == j) {
                        continue;
                    }
                    double p0 = p[i] * p[j];
                    std::vector<int> lefts, rights;
                    for (int k = 0; k < n; ++ k) {
                        if (k == i || k == j) {
                            continue;
                        }
                        if (to_left(i, j, k)) {
                            lefts.push_back(k);
                        } else {
                            p0 *= 1. - p[k];
                            rights.push_back(k);
                        }
                    }
                    if (rights.empty()) {
                        continue;
                    }
                    std::sort(ALL(rights), [&](int i, int j) {
                        return points[i].x + .5 < points[j].x || fabs(points[i].x - points[j].x) < .5 && points[i].y + .5 < points[j].y;
                    });
                    std::vector<int> hull;
                    for (auto&& k : rights) {
                        while (hull.size() >= 2 && !to_left(hull[hull.size() - 2], hull.back(), k)) {
                            hull.pop_back();
                        }
                        hull.emplace_back(k);
                    }
                    int top = hull.size();
                    std::reverse(ALL(rights));
                    for (auto&& k : rights) {
                        while (hull.size() > top && !to_left(hull[hull.size() - 2], hull.back(), k)) {
                            hull.pop_back();
                        }
                        hull.emplace_back(k);
                    }
                    hull.pop_back();
                    std::vector<int> cuts(n);
                    for (auto&& k : lefts) {
                        int& best = cuts[k];
                        best = rights[0];
                        for (auto&& l : rights) {
                            if (points[best].subtract(points[k]) < points[l].subtract(points[k])) {
                                best = l;
                            }
                        }
                    }
                    std::sort(lefts.begin(), lefts.end(), [&](int i, int j) {
                        return points[i].subtract(points[cuts[i]]) < points[j].subtract(points[cuts[j]]);
                    });
                    for (auto&& k : lefts) {
                        int cut = rights[0];
                        for (auto&& l : rights) {
                            if (points[l].subtract(points[k]) < points[cut].subtract(points[k])) {
                                cut = l;
                            }
                        }
                        double p1 = p0 * (1. - p[k]);
                        std::vector<bool> used(n);
                        for (auto&& l : lefts) {
                            if (to_left(cut, k, l)) {
                                p1 *= p[l];
                                used[l] = true;
                            }
                        }
                        for (auto&& l : lefts) {
                            if (used[l]) {
                                continue;
                            }
                            double begin = std::max(0., intercept(i, j, cut, k));
                            double end = std::min(1., intercept(i, j, cuts[l], l));
                            if (begin <= end) {
                                auto&& d = points[j].subtract(points[i]);
                                auto&& p_begin = points[i].add(d.multiply(begin));
                                auto&& p_end = points[i].add(d.multiply(end));
                                result += p_begin.det(p_end) * p1 * (k == l ? 1. : 1. - p[l]);
                            }
                            if (k == l) {
                                break;
                            }
                            p1 *= p[l];
                        }
                    }
                }
            }
            for (auto& pi : p) {
                pi = 1. - pi;
            }
        }
        return .5 * result;
    }
};
