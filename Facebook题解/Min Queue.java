public class MinQueue {
    private Queue<Integer> queue;
    private Deque<Integer> minQueue;

    public MinQueue() {
        queue = new LinkedList<>();
        minQueue = new ArrayDeque<>();
    }

    public void offer(int x) {
        //remove all nums from the back to front that are larger than x in minQueue
        while (!minQueue.isEmpty() && minQueue.peekLast() > val) {
            minQueue.pollLast();
        }
        queue.offer(x);
        minQueue.offer(x);
    }

    public void poll() {
        if (queue.isEmpty()) {
            return;
        }
        if (queue.peek().equals(minQueue.peek())) {
            minQueue.poll();
        }
        queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public int getMin() {
        return minQueue.peek();
    }
}