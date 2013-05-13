# Testing Round #6
# Problem A -- Candies
n, m = gets.split.map(&:to_i)
puts [*0...m].map { |i| n / m + (i < n % m ? 1 : 0) }.join(' ')
