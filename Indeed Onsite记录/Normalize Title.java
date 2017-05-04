/* =============================================================================
Question Description
=============================================================================*/
Given a rawTitle, and a list(or array) of clean titles. For each clean title,
the raw title can get a "match point". For example, if raw title is "senior software
engineer" and clean titles are "software engineer" and "mechanical engineer", the
"match point" will be 2 and 1. In this case we return "software engineer" because
it has higher "match point".
/* =============================================================================
code
=============================================================================*/
public class Normalized_Title {
    public String getHighestTitle(String rawTitle, String[] cleanTitles){
        String res = "";
        int highScore = 0;
        for (String ct : cleanTitles){
            int curScore = getScore(rawTitle, ct);
            if (curScore > highScore){
                highScore = curScore;
                res = ct;
            }
        }
        return res;
    }
    //思路非常简单,两个title分别去查一下就行了。
    //这个下面有问题，比如a b c和d c的例子，那只能开二维矩阵去搜最高分。
    //不考虑顺序的话，就用map来记录词和位置吧。（而且它说没有重复的词，也是暗示用map）
    public int getScore(String raw, String ct){
        int s = 0, temp = 0;
        int rIdx = 0, cIdx = 0;
        String[] rA = raw.split(" ");
        String[] cA = ct.split(" ");
        while (rIdx < rA.length){
            String rCur = rA[rIdx];
            String cCur = cA[cIdx];
            if (!rCur.equals(cCur)){
                cIdx = 0;
                temp = 0;
            }
            else {
                temp++;
                cIdx++;
            }
            rIdx++;
            s = Math.max(s, temp);
            if (cIdx == cA.length) break;
        }

        return s;
    }
    public static void main(String[] args) {
        Normalized_Title test = new Normalized_Title();
        String rawTitle = "senior software engineer";
        String[] cleanTitles = {
            "software engineer",
                "mechanical engineer",
                "senior software engineer"};

        String result = test.getHighestTitle(rawTitle, cleanTitles);
        System.out.println(result);
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
raw title和clean title中有duplicate word怎么办
比如raw = "a a a b", clean = "a a b"

这样的话，靠指针就抓不出第二个a开始的aab，因为查第二个a的时候，是当做第二个a来算的。这个case
应该返回3而不是2。
那还想什么，开二维矩阵走DP吧。
/* =============================================================================
Follow Up code
=============================================================================*/
    public String getHightestTitleWithDup(String rawTitle, String[] cleanTitles){
        String res = "";
        int highScore = 0;
        String[] rA = rawTitle.split(" ");
        for (String ct: cleanTitles) {
            String[] cA = ct.split(" ");
            int temp = getScoreWithDup(rA, cA);
            System.out.println("temp is "+ temp);
            if (temp > highScore){
                highScore = temp;
                res = ct;
            }
        }
        return res;
    }
    //二维矩阵里面每个位置都要查,因为不一定是从哪个位置开始匹配的,反正复杂度都是一样的。
    public int getScoreWithDup(String[] rA, String[] cA){
        int col = rA.length;
        int row = cA.length;
        int res = 0;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++){
            String cCur = cA[i];
            for (int j = 0; j < col; j++){
                String rCur = rA[j];
                if (rCur.equals(cCur)){
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = Math.max(1, dp[i-1][j-1] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
/* =============================================================================
题目内容：
=============================================================================*/
题目内容没太看懂，大概是给出一群raw title（String），比对clean title来看得分情况。得分就是
词一致就行。
返回能拿到最高分的clean title。

如果这样的话，就直接暴力去搜，然后找出得了多少分，再维护一个最高分就行了。

题目内容不清晰，感觉还是用set记录词比较好，然后一一比对，找得分，这样的话完全不考虑词的先后顺序了
有重复词的话，就map统计词的频率，到时候上限就是raw title的词频。
/* =============================================================================
地里面经总结
=============================================================================*/
第二轮，似乎是新题，Normlize title

输入一组 raw titles, 例如"senior software engineer"，还有一组clean title "software engineer",
"mechanical engineer"
每个raw title 求匹配得分最高的clean title，这里 senior software enginner
和software engineer 有两个词匹配，得分2；和mechanical engineer有一个词匹配，得分1，返回software engineer

follow up 是raw title和clean title中有duplicate word怎么办
//干，这题是16年11月中的面经，还是要准备的，而且只出了一次。