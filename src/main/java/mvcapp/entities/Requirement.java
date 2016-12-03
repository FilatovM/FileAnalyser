package mvcapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requirements")
public class Requirement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "comment")
    private String comment;

    @Column(name = "done")
    private Boolean done;

    @Column(name = "time estimated")
    private Integer time;

    @Column(name = "date")
    private Date date;

    //Getters and Setters
    public Integer getId(){
        return id;
    }

    public void setId(Integer x){
        this.id = x;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String s){
        this.title = s;
    }

    public String getText(){
        return text;
    }

    public void setText(String s){
        this.text = s;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String s){
        this.comment = s;
    }

    public boolean isDone(){
        return done;
    }

    public void setDone(Boolean b){
        this.done = b;
    }

    public Integer getTime(){
        return time;
    }

    public void setTime(Integer x){
        this.time = x;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date d){
        this.date = d;
    }
}
