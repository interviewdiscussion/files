public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.empty() || x <= minStack.peek()) {//we only push x into minStack when it's <= minStack.peek()
            minStack.push(x);
        }
    }
    
    public void pop() {
        if (stack.empty()) {//remember to check whether the stack is empty
            return;
        }
        if (stack.peek().equals(minStack.peek())) {//use equals, not == !!!
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}