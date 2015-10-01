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
    [10, 100, 10000].each do |m|
      [1, 10].each do |k|
        test :random, '?', n, m, k
      end
    end
  end

  sh 'rm -rf *.exe'
end
