package com.rmv.oop.dtw.algotithm;

public final class DTW {

    public DTWResult compute(final float[] sample, final float[] template) {
        final int sLength = sample.length;
        final int mLength = template.length;

        if (sLength == 0 || mLength == 0) {
            return new DTWResult(new int[][]{}, Double.NaN);
        }

        // Allocate the Warping Path. (Math.max(N, M) <= K < (N + M).
        final int[][] warpingPath = new int[sLength + mLength][2];

        final double[][] localDistances = new double[sLength][mLength];
        final double[][] globalDistances = new double[sLength][mLength];
        final double[] minimalBuffer = new double[3];

        int i, j;
        for (i = 0; i < sLength; i++) {
            // Fetch the Sample.
            final float lSample = sample[i];
            // Iterate the Template.
            for (j = 0; j < mLength; j++) {
                // Calculate the Distance between the Sample and the Template for this Index.
                localDistances[i][j] = this.getDistanceBetween(lSample, template[j]);
            }
        }

        globalDistances[0][0] = localDistances[0][0];

        for (i = 1; i < sLength; i++) {
            globalDistances[i][0] = localDistances[i][0] + globalDistances[i - 1][0];
        }

        for (j = 1; j < mLength; j++) {
            globalDistances[0][j] = localDistances[0][j] + globalDistances[0][j - 1];
        }

        for (i = 1; i < sLength; i++) {
            for (j = 1; j < mLength; j++) {
                // Accumulate the path.
                globalDistances[i][j] = (Math.min(Math.min(globalDistances[i - 1][j], globalDistances[i - 1][j - 1]), globalDistances[i][j - 1])) + localDistances[i][j];
            }
        }

        // Update iteration variables.
        i = warpingPath[0][0] = (sLength - 1);
        j = warpingPath[0][1] = (mLength - 1);

        // Define the Scalar Qualifier.
        int k = 1;

        // Whilst there are samples to process...
        while ((i + j) != 0) {
            // Handle the offset.
            if (i == 0) {
                // Decrement the iteration variable.
                j -= 1;
            } else if (j == 0) {
                // Decrement the iteration variable.
                i -= 1;
            } else {
                // Update the contents of the minimal buffer.
                minimalBuffer[0] = globalDistances[i - 1][j];
                minimalBuffer[1] = globalDistances[i][j - 1];
                minimalBuffer[2] = globalDistances[i - 1][j - 1];
                // Calculate the Index of the Minimum.
                final int minimumIndex = this.getMinimumIndex(minimalBuffer);
                // Declare booleans.
                final boolean minIs0 = (minimumIndex == 0);
                final boolean minIs1 = (minimumIndex == 1);
                final boolean minIs2 = (minimumIndex == 2);
                // Update the iteration components.
                i -= (minIs0 || minIs2) ? 1 : 0;
                j -= (minIs1 || minIs2) ? 1 : 0;
            }
            // Increment the qualifier.
            k++;
            // Update the Warping Path.
            warpingPath[k - 1][0] = i;
            warpingPath[k - 1][1] = j;
        }

        return new DTWResult(this.reverse(warpingPath, k), ((globalDistances[sLength - 1][mLength - 1]) / k));
    }

    // Changes the order of the warping path, in increasing order.
    private int[][] reverse(final int[][] path, final int k) {
        final int[][] revertedPath = new int[k][2];
        for (int i = 0; i < k; i++) {
            System.arraycopy(path[k - i - 1], 0, revertedPath[i], 0, 2);
        }
        return revertedPath;
    }

    protected double getDistanceBetween(double p1, double p2) {
        return (p1 - p2) * (p1 - p2);
    }

    protected final int getMinimumIndex(final double[] array) {
        int index = 0;
        double minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            final boolean isSmaller = array[i] < minValue;
            minValue = isSmaller ? array[i] : minValue;
            index = isSmaller ? i : index;
        }
        return index;
    }
}
