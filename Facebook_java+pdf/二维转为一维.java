    int[] arr=new int[grid.length*grid[0].length];
    for(int i=0;i<grid.length;i++){
        for(int j=0;j<grid[0].length;j++){
            arr[i*grid[0].length+j]=grid[i][j];
        }
    }