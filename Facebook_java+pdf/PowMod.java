class myCode {
    public static void main(String[] args) {
        myCode sol = new myCode();
        int res = sol.powmode(3,6,7);
        System.out.print(res);
    }
    public int powmode(int a , int b , int c){
        if( b == 0) return 1;
        else if ( b == 1 ) return a % c;
        a = a % c;
        if ( b % 2 == 0 ) return powmode( a * a, b / 2 , c );
        else a = a * powmode( a * a, b / 2 , c );
        return a % c;               
    }   
}