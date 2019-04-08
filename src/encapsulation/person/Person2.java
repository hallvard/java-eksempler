package encapsulation.person;

public class Person2 {

	// tag::variables[]
	private String fullName = "? ?";
	// end::variables[]
	
	public static boolean isValidName(String name, boolean allowBlank) {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (! (Character.isLetter(c) || (c == ' ' && allowBlank) || ".-".indexOf(c) >= 0)) {
				return false;
			}
		}
		return true;
	}
	
	private void checkName(String name, boolean allowBlank) {
		if (! isValidName(name, allowBlank)) {
			throw new IllegalArgumentException("Illegal character(s) in name: " + name);
		}
	}
	
	// tag::givenName-methods[]
	public String getGivenName() {
		int pos = fullName.lastIndexOf(' '); // <1>
		String gn = fullName.substring(0, pos); // <2>
		if (gn.equals("?")) { // <3>
			gn = null;
		}
		return gn;
	}
	
	public void setGivenName(String givenName) {
		checkName(givenName, true);
		if (givenName == null) { // <4>
			givenName = "?";
		}
		int pos = fullName.lastIndexOf(' '); // <1>
		String familyName = fullName.substring(pos + 1); // <5>
		this.fullName = givenName + " " + familyName;
	}
	// end::givenName-methods[]
	
	// tag::familyName-methods[]
	public String getFamilyName() {
		int pos = fullName.lastIndexOf(' '); // <1>
		String fn = fullName.substring(pos + 1); // <5>
		if (fn.equals("?")) { // <3>
			fn = null;
		}
		return fn;
	}
	
	public void setFamilyName(String familyName) {
		checkName(familyName, false);
		if (familyName == null) { // <4>
			familyName = "?";
		}
		int pos = fullName.lastIndexOf(' '); // <1>
		String givenName = fullName.substring(0, pos); // <2>
		this.fullName = givenName + " " + familyName;
	}
	// end::familyName-methods[]

	// tag::fullName-methods[]
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		checkName(fullName, true);
		this.fullName = fullName;
	}
	// end::fullName-methods[]

	//

	// tag::main-method[]
	public static void main(String[] args) {
		Person2 p1 = new Person2();
		p1.setGivenName("Hallvard");
		p1.setFamilyName("Trætteberg");
		System.out.println(p1.getGivenName() + " " + p1.getFamilyName() + " == " + p1.getFullName());
		Person2 p2 = new Person2();
		p2.setFullName("Hallvard Trætteberg");
		System.out.println(p2.getGivenName() + " " + p2.getFamilyName() + " == " + p2.getFullName());
	}
	// end::main-method[]
}
