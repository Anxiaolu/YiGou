package servlet;


import javax.faces.bean.ManagedBean;

@ManagedBean(name="genderOptions")

public class genderOptions {
	private String[] gender = 
	    { "man", "woman"};
	  

	  public String[] getGender() {
	    return(gender);
	  }

}
