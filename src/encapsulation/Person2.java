package encapsulation;

public class Person2 {

	private String fullName = "? ?";
	
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
	
	public String getGivenName() {
		int pos = fullName.lastIndexOf(' ');
		String gn = fullName.substring(0, pos);
		if (gn.equals("?")) {
			gn = null;
		}
		return gn;
	}
	
	public void setGivenName(String givenName) {
		checkName(givenName, true);
		if (givenName == null) {
			givenName = "?";
		}
		int pos = fullName.lastIndexOf(' ');
		fullName = givenName + " " + fullName.substring(pos + 1);
	}
	
	public String getFamilyName() {
		int pos = fullName.lastIndexOf(' ');
		String fn = fullName.substring(pos + 1);
		if (fn.equals("?")) {
			fn = null;
		}
		return fn;
	}
	public void setFamilyName(String familyName) {
		checkName(familyName, false);
		if (familyName == null) {
			familyName = "?";
		}
		int pos = fullName.lastIndexOf(' ');
		fullName = fullName.substring(0, pos) + " " + familyName;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		checkName(fullName, true);
		this.fullName = fullName;
	}
	
	//

	public static void main(String[] args) {
		Person2 p1 = new Person2();
		p1.setGivenName("Hallvard");
		p1.setFamilyName("Trætteberg");
		System.out.println(p1.getGivenName() + " " + p1.getFamilyName() + " == " + p1.getFullName());
		Person2 p2 = new Person2();
		p2.setFullName("Hallvard Trætteberg");
		System.out.println(p2.getGivenName() + " " + p2.getFamilyName() + " == " + p2.getFullName());
	}
}
