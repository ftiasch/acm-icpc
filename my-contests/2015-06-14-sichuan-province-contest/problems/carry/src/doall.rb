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
  [100, 1000, 100000].each do |n|
    [1000, 1000000, 1000000000].each do |m|
      test :random, '?', n, m
    end
  end

  sh 'rm -rf *.exe'
end
