import java.util.Arrays;
class Face implements Cloneable{
    protected final int [][] grid;
    public Face(int [][] grid)
    {
        this.grid = grid;
    }

    public Face clone()
    {
        int replica [][] = new int [3][3];
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                replica[x][y] = this.grid[x][y];
            }
        }
        Face replicated = new Face(replica);
        return replicated;
    }

    public Face half()
    {
        int result [][] = new int [3][3];
        for(int x = 0;x < 3; x++){
            for(int y = 0; y < 3; y++){
                result[2-x][2-y] = this.grid[x][y];
            }
        }
        return new Face(result);
    }

    public Face left()
    {
        int result[][] = new int [3][3];
	for(int x=0; x<3; x++){
		for(int y=0; y<3; y++){
		    result[2-y][x] = this.grid[x][y];
		}
	}
	return new Face(result);
    }

    public Face right()
    {
	int result[][] = new int [3][3];
	for(int x = 0; x < 3; x++){
		for(int y = 0; y<3; y++){
		    result[y][2-x] = this.grid[x][y];
		}
	}
	return new Face(result);
    }

    public int[][] toIntArray()
    {
        return this.grid; 
    }

    @Override
    public String toString(){
        String s1 = new String();
        for(int x = 0; x < 3; x ++) {
                s1 = s1 + "\n";
            for ( int y = 0; y < 3; y++){
                s1 = s1 + String.format("%02d", this.grid[x][y]);
            }
                continue;
        }
	s1 = s1 + "\n";
        return s1;
}
}
