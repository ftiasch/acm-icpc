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
  test '003.hand'
  [[10, 20], [100, 200], [100, 1000], [1000, 2000], [1000, 10000]].each do |n, m|
    [2, 10, 1000000].each do |k|
      test :random, '?', n, m, k
    end
  end

  sh 'rm -rf *.exe'
end
