# Codeforces Beta Round #69
# Problem A -- Panoramix's Prediction
def is_prime(n)
    2.upto Math.sqrt(n).to_i do |i|
        return false if n % i == 0
    end
    true
end

n, m = gets.split.map(&:to_i)
n += 1
n += 1 until is_prime(n)
puts n == m ? "YES" : "NO"
