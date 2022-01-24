package com.example.vocabularyappztp.model.decorator;

public class PointDecorator implements Points{

  private Points points;

  public PointDecorator(Points points) {
    this.points = points;
  }

  @Override
  public int getPoints() {
    return points.getPoints();
  }
}
