//below iss a dfs solution,which is faster than bfs cuz no dups are created, but stack overflow may occur if string too long.
//you can use bfs which needs a hashset, a queue and a boolean found. if curr string is valid, add it into res, found = true,
//so that only curr level's strings in the queue will be checked later(cuz we only need min ans); if curr string isn't valid,
//try to remove one char(par) i<s.length() from the string, then add the new string to the queue(if !containsKey)
这题就是DFS做最好，BFS的不要看    O(nk)
k is the total "number of recursive calls"
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(res, s, 0, 0, new char[]{'(', ')'});
        return res;
    }
    private void remove(List<String> res, String s, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;//if the prefix is in order legal(that is,within curr prefix,num of '(' is >= num of ')')
            //which means we can search j within previous legal prefix to make current prefix to i legal(so check last_j to i)
            for (int j = last_j; j <= i; j++) {//j <= i, not j < i !!!
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {//if it's first or not consecutive
                    remove(res, s.substring(0, j) + s.substring(j + 1), i, j, par);//don't forget to update last_i&last_j
                }
            }
            return;//important! this can avoid dups, too
        }
        String reversed = new StringBuilder(s).reverse().toString();//see how to get the reversed string
        if (par[0] == '(') {//if we haven't checked the reversed string
            remove(res, reversed, 0, 0, new char[]{')', '('});
        } else {//if the reversed string has been checked
            res.add(reversed);//we need to reverse the string twice in total
        }
    }
}
// bfs
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {//no s.length() == 0 !!! for "" we need to add it to res cuz it's a valid string
            return res;
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                res.add(s);
                found = true;//then we will only check the rest of the strings in queue(same level), cuz we need min ans
            }
            if (found) {
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {//make sure it's a par, not a digit/letter/etc.
                    continue;
                }
                String next = s.substring(0, i) + s.substring(i + 1);//remove 1 par to get next possible valid string
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }
}

