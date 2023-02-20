package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 2nd task. Please implement the class to satisfy the
 * interface. *
 */
public class YourSolver implements PyramidSolver {

	@Override
	public long pyramidMaximumTotal(Pyramid pyramid) {
		return findMaxPathSum_Iteration(pyramid.getData(), pyramid.getRows(), pyramid.getRows() - 1);
	}

	/*
	 * I have selected the iteration process for my solution as the recursive already
	 * used.
	 * 
	 * Solution has time complexity on O(n^2) and space complexity on O(1) The
	 *
	 * Program will perform a scan from bottom row to up and compare the addition to
	 * the adjacent field in each iteration in order to find the maximum sum path
	 * and returns the maximum value after processing. 
	 * 
	 * All test runs successfully.
	 */
	static int findMaxPathSum_Iteration(int pyramid[][], int row, int column) {
		int sum = 0;
		// loop will avoid the last two rows
		for (int i = row - 2; i >= 0; i--) { 
			for (int j = 0; j < column - i; j++) {
				if (j - 1 >= 0)
					pyramid[i][j] += Math.max(pyramid[i + 1][j], pyramid[i + 1][j - 1]);
				else
					pyramid[i][j] += pyramid[i + 1][j];
				sum = Math.max(sum, pyramid[i][j]);
			}
		}

		return sum;
	}

}
