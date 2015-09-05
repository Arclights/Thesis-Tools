package objects;

import java.util.ArrayList;

/**
 * This class represents a component in the assembly.
 * An example of a component could be a button.
 *
 * A component can be made of sub components.
 * For example the component button-switch has the sub components button and switch
 * Let's call a component without any subcomponents a primitive component
 */
public class Component extends AssemblyObject {
	public ArrayList<Component> subcomponents;

	public Component(String id) {
		super(id);
		subcomponents = new ArrayList<>();
	}

	/**
	 * Adds a subcomponent to this component
	 * @param component The subcomponent
	 */
	public void addSubcomponent(Component component) {
		subcomponents.add(component);
	}

	/**
	 * Returns all the sub components for this component recursively all the way down to the primitive components
	 * @return A list of all the related primitive components
	 */
	public ArrayList<String> getSubComponents() {
		ArrayList<String> out = new ArrayList<>();
		if (subcomponents.isEmpty()) {
			out.add(id);
		} else {
			for (Component c : subcomponents) {
				out.addAll(c.getSubComponents());
				out.add(c.id);
			}
		}
		return out;
	}

}
