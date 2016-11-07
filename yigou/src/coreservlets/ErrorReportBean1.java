package coreservlets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ErrorReportBean1 {
  private String name,password,repassword, emailAddress,telephone;
                 

  public String getName() {
    return(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return(emailAddress);
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getpassword() {
    return(password);
  }

  public void setpassword(String password) {
    this.password = password;
  }

  public String getrepassword() {
    return(repassword);
  }

  public void setrepassword(String repassword) {
    this.repassword = repassword;
  }

  public String gettelephone() {
    return(telephone);
  }

  public void settelephone(String telephone) {
    this.telephone = telephone;
  }

  protected void storeReportInDatabase() {
    // Store the error report
  }
  
  public String sendReport() {
    storeReportInDatabase();
    return("report-confirmation");
  }
}
