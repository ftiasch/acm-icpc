# Croc Champ 2013 - Round 2
# Problem A -- Ksusha and Array
n = gets.to_i
as = gets.split.map(&:to_i)
g = as.inject(0) { |g, a| g.gcd(a) }
as.each do |a|
  if g % a == 0
    puts a
    exit 0
  end
end
puts -1
