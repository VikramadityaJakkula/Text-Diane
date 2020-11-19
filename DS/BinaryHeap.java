    package DS;


    /**
     * Implements a binary heap.
     */
    public class BinaryHeap
    {

        public BinaryHeap( int capacity )
        {
            currentSize = 0;
            array = new Comparable[ capacity + 1 ];
        }

        public void insert( Comparable t ) throws Overflow
        {
            if( isFull( ) )
                throw new Overflow( );

                
            int square = ++currentSize;
            for( ; square > 1 && t.compareTo( array[ square / 2 ] ) < 0; square /= 2 )
                array[ square ] = array[ square / 2 ];
            array[ square ] = t;
        }

        /**
         * Find the smallest item in the priority queue.
         */
        public Comparable findMin( )
        {
            if( isEmpty( ) )
                return null;
            return array[ 1 ];
        }

        /**
         * Remove the smallest item from the priority queue. */
        
	   public Comparable deleteMin( )
        {
            if( isEmpty( ) )
                return null;

            Comparable minItem = findMin( );
            array[ 1 ] = array[ currentSize-- ];
            moveDown( 1 );

            return minItem;
        }

        /**
         * heap order from an arbitrary
         * arrangement of items. Runs in linear time.
         */
        private void buildHeap( )
        {
            for( int i = currentSize / 2; i > 0; i-- )
                moveDown( i );
        }

        /**
         * if the priority queue is empty.
         */
        public boolean isEmpty( )
        {
            return currentSize == 0;
        }

        /**
         * if the priority queue is full.
         */
        public boolean isFull( )
        {
            return currentSize == array.length - 1;
        }

        /**
         * Make the priority queue empty.
         */
        public void makeEmpty( )
        {
            currentSize = 0;
        }

        private static final int DEFAULT_CAPACITY = 10000;

        private int currentSize;// Number of elements in heap
        private Comparable [ ] array; // The heap array

        /**
         * to move down in the heap.
         */
        private void moveDown( int square )
        {
	      int child;
	      Comparable tmp = array[ square ];
	      for( ; square * 2 <= currentSize; square = child )
            {
	          child = square * 2;
	          if( child != currentSize &&
	               array[ child + 1 ].compareTo( array[ child ] ) < 0 )
              child++;
     	     if( array[child].compareTo( tmp ) < 0 )
              array[square] = array[child];
                else
              break;
            }
	      array[square] = tmp;
        }

     public static void main( String [ ] args )
        {
            int numItems = 10000;
            BinaryHeap h = new BinaryHeap( numItems );
            int i = 37;

            try
            {
                for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                    h.insert( new MyInteger( i ) );
                for( i = 1; i < numItems; i++ )
                    if( ((MyInteger)( h.deleteMin( ) )).intValue( ) != i )
                        System.out.println( "Oops! " + i );

                for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                h.insert( new MyInteger( i ) );
                h.insert( new MyInteger( 0 ) );
                i = 10000000;
                h.insert( new MyInteger( i ) );
                for( i = 1; i <= numItems; i++ )
                if(((MyInteger(h.deleteMin( ))).intValue()!= i )
                    System.out.println( "Oops! " + i + " " );
            }
            catch( Overflow e )
              { System.out.println( "Overflow! " + i  ); }
        }
    }
