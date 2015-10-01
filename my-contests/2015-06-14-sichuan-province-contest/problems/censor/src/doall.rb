require_relative 'testlib'

doall do
  sh 'CXXFLAGS="-O2 -std=c++11 -Wall -Wno-unused-result -Wno-parentheses -Wno-varargs -Wno-attributes"'

  make 'random.cpp'
  make 'random_ab.cpp'
  make 'random_fib.cpp'
  make 'validator.cpp'
  make 'solution.cpp'

  sh 'mkdir -p ../tests'

  test '001.hand'
  test '002.hand'
  test '003.hand'
  test '004.hand'
  test :random,     '?', 1000,   5000000
  test :random_ab,  '?', 1000,   5000000
  test :random_fib, '?', 1000,   5000000
  test :random,     '?', 100000, 5000000
  test :random_ab,  '?', 100000, 5000000
  test :random_fib, '?', 100000, 5000000

  sh 'rm -rf *.exe'
end
