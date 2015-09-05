package containers;

import java.util.HashMap;

import objects.Tool;

/**
 * This class represents a look-up matrix of the time it takes to change from one tool to another
 *
 * @author Tommy Kvant
 */
public class ToolChangeDurations {
	HashMap<Tool, HashMap<Tool, Integer>> matrix;

	public ToolChangeDurations() {
		matrix = new HashMap<>();
	}

	/**
	 * Adds the time it takes to change from fromTool to toTool
	 * @param fromTool The tool to change from
	 * @param toTool The tool to change to
	 * @param duration The duration of the change
	 */
	public void addDuration(Tool fromTool, Tool toTool, int duration) {
		HashMap<Tool, Integer> row;
		if (!matrix.containsKey(fromTool)) {
			row = new HashMap<>();
		} else {
			row = matrix.get(fromTool);
		}
		row.put(toTool, duration);
		matrix.put(fromTool, row);
	}

	/**
	 * Returns the duration of a change from tool fromTool to toTool
	 * @param fromTool The tool to change from
	 * @param toTool The tool to change to
	 * @return The duration of the change
	 */
	public int getDuration(Tool fromTool, Tool toTool) {
		if (matrix.containsKey(fromTool)) {
			if (matrix.get(fromTool).containsKey(toTool)) {
				return matrix.get(fromTool).get(toTool);
			}
		}
		return 0;

	}

}
