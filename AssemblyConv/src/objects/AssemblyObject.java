package objects;

/**
 * This class represents an object that goes into an assembly
 * It is the super class of all objects in the assembly
 */
public abstract class AssemblyObject {
	public String id;
	public int i;

	/**
	 * @param id The id of this assembly object
	 */
	public AssemblyObject(String id) {
		this.id = id;
	}

}
