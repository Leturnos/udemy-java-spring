package com.udemy.spring_boot.integrationtests.dto.Wrapper;

import com.udemy.spring_boot.integrationtests.dto.BookDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class BookPagedModel {
	
	@XmlElement(name = "content") 
	private List<BookDTO> content;

	public BookPagedModel() {}

	public List<BookDTO> getContent() {
		return content;
	}

	public void setContent(List<BookDTO> content) {
		this.content = content;
	}
}
