package com.example.imakeyouth.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
public class User_project implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer user_id;
	private Integer project_id;


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

}
