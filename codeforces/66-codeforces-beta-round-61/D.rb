# Codeforces Beta Round #61
# Problem D -- Petya and His Friends
def is_prime(n)
    i = 2
    while i * i <= n
        return false if n % i == 0
        i += 1
    end
    true
end

n = gets.to_i

if n == 2 
    puts -1
else
    i = 2
    primes = []
    while primes.size < n
        primes << i if is_prime(i)
        i += 1
    end

    product = primes.inject(1) { |p, i| p * i }
    puts primes.map { |p| product / p }
end
