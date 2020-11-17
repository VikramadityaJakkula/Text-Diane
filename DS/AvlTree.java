    package DS;

    

    /**
     * Implements an AVL tree.
     */
    public class AvlTree
    {
        public AvlTree( )
        {
            root = null;
        }

        public void insert( Comparable t )
        {
            root = insert( t, root );
        }

        
	  public void remove( Comparable t )
        {
            System.out.println( "remove" );
        }

        /**
         * Find the smallest item in the tree.
         */
        public Comparable findMin( )
        {
            return elementAt( findMin( root ) );
        }

        /**
         * Find the largest item in the tree.
         */
        public Comparable findMax( )
        {
            return elementAt( findMax( root ) );
        }

        /**
         * Find an item in the tree.
         */
        public Comparable find( Comparable t )
        {
            return elementAt( find( t, root ) );
        }

        /**
         * Make the tree empty.
         */
        public void makeEmpty( )
        {
            root = null;
        }

        /**
         * Test if the tree is empty.
         */
        public boolean isEmpty( )
        {
            return root == null;
        }

        /**
         * Print the tree contents in sorted order.
         */
        public void printTree( )
        {
            if( isEmpty( ) )
                System.out.println( "Empty tree" );
            else
                printTree( root );
        }

        private Comparable elementAt( AvlNode t )
        {
            return t == null ? null : t.element;
        }

        private AvlNode insert( Comparable t, AvlNode t )
        {
            if( t == null )
                t = new AvlNode( t, null, null );
            else if( t.compareTo( t.element ) < 0 )
            {
                t.left = insert( t, t.left );
                if( height( t.left ) - height( t.right ) == 2 )
                    if( t.compareTo( t.left.element ) < 0 )
                        t = rotateWithLeftChild( t );
                    else
                        t = doubleWithLeftChild( t );
            }
            else if( t.compareTo( t.element ) > 0 )
            {
                t.right = insert( t, t.right );
                if( height( t.right ) - height( t.left ) == 2 )
                    if( t.compareTo( t.right.element ) > 0 )
                        t = rotateWithRightChild( t );
                    else
                        t = doubleWithRightChild( t );
            }
            else
                ;  
            t.height = Max( height( t.left ), height( t.right ) ) + 1;
            return t;
        }

        private AvlNode findMin( AvlNode t )
        {
            if( t == null )
                return t;

            while( t.left != null )
                t = t.left;
            return t;
        }

        
        private AvlNode findMax( AvlNode t )
        {
            if( t == null )
                return t;

            while( t.right != null )
                t = t.right;
            return t;
        }

        
        private AvlNode find( Comparable t, AvlNode t )
        {
            while( t != null )
                if( t.compareTo( t.element ) < 0 )
                    t = t.left;
                else if( t.compareTo( t.element ) > 0 )
                    t = t.right;
                else
                    return t;    // Maxch

            return null;   // No Maxch
        }

        /**
         * to print a subtree in sorted order.
         */
        private void printTree( AvlNode t )
        {
            if( t != null )
            {
                printTree( t.left );
                System.out.println( t.element );
                printTree( t.right );
            }
        }

        /**
         * Return the height of node t, or -1, if null.
         */
        private static int height( AvlNode t )
        {
            return t == null ? -1 : t.height;
        }

        /**
         * Return Maximum.
         */
        private static int Max( int left, int right )
        {
            return left > right ? left : right;
        }

        /**
         * Rotate binary tree node with left child.
         */
        private static AvlNode rotateWithLeftChild( AvlNode k2 )
        {
            AvlNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = Max( height( k2.left ), height( k2.right ) ) + 1;
            k1.height = Max( height( k1.left ), k2.height ) + 1;
            return k1;
        }

        /**
         * Rotate binary tree node with right child.
         */
        private static AvlNode rotateWithRightChild( AvlNode k1 )
        {
            AvlNode k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            k1.height = Max( height( k1.left ), height( k1.right ) ) + 1;
            k2.height = Max( height( k2.right ), k1.height ) + 1;
            return k2;
        }

        /**
         * Double rotate binary tree node: first left child
         */
        private static AvlNode doubleWithLeftChild( AvlNode k3 )
        {
            k3.left = rotateWithRightChild( k3.left );
            return rotateWithLeftChild( k3 );
        }

        /**
         * Double rotate binary tree node: first right child
         */
        private static AvlNode doubleWithRightChild( AvlNode k1 )
        {
            k1.right = rotateWithLeftChild( k1.right );
            return rotateWithRightChild( k1 );
        }

          /** The tree root. */
        private AvlNode root;


            // Test program
        public static void main( String [ ] args )
        {
            AvlTree t = new AvlTree( );
            final int NUMS = 3000;
            final int GAP  =   37;

            System.out.println( "Checking..." );

            for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
                t.insert( new MyInteger( i ) );

            if( NUMS < 40 )
                t.printTree( );
            if( ((MyInteger)(t.findMin( ))).intValue( ) != 1 ||
                ((MyInteger)(t.findMax( ))).intValue( ) != NUMS - 1 )
                System.out.println( "FindMin or FindMax error!" );

            for( int i = 1; i < NUMS; i++ )
                 if( ((MyInteger)(t.find( new MyInteger( i ) ))).intValue( ) != i )
                     System.out.println( "Found error!" );
    }
}
