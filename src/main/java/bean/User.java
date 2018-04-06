package bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户实体类
 * create table user (
 id int(8) not null auto_increment primary key,
 username varchar(128) not null,
 email varchar(128) not null,
 tel varchar(128),
 password varchar(128) not null,
 gender char(1) not null default '0',  -- 0 女	1 男
 create_date datetime not null,
 update_date datetime not null,
 user_type char(1) not null default '0', -- 0 普通用户  1 管理员
 del_flag char(1) not null default '0'   -- 0正常状态 9 删除状态
 );

 */
public class User {
	private Integer id;
	private String username;
	private String email;
	private String tel;
	private String password;
	private String gender;
	private Timestamp createDate;
	private String userType;
	private String delFlag;

	public User() {
	}

	public User(String username, String email, String tel, String password, String gender, String userType) {
		this.username = username;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.gender = gender;
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", tel='" + tel + '\'' +
				", password='" + password + '\'' +
				", gender=" + gender +
				", createDate=" + createDate +
				", userType=" + userType +
				", delFlag=" + delFlag +
				'}';
	}
}
