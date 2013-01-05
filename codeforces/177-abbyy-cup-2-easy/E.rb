# ABBYY Cup 2.0 - Easy
# Problem E - Space Voyage
$n, c = gets.split.map(&:to_i)
$a, $b = [], []
$n.times do
    a_i, b_i = gets.split.map(&:to_i)
    $a << a_i
    $b << b_i
end

def get_value(x)
    ret = $n
    $n.times do |i|
        ret += $a[i] * x / $b[i]
    end
    ret 
end

low, high = 1, 10 ** 18 + 1
while low < high
    middle = low + high >> 1
    if get_value(middle) >= c
        high = middle
    else
        low = middle + 1
    end
end
minimum = high

low, high = 0, 10 ** 18
while low < high
    middle = low + high + 1 >> 1
    if get_value(middle) <= c
        low = middle
    else
        high = middle - 1
    end
end
maximum = low

answer = [maximum - minimum + 1, 0].max
answer = -1 if answer == 10 ** 18
puts answer
