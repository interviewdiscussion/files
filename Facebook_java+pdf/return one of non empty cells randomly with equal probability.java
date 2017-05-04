给你一个数字n, 代表从1->n  n个slot， 再给一个数组存着空slot的id. 让你同概率下randomly 返回一个非空的slot。
example:
n = 8, slots: 1,2,3,4,5,6,7,8
emptycells = {3, 5}
non empty cells: 1,2,4,6,7,8
return one of non empty cells randomly with equal probability.

public int returnEmptyCell(int slots , int[] emptyCell){
        Set<Integer> hs = new HashSet<>();
        for(int i = 0 ; i < emptyCell.length ; i++){
            hs.add(emptyCell[i]);
        }
        List<Integer> list  = new ArrayList<>();
        for(int i = 1 ; i <= slots ; i++){
            if(!hs.contains(i)){
                list.add(i);
            }
        }
        int rand = new Random().nextInt(list.size());
        return list.get(rand);
    }
