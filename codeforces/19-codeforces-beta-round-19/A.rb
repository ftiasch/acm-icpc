# Codeforces Beta Round #19
# Problem A -- World Football Cup
class Team
  attr_accessor :name, :point, :net_score, :score

  def initialize(name)
    self.name = name
    self.point = self.net_score = self.score = 0
  end

  def add(x, y)
    self.point += [0, 1, 3][(x <=> y) + 1]
    self.net_score += x - y
    self.score += x
  end

  def <=>(o)
    [:point, :net_score, :score].each do |t|
      ret = self.send(t) <=> o.send(t)
      return ret unless ret == 0
    end
  end
end

n = gets.to_i
teams = Hash.new
n.times do
  name = gets.strip
  teams[name] = Team.new(name)
end
(n * (n - 1) / 2).times do
  a, b, x, y = gets.strip.split(/[\:\- ]/)
  x = x.to_i
  y = y.to_i
  teams[a].add(x, y)
  teams[b].add(y, x)
end
winner = teams.values.sort[-n / 2..-1].map(&:name).sort
puts winner
