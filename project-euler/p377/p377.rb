MOD = 10 ** 9

def make_matrix(m)
    Array.new(m) { Array.new(m, 0) }
end

def matrix_product(a, b)
    m = a.size
    c = make_matrix(m)
    m.times do |i|
        m.times do |j|
            c[i][j] = [*0...m].map { |k| a[i][k] * b[k][j] }.inject(0, :+) % MOD
        end
    end
    c
end

def matrix_power(a, n)
    m = a.size
    c = make_matrix(m)
    m.times { |i| c[i][i] = 1 }
    while n > 0
        if n.odd?
            c = matrix_product(c, a)
        end
        a = matrix_product(a, a)
        n >>= 1
    end
    c
end

def solve(n)
    x = make_matrix(18)
    x[0][8] = 1
    a = make_matrix(18)
    9.times do |i|
        a[i][i - 1] = a[9 + i][8 + i] = 1 if i > 0
        a[i][8], a[i][17], a[9 + i][17] = 1, 9 - i, 10
    end
    matrix_product(x, matrix_power(a, n))[0][17]
end

puts solve(5)
puts [*1..17].map { |i| solve(13 ** i) }.inject(0, :+) % MOD
