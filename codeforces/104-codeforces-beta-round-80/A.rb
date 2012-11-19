# Codeforces Beta Round #80
# Problem A -- Blackjack
cards = [0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 15, 4]
n = gets.to_i - 10
puts (0 <= n and n <= 11) ? cards[n] : 0
