public interface DS2Interface {

    /**
     *
     * @param input The array to perform recursive rod cutting on. The array starts with the number 0.
     * @return The largest possible profit obtained for this instance of rod cutting.
     */
    int recursiveRodCutting(int[] input);


    /**
     *
     * @param input The array to perform dynamic rod cutting on. The array starts with the number 0.
     * @return The largest possible profit obtained for this instance of rod cutting.
     */
    int dynamicRodCutting(int[] input);

    /**
     *
     * @param input An n x n matrix.
     * @return The maximal sum of adjacent entries.
     */
    int matrix(int[][] input);
}
