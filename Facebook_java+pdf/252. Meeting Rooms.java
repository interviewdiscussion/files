/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
## Idea 
* First, the intervals has to be sorted by start time, if not, sort it yourselt 
* Compare every end[i] and start[i+1]

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (x, y) -> x.start - y.start);
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i-1].end > intervals[i].start)
                return false;
        return true;
    }
}