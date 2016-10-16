import java.io.FileNotFoundException;

public class Main implements DS2Interface {


    /* Implement these methods */

    @Override
    public int recursiveRodCutting(int[] input) {
        int length=input.length-1;
    	if(length==0){
        	return 0;
        }
        int q=Integer.MIN_VALUE;
        for(int i=1;i<=length;i++){
        	q=max(q, (input[i]+recursiveRodCutting(copyArray(input, input.length-i))));
        }
    	return q;
    }

	public int max(int... integers){
    	int result=Integer.MIN_VALUE;
    	for(int e: integers){
    		if(e>result){
    			result=e;
    		}
    	}
    	return result;
    }
    
    public int[] copyArray(int[] input, int length){
    	int[] result=new int[length];
    	for(int i=0;i<length;i++){
    		result[i]=input[i];
    	}
    	return result;
    }
    
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
    
	int maxSubArray(int [] input){
		int[] B=new int[input.length];
		B[0]=max(input[0], 0);
		int m=B[0];
		for(int r=1;r<input.length;r++){
			B[r]=max(0, B[r-1]+input[r]);
			m=max(m, B[r]);
		}
		return m;
	}
	
	int matrix2(int[][] input){
		if(input.length==0 || input[0].length==0){
			return 0;
		}
		return maxSubRow(input)+maxSubColumn(input)+maxSubDiagonal(input)+maxSubAntiDiagonal(input);
	}
	
	int maxSubRow(int[][] input){
		int result=0;
		int[] temp=new int[input[0].length];
		for(int i=0;i<input.length;i++){
			for(int j=0;j<input[0].length;j++){
				temp[j]=input[i][j];
			}
			int tempMaxSubRow=maxSubArray(temp);
			if(tempMaxSubRow>result){
				result=tempMaxSubRow;
			}
		}
		return result;
	}
	
	int maxSubColumn(int[][] input){
		int result=0;
		int[] temp=new int[input.length];
		for(int i=0;i<input[0].length;i++){
			for(int j=0;j<input.length;j++){
				temp[j]=input[j][i];
			}
			int tempMaxColumn=maxSubArray(temp);
			if(tempMaxColumn>result){
				result=tempMaxColumn;
			}
		}
		return result;
	}
	
	int maxSubDiagonal(int[][] input){
		int result=0;
		if(input.length!=0 && input[0].length!=0){
			int numberOfDiagonals=input.length+input[0].length-1;
			int maxLengthDiagonal=input.length<input[0].length ? input.length : input[0].length;
			int columnCounter=0;
			int rowCounter=0;
			for(int i=0;i<numberOfDiagonals;i++){
				int[] temp;
				if(columnCounter==0){
					temp=new int[rowCounter<maxLengthDiagonal?rowCounter+1:maxLengthDiagonal];
				}else{
					temp=new int[maxLengthDiagonal-columnCounter];
				}
				for(int j=0;j<temp.length;j++){
					temp[j]=input[rowCounter-j][columnCounter+j];
				}
				if(rowCounter<input.length-1){
					rowCounter+=1;
				}else{
					columnCounter+=1;
				}
				int maxSubArray=maxSubArray(temp);
				result=maxSubArray>result ? maxSubArray : result;
			}
		}
		return result;
	}
	
	int maxSubAntiDiagonal(int[][] input){
		int result=0;
		if(input.length!=0 && input[0].length!=0){
			int numberOfDiagonals=input.length+input[0].length-1;
			int maxLengthDiagonal=input.length<input[0].length ? input.length : input[0].length;
			int columnCounter=input[0].length-1;//start at first row and last column
			int rowCounter=0;
			for(int i=0;i<numberOfDiagonals;i++){
				int[] temp;
				if(columnCounter==input[0].length-1){
					temp=new int[rowCounter<maxLengthDiagonal?rowCounter+1:maxLengthDiagonal];
				}else{
					temp=new int[maxLengthDiagonal-(input[0].length-1-columnCounter)];
				}
				for(int j=0;j<temp.length;j++){
					temp[j]=input[rowCounter-j][columnCounter-j];
				}
				if(rowCounter<input.length-1){
					rowCounter+=1;
				}else{
					columnCounter-=1;
				}
				int maxSubArray=maxSubArray(temp);
				result=maxSubArray>result ? maxSubArray : result;
			}
		}
		return result;
	}
	
	@Override
	public int matrix(int[][] input){
		if(input.length==0||input[0].length==0){
			return 0;
		}
		return maxHorizontal(input)+maxDiagonal(input)+maxAntiDiagonal(input)+maxVertical(input);
	}
	
	
	int maxHorizontal(int[][] input){
		int maxHorizontal = Integer.MIN_VALUE;
		for(int i=0; i<input.length;i++){
			int[] temp = new int[input[0].length];
			for(int j=0; j<input[0].length;j++){
				 temp[j]= input[i][j];
			}
			int maxSubArray = maxSubArray(temp);
			if(maxSubArray>maxHorizontal){
				maxHorizontal= maxSubArray;
			}
		}
		return maxHorizontal;
	}
	
	int maxVertical(int[][] input){
		int maxVertical = Integer.MIN_VALUE;
		for(int i=0; i<input[0].length;i++){
			int[] temp = new int[input.length];
			
			for(int j=0; j<input.length;j++){
				 temp[j]= input[j][i];
			}
			int maxSubArray = maxSubArray(temp);
			if(maxSubArray>maxVertical){
				maxVertical= maxSubArray;
			}
		}
		return maxVertical;
	}
	
	int maxAntiDiagonal(int[][] input){
		int maxAntiDiagonal = Integer.MIN_VALUE;
		int counter = 0;
		int secondCounter = 0;
		for(int i =0;i<(input.length*2)-1;i++){
			if(i<input.length){
				counter = i+1;
			}
			else{
				counter = counter -1; //to create a counter that goes up from 1 to input.length to 1 e.g. 1,2,3,2,1
				secondCounter +=1; 
			}
			int [] temp = new int[counter];
			for(int j=0;j<counter;j++){
				temp[j]=input[input.length-counter+j-secondCounter][j+secondCounter];
			}
			int maxSubArray = maxSubArray(temp);
			if(maxSubArray>maxAntiDiagonal){
				maxAntiDiagonal= maxSubArray;
			}
		}
		return maxAntiDiagonal;
	}
	
	
	int maxDiagonal(int[][] input){
		int maxDiagonal = Integer.MIN_VALUE;
		int counter = 0;
		int secondCounter = 0;
		for(int i =0;i<(input.length*2)-1;i++){
			if(i<input.length){
				counter = i+1;
			}
			else{
				counter = counter -1;
				secondCounter +=1;
			}
			int [] temp = new int[counter];
			for(int j=0;j<counter;j++){
				temp[j]= input[counter-1-j+secondCounter][j+secondCounter];
			}
			int maxSubArray = maxSubArray(temp);
			if(maxSubArray>maxDiagonal){
				maxDiagonal= maxSubArray;
			}
		}
		return maxDiagonal;
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
