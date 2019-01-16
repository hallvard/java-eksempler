package encapsulation;

public class Person1 {

	private String givenName;
	private String familyName;
	
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
	
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		checkName(givenName, true);
		this.givenName = givenName;
	}
	
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		checkName(familyName, false);
		this.familyName = familyName;
	}
	
	public String getFullName() {
		String gn = givenName;
		if (gn == null) {
			gn = "?";
		};
		String fn = familyName;
		if (fn == null) {
			fn = "?";
		};
		return gn + " " + fn;
	   // kompakt variant:
	   // return (givenName != null ? givenName : "?") + " " + (familyName != null ? familyName : "?") 
	}

	public void setFullName(String fullName) {
		checkName(fullName, true);
		int pos = fullName.lastIndexOf(' ');
		String gn = fullName.substring(0, pos);
		if (gn.equals("?")) {
			gn = null;
		}
		String fn = fullName.substring(pos + 1);
		if (fn.equals("?")) {
			fn = null;
		}
		this.givenName = gn;
		this.familyName = fn;
	}
	
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
