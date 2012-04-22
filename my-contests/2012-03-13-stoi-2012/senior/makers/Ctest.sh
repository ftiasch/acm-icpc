#!/bin/bash
# test_count max_n
make C
make C-50
for count in `seq $1`; do
    echo Test \#$count
    ./Cgen.py $2 1 | tee C.in > /dev/null
    cat C.in | ./C | tee C.out > /dev/null
    cat C.in | ./C-50 | tee C-50.out > /dev/null
    if ! diff C.out C-50.out; then
        break
    fi 
done
echo "All test(s) OK"
