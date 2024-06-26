import java.io.*;
import java.util.*;

class querysa {
    public static Integer[] my_binary_search(Integer[] suffix_array, int len, int P_len,
        String reference_sentinel, String P, int char_cmp) {
        int left = 0;
        int right = len;
        while (left < right) {
            int mid = (int) Math.floor((left + right) / 2);
            int sac = suffix_array[mid];
            int pac = 0;
            int cmp = 0;
            while ((cmp == 0) && (sac < len) && (pac < (P_len + 1))) {
                char sac_char = reference_sentinel.charAt(sac);
                char pac_char = P.charAt(pac);
                char_cmp++;
                cmp = Character.compare(pac_char, sac_char);
                if (cmp != 0) {
                    break;
                }
                sac++;
                pac++;
                if ((sac >= len) && (pac < (P_len + 1))) {
                    cmp = 1;
                }
                else if ((sac < len) && (pac >= (P_len + 1))) {
                    cmp = -1;
                }
            }
            if (cmp < 0) {
                if ((mid == (left + 1))) {
                    Integer[] to_ret = {mid, char_cmp};
                    return to_ret;
                }
                else {
                    right = mid;
                }
            }
            else {
                if ((mid == (right - 1))) {
                    Integer[] to_ret = {right, char_cmp};
                    return to_ret;
                }
                else {
                    left = mid;
                }
            }
        }
        Integer[] to_ret = {0, 0};
        return to_ret;
    }
    public static Integer[] simpaccel_binary_search(Integer[] suffix_array, int len, int P_len,
        String reference_sentinel, String P, int char_cmp, int left_lcp, int last_left, int right_lcp, int last_right) {
        int left = 0;
        int right = len;
        while (left < right) {
            int new_char_cmp = 0;
            int mid = (int) Math.floor((left + right) / 2);
            int sac = suffix_array[mid];
            int pac = 0;
            int cmp = 0;
            for (int i = 0; i < Math.min(left_lcp, right_lcp); i++) {
                new_char_cmp++;
                sac++;
                pac++;
            }
            while ((cmp == 0) && (sac < len) && (pac < (P_len + 1))) {
                char sac_char = reference_sentinel.charAt(sac);
                char pac_char = P.charAt(pac);
                char_cmp++;
                new_char_cmp++;
                cmp = Character.compare(pac_char, sac_char);
                if (cmp != 0) {
                    break;
                }
                sac++;
                pac++;
                if ((sac >= len) && (pac < (P_len + 1))) {
                    cmp = 1;
                }
                else if ((sac < len) && (pac >= (P_len + 1))) {
                    cmp = -1;
                }
            }
            if (cmp < 0) {
                if ((mid == (left + 1))) {
                    Integer[] to_ret = {mid, char_cmp};
                    return to_ret;
                }
                else {
                    right = mid;
                    right_lcp = new_char_cmp - 1;
                    last_right = mid;
                }
            }
            else {
                if ((mid == (right - 1))) {
                    Integer[] to_ret = {right, char_cmp};
                    return to_ret;
                }
                else {
                    left = mid;
                    left_lcp = new_char_cmp - 1;
                    last_left = mid;
                }
            }
        }
        Integer[] to_ret = {0, 0};
        return to_ret;       
    }
    public static Integer[] pref_binary_search(Integer[] suffix_array, int len, int P_len,
        String reference_sentinel, String P, int char_cmp, int left, int right) {
        while (left < right) {
            int mid = (int) Math.floor((left + right) / 2);
            int sac = suffix_array[mid];
            int pac = 0;
            int cmp = 0;
            while ((cmp == 0) && (sac < len) && (pac < (P_len + 1))) {
                char sac_char = reference_sentinel.charAt(sac);
                char pac_char = P.charAt(pac);
                char_cmp++;
                cmp = Character.compare(pac_char, sac_char);
                if (cmp != 0) {
                    break;
                }
                sac++;
                pac++;
                if ((sac >= len) && (pac < (P_len + 1))) {
                    cmp = 1;
                }
                else if ((sac < len) && (pac >= (P_len + 1))) {
                    cmp = -1;
                }
            }
            if (cmp < 0) {
                if ((mid == (left + 1)) || (mid == left)) {
                    Integer[] to_ret = {mid, char_cmp};
                    return to_ret;
                }
                else {
                    right = mid;
                }
            }
            else {
                if ((mid == (right - 1))) {
                    Integer[] to_ret = {right, char_cmp};
                    return to_ret;
                }
                else {
                    left = mid;
                }
            }
        }
        Integer[] to_ret = {0, 0};
        return to_ret;
    }
    public static void main(String args[]) {
        String index = args[0];
        String queries = args[1];
        String query_mode = args[2];
        String output = args[3];
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
            ArrayList<String> query_names = new ArrayList<String>();
            ArrayList<String> query_list = new ArrayList<String>();
            try (Scanner sc = new Scanner(new File(queries))) {
                String query = "";
                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    if (line.isEmpty() == false) {
                        if (line.charAt(0) == '>') {
                            if (!query.isEmpty()) {
                                query_list.add(query);
                                query = "";
                            }
                            query_names.add(line);
                        }
                        else {
                            query += line;
                        }
                    }
                }
                if (!query.isEmpty()) {
                    query_list.add(query);
                }
            }
            try {
                FileWriter fw = new FileWriter(output);
                for (int i = 0; i < query_names.size(); i++) {
                    String query_name = query_names.get(i).substring(1);
                    int char_cmp_lb = 0;
                    int char_cmp_ub = 0;
                    int k = 0;
                    ArrayList<Integer> hits = new ArrayList<Integer>();
                    String P = query_list.get(i);
                    int P_len = P.length();
                    if (query_mode.equals("naive")) {
                        //Lower bound search
                        String lower_P = P + "#";
                        Integer[] mbs = my_binary_search(suffix_array, len, P_len,
                            reference_sentinel, lower_P, char_cmp_lb);
                        int lower_bound = mbs[0];
                        char_cmp_lb = mbs[1];
                        //Upper bound search
                        String upper_P = P + "}";
                        Integer[] umbs = my_binary_search(suffix_array, len, P_len,
                            reference_sentinel, upper_P, char_cmp_ub);
                        int upper_bound = umbs[0];
                        char_cmp_ub = umbs[1];
                        for (int j = lower_bound; j < upper_bound; j++) {
                            if ((suffix_array[j] + P_len) < len) {
                                k++;
                                hits.add(suffix_array[j]);
                            }
                        }
                        fw.write(query_name + "\t" + char_cmp_lb + "\t" + char_cmp_ub + "\t" + k);
                        for (int hit: hits) {
                            fw.write("\t" + hit);
                        }
                        fw.write("\n");
                    }
                    else if (query_mode.equals("simpaccel")) {
                        //Lower bound search
                        String simpaccel_lower_P = P + "#";
                        Integer[] simpaccel_mbs = simpaccel_binary_search(suffix_array, len, P_len,
                            reference_sentinel, simpaccel_lower_P, char_cmp_lb, 0, 0, 0, len);
                        int simpaccel_lower_bound = simpaccel_mbs[0];
                        char_cmp_lb = simpaccel_mbs[1];
                        //Upper bound search
                        String simpaccel_upper_P = P + "}";
                        Integer[] simpaccel_umbs = simpaccel_binary_search(suffix_array, len, P_len,
                            reference_sentinel, simpaccel_upper_P, char_cmp_ub, 0, 0, 0, len);
                        int simpaccel_upper_bound = simpaccel_umbs[0];
                        char_cmp_ub = simpaccel_umbs[1];
                        for (int j = simpaccel_lower_bound; j < simpaccel_upper_bound; j++) {
                            if ((suffix_array[j] + P_len) < len) {
                                k++;
                                hits.add(suffix_array[j]);
                            }
                        }
                        fw.write(query_name + "\t" + char_cmp_lb + "\t" + char_cmp_ub + "\t" + k);
                        for (int hit: hits) {
                            fw.write("\t" + hit);
                        }
                        fw.write("\n");
                    }
                    else if (query_mode.equals("prefaccel")) {
                        int new_k = tw.k;
                        int prefix = 0b00;
                        for (int j = 0; j < new_k; j++) {
                            char ch = P.charAt(j);
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
                            ArrayList<Integer> search_range = hashmap.get(prefix);
                            Integer left = search_range.get(0);
                            Integer right = search_range.get(1);
                            String prefaccel_lower_P = P + "#";
                            Integer [] prefaccel_mbs = pref_binary_search(suffix_array, len, P_len,
                                reference_sentinel, prefaccel_lower_P, char_cmp_lb, left, right);
                            int prefaccel_lower_bound = prefaccel_mbs[0];
                            String prefaccel_upper_P = P + "}";
                            Integer [] prefaccel_umbs = pref_binary_search(suffix_array, len, P_len,
                                reference_sentinel, prefaccel_upper_P, char_cmp_ub, left, right);
                            int prefaccel_upper_bound = prefaccel_umbs[0];
                            if ((left == 1176122) && (right == 1176124)) {
                                System.out.println(prefaccel_lower_bound);
                                System.out.println(prefaccel_upper_bound);
                            }
                            for (int j = prefaccel_lower_bound; j < prefaccel_upper_bound; j++) {
                                if ((suffix_array[j] + P_len) < len) {
                                    k++;
                                    hits.add(suffix_array[j]);
                                }
                            }
                            fw.write(query_name + "\t" + left + "\t" + right + "\t" + k);
                            for (int hit: hits) {
                                fw.write("\t" + hit);
                            }
                            fw.write("\n");
                        }
                        else {
                            fw.write(query_name + "\t" + 0 + "\t" + 0 + "\t" + 0);
                            fw.write("\n");
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
