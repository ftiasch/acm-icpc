# Codeforces Round #169
# Problem A -- Lunch Rush
n, k = gets.split.map(&:to_i)
puts([*1..n].map do
    f, t = gets.split.map(&:to_i)
    if t <= k
        f
    else
        f - (t - k)
    end
end.max)
