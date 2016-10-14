import java.io.FileNotFoundException;

public class Main implements DS2Interface {


    /* Implement these methods */

    @Override
    public int recursiveRodCutting(int[] input) {
        if(input.length==1){
        	return 0;
        }
        int result = Integer.MIN_VALUE;
        for(int i=1;i<input.length;i++){
        	result = max(result, (input[i]+recursiveRodCutting(copyArray(input, input.length-i))));
        }
    	return result;
    }

	public int max(int... integers){
    	int result = Integer.MIN_VALUE;
    	
    	for(int e: integers){
    		if(result<=e){
    			result = e;
    		}
    	}
    	return result;
    }
    
    public int[] copyArray(int[] input, int length){
    	int[] temp = new int[length];
    	for(int i=0;i<length;i++){
    		temp[i]=input[i];
    	}
    	return temp;
    }
    

    @Override
    public int dynamicRodCutting(int[] input) {
        int[] array = new int[input.length];
    	array[0]=0;
    	for(int i=1;i<input.length;i++){
    		int q = Integer.MIN_VALUE;
    		for(int j=1;j<i+1;j++){
    			q = max(q,(input[j]+array[i-j]));
    		}
    		array[i]=q;
    	}
    	
    	return array[input.length-1];
    }

   

	int maxSubArray(int [] input){
		int[] B = new int[input.length];
		B[0]=max(input[0],0);
		int m= B[0];
		for(int i=1;i<input.length;i++){
			B[i]=max(0,B[i-1]+input[i]);
			m = max(m,B[i]);
		}
		return m;
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
				counter = counter -1;
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
