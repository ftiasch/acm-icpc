# Yandex Algorithm 2011 Round 2
# Problem A -- Reflection
l, r = gets.split.map(&:to_i)
a, b, answer = 1, 9, 0
while a <= r
    c, d = [l, a].max, [r, b].min
    if c <= d
        answer = [(b - c) * c, (b - d) * d, answer].max
        if c <= b / 2 and b / 2 <= d
            answer = [(b - b / 2) * (b / 2), answer].max
        end
    end
    a *= 10
    b *= 10
    b += 9
end
puts answer
