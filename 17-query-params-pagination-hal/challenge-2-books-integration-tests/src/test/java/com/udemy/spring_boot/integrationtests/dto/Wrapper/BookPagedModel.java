package com.udemy.spring_boot.integrationtests.dto.Wrapper;

import com.udemy.spring_boot.integrationtests.dto.BookDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class BookPagedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content") // dispensável, já que a variável tem o mesmo nome
    private List<BookDTO> content;

    public BookPagedModel() {}

    public List<BookDTO> getContent() {
        return content;
    }

    public void setContent(List<BookDTO> content) {
        this.content = content;
    }
}
