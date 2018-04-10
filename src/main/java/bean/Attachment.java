package bean;

import java.sql.Timestamp;

/**
 * 附件表,用于文件上传\下载
 * CREATE TABLE attachment (
 id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
 origin_name VARCHAR(1024) NOT NULL ,
 local_name VARCHAR(1024) NOT NULL ,
 create_date TIMESTAMP NOT NULL
 );

 */
public class Attachment {
    private Integer id;
    private String originName;
    private String localName;
    private Timestamp createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
