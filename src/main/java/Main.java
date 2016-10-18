import java.io.FileNotFoundException;

public class Main implements DS2Interface {
	//
	
	/* General comments that apply to all methods:
 	 * 	1) Variable initialization takes constant time.
	 * 	2) We tried to keep the algorithms as close to the provided (book and lectures) pseudo-code as possible
	 *	3) The pseudo-code of the algorithms sometimes assumes that the first element of an array has index '1', 
	 *	whereas in Java arrays start with the index '0', so where relevant we initialized counters in loops that 
	 *	are 1 less than those used in the pseudo-code
	 *	4) As stated in the assignment for the rod cutting exercises, we infer the length of a rod being one less 
	 *	than the length of the input array (to allow the input of a single 0 in the input). 
	 */

	/* 	General comments about our recursiveRodCutting implementation:
	 * 	Our implementation follows the pseudo-code very closely except for the difference in input, where as 
	 * 	opposed to the pseudo-code, we only get the price list as input, and treat its length as the rod length, 
	 * 	as per the assignment. Because of this, our implementation has the same time complexity as the pseudo-code,
	 *  O(2^n), if we assume that the 'copyArray' method takes constant time. In reality that method has a time complexity
	 *  of Theta(length), which in turn makes the actual time complexity of recursiveRodCutting considerably worse:
	 *  instead of O(2^n) which is already quite expensive, we have O(2^(n^2)). This is a direct result of the difference 
	 *  in input that the method takes. The time complexity of O(2^n) could be easily achieved if the method input would 
	 *  mimic that of the pseudo-code.
	 */
    @Override
    public int recursiveRodCutting(int[] input) {
        int length=input.length-1;
    	if(length==0){
        	return 0;
        }
        int q=Integer.MIN_VALUE;//Integer.MIN_VALUE stands for "negative infinity" through out the assignment
        for(int i=1;i<=length;i++){
        	q=max(q, (input[i]+recursiveRodCutting(copyArray(input, input.length-i))));
        }
    	return q;
    }

    /*	This is a helper that method returns the largest of a variable number of integers that can be passed to it. 
     *	It loops over the supplied integers checking each once, so it's time complexity is generally in Theta(n), but
     *	because throughout this assignment we only use it to determine the biggest of two values, it takes constant time 
     *	in the methods where it is used.
     */
	public int max(int... integers){
    	int result=Integer.MIN_VALUE;
    	for(int e: integers){
    		if(e>result){
    			result=e;
    		}
    	}
    	return result;
    }
	
	/*	This is a helper method that returns a copy of a sub-array from the supplied array, starting from the first 
	 * 	element up to the element specified by the length parameter.
     *	It loops over the elements specified by the length parameter, so it's time complexity is Theta(length).
     */
    public int[] copyArray(int[] input, int length){
    	int[] result=new int[length];
    	for(int i=0;i<length;i++){
    		result[i]=input[i];
    	}
    	return result;
    }
    
	/* 	General comments about our dynamicRodCutting implementation:
	 * 	Similarly to our implementation of recursiveRodCutting, our implementation of dynamicRodCutting closely 
	 * 	follows the pseudo-code  except for the difference in input. Because of this, our implementation 
	 * 	has the same time complexity as the pseudo-code, O(n^2). 
	 */
    @Override
    public int dynamicRodCutting(int[] input) {
    	int length=input.length-1;
        int[] b = new int[input.length];
    	b[0]=0;
    	for(int j=1;j<=length;j++){
    		int q = Integer.MIN_VALUE;
    		for(int i=1;i<=j;i++){
    			q = max(q, (input[i]+b[j-i]));
    		}
    		b[j]=q;
    	}
    	return b[length];
    }
    
    /* 	General comments about our maxSubArray (used as a helper in the 'matrix' method) implementation:
	 * 	Our implementation closely follows the pseudo-code  except for the difference in input (we infer the length 
	 * 	from the 'length' parameter of the input array). Because of this, our implementation has the same time 
	 * 	complexity as the pseudo-code, O(n).
	 */
	int maxSubArray(int[] input){
		int[] B=new int[input.length];
		B[0]=max(input[0], 0);
		int m=B[0];
		for(int r=1;r<input.length;r++){
			B[r]=max(0, B[r-1]+input[r]);
			m=max(m, B[r]);
		}
		return m;
	}
	
	
	/* 	General comments about our 'matrix' method implementation:
	 * 	The result is given by adding the intermediate results of determining the maximum of adjacent entries in the 
	 * 	4 specified directions: horizontal, vertical, diagonal and anti-diagonal. The addition of the intermediate results 
	 * 	takes constant time, but because each of these helper methods loops over all entries in the matrix, the resulting
	 * 	time complexity is thus 4*Theta(n^2) = Theta(n^2), n being the number of the rows or columns of the square n*n input matrix, or
	 * 	Theta(n) if n is the number of entries in the input matrix.
	 */
	@Override
	public int matrix(int[][] input){
		if(input.length==0 || input[0].length==0){
			return 0;
		}
		return maxSubRow(input)+maxSubColumn(input)+maxSubDiagonal(input)+maxSubAntiDiagonal(input);
	}
	
