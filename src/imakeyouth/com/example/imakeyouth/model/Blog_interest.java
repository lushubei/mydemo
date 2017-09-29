package com.example.imakeyouth.model;

import java.io.Serializable;

/**
 * <p>
 * 博客，兴趣关系表
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
public class Blog_interest implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer blog_id;
	private Integer interest_id;


	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public Integer getInterest_id() {
		return interest_id;
	}

	public void setInterest_id(Integer interest_id) {
		this.interest_id = interest_id;
	}

}
