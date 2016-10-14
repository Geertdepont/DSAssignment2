import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {


    static int [] readLine(String line) {
        Scanner in = new Scanner(line);

        List<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            int e = in.nextInt();
            list.add(e);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    static int [][] readMatrix(String file) throws FileNotFoundException {
        InputStream inputStream;
        if (file == null) {
            inputStream = System.in;
        } else {
            inputStream = new FileInputStream(file);
        }
        Scanner in = new Scanner(inputStream);
        
        ArrayList<int[]> result = new ArrayList<>();
        while (in.hasNextLine()) {
            result.add(readLine(in.nextLine()));
        }
        int[][] ret = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }

    static int[] readInput(String file) throws FileNotFoundException {

        InputStream inputStream;
        if (file == null) {
            inputStream = System.in;
        } else {
            inputStream = new FileInputStream(file);
        }
        Scanner in = new Scanner(inputStream);

        List<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            int e = in.nextInt();
            list.add(e);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
