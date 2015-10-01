#!/usr/bin/env bash
count=0
while true; do
    count=$(($count + 1))
    ./random     $count $@ > input
    ./solution < input > output.1
    ./slow     < input > output.2
    cat output.1
    if diff output.1 output.2; then
        echo Passed \#$count
    else
        echo Failed \#$count
        break
    fi
done
