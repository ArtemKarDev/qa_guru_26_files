package guru.qa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GlossaryOption {
    private List<GlossaryOption> glossaryOption;

    @SerializedName("term")
    private String term;
    @SerializedName("definition")
    private String definition;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }


}
