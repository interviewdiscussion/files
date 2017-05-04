//return friends of friends that are not this person's friends
第二层的friends排序，选出k个共同好友做多的friends
//Friend Recommendation:bucket sort,O(m) time,O(n) space,m is num of person's friends' friends,n is num of valid friends
mutual friends， 已知一个function可以return 给定某人的friends。 找出A B的mutual friends:这个用hashset
public class Solution {
    public class Person {
        int id;
        HashSet<Integer> friends = new HashSet<>();
    }

    private List<Integer> friendsRecommend(Person person, int k) {
        List<Integer> res = new ArrayList<>();
        if (person == null) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int friend : person.friends) {
            for (int recommend : friend.friends) {
               int id = recommend.id;
               if (person.friends.contains(id) || id == person.id) {
                   continue;//if person already has this friend,or this is person himself/herself,continue
               }
               if (!map.containsKey(id)) {//we use hashmap because if friend a has friend c, friend b has friend c
                   map.put(id, 0);
               }
               map.put(id, map.get(id) + 1);
           }
        }
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<Integer>());//we use frequency as index, so <= length
        }
        for (int key : map.keySet()) {//unlike heap solution, we only need keySet() here
            buckets.get(map.get(key)).add(key);//get the id(key), add the id to its frequency bucket
        }
        for (int i = buckets.size() - 1; i >= 0; i--) {
            for (int j = 0; j < buckets.get(i).size(); j++) {//start adding the vals at the last bucket's last index
                res.add(buckets.get(i).get(j));
                if (res.size() == k) {
                    return res;//this two loops are O(k) time, when res has k nums, return it
                }
            }
        }
        return res;
    }
}

//Friend Recommendation: Quick Select, average O(m + n) time, O(1) space, worst case O(m + n^2) time
//m is num of person's friends' friends,n is num of valid friends
public class Solution {
    public class Person {
        int id;
        HashSet<Integer> friends = new HashSet<>();
    }

    private List<Integer> friendsRecommend(Person person, int k) {
        List<Integer> res = new ArrayList<>();
        if (person == null) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int friend : person.friends) {
            for (int recommend : friend.friends) {
               int id = recommend.id;
               if (person.friends.contains(id) || id == person.id) {
                   continue;//if person already has this friend,or this is person himself/herself,continue
               }
               if (!map.containsKey(id)) {
                   map.put(id, 0);
               }
               map.put(id, map.get(id) + 1);
           }
        }
        
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        int left = 0;
        int right = list.size() - 1;
        int index = -1;
        while (true) {
            int pos = partition(list, left, right);
            if (pos + 1 == k) {
                index = pos;
                break;
            } else if (pos + 1 > k) {
                right = pos - 1;
            } else {
                left = pos + 1;
            }
        }
        if (index == -1) {
            return res;
        }
        for (int i = 0; i <= index; i++) {
            int id = list.get(i).getKey();
            res.add(id);
        }
        return res;
    }
    
    private int partition(ArrayList<Map.Entry<Integer, Integer>> list, int left, int right) {
        Random rand = new Random();
        int index = rand.nextInt(right - left + 1) + left;//remember to add + left !!!
        Map.Entry<Integer, Integer> pivot = list.get(index);
        int pVal = pivot.getValue();
        swap(list, left, index);//index, not pivot !!
        int l = left + 1;
        int r = right;
        while (l <= r) {
            int lVal = list.get(l).getValue();
            int rVal = list.get(r).getValue();
            if (lVal < pVal && rVal > pVal) {
                swap(list, l, r);
            }
            if (lVal >= pVal) {
                l++;
            }
            if (rVal <= pVal) {
                r--;
            }
        }
        swap(list, left, r);
        return r;
    }
    
    private void swap(ArrayList<Map.Entry<Integer, Integer>> list, int left, int right) {
        Map.Entry<Integer, Integer> temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}