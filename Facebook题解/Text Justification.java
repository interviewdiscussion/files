public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();
        for (int i = 0, currLineWords = 0, wordsLength = 0; i < words.length; i += currLineWords) {//initialize ints here
            currLineWords = 0;//we need to update these two variable for each line of text
            wordsLength = 0;
            //we first count the num of words we can add to curr line, and the length of words we can add to curr line
            while (i + currLineWords < words.length && wordsLength + words[i + currLineWords].length() <= L - currLineWords) {
                wordsLength += words[i + currLineWords++].length();
            }//currWordsLength + nextWordlength <= L - currLineWords(stands for min num of spaces needed if next word added)
            //L - currLineWords means the rest of spaces excluding the spaces of whitespaces
            
            //this part starts to construct spaces and strings. we first record how many spaces and extraspaces needed
            //1.L - wordsLength means the total spaces needed
            //2.currLineWords - 1 means the num of part of spaces between words,eg.4 words have 3 parts
            StringBuilder temp = new StringBuilder(words[i]);
            StringBuilder spaces = new StringBuilder();
            int extraSpaces = 0;//!!! need to put this variable outside of loop cuz we will need this in the next loop !!!
            if (currLineWords != 1) {//!!! else / (currLineWords-1) wrong!if only have 1 word,don't need spaces between words
                int numOfSpaces = (L - wordsLength) / (currLineWords - 1);//min num of spaces needed for each part of spaces
                extraSpaces = (L - wordsLength) % (currLineWords - 1);//extra num of spaces needed for dividing evenly
                for (int j = 0; j < numOfSpaces; j++) {
                    spaces.append(" ");//construct the min num of spaces
                }
            }
            
            //for each loop, we first add whitespaces and then add the word
            for (int j = 0; j < currLineWords - 1; j++) {//j:jth part of spaces between curr line's words
                if (i + currLineWords == words.length) {//if all words hav been included(last line),only add " " between words
                    temp.append(" ");
                } else {//else we need to evenly add the spaces
                    if (extraSpaces-- > 0) {//if have extra spaces to add,add 1 for each part til all added from left to right
                        temp.append(" ");
                    }
                    temp.append(spaces);
                }
                temp.append(words[i + j + 1]);//cuz we already add the words[i], so we start at words[i + 1 + j]
            }
            
            //if we only have 1 word in curr line, or it's the last line of text, we fill in the rest of string with spaces
            for (int j = temp.length(); j < L; j++) {//int j, not int i !!!
                temp.append(" ");
            }
            res.add(temp.toString());//add the word into res
        }
        return res;
    }
}