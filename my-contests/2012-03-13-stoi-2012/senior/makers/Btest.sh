#!/bin/bash
# test_count max_n
make B
make B-50
for count in `seq $1`; do
    # echo Test \#$count
    ./Bgen.py $2 | tee B.in > /dev/null
    cat B.in | ./B | tee B.out > /dev/null
    cat B.in | ./B-50 | tee B-50.out > /dev/null
    if ! diff B.out B-50.out; then
        break
    fi 
done
echo "All test(s) OK"
