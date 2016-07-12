package com.hitv.android.uiversion2.bean;

import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name = "getRecommendContentResponse", type = RecommendContentXML.class)
public class RecommendContentXML {
	private PageInfo pageInfo;
	
	private Result result;
	private List<RecommendPlace> recommendPlace;

	public List<RecommendPlace> getRecommendPlace() {
		return recommendPlace;
	}

	@XmlAnnotation(name = "recommendPlace", type = ArrayList.class, target = RecommendPlace.class)
	public void setRecommendPlace(List<RecommendPlace> recommendPlace) {
		this.recommendPlace = recommendPlace;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	@XmlAnnotation(name = "pageInfo", type = PageInfo.class, target = PageInfo.class)
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Result getResult() {
		return result;
	}

	@XmlAnnotation(name = "result", type = Result.class, target = Result.class)
	public void setResult(Result result) {
		this.result = result;
	}
}
