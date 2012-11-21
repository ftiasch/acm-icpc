# Codeforces Round #124
# Problem C -- Lexicographically Maximum Subsequence
answer = []
gets.each_char do |c|
    answer.pop while not answer.empty? and answer[-1] < c
    answer << c
end
puts answer.join("")
