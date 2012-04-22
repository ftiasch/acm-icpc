#!/bin/bash
make A
limit=(-1 1000 1000 1000 1000 1000 100000 100000 100000 100000 100000)
for i in `seq 10`; do 
    echo A -- Test \#$i
    ./Agen.py ${limit[$i]} | tee math.$i.in > /dev/null
    time ./A < math.$i.in > math.$i.out 
done

make B
limit=(-1 100 100 100 100 100 1000 1000 1000 1000 1000)
for i in `seq 10`; do 
    echo B -- Test \#$i
    ./Bgen.py ${limit[$i]} | tee trap.$i.in > /dev/null
    time ./B < trap.$i.in > trap.$i.out 
done

make C
limit=(-1 100 100 100 100 100 5000 5000 5000 5000 5000)
for i in `seq 10`; do 
    echo C -- Test \#$i
    ./Cgen.py ${limit[$i]} $((i % 5)) | tee ball.$i.in > /dev/null
    time ./C < ball.$i.in > ball.$i.out
done

make D
limit=(-1 1000 1000 1000 1000 1000 50000 50000 50000 50000 50000)
for i in `seq 10`; do 
    echo D -- Test \#$i
    ./Dgen.py ${limit[$i]} | tee xor.$i.in > /dev/null
    time ./D < xor.$i.in > xor.$i.out
done

rm -rf A B C D
