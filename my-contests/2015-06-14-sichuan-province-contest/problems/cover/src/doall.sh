#!/usr/bin/env bash -x
set -o errexit
CXXFLAGS="-O2 -std=c++11 -Wall -Wno-unused-result -Wno-parentheses -Wno-varargs -Wno-attributes"
c++ $CXXFLAGS random.cpp -orandom.exe
c++ $CXXFLAGS validator.cpp -ovalidator.exe
c++ $CXXFLAGS solution.cpp -osolution.exe
mkdir -p ../tests
cp 001.hand ../tests/001
./validator.exe < ../tests/001
./solution.exe < ../tests/001 > ../tests/001.a
cp 002.hand ../tests/002
./validator.exe < ../tests/002
./solution.exe < ../tests/002 > ../tests/002.a
cp 003.hand ../tests/003
./validator.exe < ../tests/003
./solution.exe < ../tests/003 > ../tests/003.a
./random.exe 1 500 30 1000 > ../tests/004
./validator.exe < ../tests/004
./solution.exe < ../tests/004 > ../tests/004.a
./random.exe 3 500 30 1000 > ../tests/005
./validator.exe < ../tests/005
./solution.exe < ../tests/005 > ../tests/005.a
./random.exe 10 500 30 1000 > ../tests/006
./validator.exe < ../tests/006
./solution.exe < ../tests/006 > ../tests/006.a
./random.exe 12 500 30 1000 > ../tests/007
./validator.exe < ../tests/007
./solution.exe < ../tests/007 > ../tests/007.a
./random.exe 14 500 30 1000 > ../tests/008
./validator.exe < ../tests/008
./solution.exe < ../tests/008 > ../tests/008.a
./random.exe 21 500 30 1000 > ../tests/009
./validator.exe < ../tests/009
./solution.exe < ../tests/009 > ../tests/009.a
./random.exe 23 500 30 1000 > ../tests/010
./validator.exe < ../tests/010
./solution.exe < ../tests/010 > ../tests/010.a
./random.exe 30 500 30 1000 > ../tests/011
./validator.exe < ../tests/011
./solution.exe < ../tests/011 > ../tests/011.a
./random.exe 32 500 30 1000 > ../tests/012
./validator.exe < ../tests/012
./solution.exe < ../tests/012 > ../tests/012.a
./random.exe 34 500 30 1000 > ../tests/013
./validator.exe < ../tests/013
./solution.exe < ../tests/013 > ../tests/013.a
./random.exe 36 500 30 1000 > ../tests/014
./validator.exe < ../tests/014
./solution.exe < ../tests/014 > ../tests/014.a
./random.exe 38 500 30 1000 > ../tests/015
./validator.exe < ../tests/015
./solution.exe < ../tests/015 > ../tests/015.a
./random.exe 40 500 30 1000 > ../tests/016
./validator.exe < ../tests/016
./solution.exe < ../tests/016 > ../tests/016.a
./random.exe 43 500 30 1000 > ../tests/017
./validator.exe < ../tests/017
./solution.exe < ../tests/017 > ../tests/017.a
./random.exe 45 500 30 1000 > ../tests/018
./validator.exe < ../tests/018
./solution.exe < ../tests/018 > ../tests/018.a
./random.exe 51 500 30 1000 > ../tests/019
./validator.exe < ../tests/019
./solution.exe < ../tests/019 > ../tests/019.a
./random.exe 58 500 30 1000 > ../tests/020
./validator.exe < ../tests/020
./solution.exe < ../tests/020 > ../tests/020.a
./random.exe 60 500 30 1000 > ../tests/021
./validator.exe < ../tests/021
./solution.exe < ../tests/021 > ../tests/021.a
./random.exe 62 500 30 1000 > ../tests/022
./validator.exe < ../tests/022
./solution.exe < ../tests/022 > ../tests/022.a
./random.exe 69 500 30 1000 > ../tests/023
./validator.exe < ../tests/023
./solution.exe < ../tests/023 > ../tests/023.a
./random.exe 71 500 30 1000 > ../tests/024
./validator.exe < ../tests/024
./solution.exe < ../tests/024 > ../tests/024.a
./random.exe 73 500 30 1000 > ../tests/025
./validator.exe < ../tests/025
./solution.exe < ../tests/025 > ../tests/025.a
./random.exe 75 500 30 1000 > ../tests/026
./validator.exe < ../tests/026
./solution.exe < ../tests/026 > ../tests/026.a
./random.exe 77 500 30 1000 > ../tests/027
./validator.exe < ../tests/027
./solution.exe < ../tests/027 > ../tests/027.a
./random.exe 84 500 30 1000 > ../tests/028
./validator.exe < ../tests/028
./solution.exe < ../tests/028 > ../tests/028.a
./random.exe 88 500 30 1000 > ../tests/029
./validator.exe < ../tests/029
./solution.exe < ../tests/029 > ../tests/029.a
./random.exe 92 500 30 1000 > ../tests/030
./validator.exe < ../tests/030
./solution.exe < ../tests/030 > ../tests/030.a
./random.exe 97 500 30 1000 > ../tests/031
./validator.exe < ../tests/031
./solution.exe < ../tests/031 > ../tests/031.a
./random.exe 101 500 30 1000 > ../tests/032
./validator.exe < ../tests/032
./solution.exe < ../tests/032 > ../tests/032.a
./random.exe 104 500 30 1000 > ../tests/033
./validator.exe < ../tests/033
./solution.exe < ../tests/033 > ../tests/033.a
./random.exe 105 500 30 1000 > ../tests/034
./validator.exe < ../tests/034
./solution.exe < ../tests/034 > ../tests/034.a
./random.exe 106 500 30 1000 > ../tests/035
./validator.exe < ../tests/035
./solution.exe < ../tests/035 > ../tests/035.a
./random.exe 108 500 30 1000 > ../tests/036
./validator.exe < ../tests/036
./solution.exe < ../tests/036 > ../tests/036.a
./random.exe 112 500 30 1000 > ../tests/037
./validator.exe < ../tests/037
./solution.exe < ../tests/037 > ../tests/037.a
./random.exe 116 500 30 1000 > ../tests/038
./validator.exe < ../tests/038
./solution.exe < ../tests/038 > ../tests/038.a
./random.exe 119 500 30 1000 > ../tests/039
./validator.exe < ../tests/039
./solution.exe < ../tests/039 > ../tests/039.a
./random.exe 121 500 30 1000 > ../tests/040
./validator.exe < ../tests/040
./solution.exe < ../tests/040 > ../tests/040.a
./random.exe 123 500 30 1000 > ../tests/041
./validator.exe < ../tests/041
./solution.exe < ../tests/041 > ../tests/041.a
./random.exe 125 500 30 1000 > ../tests/042
./validator.exe < ../tests/042
./solution.exe < ../tests/042 > ../tests/042.a
./random.exe 127 500 30 1000 > ../tests/043
./validator.exe < ../tests/043
./solution.exe < ../tests/043 > ../tests/043.a
./random.exe 128 500 30 1000 > ../tests/044
./validator.exe < ../tests/044
./solution.exe < ../tests/044 > ../tests/044.a
./random.exe 129 500 30 1000 > ../tests/045
./validator.exe < ../tests/045
./solution.exe < ../tests/045 > ../tests/045.a
./random.exe 131 500 30 1000 > ../tests/046
./validator.exe < ../tests/046
./solution.exe < ../tests/046 > ../tests/046.a
./random.exe 132 500 30 1000 > ../tests/047
./validator.exe < ../tests/047
./solution.exe < ../tests/047 > ../tests/047.a
./random.exe 134 500 30 1000 > ../tests/048
./validator.exe < ../tests/048
./solution.exe < ../tests/048 > ../tests/048.a
./random.exe 136 500 30 1000 > ../tests/049
./validator.exe < ../tests/049
./solution.exe < ../tests/049 > ../tests/049.a
./random.exe 144 500 30 1000 > ../tests/050
./validator.exe < ../tests/050
./solution.exe < ../tests/050 > ../tests/050.a
./random.exe 149 500 30 1000 > ../tests/051
./validator.exe < ../tests/051
./solution.exe < ../tests/051 > ../tests/051.a
./random.exe 151 500 30 1000 > ../tests/052
./validator.exe < ../tests/052
./solution.exe < ../tests/052 > ../tests/052.a
./random.exe 153 500 30 1000 > ../tests/053
./validator.exe < ../tests/053
./solution.exe < ../tests/053 > ../tests/053.a
rm -rf *.exe
