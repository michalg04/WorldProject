final class Viewport
{
   private int row;
   private int col;
   private final int numRows;
   private final int numCols;

   public Viewport(int numRows, int numCols)
   {
      this.numRows = numRows;
      this.numCols = numCols;
   }

   public void shift(int col, int row)
   {
      this.col = col;
      this.row = row;
   }
   public boolean contains(Point p)
   {
      return p.y >= row && p.y < row + numRows &&
              p.x >= col && p.x < col + numCols;
   }

   public Point viewportToWorld(int col1, int row1)
   {
      return new Point(col1 + col, row1 + row);
   }

   public Point worldToViewport(int col1, int row1)
   {
      return new Point(col1 - col, row1 - row);
   }

   public int getRow() {return row;}
   public int getNumRows() {return numRows;}
   public int getCol() {return col;}
   public int getNumCols() {return numCols;}
}

