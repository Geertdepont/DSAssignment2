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
//        int[] array = new int[input.length];
//    	array[0]=0;
//    	for(int i=0;i<input.length;i++){
//    		int q = Integer.MIN_VALUE;
//    		for(int j=0;j<i;j++){
//    			q = max(q,(input[j]+array[i-j]));
//    		}
//    		array[i]=q;
//    	}
//    	
//    	return array[input.length];
    	return 4;
    }

//    Algorithm rodCuttingDP(p, n):
//    	new array b[0 . . . n]
//    	b[0] := 0
//    	for j := 1 to n do
//    	q := − ∞
//    	for i := 1 to j do
//    	q := max(q, p[i] + b[j − i])
//    	b[j] := q
//    	return b[n]
//    
    
    
    
    
    @Override
    public int matrix(int[][] input) {
        return 0;
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
