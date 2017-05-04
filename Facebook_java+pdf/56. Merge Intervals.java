// input is unsorted and has some overlapping intervals, output should be sorted: O(nlogn) time, O(1) space(res doesn't count)
## Idea
* Sorting intervals by their staring pointers 
* Take the first interbal and compare its end with next intervals starts
* Case 1 : If we find overlap, we update the end to be the max end of overlapping intervals
* Case 2 : If we find no overlapping interval, we add it to result and start over
1，正常题O(nlogn) time, O(1)
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {//size(), not length !!!
            return intervals;
        }
        //O(nlogn)
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        int start = intervals.get(0).start;//remember to add .start !!!
        int end = intervals.get(0).end;//remember to add .end !!!
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.start <= end) {//if overlap, update end, which is needed to create a new interval
                end = Math.max(end, i.end);
            } else {
                res.add(new Interval(start, end));//if not overlapped, we can create a new interval
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end));//remember to add the last interval
        return res;
    }
}
返回总时间
// input is unsorted and has some overlapping intervals, output is the total non-overlapping time; O(nlogn) time, O(1) space
public class Solution {
    public int totalTime(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {//size(), not length !!!
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        //you can also merge intervals before calculating,which makes calculation easier,but takes some memory(new arraylist)
        int total = 0;
        Interval prev = new Interval(0, 0);
        for (Interval curr : intervals) {
            if (prev.end < curr.start) {
                total += curr.end - curr.start;//add the whole part(non-overlapping)
                prev = curr;
            } else if (curr.end > prev.end) {
                total += curr.end - prev.end;//only add the non overlapping part after prev.end
                prev = curr;
            }
            //else curr.end<=prev.end(curr inside prev),don't calculate anything,and prev isn't updated(prev.end is bigger)
        }
        return total;
    }
}
// if the format of intervals are "March, 2014" etc, first convert it to "201403" by "2014" + "03"(hashmap:March->03)
// or first convert it to 2014 * 12 + 3, if the output is num of months
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=160738&fromuid=109727