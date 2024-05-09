#!/bin/bash

FQ="../test_data/test_input/salmonella_sub.fa"

printf "start 10\n"
start_time=$(date +%s%N)
java -cp "." queryminimizersa "../bin/salmonella_sub_64.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_basic_10.txt" "10"
end_time=$(date +%s%N)
nano_time=$((end_time-start_time))
printf "Standard Suffix Array 10 Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
for n in {1..9};
do
    printf "${n}\n"
    KMERWIDTH="${n}"
    WINT="$((10-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}.txt" "10"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}_rev.txt" "10"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
done

printf "start 20\n"
start_time=$(date +%s%N)
java -cp "." queryminimizersa "../bin/salmonella_sub_64.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_basic_20.txt" "20"
end_time=$(date +%s%N)
nano_time=$((end_time-start_time))
printf "Standard Suffix Array 20 Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
for n in {1..19};
do
    printf "${n}\n"
    KMERWIDTH="${n}"
    WINT="$((20-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}.txt" "20"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}_rev.txt" "20"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
done

printf "start 30\n"
start_time=$(date +%s%N)
java -cp "." queryminimizersa "../bin/salmonella_sub_64.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_basic_30.txt" "30"
end_time=$(date +%s%N)
nano_time=$((end_time-start_time))
printf "Standard Suffix Array 30 Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
for n in {1..29};
do
    printf "${n}\n"
    KMERWIDTH="${n}"
    WINT="$((30-n))"
    W="${WINT}"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}.txt" "30"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"

    start_time=$(date +%s%N)
    java -cp "." queryminimizersa "../bin/salmonella_sub_64_minimizer_${KMERWIDTH}_${W}_rev.bin" "../test_data/test_input/reads_sal_sub.fq" "naive" "../bin/reads_sal_sub_lex_${KMERWIDTH}_${W}_rev.txt" "30"
    end_time=$(date +%s%N)
    nano_time=$((end_time-start_time))
    printf "Reverse Lexicographic Suffix Array $KMERWIDTH $W Elapsed Time (ns): $nano_time ns\n" >> "../bin/query_timing.txt"
done