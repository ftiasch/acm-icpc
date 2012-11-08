# Codeforces Round #110
# Problem A -- Game Outcome
a = []
n = gets.to_i
n.times { a << gets.split.map(&:to_i) }
rows = (columns = [0] * n).dup
(0...n).each do |i|
    (0...n).each do |j|
        rows[i] += a[i][j]
        columns[j] += a[i][j]
    end
end
puts rows.product(columns).select {|x, y| x < y }.size
