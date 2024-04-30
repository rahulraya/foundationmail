package com.example.foundationMail.Model;

import java.util.List;

public class EmailRequest {
    private String to;
    private String subject;
    private String text;
    private List<String> ngos;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getNgos() {
		return ngos;
	}
	public void setNgos(List<String> ngos) {
		this.ngos = ngos;
	}

    
}

