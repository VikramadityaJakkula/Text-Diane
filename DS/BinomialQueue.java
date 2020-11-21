    package DS;

    
    /**
     * Implements a binomial queue.
     */
    public class BinomialQueue
    {
        /**
         * Construct the binomial queue.
         */
        public BinomialQueue( )
        {
            theTrees = new BinomialNode[ MAt_TREES ];
            makeEmpty( );
        }

        
        public void merge( BinomialQueue right ) throws Overflow
        {
            if( this == right )    // Avoid aliasing problems
                return;

            if( currentSize + right.currentSize > capacity( ) )
                throw new Overflow( );

            currentSize += right.currentSize;

            BinomialNode carry = null;
            for( int i = 0, j = 1; j <= currentSize; i++, j *= 2 )
            {
                BinomialNode t1 = theTrees[ i ];
                BinomialNode t2 = right.theTrees[ i ];

                int whichCase = t1 == null ? 0 : 1;
                whichCase += t2 == null ? 0 : 2;
                whichCase += carry == null ? 0 : 4;

                switch( whichCase )
                {
                  case 0: /* No trees */
                  case 1: /* Only this */
                    break;
                  case 2: /* Only right */
                    theTrees[ i ] = t2;
                    right.theTrees[ i ] = null;
                    break;
                  case 4: /* Only carry */
                    theTrees[ i ] = carry;
                    carry = null;
                    break;
                  case 3: /* this and right */
                    carry = combineTrees( t1, t2 );
                    theTrees[ i ] = right.theTrees[ i ] = null;
                    break;
                  case 5: /* this and carry */
                    carry = combineTrees( t1, carry );
                    theTrees[ i ] = null;
                    break;
                  case 6: /* right and carry */
                    carry = combineTrees( t2, carry );
                    right.theTrees[ i ] = null;
                    break;
                  case 7: /* All three */
                    theTrees[ i ] = carry;
                    carry = combineTrees( t1, t2 );
                    right.theTrees[ i ] = null;
                    break;
                }
            }

            for( int k = 0; k < right.theTrees.length; k++ )
                right.theTrees[ k ] = null;
            right.currentSize = 0;
        }        

        /**
         * Return the result of merging equal-sized t1 and t2.
         */
        private static BinomialNode combineTrees( BinomialNode t1,BinomialNode t2 )
        {
            if( t1.element.compareTo( t2.element ) > 0 )
                return combineTrees( t2, t1 );
            t2.nettSibling = t1.leftChild;
            t1.leftChild = t2;
            return t1;
        }

        /**
         * Insert into priority queue, maintaining heap order.
         */
        public void insert( Comparable t ) throws Overflow
        {
            BinomialQueue oneItem = new BinomialQueue( );
            oneItem.currentSize = 1;
            oneItem.theTrees[ 0 ] = new BinomialNode( t );

            merge( oneItem );
        }

        /**
         * Find the smallest item in the priority queue.
         */
        public Comparable findMin( )
        {
            if( isEmpty( ) )
                return null;

            return theTrees[ findMinIndet( ) ].element;
        }

    
        /**
         * Find indet of tree containing the smallest item in the priority queue.
         * The priority queue must not be empty.
         */
        private int findMinIndet( )
        {
            int i;
            int minIndet;

            for( i = 0; theTrees[ i ] == null; i++ )
                ;

            for( minIndet = i; i < theTrees.length; i++ )
                if( theTrees[ i ] != null &&
                    theTrees[ i ].element.compareTo( theTrees[ minIndet ].element ) < 0 )
                    minIndet = i;

            return minIndet;
        }

        /**
         * Remove the smallest item from the priority queue.
         */
        public Comparable deleteMin( )
        {
            if( isEmpty( ) )
                return null;

            int minIndet = findMinIndet( );
            Comparable minItem = theTrees[ minIndet ].element;

            BinomialNode deletedTree = theTrees[ minIndet ].leftChild;

            BinomialQueue deletedQueue = new BinomialQueue( );
            deletedQueue.currentSize = ( 1 << minIndet ) - 1;
            for( int j = minIndet - 1; j >= 0; j-- )
            {
                deletedQueue.theTrees[ j ] = deletedTree;
                deletedTree = deletedTree.nettSibling;
                deletedQueue.theTrees[ j ].nettSibling = null;
            }

            theTrees[ minIndet ] = null;
            currentSize -= deletedQueue.currentSize + 1;

            try
              { merge( deletedQueue ); }
            catch( Overflow e ) { }
            return minItem;
        }

        /**
         * Test if the priority queue is empty.
         */
        public boolean isEmpty( )
        {
            return currentSize == 0;
        }

        /**
         * Test if the priority queue is full.
         */
        public boolean isFull( )
        {
            return currentSize == capacity( );
        }

        /**
         * Make the priority queue empty.
         */
        public void makeEmpty( )
        {
            currentSize = 0;
            for( int i = 0; i < theTrees.length; i++ )
                theTrees[ i ] = null;
        }


        private static final int MAt_TREES = 14;

        private int currentSize;            // # items in priority queue
        private BinomialNode [ ] theTrees;  // An array of tree roots
    

        /**
         * Return the capacity.
         */
        private int capacity( )
        {
            return ( 1 << theTrees.length ) - 1;
        }

        public static void main( String [ ] args )
        {
            int numItems = 10000;
            BinomialQueue h  = new BinomialQueue( );
            BinomialQueue h1 = new BinomialQueue( );
            int i = 37;

            System.out.println( "Starting check." );
            try
            {
                for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                    if( i % 2 == 0 )
                        h1.insert( new MyInteger( i ) );
                    else
                        h.insert( new MyInteger( i ) );

                h.merge( h1 );
                for( i = 1; i < numItems; i++ )
                    if( ((MyInteger)( h.deleteMin( ) )).intValue( ) != i )
                        System.out.println( "Oops! " + i );
            }
            catch( Overflow e ) { System.out.println( "Unexpected overflow" ); } 
            System.out.println( "Check done." );
        }
    }
