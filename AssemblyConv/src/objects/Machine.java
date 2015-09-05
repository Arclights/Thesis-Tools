package objects;

import java.util.ArrayList;

/**
 * This class represents a machine that can manipulate components
 */
public class Machine extends AssemblyObject {

	/* A list of task that is out of range for this machine */
	public ArrayList<Task> tasksOutOfRange;

	public Machine(String id) {
		super(id);
		tasksOutOfRange = new ArrayList<>();
	}

	/**
	 * Adds a task that is out of range for this machine
	 *
	 * @param t Task out of range
	 */
	public void addOutOfRangeTask(Task t) {
		tasksOutOfRange.add(t);
	}

}
