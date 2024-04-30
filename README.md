# CMSC701-Final-Project: Sparse Suffix Array Based on Sampling Minimizers

Motivation: We discussed the suffix array in the class, which is a mainstay of full-text indexing data structures. The main shortcoming of the suffix array is its size. Yet, for the purposes of tasks like read mapping (especially for longer reads), it's likely not necessary to have indexed the position of every suffix. Indeed, sparse variants of the suffix array exist that retain only a (usually regularly sampled) collection of offsets in the original text. This project seeks to combine the idea of suffix array sampling with the notion of [minimizers](https://academic.oup.com/bioinformatics/article/20/18/3363/202143), which has been fruitful in helping to select consistent, sparse and informative sets of k-mers.

Project: Implement a sparse, sampled suffix array where, instead of performing a regular sampling of the initial suffixes, you instead select which suffixes to retain based on which suffixes represent the start of minimizer sequences. This data structure will have a size and density dictated by the particular minimizer scheme used and parameters selected, and will allow you to quickly look up any minimizers that occur in the reference string. You should implement this scheme generally, and explore different minimizer definitions (e.g. lexicographic, hash-based, and other schemes) as well as different parameters for the window length and minimizer size. You should also evaluate the speed of lookup in this data structure, and potentially with auxiliary data structures (e.g. short prefix tables). Time permitting, perhaps try to evaluate this data structure in the context of read alignment, or build a small read mapper based around it.

buildsa: Input/Output
The input consists of 3 arguments, given in this order:
1. reference - the path to a FASTA format file containing the reference of which the program will build the suffix array.
2. k - the size of the prefix to use in the prefix lookup table of the suffix array.
3. output - the program will write a single binary output file to a file with this name, that contains a serialized version of the input string and the suffix array.

inspectsa: Input/Output
The input consists of 3 arguments, given in this order:
1. index - the path to the binary file containing the serialized suffix array (as written by buildsa above).
2. sample_rate - the rate at which suffix array entries will be sampled in the output.
3. output - the program will write text output to this file containing some basic statistics about the suffix array, as well as a text representation of the suffix array.

querysa: Input/Output
1. index - the path to the binary file containing the serialized suffix array (as written by buildsa above).
2. queries - the path to an input file in FASTA format containing a set of records.
3. query mode - this argument should be one of three strings; either naive, simpaccel, or prefaccel. If the string is naive the program should perform queries using the naive binary search algorithm. If the string is simpaccel the program should perform queries using the “simple accelerant” algorithm we covered in class — the algorithm that carries along the 2 extra LCPs as search is peformed, not the one that pre-computes the LCPs for all search intervals*. If the string is prefaccel the program should perform queries using a prefix-table accelerated search where it jumps to the appropriate interval matching the prefix, and then continues to do binary search only in that range.
4. output - the name to use for the resulting output.
