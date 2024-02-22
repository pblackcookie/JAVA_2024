package edu.uob;

public abstract class TwoDimensionalShape {
  private Colour colour;
  public TwoDimensionalShape() {
    //this.colour = colour;
  }
  public Colour getColour(){
    return colour;
  }

  public void setColour(Colour colour){
    this.colour = colour;
  }

  public abstract double calculateArea();

  public abstract int calculatePerimeterLength();
}
