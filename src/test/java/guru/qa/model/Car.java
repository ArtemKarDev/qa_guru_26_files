package guru.qa.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String mark;
    private String model;
    private Integer year;

    private List<String> colors = new ArrayList<String>();

    private CarFeatures features;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public CarFeatures getFeatures() {
        return features;
    }

    public void setFeatures(CarFeatures features) {
        this.features = features;
    }
}
