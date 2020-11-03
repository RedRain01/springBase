package com.example.oracleserver.entity;

/**
 * <p>
 * @author WillYang
 * @Date 2019-10-14 11:02:46
 * @since 1.0
 */
public class Whytest {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** . */
    private Integer id ;

    /** . */
    private String name ;

    /** . */
    private String state ;
    /** . */
    private String writer ;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    /** set . */
    public void setId(Integer id) {
        this.id = id;
    }

    /** get . */
    public Integer getId() {
        return this.id;
    }


    /** set . */
    public void setName(String name) {
        this.name = name;
    }

    /** get . */
    public String getName() {
        return this.name;
    }

}

