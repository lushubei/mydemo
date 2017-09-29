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
public class Activity_interest implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer activity_id;
	private Integer interest_id;


	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getInterest_id() {
		return interest_id;
	}

	public void setInterest_id(Integer interest_id) {
		this.interest_id = interest_id;
	}

}
