# CMSC701-Final-Project: Sparse Suffix Array Based on Sampling Minimizers

Motivation: We discussed the suffix array in the class, which is a mainstay of full-text indexing data structures. The main shortcoming of the suffix array is its size. Yet, for the purposes of tasks like read mapping (especially for longer reads), it's likely not necessary to have indexed the position of every suffix. Indeed, sparse variants of the suffix array exist that retain only a (usually regularly sampled) collection of offsets in the original text. This project seeks to combine the idea of suffix array sampling with the notion of [minimizers](https://academic.oup.com/bioinformatics/article/20/18/3363/202143), which has been fruitful in helping to select consistent, sparse and informative sets of k-mers.

Project: Implement a sparse, sampled suffix array where, instead of performing a regular sampling of the initial suffixes, you instead select which suffixes to retain based on which suffixes represent the start of minimizer sequences. This data structure will have a size and density dictated by the particular minimizer scheme used and parameters selected, and will allow you to quickly look up any minimizers that occur in the reference string. You should implement this scheme generally, and explore different minimizer definitions (e.g. lexicographic, hash-based, and other schemes) as well as different parameters for the window length and minimizer size. You should also evaluate the speed of lookup in this data structure, and potentially with auxiliary data structures (e.g. short prefix tables). Time permitting, perhaps try to evaluate this data structure in the context of read alignment, or build a small read mapper based around it.
