#!/bin/bash

FA="../test_data/test_input/salmonella_sub.fa"
K="16"
MINIMIZERSCHEME="lexicographic"

start_time=$(date +%s%N)
java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64.bin" "none" "0" "0"
end_time=$(date +%s%N)
nano_time=$((end_time-start_time))
printf "Standard Suffix Array Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"

for n in {1..9};
do
    KMERWIDTH="${n}"
    WINT="$((10-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "lexicographic_rev" ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"
done

for n in {1..19};
do
    KMERWIDTH="${n}"
    WINT="$((20-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "lexicographic_rev" ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"
done

for n in {1..29};
do
    KMERWIDTH="${n}"
    WINT="$((30-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" ${MINIMIZERSCHEME} ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"

    start_time=$(date +%s%N)
    java -cp "." buildminimizersa "../test_data/test_input/salmonella_sub.fa" ${K} "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "lexicographic_rev" ${KMERWIDTH} ${W}
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/timing.txt"
done