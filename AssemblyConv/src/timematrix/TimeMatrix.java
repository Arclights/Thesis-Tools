package timematrix;

import java.util.HashMap;

/**
 * This class represents a 2D matrix of the duration for moving from one task to another
 */
public class TimeMatrix {

	HashMap<String, HashMap<String, Integer>> matrix;

	public TimeMatrix() {
		matrix = new HashMap<>();
	}

	/**
	 * Returns the duration of moving from task fromTaskId to toTaskId
	 *
	 * @param fromTaskId The id of the task moving from
	 * @param toTaskId   The id of the task moving to
	 * @return The duration of the move
	 */
	public Integer getTime(String fromTaskId, String toTaskId) {
		if (!matrix.containsKey(fromTaskId)) {
			throw new IllegalAccessError(
					"The time matrix doesn't contain a row for " + fromTaskId);
		}
		HashMap<String, Integer> tmp = matrix.get(fromTaskId);
		if (!tmp.containsKey(toTaskId)) {
			throw new IllegalAccessError(
					"The time matrix doesn't contain a column for \"" + toTaskId
							+ "\"");
		}
		return tmp.get(toTaskId);
	}

	/**
	 * Adds the duration for moving from task fromTaskId to toTaskId to the matrix
	 *
	 * @param fromTaskId The id of the task moving from
	 * @param toTaskId   The id of the task moving to
	 * @param time       The duration of the move
	 */
	public void addTime(String fromTaskId, String toTaskId, int time) {
		HashMap<String, Integer> tmp;
		if (matrix.containsKey(fromTaskId)) {
			tmp = matrix.get(fromTaskId);
		} else {
			tmp = new HashMap<>();
			matrix.put(fromTaskId, tmp);
		}
		tmp.put(toTaskId, time);
	}

}
