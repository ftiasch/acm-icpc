# Codeforces Alpha Round #20 
# Problem A -- BerOS file system
path  = gets.chop.split(/\/+/) * '/'
puts path == '' ? '/' : path
