package com.hitv.android.uiversion2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name = "recommendPlace", type = RecommendPlace.class)
public class RecommendPlace implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1376409638254999430L;
	private String tvThumbnailUrl;
	private String tvThumbnailLocalUrl;
	private String mobileThumbnailUrl;
	private List<Content> content;
	private List<RecommendPlaceBackground> recommendPlaceBackground;

	public List<Content> getContent() {
		return content;
	}

	@XmlAnnotation(name = "content", type = ArrayList.class, target = Content.class)
	public void setContent(List<Content> content) {
		this.content = content;
	}

	public String getTvThumbnailUrl() {
		return tvThumbnailUrl;
	}

	@XmlAnnotation(name = "tvThumbnailUrl", type = String.class)
	public void setTvThumbnailUrl(String tvThumbnailUrl) {
		this.tvThumbnailUrl = tvThumbnailUrl;
	}

	public String getTvThumbnailLocalUrl() {
		return tvThumbnailLocalUrl;
	}

	@XmlAnnotation(name = "tvThumbnailLocalUrl", type = String.class)
	public void setTvThumbnailLocalUrl(String tvThumbnailLocalUrl) {
		this.tvThumbnailLocalUrl = tvThumbnailLocalUrl;
	}

	public String getMobileThumbnailUrl() {
		return mobileThumbnailUrl;
	}

	@XmlAnnotation(name = "mobileThumbnailUrl", type = String.class)
	public void setMobileThumbnailUrl(String mobileThumbnailUrl) {
		this.mobileThumbnailUrl = mobileThumbnailUrl;
	}

	public List<RecommendPlaceBackground> getContentBackground() {
		return recommendPlaceBackground;
	}

	@XmlAnnotation(name = "recommendPlaceBackground", type = ArrayList.class, target = RecommendPlaceBackground.class)
	public void setContentBackground(
			List<RecommendPlaceBackground> recommendPlaceBackground) {
		this.recommendPlaceBackground = recommendPlaceBackground;
	}

}
