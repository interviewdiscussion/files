//    //两个指针一起挪，边挪边比，如果都是字符串，直接比大小，相等继续，如果一个是字符串一个是数字，直接字符串更大，
//    //如果两个都是数字，同时遍历到数字的末尾，把整个数字取下来，对数字比大小，数值大的更大，如果相等再继续重复上面的过程，
//    //如果到一个字符串已经完了还是相等，那么如果另一个字符串也同时遍历完的话就相等，不然长的字符串更大
public class Solution {
   public static Comparator<String> NaturalComparator = new Comparator<String>() {
       @Override
       public int compare(String str1, String str2) {
           int index1 = 0;
           int index2 = 0;
           while (index1 < str1.length() && index2 < str2.length()) {
               char letter1 = str1.charAt(index1);
               char letter2 = str2.charAt(index2);
               if (letter1 == letter2 &&  !Character.isDigit(letter2)) { // both are char and equals, skip
                   index1++;
                   index2++;
               }
               else if (Character.isDigit(letter1) && Character.isDigit(letter2)){ //both are number
                   int number1 = 0;
                   int number2 = 0;
                   while (index1 < str1.length() && Character.isDigit(str1.charAt(index1))) {//avoid overflow? add count < 10 here
                       number1 = number1 * 10 + str1.charAt(index1) - '0';
                       index1++;
                   }
                   while (index2 < str2.length() && Character.isDigit(str2.charAt(index2))) {//avoid overflow? add count < 10 here
                       number2 = number2 * 10 + str2.charAt(index2) - '0';
                       index2++;
                   }
                   if (number1 > number2) {//number in str2 is smaller, so it should be before str1
                       return 1;
                   }
                   else if (number1 < number2) {
                       return -1;
                   }
               }
               else { // not equals or one is number
                   if (Character.isDigit(letter2)) {//number should be before letter
                       return 1;
                   }
                   else if (Character.isDigit(letter1)) {
                       return -1;
                   }
                   if (letter1 > letter2) {//both letter but not equals
                       return 1;
                   }
                   return -1;
               }
           }
           if (index1 == str1.length() && index2 == str2.length()) {//check all the char already and all the same
               return 0;
           }
           else if (index1 < str1.length()) {//str2 is shorter, so it should be before str1
               return 1;
           }
           return -1;
       }
   };
}