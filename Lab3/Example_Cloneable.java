class Clone{

    Face a.array = [1,2,3,4,5,6,7,8,9];

    int Barr [][] = new int [3][3];
    for(int y = 0 ; y < 3 ; y++){
        for(int x = 0 ; x < 3; x++){
            barr[y][x] = array[y][x];
        }
    }
    Face b = new Face(barr); // When an int array is changed in Face B, it won't affect the Face in A. (Deepl cloning etc.)
}
