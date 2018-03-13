package com.example.oryossipof.alphahotal;

/**
 * Created by or yossipof on 02/12/2017.
 */

public class SelectOption {


    private String Description;
    private int drawable;
    private Class intent;

    public SelectOption(String description, int drawable, Class intent) {
        Description = description;
        this.drawable = drawable;
        this.intent = intent;

    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public Class getIntent() {
        return intent;
    }

    public void setIntent(Class intent) {
        this.intent = intent;
    }
}
