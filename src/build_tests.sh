#!/bin/bash

FA="../test_data/test_input/salmonella_sub.fa"
K="16"
MINIMIZERSCHEME="lexicographic"

for n in {2..8};
do
    KMERWIDTH="${n}"
    WINT="$((10-n))"
    W="${WINT}"
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
done

for n in {2..18};
do
    KMERWIDTH="${n}"
    WINT="$((20-n))"
    W="${WINT}"
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
done

for n in {2..28};
do
    KMERWIDTH="${n}"
    WINT="$((30-n))"
    W="${WINT}"
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
done