package com.example.imakeyouth.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xiaobei
 * @since 2017-11-19
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String sex;
	/**
	 * 0:系统管理员，1:普通用户
	 */
	private Integer user_type;
	private Date create_time;
	private Date birthday;
	private String session_id;
	/**
	 * session失效时间
	 */
	private Date session_expired_date;
	/**
	 * session有效时间
	 */
	private Integer session_expired_minutes;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Date getSession_expired_date() {
		return session_expired_date;
	}

	public void setSession_expired_date(Date session_expired_date) {
		this.session_expired_date = session_expired_date;
	}

	public Integer getSession_expired_minutes() {
		return session_expired_minutes;
	}

	public void setSession_expired_minutes(Integer session_expired_minutes) {
		this.session_expired_minutes = session_expired_minutes;
	}

}
