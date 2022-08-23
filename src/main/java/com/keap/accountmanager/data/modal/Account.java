package com.keap.accountmanager.data.modal;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Builder
@Getter
@Document(indexName = "account")
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @Generated
    private String id;

    @Field(type = FieldType.Text, name = "username")
    private String username;

    @Field(type = FieldType.Text, name = "company")
    private String company;

    @Field(type = FieldType.Text, name = "title")
    private String title;
}
