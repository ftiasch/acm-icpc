# Codeforces Round #169
# Problem B -- Little Girl and Game
count = [0] * 26
gets.chomp.each_char do |c|
    count[c.ord - 'a'.ord] += 1
end
odds = count.count { |x| x % 2 == 1 }
puts (odds <= 1 or odds % 2 == 1) ? "First" : "Second"
