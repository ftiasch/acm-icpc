# Codeforces Round #126
# Problem B -- Drinks
n = gets.to_i
puts gets.split.map(&:to_i).inject { |s, i| s + i } / n.to_f
