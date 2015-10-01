require_relative 'testlib'

doall do
  sh 'CXXFLAGS="-O2 -std=c++11 -Wall -Wno-unused-result -Wno-parentheses -Wno-varargs -Wno-attributes"'

  make 'random.cpp'
  make 'validator.cpp'
  make 'solution.cpp'

  sh 'mkdir -p ../tests'

  test '001.hand'
  test '002.hand'
  test '003.hand'
  test '004.hand'
  test '005.hand'
  test '006.hand'
  10.times { test :random, '?', 100,  10  }
  10.times { test :random, '?', 1000, 100 }

  sh 'rm -rf *.exe'
end
