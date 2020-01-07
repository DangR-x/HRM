package cn.itsource.hrm.doucument;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 14:53
 * @Version v1.0
 */


@Data
@Document(indexName = "hrm",type = "course")//indexName索引库名称，type索引库类型
public class CourseDocument {

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String users;
    @Field(type = FieldType.Long)
    private Long courseTypeId;
    @Field(type = FieldType.Keyword)
    private String gradeName;
    @Field(type = FieldType.Long)
    private Long grade;
    @Field(type = FieldType.Integer)
    private Integer status;
    @Field(type = FieldType.Long)
    private Long tenantId;
    @Field(type = FieldType.Keyword)
    private String tenantName;
    @Field(type = FieldType.Long)
    private Long user_id;
    @Field(type = FieldType.Keyword)
    private String userName;
    @Field(type = FieldType.Long)
    private Long start_time;
    @Field(type = FieldType.Long)
    private Long end_time;
    @Field(type = FieldType.Keyword)
    private String pic;
    //test单词分词，指定分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String All;


}
