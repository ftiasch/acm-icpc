# Codeforces Round #169
# Problem D -- Little Girl and Maximum XOR
l, r = gets.split.map(&:to_i)
if l == r
    puts 0
else
    59.downto(0) do |i|
        if l >= (1 << i) 
            l -= 1 << i
            r -= 1 << i
        elsif r >= (1 << i)
            puts (1 << i + 1) - 1
            exit 0
        end
    end
end
