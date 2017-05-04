## Idea 
* For every string, convery in to characters array and sort
* So strings within the same group can be seen as a same string
* Create a map, and use the same string as a key, grouped group anagrams as value
* Generate result based on value
// hash+sort solution: O(mnlogn) time, O(m) space, m is the num of strs, n is the length of strs
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        for(String str:strs){
            char[] ch=str.toCharArray();
            Arrays.sort(ch);
            String s=new String(ch);
            if(map.containsKey(s)){
                List<String> sub=res.get(map.get(s));
                sub.add(str);
            }else{
                List<String> sub=new ArrayList<>();
                sub.add(str);
                res.add(sub);
                map.put(s,res.size()-1);
            }
        }
        return res;
    }
}
// hash+counting sort: O(mn) time, O(m) space, m is the num of strs, n is the length of strs
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];//cuz inputs are lowercase letters, we only need 26
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;//count the occurrences of each char
            }
            String anagram = "";//build a string key, eg."aabcccdd" -> 2a1b3c2d
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {//remember to add this !!! we only need chars that exist in the string !!! else get TLE!!!
                    anagram += String.valueOf(count[i]) + String.valueOf((char)('a' + i));//use (char)('a'+i) to build string!
                }
            }
            if (!map.containsKey(anagram)) {
                map.put(anagram, new ArrayList<>());
            }
            map.get(anagram).add(s);
        }
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {//Map.Entry, not Map.entry !!!!!
            res.add(entry.getValue());
        }
        return res;
    }
}