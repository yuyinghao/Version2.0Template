package com.hitv.android.hotel.bean;

import java.util.ArrayList;
import java.util.List;

import com.dmx.common.xml.core.XmlAnnotation;
import com.hitv.android.uiversion2.bean.Result;

@XmlAnnotation(name="getChildChannelResponse", type=ChildChannelResponse.class)
public class ChildChannelResponse {
	
	private Result result;
	private List<Channel> channels;
	private String mainChannelId;
	private String mainChannelContentSize;
	private List<MainBackground> mainBackgrounds;
	public Result getResult() {
		return result;
	}
	
	@XmlAnnotation(name="result", type=Result.class, target=Result.class)
	public void setResult(Result result) {
		this.result = result;
	}

	public List<MainBackground> getMainBackgrounds() {
		return mainBackgrounds;
	}

	@XmlAnnotation(name="mainBackgrounds", type=ArrayList.class, target=MainBackground.class)
	public void setMainBackgrounds(List<MainBackground> mainBackgrounds) {
		this.mainBackgrounds = mainBackgrounds;
	}

	public List<Channel> getChannels() {
		return channels;
	}
	
	@XmlAnnotation(name="channels", type=ArrayList.class, target=Channel.class)
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public String getMainChannelContentSize() {
		return mainChannelContentSize;
	}

	@XmlAnnotation(name="mainChannelContentSize", type=String.class)
	public void setMainChannelContentSize(String mainChannelContentSize) {
		this.mainChannelContentSize = mainChannelContentSize;
	}

	public String getMainChannelId() {
		return mainChannelId;
	}

	@XmlAnnotation(name="mainChannelId", type=String.class)
	public void setMainChannelId(String mainChannelId) {
		this.mainChannelId = mainChannelId;
	}

}
