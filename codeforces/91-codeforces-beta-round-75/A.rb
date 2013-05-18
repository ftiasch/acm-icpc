# Codeforces Beat Round #75
# Problem A -- Chips
n, m = gets.split.map(&:to_i)
i = 1
while i <= m do 
    m -= i
    i = 1 + i % n
end
puts m
