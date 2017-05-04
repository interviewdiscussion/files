

hashmap存频率，然后维持count最大的
public char hepler(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        int count=0;
        char cc='0';
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            Character.toLowerCase(c); 不区分大小写
            if(!Character.isLetterOrDigit(c)) continue;去掉其他字符
            if(c==' ') continue; 去掉空格加这个
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else map.put(c,1);
            if(map.get(c)>count){
                count=map.get(c);
                cc=c;
            }
        }
        return cc;
    }
优化时间，count维持两个，频率第一大，第二大，1-2如果大于剩下的，就可以了。
        HashMap<Character,Integer> map=new HashMap<>();
        int count1=0,count2=0;
        char cc='0',ss='0';
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!Character.isLetterOrDigit(c)) continue;
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else map.put(c,1);
            if(map.get(c)>=count1){
                count2=count1;
                ss=cc;
                count1=map.get(c);
                cc=c;
            }else{
                if(map.get(c)>=count2){
                    count2=map.get(c);
                    ss=c;
                }
            }
            if(count1-count2>s.length()-i){
                return cc;
            }
        }
        return cc;