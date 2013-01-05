# Codeforces Round #156
# Problem B -- Mr. Bender and Square
def square(x) 
    x * x
end

def triangle(x)
    x * (x + 1) / 2
end

def solve(n, x, y, t)
    count = t * t + (t + 1) * (t + 1)
    4.times do
        count -= square(t - x + 1) if x <= t
        count += triangle(t - x - y + 1) if x + y <= t
        x, y = n + 1 - y, x
    end
    count
end

n, x, y, c = gets.split.map(&:to_i)
low, high = 0, (n - 1) * 2
while low < high
    middle = (low + high) / 2
    if solve(n, x, y, middle) >= c
        high = middle 
    else
        low = middle + 1
    end
end
p high
