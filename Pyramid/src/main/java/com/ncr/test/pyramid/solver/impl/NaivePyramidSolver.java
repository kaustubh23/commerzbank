package com.ncr.test.pyramid.solver.impl;

import java.util.HashMap;
import java.util.Map;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: There is something wrong here. A few things actually...
 */
public class NaivePyramidSolver implements PyramidSolver {
	// declare new field for memoization
	Map<String, Long> memoization = new HashMap<>();;

	@Override
	public long pyramidMaximumTotal(Pyramid pyramid) {
		return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
	}

	private long getTotalAbove(int row, int column, Pyramid pyramid) {

		// Equals to zero condition was missing the last column processing.
		// Below condition will enable it. 

		long result = 0;
		if (column > pyramid.getData().length - 1) {
			return 0;
		}
		// Added this condition so that it will return the first row element which was
		// missing

		if (row == 0) {
			return pyramid.getData()[row][column];
		}

		/*
		 * Added the memoization logic in order to process large data result. This
		 * element stores the already processed results and only perform processing if
		 * the result is not present. This will enable the function to process large
		 * data which was failing in the prior logic.
		 */
		long myValue = pyramid.get(row, column);
		if (memoization.containsKey(Integer.toString(row) + "" + Integer.toString(column))) {
			return memoization.get(Integer.toString(row) + "" + Integer.toString(column));
		} else {
			memoization.put(Integer.toString(row) + "" + Integer.toString(column), myValue);
			long left = myValue + getTotalAbove(row - 1, column, pyramid);
			long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
			result = Math.max(left, right);
		}
		return result;

	}

}
