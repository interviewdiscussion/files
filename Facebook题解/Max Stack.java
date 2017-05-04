public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (maxStack.empty() || x >= maxStack.peek()) {//we only push x into minStack when it's <= minStack.peek()
            maxStack.push(x);
        }
    }
    
    public void pop() {
        if (stack.empty()) {//remember to check whether the stack is empty
            return;
        }
        if (stack.peek().equals(maxStack.peek())) {//use equals, not == !!!
            maxStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMax() {
        return maxStack.peek();
    }
}