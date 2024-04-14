package guru.qa.model;

import com.google.gson.annotations.SerializedName;

public class GlossaryInner {
    @SerializedName("SortAs")
    private String sortAs;
    @SerializedName("Acronym")
    private String acronym;

    public String getSortAs() {
        return sortAs;
    }

    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
