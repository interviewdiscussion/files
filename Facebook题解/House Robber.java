public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int rob = 0;//the max profit we can get from 0 to i house, if we rob curr i house
        int notRob = 0;//the max profit we can get from 0 to i house, if we don't rob curr i house
        for (int i : nums) {
            int temp = rob;//store the value of "we rob last house"
            rob = notRob + i;//if rob curr house,we can't rob last house,so rob curr = not rob last house + curr house's money
            notRob = Math.max(notRob, temp);//if we don't rob curr house,we choose the max money from we rob/notRob last house
            max = Math.max(rob, notRob);//try to update the max from we rob/notRob curr house
        }
        return max;
    }
}

//output all houses that are robbed
public class Solution {
    public static ArrayList<Integer> rob(int[] nums) {
        ArrayList<Integer> rob_houses = new ArrayList<>();
        ArrayList<Integer> notRob_houses = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rob_houses;
        }
        boolean robCurr = true;
        int max = 0;
        int rob = 0;//the max profit we can get from 0 to i house, if we rob curr i house
        int notRob = 0;//the max profit we can get from 0 to i house, if we don't rob curr i house
        for (int i = 0; i < nums.length; i++) {
            int temp = rob;//store the value of "we rob last house"
            ArrayList<Integer> tempRob_houses = rob_houses;

            rob = notRob + nums[i];//if rob curr house,we can't rob last house,so rob curr = not rob last house + curr house's money
            rob_houses = new ArrayList<>(notRob_houses);
            rob_houses.add(i);

            if (notRob < temp) {
                notRob_houses = tempRob_houses;
            }
            notRob = Math.max(notRob, temp);//if we don't rob curr house,we choose the max money from we rob/notRob last house

            if (rob >= notRob) {
                robCurr = true;
            } else {
                robCurr = false;
            }
            max = Math.max(rob, notRob);//try to update the max from we rob/notRob curr house
        }
        if (robCurr) {
            return rob_houses;
        }
        return notRob_houses;
    }
}