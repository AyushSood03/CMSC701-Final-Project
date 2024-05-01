import java.io.*;
import java.util.*;

class buildsa {
    public static class to_write implements Serializable {
        private static final long serialVersionUID = 1L;
        public String ref_sent;
        public Integer[] sa;
        public HashMap<Integer, ArrayList<Integer>> ht;
        public int k;

        public to_write(String ref_sent, Integer[] sa, HashMap<Integer, ArrayList<Integer>> ht, int k) {
            this.ref_sent = ref_sent;
            this.sa = sa;
            this.ht = ht;
            this.k = k;
        }
    }
    public static void main(String args[]) throws FileNotFoundException {
        //Read in arguments
        String reference = args[0];
        int k = Integer.parseInt(args[1]);
        String output = args[2];
        //Read FASTA file
        String reference_str = "";
        try (Scanner sc = new Scanner(new File(reference))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() == false) {
                    if (line.charAt(0) != '>') {
                        reference_str = reference_str + line;
                    }
                }
            }
        }
        //Add sentinel '$' to reference
        String reference_sentinel = reference_str + "$";
        int len = reference_sentinel.length();
        //Build suffix array
        Integer suffix_array[] = new Integer[len];
        for (int i = 0; i < len; i++) {
            suffix_array[i] = i;
        }
        Arrays.sort(suffix_array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                for (int i = 0; i < Math.min(len - o1, len - o2); i++) {
                    if ((o1 + i >= len) && (o2 + i < len)) {
                        return -1;
                    }
                    if ((o1 + i < len) && (o2 + i >= len)) {
                        return 1;
                    }
                    if ((o1 + i >= len) && (o2 + i >= len)) {
                        return 0;
                    }
                    if ((o1 + i < len) && (o2 + i < len)) {
                        char ch1 = reference_sentinel.charAt(o1 + i);
                        char ch2 = reference_sentinel.charAt(o2 + i);
                        int ch_comp = Character.compare(ch1, ch2);
                        if (ch_comp != 0) {
                            return ch_comp;
                        }
                    }
                }
                return 0;
            }
        });
        //Build prefix lookup
        HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int entry = suffix_array[i];
            if ((entry + k) < len - 1) {
                int prefix = 0b00;
                for (int j = entry; j < entry + k; j++) {
                    char ch = reference_sentinel.charAt(j);
                    if (Character.compare(ch, 'A') == 0) {
                        prefix = (int) ((prefix << 2) + 0);
                    }
                    else if (Character.compare(ch, 'C') == 0) {
                        prefix = (int) ((prefix << 2) + 1);
                    }
                    else if (Character.compare(ch, 'G') == 0) {
                        prefix = (int) ((prefix << 2) + 2);
                    }
                    else if (Character.compare(ch, 'T') == 0) {
                        prefix = (int) ((prefix << 2) + 3);
                    }
                }
                if (hashmap.containsKey(prefix)) {
                    hashmap.get(prefix).set(1, i + 1);
                }
                else {
                    ArrayList<Integer> nums = new ArrayList<Integer>();
                    nums.add(i);
                    nums.add(i + 1);
                    hashmap.put(prefix, nums);
                }
            }
        }
        try {
            System.out.println("Writing to file");
            to_write tw = new to_write(reference_sentinel, suffix_array, hashmap, k);
            System.out.println("Write object created");
            FileOutputStream fos = new FileOutputStream(output);
            System.out.println("FileOutputStream created");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("ObjectOutputStream created");
            oos.writeObject(tw);
            System.out.println("Written to file");
            oos.close();
            System.out.println("ObjectOutputStream closed");
            fos.close();
            System.out.println("FileOutputStream closed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
