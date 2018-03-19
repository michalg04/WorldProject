final class Point
{
   public final int x;
   public final int y;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }

   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }

   public int distanceSquared(Point p){
      double deltaX = Math.pow(x - p.x, 2.0);
      double deltaY = Math.pow(y - p.y, 2.0);
      double dis = Math.sqrt(deltaX + deltaY);
      return  (int)Math.pow(dis, 2);
   }
   public int getX() {return x;}
   public int getY() {return y;}

}
