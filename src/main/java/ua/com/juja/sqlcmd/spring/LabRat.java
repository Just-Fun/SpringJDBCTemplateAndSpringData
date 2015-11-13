package ua.com.juja.sqlcmd.spring;

/**
 * Created by oleksandr.baglai on 13.11.2015.
 */
public class LabRat {

    private HelloWorldService service;
    private String text;
    private String name;

    public LabRat(String text, HelloWorldService service) {
        this.text = text;
        this.service = service;
    }

    public String getText() {
        return text + "{" + service.getData() + "}";
    }

    public void init() {
        //
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name + "{" + service.getData() + "}";
    }

    public void sayHi(){
        System.out.println(getText());
    }

}