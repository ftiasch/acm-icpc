# Codeforces Round #156
# Problem A -- Greg's Workout
n = gets.to_i
count = [0] * 3
gets.split.map(&:to_i).each_with_index do |a, i|
    count[i % 3] += a
end
i = 0
i += 1 until count[i] == count.max
puts %w(chest biceps back)[i]
