// below is a one-end bfs solution: O(mn) time
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (wordList == null) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();//store the words that are transformed to the next level
        HashSet<String> used = new HashSet<>();//store the words that we have used
        wordList.add(endWord);//remember to add the endWord to dict
        queue.offer(beginWord);
        used.add(beginWord);
        int length = 1;//should be 1
        while (!queue.isEmpty()) {//O(m)
            length++;//for each level we increase the length
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                for (String nextWord : getNextWords(wordList, used, currWord)) {//O(26n)
                    if (nextWord.equals(endWord)) {//if we reach the endWord
                        return length;
                    }
                    used.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;//no available ways
    }
    
    private ArrayList<String> getNextWords(Set<String> dict, HashSet<String> used, String word) {
        ArrayList<String> nextWords = new ArrayList<>();
        for (char c = 'a'; c < 'z'; c++) {//O(26)
            for (int i = 0; i < word.length(); i++) {//O(n)
                if (c == word.charAt(i)) {//if it's the same as the curr word
                    continue;
                }
                String nextWord = word.substring(0, i) + c + word.substring(i + 1);//get the next possible word
                if (used.contains(nextWord)) {//if the word has been used
                    continue;
                }
                if (dict.contains(nextWord)) {//if the word it's in the dict
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}

// two-end bfs solution (time complexity is the same as below)
public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    if (beginWord.equals(endWord)) {
        return 1;
    }
    if (wordList == null) {
        return 0;
    }
    int pathLength = 2;
    Set<String> start = new HashSet<>();
    Set<String> end = new HashSet<>();
    start.add(beginWord);
    end.add(endWord);
    wordList.remove(beginWord);
    wordList.remove(endWord);
    
    while(!start.isEmpty()){
        if(start.size() > end.size()){
            Set<String> temp = start;
            start = end;
            end = temp;
        }
        Set<String> next = new HashSet<>();
        for(String cur : start){
            char[] strArray = cur.toCharArray();
            for(int i = 0; i < strArray.length;i++){
                char old = strArray[i];
                for(char c = 'a';c <= 'z';c++){
                    strArray[i] = c;
                    String str = String.valueOf(strArray);
                    if(end.contains(str)){
                        return pathLength;
                    }
                    if(wordList.contains(str)){
                        next.add(str);
                        wordList.remove(str);
                    }
                }
                strArray[i] = old;
            }
        }
        start = next;
        pathLength++;
    }
    return 0;
}

//output a valid path (you can also create a string with the result array)
class WordLadder {
    public List<String> findLadder(String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> path = new HashMap<>();
        List<String> result = new ArrayList<>();
        queue.offer(start);
        path.put(start, "");
        while (!queue.isEmpty()) {
            String word = queue.poll();
            for (int i = 0; i < word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (word.charAt(i) == c) {
                        continue;
                    }
                    String newWord = word.substring(0, i) + c + word.substring(i + 1);
                    if (newWord.equals(end)) {
                        path.put(end, word);
                        break;
                    }
                    if (dict.contains(newWord) && !path.containsKey(newWord)) {
                        path.put(newWord, word);
                        queue.offer(newWord);
                    }
                }
            }
        }
        if (!path.containsKey(end)) {
            return result;
        }
        String mover = end;
        while (!path.get(mover).equals("")) {
            result.add(mover);
            mover = path.get(mover);
        }
        result.add(start);
        Collections.reverse(result);
        return result;
    }
}