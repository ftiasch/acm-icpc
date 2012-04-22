#!/bin/bash
N=(1000 1000 1000 1000 1000 1000000000 1000000000 1000000000 1000000000 1000000000)
make sequence
for i in `seq 10`; do
    python sequence-gen.py ${N[$(($i - 1))]} | tee sequence.$i.in
    cat sequence.$i.in | ./sequence | tee sequence.$i.out
done
rm -rf sequence

N=(5 5 5 100 100 100 100 100 100 100)
javac Product.java
for i in `seq 10`; do
    python product-gen.py ${N[$(($i - 1))]} | tee product.$i.in
    cat product.$i.in | java Product | tee product.$i.out
done
rm -rf Product.class

N=(1000 1000 1000 50000 50000 100000 100000 100000 200000 200000)
make queue
for i in `seq 10`; do
    python queue-gen.py ${N[$(($i - 1))]} | tee queue.$i.in
    cat queue.$i.in | ./queue | tee queue.$i.out | head -10
done
rm -rf queue
