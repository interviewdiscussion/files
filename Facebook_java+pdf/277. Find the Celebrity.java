/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
O(n) O(1)
# Idea 
* The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
* Find candidate
* Check candidate 
public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n < 2) {
            return -1;
        }
        int possible = 0;
        for (int i = 1; i < n; i++) {
            if (knows(possible, i)) {//if possible knows i, i will be the possible one; else possible doesn't change
                possible = i;
            }
        }
        for (int i = 0; i < n; i++) {//check whether final possible value is the celebrity
            if (possible != i && ((knows(possible, i) || !knows(i, possible)))) {
                return -1;//if i is not p, and p knows i, or i doesn't know p, then celebrity doesn't exist
            }
        }
        return possible;
    }
}
//each time we call knows(a, b), we will know at least one fact, so for each call, a possible person will be left
//1.if a knows b, a is not possible to be the celebrity
//2.if a don't know b, b is not possible to be the celebrity
