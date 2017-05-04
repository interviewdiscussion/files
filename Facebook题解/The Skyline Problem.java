public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> lines = new ArrayList<>();
        for (int[] bldg : buildings) {//remember to add new !!!
            lines.add(new int[]{bldg[0], -bldg[2]});//add signs to distinguish if it's the left or right side of buildings
            lines.add(new int[]{bldg[1], bldg[2]});//nega means left side of a building,posi means right side of a building
        }
        Collections.sort(lines, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];//sorted from left to right (x coordinate)
                }
                return a[1] - b[1];//if x coordinate equals, sorted from smaller height to bigger height
            }
        });
        //if a[0]==b[0],return a[1]-b[1],which means let smaller height stay before bigger height.here are two main cases:
        //case A:both lines are left sides/right sides of buildings
        //1:both left(neg):higher line is smaller,so we offer higher line first,outputs a point,lower line changes nothing
        //2:both right(pos):lower line is smaller,so we poll lower line first,changes nothing,higher line outputs a point
        //3:both same heights,who's the first doesn't matter
        //case B:one of the line is the left side of a building, the other is the right side of a building
        //1:height:left(neg) < or > right(pos),left always the first to be offered,if<,outputs high now,if>,outputs low later
        //2:right side of bldg1 and left side of bldg2 have same heights,then no point is put into res cuz top doesn't change
        //cuz before we poll right side, left side has been offered,so that heap.top holds,prev always == curr,no point added
        TreeMap<Integer, Integer> map = new TreeMap<>();//the default order of treemap is also ascending
        map.put(0, 1);//to avoid processing an empty treemap, why not use treeset? cuz treemap can store duplicated values
        int prev = 0;
        for (int[] line : lines) {
            if (line[1] < 0) {
                map.put(-line[1], map.getOrDefault(-line[1], 0) + 1);//we need to turn the negative heights back to positive
            } else {
                map.put(line[1], map.getOrDefault(line[1], 0) - 1);
                if (map.get(line[1]) == 0) {
                    map.remove(line[1]);
                }
            }
            int curr = map.lastKey();//we need max height,cuz it's in acsending order,we use lastKey(),instead of firstKey()
            if (prev != curr) {//add a point to res only when the curr height changes comparing to prev height
                res.add(new int[]{line[0], curr});//remember to add new !!!
                prev = curr;//update prev !
            }
        }
        return res;
    }
}
// sort + treemap: O(nlogn) time, O(n) space, note that treemap is sorted by its keys, not values!!