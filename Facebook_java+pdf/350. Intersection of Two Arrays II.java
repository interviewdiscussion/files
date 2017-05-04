public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> arr=new ArrayList<>();
        for(int num:nums1){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }
        for(int num:nums2){
            if(map.containsKey(num)&&map.get(num)>0){
                arr.add(num);
                map.put(num,map.get(num)-1);
            }
        }
        int[] res=new int[arr.size()];
        int k=0;
        for(Integer num:arr){
            res[k++]=num;
        }
        return res;
    }
}
//You can use sort as well, which is the same as problem I

// What if the given array is already sorted? How would you optimize your algorithm?
// Use two pointers

// What if nums1's size is small compared to nums2's size? Which algorithm is better?
// Use hash on nums1 and scan nums2, less space but more time
// Use hash on nums2 and scan nums1, less time but more space
// Or maybe use binary search on nums2(if sorted)

// What if elements of nums2 are stored on disk, and memory is limited so that you can't load all elements into memory at once?
// Maybe use binary search on nums1(sort it first)
// Or maybe use hash on nums1(need space)

// Binary search如果找到了一个元素index，那就用这次的index作为下次binary search的开始。可以节约掉之前的东西，不用search了。
// 然后问，如果找不到呢，如何优化。说如果找不到，也返回上次search结束的index，然后下次接着search。
// 就是上一次找到了，就用这个index继续找这次的；如果找不到，也有一个ending index，就用那个index当starting index。
// 比如[1, 89，100]，去找90；如果不存在，那么binary search的ending index应该是89，所以下次就从那个index开始。
// 如果找不到，会返回要插入的位置index + 1，index是要插入的位置，我写的就是返回要插入的index的。
// 但是不管返回89还是100的index都无所谓，反正只差一个，对performance没有明显影响的。

// Sort & two pointers O(nlogn) time and O(n) space(if consider the arraylist which is used to store result)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[]{};
        }
        ArrayList<Integer> list = new ArrayList<>();//you can use hashset here as well
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // use this code if we don't accept dups in result
                // if (list.size() == 0 || list.get(list.size() - 1) != nums1[i]) {
                //     list.add(nums1[i]);
                // }
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}