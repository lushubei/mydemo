package com.example.imakeyouth.model;

import java.io.Serializable;

/**
 * <p>
 * 用户，虚拟项目关系表
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
public class User_activity implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer user_id;
	private Integer activity_id;


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

}
