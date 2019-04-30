package encapsulation.person;

public class Person1 {

	// tag::variables[]
	private String givenName;
	private String familyName;
	// end::variables[]
	
	// tag::validation-methods[]
	public boolean isValidName(String name, boolean allowBlank) {
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
	// end::validation-methods[]
	
	// tag::givenName-methods[]
	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		checkName(givenName, true);
		this.givenName = givenName;
	}
	// end::givenName-methods[]
	
	// tag::familyName-methods[]
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		checkName(familyName, false);
		this.familyName = familyName;
	}
	// end::familyName-methods[]
	
	// tag::fullName-methods[]
	public String getFullName() {
		String gn = givenName;
		if (gn == null) { // <1>
			gn = "?";
		};
		String fn = familyName;
		if (fn == null) { // <1>
			fn = "?";
		};
		return gn + " " + fn;
	}

	public void setFullName(String fullName) {
		checkName(fullName, true);
		int pos = fullName.lastIndexOf(' '); // <2>
		String gn = fullName.substring(0, pos); // <3>
		if (gn.equals("?")) { // <4>
			gn = null;
		}
		String fn = fullName.substring(pos + 1); // <3>
		if (fn.equals("?")) { // <4>
			fn = null;
		}
		this.givenName = gn;
		this.familyName = fn;
	}
	// end::fullName-methods[]
	
	//
	
	public static void main(String[] args) {
		Person1 p1 = new Person1();
		p1.setGivenName("Hallvard");
		p1.setFamilyName("Trætteberg");
		System.out.println(p1.getGivenName() + " " + p1.getFamilyName() + " == " + p1.getFullName());
		Person1 p2 = new Person1();
		p2.setFullName("Hallvard Trætteberg");
		System.out.println(p2.getGivenName() + " " + p2.getFamilyName() + " == " + p2.getFullName());
	}
}
