require_relative 'testlib'

doall do
  sh 'CXXFLAGS="-O2 -std=c++11 -Wall -Wno-unused-result -Wno-parentheses -Wno-varargs -Wno-attributes"'

  make 'random.cpp'
  make 'random_dis.cpp'
  make 'validator.cpp'
  make 'solution.cpp'

  sh 'mkdir -p ../tests'

  test '001.hand'
  test '002.hand'
  test '003.hand'
  test '004.hand'
  [0, 1].each do |t|
    10.times { test :random, '?', 100, 2000, 1000000000, t }
  end
  [0, 1].each do |t|
    test :random, '?', 100000, 500000, 1000000000, t
  end
  test :random_dis, '?', 100000, 500000, 1000000000

  sh 'rm -rf *.exe'
end
