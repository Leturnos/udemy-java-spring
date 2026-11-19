package com.udemy.spring_boot.integrationtests.dto.Wrapper;

import com.udemy.spring_boot.integrationtests.dto.PersonDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class PersonPagedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content") // dispensável, já que a variável tem o mesmo nome
    private List<PersonDTO> content;

    public PersonPagedModel() {}

    public List<PersonDTO> getContent() {
        return content;
    }

    public void setContent(List<PersonDTO> content) {
        this.content = content;
    }
}
