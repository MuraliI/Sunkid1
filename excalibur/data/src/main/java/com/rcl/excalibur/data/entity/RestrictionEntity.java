package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;

import java.util.List;

@Table(name = RestrictionEntity.TABLE_NAME)
public class RestrictionEntity extends Model {

    public static final String TABLE_NAME = "restriction";
    public static final String COLUMN_RESTRICTION_ID = "restriction_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_MANDATORY = "mandatory";
    public static final String COLUMN_DISPLAY_TEXT = "display_text";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEDIA = "media";
    public static final String COLUMN_ANSWERS = "answers";
    public static final String COLUMN_PRODUCT = "product";


    @Column(name = COLUMN_RESTRICTION_ID)
    public String restrictionId;
    @Column(name = COLUMN_TYPE)
    public String type;
    @Column(name = COLUMN_MANDATORY)
    public boolean mandatory;
    @Column(name = COLUMN_DISPLAY_TEXT)
    public String displayText;
    @Column(name = COLUMN_QUESTION)
    public String question;
    @Column(name = COLUMN_TITLE)
    public String title;
    @Column(name = COLUMN_DESCRIPTION)
    public String description;
    @Column(name = COLUMN_ANSWERS)
    public String answersJson;
    @Column(name = COLUMN_PRODUCT)
    public ProductEntity product;
    @Column(name = COLUMN_MEDIA)
    public MediaEntity media;

    public RestrictionEntity() {
        super();
    }

    public String[] getAnswers() {
        return new Gson().fromJson(answersJson, String[].class);
    }

    public void setAnswers(List<String> answers) {
        this.answersJson = new Gson().toJson(answers);
    }

    public void setAnswers(String[] answers) {
        this.answersJson = new Gson().toJson(answers);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    public String getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(String restrictionId) {
        this.restrictionId = restrictionId;
    }
}
