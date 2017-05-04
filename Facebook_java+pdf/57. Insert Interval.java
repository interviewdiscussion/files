这题就看第一个代码
// input: sorted, no overlaps; output should be sorted; O(n) time, O(1) space
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null) {
            return intervals;
        }
        // if the input is unsorted, add: (which will make this algo O(nlogn) time)
        // Collections.sort(intervals, new Comparator<Interval>(){
        //     public int compare(Interval a, Interval b) {
        //         return a.start - b.start;
        //     }
        // });
        List<Interval> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));//add all intervals before the insert position of the new interval
            //totalTime += intervals.get(i).end - intervals.get(i).start;
        }//then we try to merge all intervals that can be merged
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {//这里的等号特别容易忘
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);//remember to update the start !!!
            newInterval.end = Math.max(newInterval.end, intervals.get(i++).end);
            //totalTime += intervals.get(i).end - intervals.get(i).start;
        }
        res.add(newInterval);//add the newInterval when no overlap exists
        while (i < intervals.size()) {
            res.add(intervals.get(i++));//add the rest of the non overlapping intervals
            //totalTime += intervals.get(i).end - intervals.get(i).start;
        }
        return res;
    }
}
// if output is total interval time, then array should be sorted to get the time
// if output should be sorted, we may need to sort the array
// if output should be not overlapping, we may need to merge intervals

// input: unsorted, no overlaps; output: can be unsorted, return total time; O(n) time, O(n) space
public class Solution {
    public int insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null) {
            return 0;
        }
        int totalTime = 0;
        //if you need to insert intervals while calculating time,add:
        //List<Interval> res = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start <= newInterval.end || intervals.get(i).end >= newInterval.start) {//merge overlaps
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);//remember to update the start!!!
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            } else {
                totalTime += intervals.get(i).end - intervals.get(i).start;
                //res.add(intervals.get(i));//add all non-overlapping intervals
            }
        }
        totalTime += newInterval.end - newInterval.start;//we add the time of merged newInterval here
        //if you need to insert intervals while calculating time,add:
        //res.add(newInterval);//add the newInterval when all intervals have been checked so that no overlap exists
        return totalTime;
    }
}

// input: unsorted, no overlaps; output: can be unsorted; O(n) time, O(1) space
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null) {
            return intervals;
        }
        List<Interval> res = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start <= newInterval.end || intervals.get(i).end >= newInterval.start) {//merge overlaps
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);//remember to update the start too!!!
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            } else {
                res.add(intervals.get(i));//add all non-overlapping intervals
            }
        }
        res.add(newInterval);//add the newInterval when all intervals have been checked so that no overlap exists
        return res;
    }
}