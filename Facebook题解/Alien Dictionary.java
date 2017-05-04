public class Solution {
    public String alienOrder(String[] words) {
        String res = "";
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<Character, HashSet<Character>> map = new HashMap<>();//key: char, value: chars that are after the key
        HashMap<Character, Integer> degree = new HashMap<>();//key: char, value: num of chars that are before the key
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int length = Math.min(curr.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {//find first different chars, if they are different, we will know one of the orders of chars
                    if (!map.containsKey(c1)) {
                        map.put(c1, new HashSet<>());
                    }
                    HashSet<Character> set = map.get(c1);
                    if (!set.contains(c2)) {//for each char c2 that is after c1, we only need to increase the degree once
                        set.add(c2);//and that's why we need a hashset, instead of a list, to store chars after c1
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;//important! if c1 != c2, we will not need to check the rest of the chars 
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char c1 = queue.poll();
            res += c1;
            if (map.containsKey(c1)) {//check whether there are chars after c1
                for (char c2 : map.get(c1)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) {
                        queue.offer(c2);
                    }
                }
            }
        }
        if (res.length() != degree.size()) {//should check whether res length == the num of kinds of chars
            return "";
        }
        return res;
    }
}