public class test{
    public static void main(String[] args){
        System.out.println("test 2 for unit 8");

        int myArray[][] = {{0, 0, 3}, {0, 0, 0}, {7, 0, 0}};
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(myArray[i][j]);
            }
            System.out.println();
        }
      
        

        int[][] arr = {{3, 2, 1}, {4, 3, 5}};
 
        for (int row = 0; row < arr.length; row++){
            for (int col = 0; col < arr[row].length; col++){
                if (col > 0){
                    if (arr[row][col] >= arr[row][col - 1]){
                        System.out.println("Condition one");
                    }
                }
                if (arr[row][col] % 2 == 0){
                    System.out.println("Condition two");
                }
            }
        }
    }
}