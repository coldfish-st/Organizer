package organiser;

/**
 *  Build up a class to store data in a ArrayList.
 * @author Zhenge Jia
 * 
 */
public class Person {
	
	String name, sex, mobile, tele, face_qq, home_add, com_add, email, group, note, birthday;
	
	public Person () {
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.mobile = mobile;
		this.tele = tele;
		this.face_qq = face_qq;
		this.home_add = home_add;
		this.com_add = com_add;
		this.email = email;
		this.group = group;
		this.note = note;
	}
	
	public String getName () {
		return name;
	}
	
	public String getBirth () {
		return birthday;
	}
	
	public String getSex () {
		return sex;
	}
	
	public String getMobile () {
		return mobile;
	}
	
	public String getTele () {
		return tele;
	}
	
	public String getFace () {
		return face_qq;
	}
	
	public String getHome () {
		return home_add;
	}
	
	public String getCompany () {
		return com_add;
	}
	
	public String getEmail () {
		return email;
	}
	
	public String getGroup () {
		return group;
	}
	
	public String getNote () {
		return note;
	}
	
	@Override
	public String toString() {
		return "Name:" + name + "   Sex:" + sex + "    Birthday:" + birthday
				+ "   Mobile:" + mobile + "   Tele:" + tele
				+ " MSN/QQ:" + face_qq + "   Com Add:" + com_add
				+ "   Email:" + email + "   Home Add:" + home_add + "  Group:"
				+ group + "  Note:" + note + "\n";
		
	}
}
