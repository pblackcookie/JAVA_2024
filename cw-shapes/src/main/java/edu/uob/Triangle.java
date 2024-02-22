package edu.uob;

// "implements MultiVariantShape" allows triangle has two different types TwoDimensionalShape & MultiVariantShape
public class Triangle extends TwoDimensionalShape implements MultiVariantShape {
  private long s1;
  private long s2;
  private long s3;
  private static int count;

  // TODO implement me!
  public Triangle(long s1, long s2, long s3) {
    this.s1 = s1;
    this.s2 = s2;
    this.s3 = s3;
    count++;

  }

  // Get the longest side of one triangle
  public long getLongestSide(){
    return Math.max(Math.max(s1,s2),s3);
  }

  // Printf info of triangle
  public String toString(){
    return "This is a " + getColour() + " Triangle with sides of length " + s1 + ", " + s2 + ", " + s3;
  }

  //Task 5
  public static int getPopulation(){
    return Triangle.count;
  }

  // Task 7 -- int, float and double all not usable , only one is using type long ( but why?
  // only the long type can solve this question
  public TriangleVariant getVariant(){
    if(s1 <= 0 || s2 <= 0 || s3 <= 0){
      return TriangleVariant.ILLEGAL;
    }else if(s1 + s2 < s3 || s2 + s3 < s1 || s1 + s3 < s2){
      return TriangleVariant.IMPOSSIBLE;
    } else if (s1 - s2 > s3 || s2 - s3 > s1 || s1 - s3 > s2) {
      return TriangleVariant.IMPOSSIBLE;
    } else if (s2 - s1 > s3 || s3 - s2 > s1 || s3 - s1 > s2) {
      return TriangleVariant.IMPOSSIBLE;
    } else if (s1 == s2 && s2 == s3) {
      return TriangleVariant.EQUILATERAL;
    } else if ((s1 == s2 && s1 + s2 != s3)||(s2 == s3 && s2 + s3 != s1) || (s1 == s3 && s1 + s3 != s2)) {
      return TriangleVariant.ISOSCELES;
    } else if(s1*s1+s2*s2 - s3*s3 == 0|| s2*s2+s3*s3 - s1*s1 == 0||s1*s1+s3*s3 - s2*s2 == 0){
      return TriangleVariant.RIGHT;
    } else if(s1 + s2 == s3 || s1 + s3 == s2 || s2 + s3 == s1){
      return TriangleVariant.FLAT;
    }else {
      return TriangleVariant.SCALENE;
    }
  }
  // TODO implement me!
  public double calculateArea() {
    long s;
    double area;
    s = (s1 + s2 + s3) / 2;
    area =  Math.sqrt(s*(s - s1)*(s - s2)*(s - s3));
    return area;
  }

  // TODO implement me!
  public int calculatePerimeterLength() {
    long C;
    C = s1 + s2 + s3;
    return (int)C;
  }
}
