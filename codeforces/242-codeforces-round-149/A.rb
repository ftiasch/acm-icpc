x, y, a, b = gets.split.map(&:to_i)
outcomes = []
a.upto(x) do |i|
    b.upto(y) do |j|
        outcomes << [i, j] if i > j
    end
end
puts outcomes.size
outcomes.sort.each do |outcome|
    puts outcome.join(" ")
end
