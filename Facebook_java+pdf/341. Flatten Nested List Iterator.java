/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
//flatten the list while calling hasNext(), using stack; O(n) time, O(n) space
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {//we are already give a list, so we don't need to getList()
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));//add all NestedInteger in the list
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();//remember to use getInteger() cuz what we pop out it's a NestedInteger,not an Integer
    }

    @Override
    public boolean hasNext() {//amortized O(1) time
        while (!stack.empty()) {
            if (stack.peek().isInteger()) {//if it's an integer,we return true and let the pop() take place in next()
                return true;
            }
            List<NestedInteger> list = stack.pop().getList();//if not, it's a list, we pop() and getList()
            for (int i = list.size() - 1; i >= 0; i--) {//add all NestedInteger in the list
                stack.push(list.get(i));
            }
        }
        return false;
    }
}

//flatten the list while calling NestedIterator() constructor, using stack; O(n) time, O(n) space
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    List<Integer> nums;

    public NestedIterator(List<NestedInteger> nestedList) {//we are already given a list, so we don't need to getList()
        stack = new Stack<>();
        nums = new ArrayList<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));//add all NestedInteger in the list
        }
        while (!stack.empty()) {
            if (stack.peek().isInteger()) {//if it's an integer,we return true and let the pop() take place in next()
                int num = stack.pop().getInteger();
                nums.add(num);
                continue;
            }
            List<NestedInteger> list = stack.pop().getList();//if not, it's a list, we pop() and getList()
            for (int i = list.size() - 1; i >= 0; i--) {//add all NestedInteger in the list
                stack.push(list.get(i));
            }
        }
    }

    @Override
    public Integer next() {
        return nums.iterator().next();
    }

    @Override
    public boolean hasNext() {
        return nums.iterator().hasNext();
    }
}

//flatten the list while calling NestedIterator() constructor, using recursion; O(n) time, O(n) space, O(depth) stack space
public class NestedIterator implements Iterator<Integer> {
    List<Integer> nums;

    public NestedIterator(List<NestedInteger> nestedList) {//we are already give a list, so we don't need to getList()
        nums = new ArrayList<>();
        flatten(nextedList);
    }
    
    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            if (n.isInteger()) {
                nums.add(i.getInteger());
            } else {
                flatten(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return nums.iterator().next();
    }

    @Override
    public boolean hasNext() {
        return nums.iterator().hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */