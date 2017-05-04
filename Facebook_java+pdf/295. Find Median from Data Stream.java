## Idea 
* Two heap ( Small and large)
* Min for large , max for small

```
Input [2,3]

min     max                   media 
	   	 [2]                     2
[-2]	    [3]                    2.5

```
## Time 
* O(log n) + O(1)
public class MedianFinder {
    private Queue<Long> small = new PriorityQueue(),
                        large = new PriorityQueue();

    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());//add O(n)
        if (large.size() < small.size())
            large.add(-small.poll());
    }

    public double findMedian() {
        return large.size() > small.size()
               ? large.peek()//O(1)
               : (large.peek() - small.peek()) / 2.0;
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
