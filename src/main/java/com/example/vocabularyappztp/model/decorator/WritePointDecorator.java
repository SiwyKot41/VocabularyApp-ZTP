package com.example.vocabularyappztp.model.decorator;

public class WritePointDecorator extends PointDecorator{

  public WritePointDecorator(Points points) {
    super(points);
  }

  public int getPoints() {
    return super.getPoints() + 2;
  }

}
