import java.util.concurrent.RecursiveTask;

class MatrixMultiplication extends RecursiveTask<Matrix> {
  
  /** The fork threshold. */
  private static final int FORK_THRESHOLD = 128;

  /** The first matrix to multiply with. */
  private Matrix m1;

  /** The second matrix to multiply with. */
  private Matrix m2;

  /** The starting row of m1. */
  private int m1Row;

  /** The starting col of m1. */
  private int m1Col;

  /** The starting row of m2. */
  private int m2Row;

  /** The starting col of m2. */
  private int m2Col;

  /**
   * The dimension of the input (sub)-matrices and the size of the output
   * matrix.
   */
  private int dimension;

  /**
   * A constructor for the Matrix Multiplication class.
   * @param  m1 The matrix to multiply with.
   * @param  m2 The matrix to multiply with.
   * @param  m1Row The starting row of m1.
   * @param  m1Col The starting col of m1.
   * @param  m2Row The starting row of m2.
   * @param  m2Col The starting col of m2.
   * @param  dimension The dimension of the input (sub)-matrices and the size
   *     of the output matrix.
   */
  MatrixMultiplication(Matrix m1, Matrix m2, int m1Row, int m1Col, int m2Row,
                       int m2Col, int dimension) {
    this.m1 = m1;
    this.m2 = m2;
    this.m1Row = m1Row;
    this.m1Col = m1Col;
    this.m2Row = m2Row;
    this.m2Col = m2Col;
    this.dimension = dimension;
  }


  @Override
  public Matrix compute() {
    if (this.dimension < FORK_THRESHOLD) {
        return Matrix.nonRecursiveMultiply(m1, m2, m1Row, m1Col, m2Row, m2Col, dimension);
    } else {
        int size = dimension /2;
        Matrix newResult = new Matrix(dimension);

        MatrixMultiplication ma11b11 = new MatrixMultiplication(m1,m2,m1Row,m1Col,
                      m2Row,m2Col, size);
        MatrixMultiplication ma12b21 = new MatrixMultiplication(m1, m2, m1Row, 
                      m1Col + size, m2Row + size, m2Col, size);

        MatrixMultiplication ma11b12 = new MatrixMultiplication(m1, m2, m1Row, m1Col, 
                      m2Row, m2Col + size, size);
        MatrixMultiplication ma12b22 = new MatrixMultiplication(m1, m2, m1Row, 
                      m1Col + size, m2Row + size, m2Col + size, size);

        MatrixMultiplication ma21b11 = new MatrixMultiplication(m1, m2, m1Row + size, 
                      m1Col, m2Row, m2Col, size);
        MatrixMultiplication ma22b21 = new MatrixMultiplication(m1, m2, m1Row + size, 
                      m1Col + size, m2Row + size, m2Col, size);

        MatrixMultiplication ma21b12 = new MatrixMultiplication(m1, m2, m1Row + size, 
                      m1Col, m2Row, m2Col + size, size);
        MatrixMultiplication ma22b22 = new MatrixMultiplication(m1, m2, m1Row + size, 
                      m1Col + size, m2Row + size, m2Col + size, size);

        ma11b11.fork();
        ma12b21.fork();
        ma11b12.fork();
        ma12b22.fork();
        ma21b11.fork();
        ma22b21.fork();
        ma21b12.fork();
        ma22b22.fork();

        Matrix mat22b22 = ma22b22.join();
        Matrix mat21b12 = ma21b12.join();
        Matrix mat22b21 = ma22b21.join();
        Matrix mat21b11 = ma21b11.join();
        Matrix mat12b22 = ma12b22.join();
        Matrix mat11b12 = ma11b12.join();
        Matrix mat12b21 = ma12b21.join();
        Matrix mat11b11 = ma11b11.join();
        
        //ma22b22 and ma21b12 computation
        for (int i = 0; i < size; i++) {
            double[] mat1m = mat21b12.m[i];
            double[] mat2m = mat22b22.m[i];
            double[] res1m = newResult.m[i + size];
            for (int j = 0; j < size; j++) {
                res1m[j + size] = mat1m[j] + mat2m[j];
            }
        }

        //ma22b21 and ma21b11 computation
        for (int i = 0; i < size; i++) {
            double[] mat1m = mat21b11.m[i];
            double[] mat2m = mat22b21.m[i];
            double[] res1m = newResult.m[i + size];
            for (int j = 0; j < size; j++) {
                res1m[j] = mat1m[j] + mat2m[j];
            }
        }

        //ma12b22 and ma11b12 computation
        for (int i = 0; i < size; i++) {
            double[] mat1m = mat11b12.m[i];
            double[] mat2m = mat12b22.m[i];
            double[] res1m = newResult.m[i];
            for (int j = 0; j < size; j++) {
                res1m[j + size] = mat1m[j] + mat2m[j];
            }
        }

        //ma12b21 and ma11b11 computation
        for (int i = 0; i < size; i++) {
            double[] mat1m = mat11b11.m[i];
            double[] mat2m = mat12b21.m[i];
            double[] res1m = newResult.m[i];
            for (int j = 0; j < size; j++) {
                res1m[j] = mat1m[j] + mat2m[j];
            }
        }

        return newResult;
        }
  }
}
