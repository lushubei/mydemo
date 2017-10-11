package com.example.imakeyouth.model;

import java.io.Serializable;

/**
 * <p>
 * 用户兴趣表
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
public class User_interest implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer user_id;
	private Integer interest_id;


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getInterest_id() {
		return interest_id;
	}

	public void setInterest_id(Integer interest_id) {
		this.interest_id = interest_id;
	}

}
