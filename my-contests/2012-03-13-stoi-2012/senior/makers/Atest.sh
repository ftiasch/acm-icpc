#!/bin/bash
# test_count max_n
make A
make A-50
for count in `seq $1`; do
    # echo Test \#$count
    ./Agen.py $2 | tee A.in > /dev/null
    cat A.in | ./A | tee A.out > /dev/null
    cat A.in | ./A-50 | tee A-50.out > /dev/null
    if ! diff A.out A-50.out; then
        break
    fi 
done
echo "All test(s) OK"
