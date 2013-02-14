# Codeforces Round #166 
# Problem A -- Beautiful Year
def check(n)
    n.to_s.split(//).uniq.size == 4
end

n = gets.to_i
begin
    n += 1
end until check(n)
puts n
