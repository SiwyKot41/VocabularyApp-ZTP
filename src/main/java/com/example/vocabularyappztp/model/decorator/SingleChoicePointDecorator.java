package com.example.vocabularyappztp.model.decorator;

public class SingleChoicePointDecorator extends  PointDecorator{

  public SingleChoicePointDecorator(Points points) {
    super(points);
  }

  public int getPoints() {
    return super.getPoints() + 1;
  }

}
