package com.example.vocabularyappztp.model.decorator;

public class TestPointDecorator extends  PointDecorator{

    public TestPointDecorator(Points points) {
        super(points);
    }

    public int getPoints() {
        return super.getPoints() + 1;
    }

}
