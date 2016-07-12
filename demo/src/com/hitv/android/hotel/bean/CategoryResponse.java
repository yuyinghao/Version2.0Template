package com.hitv.android.hotel.bean;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;

import com.dmx.common.xml.core.XmlAnnotation;
import com.hitv.android.uiversion2.bean.PageInfo;
import com.hitv.android.uiversion2.bean.Result;

@XmlAnnotation(name="getCategoryResponse", type=CategoryResponse.class)
public class CategoryResponse {
	private Result result;
	private List<Category> categorys;
	private PageInfo pageInfo;
	private List<MainBackground> mainBackgrounds;
	public Result getResult() {
		return result;
	}
	
	@XmlAnnotation(name="result", type=Result.class, target=Result.class)
	public void setResult(Result result) {
		this.result = result;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	
	@XmlAnnotation(name="categorys", type=ArrayList.class, target=Category.class)
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	@XmlAnnotation(name="pageInfo", type=PageInfo.class, target=PageInfo.class)
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<MainBackground> getMainBackgrounds() {
		return mainBackgrounds;
	}

	@XmlAnnotation(name="mainBackgrounds", type=ListActivity.class, target=MainBackground.class)
	public void setMainBackgrounds(List<MainBackground> mainBackgrounds) {
		this.mainBackgrounds = mainBackgrounds;
	}
	
	
}
