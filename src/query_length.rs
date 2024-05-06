use std::fs::File;
use std::io::{self, BufRead, BufReader, Write};
use std::collections::HashMap;

fn main() -> io::Result<()> {
    let input_path = "../test_data/test_input/reads_sal_sub.fq";
    let output_path = "../test_data/test_input/query_length_distribution.txt";

    let input_file = File::open(input_path)?;
    let reader = BufReader::new(input_file);

    let mut length_counts: HashMap<usize, usize> = HashMap::new();

    for line in reader.lines() {
        let line = line?;
        if !line.starts_with('>')  {
            let length = line.trim().len();
            *length_counts.entry(length).or_insert(0) += 1;
        }
    }

    let mut output_file = File::create(output_path)?;
    writeln!(output_file, "Length\tFrequency")?;

    let mut lengths: Vec<_> = length_counts.keys().collect();
    lengths.sort_unstable();

    for &length in lengths {
        if let Some(&count) = length_counts.get(&length) {
            writeln!(output_file, "{}\t{}", length, count)?;
        }
    }

    Ok(())
}
