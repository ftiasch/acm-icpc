# Codeforces Round #173
# Problem A -- Bit++
puts([*1..gets.to_i].inject(0) do |x, i|
    x + (gets.index('+') ? 1 : -1)
end)
