package timematrix;

import java.util.HashMap;

import objects.Assembly;

/**
 * This class represents a 3D matrix of the duration for moving from one task to another including the time it takes
 * to change tool
 */
public class TimeMatrix3D {
	HashMap<String, HashMap<String, HashMap<Integer, Integer>>> matrix;

	public TimeMatrix3D(Assembly assembly, TimeMatrix timeMatrix) {
		matrix = new HashMap<>();
		createMatrix(assembly, timeMatrix);
	}

	/**
	 * Generates the matrix using the 2D time matrix and the Assembly object
	 * @param assembly The Assembly object
	 * @param timeMatrix The 2D time matrix
	 */
	private void createMatrix(Assembly assembly, TimeMatrix timeMatrix) {

		String from = "Start";
		addToMatrix(from, assembly, timeMatrix);

		for (int row = 1; row <= assembly.nbrTasks(); row++) {
			from = assembly.iToTask(row).id;
			addToMatrix(from, assembly, timeMatrix);
		}
	}

	/**
	 * Generates all the values to put in the matrix based on the id of hte task to move from (row in the matrix).
	 * Essentially it creates a 2D matrix based on the row.
	 * @param fromId The id of the task to generate from
	 * @param assembly The Assembly object
	 * @param timeMatrix The 2D time matrix
	 */
	private void addToMatrix(String fromId, Assembly assembly,
			TimeMatrix timeMatrix) {
		for (int column = 1; column <= assembly.nbrTasks(); column++) {
			String toId = assembly.iToTask(column).id;
			for (int fromTool = 1; fromTool <= assembly.nbrTools(); fromTool++) {
				for (int toTool = 1; toTool <= assembly.nbrTools(); toTool++) {
					int toolDiff = Math.abs(fromTool - toTool);
					if (fromId.equals("Start")) {
					}
					if (toolDiff > 0) {
						int moveTimeToToolChange = assembly.timeMatrix.getTime(fromId,
								"Change tool");
						int toolChanegDuration = assembly.getToolChangeDuration(
								assembly.iToTool(fromTool),
								assembly.iToTool(toTool));
						int moveTimeFromToolChange = assembly.timeMatrix.getTime(
								"Change tool", toId);
						addTime(fromId,
								toId,
								toolDiff,
								moveTimeToToolChange
										+ toolChanegDuration
										+ moveTimeFromToolChange);
					} else {
						int moveTimeFromIdToToId = assembly.timeMatrix.getTime(fromId, toId);
						addTime(fromId, toId, toolDiff, moveTimeFromIdToToId);
					}
				}
			}
		}
	}

	/**
	 * Returns the duration for moving from task with id fromId to task with id toId and change tools inbetween.
	 * @param fromId The id of the task to move from
	 * @param toId The id of the task to move to
	 * @param toolDiff The difference in tools used by the two tasks
	 * @return The duration of the move
	 * @throws IllegalAccessError
	 */
	public Integer getTime(String fromId, String toId, int toolDiff)
			throws IllegalAccessError {
		if (!matrix.containsKey(fromId)) {
			throw new IllegalAccessError(
					"The time matrix doesn't contain a row for " + fromId);
		}
		HashMap<String, HashMap<Integer, Integer>> row = matrix.get(fromId);
		if (!row.containsKey(toId)) {
			throw new IllegalAccessError(
					"The time matrix doesn't contain a column for \"" + toId
							+ "\"");
		}
		HashMap<Integer, Integer> cell = row.get(toId);
		if (!cell.containsKey(toolDiff)) {
			throw new IllegalAccessError(
					"The time matrix doesn't contain a tool cell for \""
							+ toolDiff + "\"");
		}
		return cell.get(toolDiff);
	}

	/**
	 * Adds the duration of the move between task with id fromId to task with id toId when the tool difference of the
	 * two tasks is toolDiff
	 * @param fromId The id of the task moving from
	 * @param toId The id of the task moving to
	 * @param toolDiff The difference in tools used by task fromId and task toId
	 * @param time The duration of the move
	 */
	public void addTime(String fromId, String toId, int toolDiff, int time) {
		HashMap<String, HashMap<Integer, Integer>> row;
		if (matrix.containsKey(fromId)) {
			row = matrix.get(fromId);
		} else {
			row = new HashMap<>();
			matrix.put(fromId, row);
		}

		HashMap<Integer, Integer> cell;
		if (row.containsKey(toId)) {
			cell = row.get(toId);
		} else {
			cell = new HashMap<>();
			row.put(toId, cell);
		}
		cell.put(toolDiff, time);
	}

	/**
	 * Returns the k-value of the matrix, that is the depth of the matrix
	 * @return The k-value
	 */
	@SuppressWarnings("unchecked")
	public int nbrK() {
		HashMap<String, HashMap<Integer, Integer>> section =(HashMap<String, HashMap<Integer, Integer>>) matrix
				.values().toArray()[0]; /* Esentially a 2D slice of the matrix based on the row */
		HashMap<Integer, Integer> slice = (HashMap<Integer, Integer>) section.values().toArray()[0];/* A cutout of the matrix being 1xk in size going along the 3D dimension */
		return slice.size();
	}

}
