package objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a task that can be performed by a machine on a component using an action
 */
public class Task extends AssemblyObject {
	public int duration;
	public Tray tray;
	public Output output;
	public Fixture fixture;
	public ArrayList<Component> componentsUsed;
	public Component componentCreated;
	public Tool toolNeeded;
	public String action;

	/* The allowed actions */
	public static final String ALLOWED_ACTION_PUTTING = "putting";
	public static final String ALLOWED_ACTION_MOUNTING = "mounting";
	public static final String ALLOWED_ACTION_TAKING = "taking";
	public static final String ALLOWED_ACTION_MOVING = "moving";
	public static final String ALLOWED_ACTION_TAPPING = "tapping";
	public static final String ALLOWED_ACTION_PHOTO = "photo";
	public static final String ALLOWED_ACTION_SPRAYING = "spraying";
	public static final String ALLOWED_ACTION_PEELING = "peeling";

	public static HashSet<String> allowedActions;
	static {
		allowedActions = new HashSet<>();
		allowedActions.add(ALLOWED_ACTION_PUTTING);
		allowedActions.add(ALLOWED_ACTION_MOUNTING);
		allowedActions.add(ALLOWED_ACTION_TAKING);
		allowedActions.add(ALLOWED_ACTION_MOVING);
		allowedActions.add(ALLOWED_ACTION_TAPPING);
		allowedActions.add(ALLOWED_ACTION_PHOTO);
		allowedActions.add(ALLOWED_ACTION_SPRAYING);
		allowedActions.add(ALLOWED_ACTION_PEELING);
	}

	public Task(String id, int duration) {
		super(id);
		this.duration = duration;
		componentsUsed = new ArrayList<>();
	}

	/**
	 * Returns the string representation of all the subcomponents that is involved in this task,
	 * except the components that are just consisting of one primitive component
	 * @return A set of sub components
	 */
	public Set<String> getSubComponents() {
		Set<String> out = new HashSet<>();
		for (Component c : componentsUsed) {
			ArrayList<String> subComps = c.getSubComponents();
			if (subComps.size() > 1) { // So we don't get the ones that are
										// single components
				out.addAll(c.getSubComponents());
			}
		}
		return out;
	}

}
