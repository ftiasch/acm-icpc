# Codeforces Beta Round #55
# Problem A -- Word
s = gets
n = s.length - 1
puts s.split(//).select { |c| c =~ /[[:upper:]]/ }.size > n / 2 ? s.upcase : s.downcase
