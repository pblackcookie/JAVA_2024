package edu.uob;

public class Shapes {

  // TODO use this class as then entry point; play around with your shapes, etc
  public static void main(String[] args) {
    // System.out.println("Hello world!");
    // Task 2
//    Triangle testTriangle = new Triangle(5, 7, 9);
//
//    double longestSide = testTriangle.getLongestSide();
//    System.out.println("The longest side of the triangle is " + longestSide);
//    // Task 3
//    System.out.println(testTriangle.toString());
    // Task 4
    TwoDimensionalShape shape;
    shape = new Circle(5);
    shape.setColour(Colour.BLUE);
    System.out.println(shape);
//    shape = new Triangle(4, 4, 4);
//    shape.setColour(Colour.BLACK);
//    System.out.println(shape);
    shape = new Rectangle(5, 3);
    shape.setColour(Colour.CYAN);
    System.out.println(shape);
    // week 3 -- task 3
    if(shape instanceof MultiVariantShape) {
      System.out.println("This shape has multiple variants");
    } else {
      System.out.println("This shape has only one variant");
    }
    // week3 -- task 4 - create an array
    TwoDimensionalShape[] array = new TwoDimensionalShape[100];
    int count = 0;
    for(int i = 0; i < 100 ; i++){
      double j = Math.random();
//      System.out.println("The value of random number is " + j + "\n");
      if(j < 0.33){
        array[i] = new Circle(5);
      }
      if(0.33 <= j && j < 0.66){
        array[i] = new Triangle(4,4,4);
      }
      if(j >= 0.66){
        array[i] = new Rectangle(4,3);
      }
      if(array[i] instanceof MultiVariantShape){
        count++;
      }
    }
    System.out.println(" [ In main function => The total number of triangle is : " + count + "\n");
    // week 3 -> Task 5
    System.out.println(" [ In Triangle class => The total number of triangle is : " + Triangle.getPopulation() + "\n");
  }
}
