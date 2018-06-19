package com.dia.common.dto.base;

import com.dia.common.util.PageUtil;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Dia
 * @Description: 公共分页请求实体
 * @version: v1.0.0
 * @date: 2018年6月14日20:19:52
 */
public class BasePageReqDto {

	
	@ApiModelProperty(notes = "第几页")
	private int page;
	@ApiModelProperty(notes = "每页数量")
	private int pageSize;
	@ApiModelProperty(notes = "是否需要显示总数 ，pc特有")
	private int showCount;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	
	public int getMySqlStart() { 
		return (PageUtil.getPageLimit(this.page) - 1) * PageUtil.getPageLimit(this.pageSize); 
	}
	
	public int getMySqlEnd() {
		return PageUtil.getPageLimit(this.pageSize);
	}

}
