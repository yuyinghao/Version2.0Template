package com.hitv.android.uiversion2.bean;

import java.io.Serializable;

import com.dmx.common.xml.core.XmlAnnotation;

@XmlAnnotation(name = "result", type = Result.class)
public class Result implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -18777147217409341L;
	private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    @XmlAnnotation(name = "code", type = Integer.class)
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @XmlAnnotation(name = "message", type = String.class)
    public void setMessage(String message) {
        this.message = message;
    }
}
