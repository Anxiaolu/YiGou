package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.imageio.plugins.common.I18N;



@ManagedBean(name="userlog")
@SessionScoped
public class LogBean {
	private String name,password;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName(){
		return name;
	}
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @return strReadLine 读取的一行数据
	 * @throws Exception  
	 */
	//从Users.csv文件中读取一行记录
	public String readaLine() throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader("/home/huanlu/workspace/validation-2/date/Users.csv"));
		String strReadLine = reader.readLine();
		return strReadLine;
	}
	
	/**
	 * 对整个User.csv文件遍历一边，获取整个文件的行数，便于后面的使用
	 * @return 返回文件的行数
	 * @throws IOException
	 */
	public int Count() throws IOException {
		File f = new File("/home/huanlu/workspace/validation-2/date/Users.csv");
		InputStream input = new FileInputStream(f);
		BufferedReader b = new BufferedReader(new InputStreamReader(input));
		//.readLine()方法每次读取只读取一行
		String value = b.readLine();
		int count = 0;
		while(value != null) {
			count++;
			value = b.readLine();
		}
		b.close();
		input.close();
		return count;
		
	}
	/**
	 * 先将每一行取出，放到一个字符串数组里面，后面将一个数组元素扩展为一个存储一条用户注册信息的记录
	 * @return 返回存储每一个用户注册新的字符串数组
	 * @throws IOException
	 */
	public String[] getLineFromCsv() throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("/home/huanlu/workspace/validation-2/date/Users.csv"));
		int a = Count();
		String[] user = new String[a + 1];
		for (int n = 0; n <= a; n++) {
			String line = reader.readLine();
			user[n] = line;
		}
		return user;
	}
	
	/**
	 * 将获取的每一行数据存入到一个一位数组里面，根据num的值来制定获取哪一行的用户名的值
	 * @param num 根据num的值制定用来获取哪一行的值
	 * @return 返回该行的用户名字段
	 * @throws IOException
	 */
	public String getWhichNameFromCsv(int num) throws IOException {
		String data[] = getLineFromCsv();
		String[] usersname = data[num].split("\t");
		return usersname[0];
	}
	/**
	 * 将获取的每一行数据存入到一个一位数组里面，根据num的值来制定获取哪一行的密码的值
	 * @param num 根据num的值指定用来获取哪一行的值
	 * @return 返回该行的密码字段
	 * @throws IOException
	 */
	public String getWhichPasswordFromCsv(int num) throws IOException {
		String data[] = getLineFromCsv();
		String[] usersname = data[num].split("\t");
		return usersname[1];
	}
	
	/**
	 * 先将获取的用户名字段进行匹配，若匹配成功，再匹配用户名后面的密码字段，
	 * 如果一直到文件最后都没有匹配到用户名，将flag的值设为1.
	 * @return 返回flag值用来判断是否查询成功
	 * @throws IOException
	 */
	public int check() throws IOException {
		int i = 1, flag = 0;
		while (flag == 0) {
			//进行用户名字段的匹配
			if (this.name.equals(getWhichNameFromCsv(i)) == true) {
				//用户名字段匹配成功之后进行密码字段的匹配
				if (this.password.equals(getWhichPasswordFromCsv(i)) == true) {
					flag = 1;
				}
			} else {
				flag = 0;
			}
			i++;
			
		}
		if(i>Count())
				flag = 0;
		return flag;
	}
	

	public String logSucess() throws IOException{
		//根据check方法返回的数值来判断是否已经匹配成功
		if(check()==1){
			return ("login_sucess");
		}
		else{
			return ("login_failed");
		}
	}
	
}
