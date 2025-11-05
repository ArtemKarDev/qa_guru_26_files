package guru.qa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Glossary {
    private String title;
    @SerializedName("ID")
    private Integer id;
    @SerializedName("glossaryOptions")
    private List<GlossaryOption> glossaryOptions;
    private GlossaryInner glossary;
    private GlossaryOption glossaryInnerList;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GlossaryInner getGlossary() {
        return glossary;
    }

    public void setGlossary(GlossaryInner glossary) {
        this.glossary = glossary;
    }

    public List<GlossaryOption> getGlossaryOptions(){
        return glossaryOptions;
    }

}
