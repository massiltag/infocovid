package com.pantheonsorbonne.infocovid.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "test")
public class Test {

    @Id
    int id;

    String attrCustom;

}
