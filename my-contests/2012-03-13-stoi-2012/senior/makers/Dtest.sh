#!/bin/bash
# test_count max_n
make D
make D-30
for count in `seq $1`; do
    echo Test \#$count
    ./Dgen.py $2 | tee D.in > /dev/null
    cat D.in | ./D | tee D.out > /dev/null
    cat D.in | ./D-30 | tee D-30.out > /dev/null
    if ! diff D.out D-30.out; then
        break
    fi 
done
echo "All test(s) OK"
