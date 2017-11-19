package com.example.imakeyouth.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 验证码发送记录
 * </p>
 *
 * @author xiaobei
 * @since 2017-11-18
 */
public class Validate_code implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 目标用户
     */
	private Long to_user_id;
    /**
     * 目的邮箱地址
     */
	private String to_email;
    /**
     * 创建时间
     */
	private Date created_date;
    /**
     * 最后修改时间
     */
	private Date updated_date;
    /**
     * 创建者ID
     */
	private Long creator_id;
    /**
     * 最后更新者ID
     */
	private Long updator_id;
    /**
     * 状态（0-失败；1-成功）
     */
	private Integer status;
    /**
     * 验证码
     */
	private String validate_code;
    /**
     * 验证码失效日期
     */
	private Date expiry_date;
    /**
     * 删除标记
     */
	private Boolean is_deleted;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(Long to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getTo_email() {
		return to_email;
	}

	public void setTo_email(String to_email) {
		this.to_email = to_email;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}

	public Long getUpdator_id() {
		return updator_id;
	}

	public void setUpdator_id(Long updator_id) {
		this.updator_id = updator_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

}
