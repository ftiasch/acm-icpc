# Codeforces Round #116
# Problem F -- Mathematical Analysis Rocks!
n = gets.to_i
a, b = [0, 1].map { gets.split.map(&:to_i) }
p = []
n.times do |i|
  p[a[i] - 1] = b[i]
end
puts p.join(' ')
