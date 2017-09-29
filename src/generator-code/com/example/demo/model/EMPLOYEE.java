package com.example.demo.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-29
 */
public class EMPLOYEE implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer ID;
	private String CODE;
	private String NAME;


	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String CODE) {
		this.CODE = CODE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

}
