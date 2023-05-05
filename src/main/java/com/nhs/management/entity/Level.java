package com.nhs.management.entity;

public enum Level {
    //Expert/Practitioner/Working/Awareness

    EXPERT(1), PRACTITIONER(2), WORKING(3), AWARENESS(4);

    private Integer level;

    private Level(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

}
