package zx.ffts.entity.chenkai;

import java.io.Serializable;

public class TsUser implements Serializable{
	private Integer userid;				//编号
	private String username;			//用户名
	private String pwd;					//密码
	private String confirmPwd;			//确认密码
	private String tel;					//电话	
	private String email;				//邮件
	private String address;				//地址
	private String realname;			//真实姓名
	private Integer balance;			//余额
	private Integer score;				//积分
	private String gender;				//性别
	private String regdate;				//注册时间
	private Integer authority;			//权限
	private String photo;				//图片
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public TsUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TsUser(Integer userid, String username, String pwd,
			String confirmPwd, String tel, String email, String address,
			String realname, Integer balance, Integer score, String gender,
			String regdate, Integer authority, String photo) {
		super();
		this.userid = userid;
		this.username = username;
		this.pwd = pwd;
		this.confirmPwd = confirmPwd;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.realname = realname;
		this.balance = balance;
		this.score = score;
		this.gender = gender;
		this.regdate = regdate;
		this.authority = authority;
		this.photo = photo;
	}

	
}
