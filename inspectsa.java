import java.io.*;
import java.util.*;

class inspectsa {
    public static void main(String args[]) throws FileNotFoundException {
        String index = args[0];
        Integer sample_rate = Integer.parseInt(args[1]);
        String output = args[2];
        try {
            FileInputStream fis = new FileInputStream(index);
            ObjectInputStream ois = new ObjectInputStream(fis);
            buildsa.to_write tw = buildsa.to_write.class.cast(ois.readObject());
            ois.close();
            fis.close();
            String reference_sentinel = tw.ref_sent;
            Integer suffix_array[] = tw.sa;
            int len = suffix_array.length;
            HashMap<Integer, ArrayList<Integer>> hashmap = tw.ht;
            Integer LCP[] = new Integer[len - 1];
            float lcp_mean = 0;
            float lcp_median = 0;
            int lcp_max = 0;
            for (int i = 0; i < len - 1; i++) {
                int index_1 = suffix_array[i];
                int index_2 = suffix_array[i + 1];
                int counter = 0;
                while ((reference_sentinel.charAt(index_1) == reference_sentinel.charAt(index_2)) && (index_1 < len) && (index_2 < len)) {
                    index_1++;
                    index_2++;
                    counter++;
                }
                LCP[i] = counter;
                lcp_mean += counter;
                if (counter > lcp_max) {
                    lcp_max = counter;
                }
            }
            lcp_mean /= (float) (len - 1);
            Arrays.sort(LCP);
            if (((len - 1) % 2) == 0) {
                lcp_median = (float) (LCP[(len - 1) / 2] + LCP[((len - 1) / 2) + 1]) / (float) 2;
            }
            else {
                lcp_median = LCP[(len - 1) / 2];
            }
            try {
                FileWriter fw = new FileWriter(output);
                fw.write(lcp_mean + "\n");
                fw.write(lcp_median + "\n");
                fw.write(lcp_max + "\n");
                if (sample_rate > 0) {
                    boolean written = false;
                    for (int i = 0; i < len; i++) {
                        if ((i % sample_rate) == 0) {
                            if (written == false) {
                                fw.write(suffix_array[i].toString());
                                written = true;
                            }
                            else {
                                fw.write("\t" + suffix_array[i].toString());
                            }
                        }
                    }
                }
                fw.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}