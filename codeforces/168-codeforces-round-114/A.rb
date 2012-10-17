# Codeforces Round #114
# Problem A -- Wizards and Demonstration
n, x, y = gets.split.map(&:to_i)
puts [(y * n + 99) / 100 - x, 0].max
