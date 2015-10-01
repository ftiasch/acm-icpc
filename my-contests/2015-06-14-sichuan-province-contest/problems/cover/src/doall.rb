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
  [1,3,10,12,14,21,23,30,32,34,36,38,40,43,45,51,58,60,62,69,71,73,75,77,84,88,92,97,101,104,105,106,108,112,116,119,121,123,125,127,128,129,131,132,134,136,144,149,151,153].each do |seed|
    test :random, seed, 500, 30, 1000
  end

  sh 'rm -rf *.exe'
end
