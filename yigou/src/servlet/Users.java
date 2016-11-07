package servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



//用户实体类
@ManagedBean(name="user")
@SessionScoped
public class Users {

	private String name; //用户名	
	private String password; //密码
	private String repassword;//二次输入密码
	private String email; //邮箱地址
	private String gender; //性别
	private Date birthday; //出生日期
	
	public Users()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		//Date d = new Date();
		//d = birthday;
		//SimpleDateFormat bir =  new SimpleDateFormat("MM/dd/yyyy");
		//bir.format(d);
		this.birthday = birthday;
	}
	public File creatFile(){
		File f = new File("/home/huanlu/workspace/validation-2/date/Users.csv");
		return f;
	}
	public void storeReportIntoDatebase() throws IOException{
		File f = creatFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(f,true));//true参数为追加数据
		//判断文件是否存在
		if(f.exists())
		{
			try {
				//将用户注册的信息写入到Users.csv文件当中
				out.write(name+"\t"+password+"\t"+gender+"\t"+email+"\t");
				out.newLine();
				out.close();
			} 
			catch (FileNotFoundException e) { 
		          // File对象的创建过程中的异常捕获
		          e.printStackTrace(); 
		        }
			catch (IOException e) { 
		          // BufferedWriter在关闭对象捕捉异常
		          e.printStackTrace(); 
		        } finally {
				// 将用户的注册信息写入到Users.csv文件当中
			}
		}
		else{
			System.out.println("数据写入异常！User.csv文件未找到！");
		}
	}
	public String sendreport() throws IOException {
		storeReportIntoDatebase();
		return("log");
	}
	
}






