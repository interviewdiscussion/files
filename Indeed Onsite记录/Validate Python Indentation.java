/* =============================================================================
Question Description:
Python is validate by indent before each line of code, so given a list of Strings
which indicates the lines of Python code. Validate if it meets the requirement
=============================================================================*/
/* =============================================================================
code
=============================================================================*/
public class Validate_Python_Indentation {
    public boolean validate(String[] lines){
        //就用stack来存之前的line就行
        Stack<String> stack = new Stack<>();
        for (String line : lines){
            int level = getIndent(line);
            //先检查是不是第一行
            if (stack.isEmpty()){
                if (level != 0) {
                    System.out.println(line);
                    return false;
                }
            }
            //再检查上一行是不是control statement
            else if (stack.peek().charAt(stack.peek().length()-1) ==':'){
                if (getIndent(stack.peek()) + 1 != level){
                    System.out.println(line);
                    return false;
                }
            }
            else {
                while (!stack.isEmpty() && getIndent(stack.peek()) > level){
                    stack.pop();
                }
                if (getIndent(stack.peek()) != level){
                    System.out.println(line);
                    return false;
                }
            }
            stack.push(line);
        }
        return true;
    }
    //这里如果它说n个空格算一次tab的话，就最后返回的时候res/n好了。
    public int getIndent(String line){
        int res = 0;
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == ' '){
                res++;
            }
            else break;
        }
        return res;
    }
    public static void main(String[] args) {
        Validate_Python_Indentation test = new Validate_Python_Indentation();
        String[] lines = {
                "def:",
                " abc:",
                "  b2c:",
                "   cc",
                " b5c",
                "b6c"
        };
        System.out.println(test.validate(lines));
        //先这样吧，应该行了。
    }
}

/*============= Following Code Credit to Zhu Siyao ===============*/
public class valid_python_indention {
    public static boolean valid_python_indentation(List<String> inputs){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<inputs.size();i++){
            String str =  inputs.get(i);
            String abbr = getAbbr(str);
            int level = str.length()-abbr.length();

            if(i!=0 && inputs.get(i-1).charAt(inputs.get(i-1).length()-1)==':'){
                if(level<=stack.peek()) return false;
            }else{
                while(!stack.isEmpty() && level<stack.peek()) stack.pop();
                if(!stack.isEmpty() && level!=stack.peek()) return false;

            }
            stack.push(level);
            System.out.println(level);
        }

        return true;

    }

    private static String getAbbr(String str) {
        String result = str.trim();
        return result;
    }

    public static void main(String[] args){
        List<String> inputs = new ArrayList<>();
        inputs.add("def");
        inputs.add("abc:");
        inputs.add("  bcc");
        inputs.add("  abc:");
        inputs.add("    def");
        inputs.add("    def");
        inputs.add("  bcc");

        System.out.println(valid_python_indentation(inputs));
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
/* =============================================================================
Follow Up code
=============================================================================*/

/* =============================================================================
题目内容：
=============================================================================*/
判断一段Python代码是否是合法的，会给出4个规则。
已知两个，1.第一行不能缩进。2.一行冒号结尾的code，下一行必须比这一行缩进的多。
主要是看缩进对不对。
要求返回第一个出错的行数。
照着规则去比对，其实stack一下就行，遇到伸出头的就一路弹出。就是把block给删除掉
猜测另外两个规则是和结尾有关，估计就是检查一下stack.size就好了。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> parse 一段Python代码，根据缩进规则，比如第一行不能有空格，前一行以冒号结尾的话，下一行空格数要比上一行多。。。
<B> python语言check，就是根据几条给定的rules，要求check一段python code缩进正确与否。
<C> 给一段Python代码，还有一些列规则，然后写程序检测该代码是否符合该规则（主要是Python的缩进规则）。
<D> 给了一些规则，但是记不起所有了，只记得2个规则是：
    1.如果一行代码以':'（冒号）结尾，那么说明它是一个control statement，紧接着的下一行需要缩进（起码要比‘：’这一行要缩进）
    2.代码开头这一行（第一行）不能缩进
<E> 大家都知道python是没有括号的，让写一个验证python语句是否合法。就是看缩进是不是正确
<F> 一道题，大概意思是给一些python代码的rule，全部符合就是valid的，否则就是invalid的，
    给你一段代码（String的形式），判断是不是valid。不难。也问了下怎么优化，细节不太记得了
<G> validate python codes. Python is validated by the indents before each line of code,
    so given a vector of strings (line of code), validate if it meets python requirement.
    I remember there are 4 basic rules provided. The solution is to use a stack. Not a hard problem.