# Codeforces Round #156
# Problem B -- Code Parsing
count = [0, 0]
gets.strip.each_char do |c|
    count[c == 'x' ? 0 : 1] += 1
end
if count[0] > count[1]
    puts "x" * (count[0] - count[1])
else
    puts "y" * (count[1] - count[0])
end
