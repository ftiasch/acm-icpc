# Bayan 2012-2013 Elimination Round 
# Problem A -- Old Peykan
read = lambda { gets.split.map(&:to_i) }
m, k = read.call
d = read.call
s = read.call
answer = now = left = delta = 0
while now < m
    delta = [delta, s[now]].max
    left += s[now] 
    while left < d[now]
        answer += k
        left += delta
    end
    answer += d[now]
    left -= d[now]
    now += 1
end
puts answer