	/* 	General comments about our maxSubRow (used as a helper in the 'matrix' method) implementation:
	 * 	The outer for-loop loops over the rows of the matrix, and the inner loops goes over the entries in that row, 
	 * 	adding them to a temporary array so we can call maxSubArray to find the max of the adjacent entries in this row.
	 * 	If this value exceeds (initially 0) then it gets stored in the result variable. The outer for loop is executed n times 
	 * 	(here n being the number of the rows or columns of the square n*n input matrix). The inner loop is executed n times for each of
	 * 	the rows and the maxSubarray also takes O(n) time for each of the rows. Overall the method takes n*(2n) time 
	 * 	(omitting constants), so the overall time complexity of this method is in Theta(n^2) (again, here n being the number of the rows 
	 *	 or columns of the square n*n input matrix).
	 */
	int maxSubRow(int[][] input){
		int result=0;
		int[] temp=new int[input[0].length];
		for(int i=0;i<input.length;i++){
			for(int j=0;j<input[0].length;j++){
				temp[j]=input[i][j];
			}
			int tempMaxSubRow=maxSubArray(temp);
			result=max(tempMaxSubRow,result);
		}
		return result;
	}
	
	/* 	General comments about our maxSubColumn (used as a helper in the 'matrix' method) implementation:
	 * 	This method is identical to maxSubRow except for looping over the columns in the outer for-loop and rows in the inner, 
	 * 	to find the max of the adjacent entries in a given column. It's time complexity is identical to maxSubRow.
	 */
	int maxSubColumn(int[][] input){
		int result=0;
		int[] temp=new int[input.length];
		for(int i=0;i<input[0].length;i++){
			for(int j=0;j<input.length;j++){
				temp[j]=input[j][i];
			}
			int tempMaxSubColumn=maxSubArray(temp);
			result=max(tempMaxSubColumn,result);
		}
		return result;
	}
	
	/* 	General comments about our maxSubDiagonal (used as a helper in the 'matrix' method) implementation:
	 * 	This method is similar to maxSubRow (and maxSubColumn) in that it has to loop over all the entries in the matrix
	 * 	to add them to their respective temporary diagonal arrays, so the resulting time complexity is identical to maxSubRow.
	 * 	However the looping over the diagonals is, perhaps, not as straight-forward, which is why the method uses additional 
	 * 	variables to facilitate this traversal. Nevertheless, each entry of the array is visited once, so no changes are 
	 * 	introduced to the time complexity of this method compared to maxSubRow.
	 */	
	int maxSubDiagonal(int[][] input){
		int result=0;
		if(input.length!=0 && input[0].length!=0){
			int numberOfDiagonals=input.length*2-1;
			int maxLengthDiagonal=input.length;
			int columnCounter=0;
			int rowCounter=0;
			for(int i=0;i<numberOfDiagonals;i++){
				int[] temp;
				temp=new int[columnCounter==0 ? rowCounter+1 : maxLengthDiagonal-columnCounter];
				for(int j=0;j<temp.length;j++){
					temp[j]=input[rowCounter-j][columnCounter+j];
				}
				if(rowCounter<input.length-1){
					rowCounter+=1;
				}else{
					columnCounter+=1;
				}
				int maxSubArray=maxSubArray(temp);
				result=max(maxSubArray,result);
			}
		}
		return result;
	}
	
	/* 	General comments about our maxSubAntiDiagonal (used as a helper in the 'matrix' method) implementation:
	 * 	This method is identical to maxSubDiagonal except for starting at the last column to produce the 
	 * 	temporary anti-diagonal arrays. It's time complexity is identical to maxSubRow and maxSubDiagonal.
	 */	
	int maxSubAntiDiagonal(int[][] input){
		int result=0;
		if(input.length!=0 && input[0].length!=0){
			int numberOfDiagonals=input.length*2-1;
			int maxLengthDiagonal=input.length;
			int columnCounter=input[0].length-1;//start at first row and last column
			int rowCounter=0;
			for(int i=0;i<numberOfDiagonals;i++){
				int[] temp;
				temp=new int[columnCounter==input[0].length-1 ? rowCounter+1 : maxLengthDiagonal-(input[0].length-1-columnCounter)];
				for(int j=0;j<temp.length;j++){
					temp[j]=input[rowCounter-j][columnCounter-j];
				}
				if(rowCounter<input.length-1){
					rowCounter+=1;
				}else{
					columnCounter-=1;
				}
				int maxSubArray=maxSubArray(temp);
				result=max(maxSubArray,result);
			}
		}
		return result;
	}
	
    /* BEGIN UTIL FUNCTION. DO NOT TOUCH */



    void start(String assignment, String file) throws FileNotFoundException {
        switch (assignment) {
            case "rod": {
                int result = recursiveRodCutting(Util.readInput(file));
                System.out.printf("%d\n", result);
            }
                break;
            case "dynrod": {
                int result = dynamicRodCutting(Util.readInput(file));
                System.out.printf("%d\n", result);
                break;
            }
            case "matrix": {
                int result = matrix(Util.readMatrix(file));
                System.out.printf("%d\n", result);
                break;
            }
            default:
                System.out.printf("Invalid assignment provided: %s\n", assignment);
                printHelp(null);
                System.exit(1);
        }
    }

    static void printArray(int[] array) {
        for (int e: array) {
            System.out.printf("%d ", e);
        }
    }

    static void printMatrix(int[][] matrix) {
        for (int[] l: matrix) {
            printArray(l);
            System.out.printf("\n");
        }
    }

    static void printHelp(String[] argv) {
        System.out.printf("Usage: java -jar DS2.jar <assignment> [<input_file>]\n");
        System.out.printf("\t<algorithm>: rod, dynrod, matrix\n");
        System.out.printf("E.g.: java -jar DS2.jar rod example.txt\n");
    }

    public static void main(String argv[]) {
        if (argv.length == 0) {
            printHelp(argv);
            System.exit(1);
        }
        try {
            (new Main()).start(argv[0], argv.length < 2 ? null : argv[1]);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getMessage());
        }

    }

}
