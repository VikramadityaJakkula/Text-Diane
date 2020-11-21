    package DS;

    
    /**
     * Implements a treap.
     * Note that all "matching" is based on the compareTo method.
     */
    public class Treap
    {
        /**
         * Construct the treap.
         */
        public Treap( )
        {
            root = nullNode;
        }

        /**
         * Insert into the tree. Does nothing if s is already present.
         */
        public void insert( Comparable s )
        {
            root = insert( s, root );
        }

        /**
         * Remove from the tree. Does nothing if s is not found.
         */
        public void remove( Comparable s )
        {
            root = remove( s, root );
        }

        /**
         * Find the smallest item in the tree.
         */
        public Comparable findMin( )
        {
            if( isEmpty( ) )
                return null;

            TreapNode ptr = root;

            while( ptr.left != nullNode )
                ptr = ptr.left;

            return ptr.element;
        }

        /**
         * Find the largest item in the tree.
	    */
        public Comparable findM( )
        {
            if( isEmpty( ) )
                return null;

            TreapNode ptr = root;

            while( ptr.right != nullNode )
                ptr = ptr.right;

            return ptr.element;
        }

        /**
         * Find an item in the tree.
         */
        public Comparable find( Comparable s )
        {
            TreapNode current = root;
            nullNode.element = s;

            for( ; ; )
            {
                if( s.compareTo( current.element ) < 0 )
                    current = current.left;
                else if( s.compareTo( current.element ) > 0 ) 
                    current = current.right;
                else if( current != nullNode )
                    return current.element;
                else
                    return null;
            }
        }

        /**
         * Make the tree empty.
         */
        public void makeEmpty( )
        {
            root = nullNode;
        }

        /**
         * Test if the tree is empty.
         */
        public boolean isEmpty( )
        {
            return root == nullNode;
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

        /**
         * Internal method to insert into a subtree.
         */
        private TreapNode insert( Comparable s, TreapNode t )
        {
            if( t == nullNode )
                t = new TreapNode( s, nullNode, nullNode );
            else if( s.compareTo( t.element ) < 0 )
            {
                t.left = insert( s, t.left );
                if( t.left.priority < t.priority )
                    t = rotateWithLeftChild( t );
            }
            else if( s.compareTo( t.element ) > 0  )
            {
                t.right = insert( s, t.right );
                if( t.right.priority < t.priority )
                    t = rotateWithRightChild( t );
            }
            // Otherwise, it's a duplicate; do nothing

            return t;
        }

        /**
         * Internal method to remove from a subtree.
         */
        private TreapNode remove( Comparable s, TreapNode t )
        {
            if( t != nullNode )
            {
                if( s.compareTo( t.element ) < 0 )
                    t.left = remove( s, t.left );
                else if( s.compareTo( t.element ) > 0 )
                    t.right = remove( s, t.right );
                else
                {
                        // Match found
                    if( t.left.priority < t.right.priority )
                        t = rotateWithLeftChild( t );
                    else
                        t = rotateWithRightChild( t );

                    if( t != nullNode )     // Continue on down
                        t = remove( s, t );
                    else
                        t.left = nullNode;  // At a leaf
                }
            }
            return t;
        }

        /**
         * Internal method to print a subtree in sorted order.
         */
        private void printTree( TreapNode t )
        {
            if( t != t.left )
            {
                printTree( t.left );
                System.out.println( t.element.toString( ) );
                printTree( t.right );
            }
        }

        /**
         * Rotate binary tree node with left child.
         */
        static TreapNode rotateWithLeftChild( TreapNode k2 )
        {
            TreapNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            return k1;
        }

        /**
         * Rotate binary tree node with right child.
         */
        static TreapNode rotateWithRightChild( TreapNode k1 )
        {
            TreapNode k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            return k2;
        }

        private TreapNode root;
        private static TreapNode nullNode;
            static         // Static initializer for NullNode
            {
                nullNode = new TreapNode( null );
                nullNode.left = nullNode.right = nullNode;
                nullNode.priority = Integer.M_VALUE;
            }

            // Test program
        public static void main( String [ ] args )
        {
            Treap t = new Treap( );
            final int NUMS = 4000;
            final int GAP  =   37;

            System.out.println( "Checking... (no bad output means success)" );

            for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
                t.insert( new MyInteger( i ) );
            System.out.println( "Inserts complete" );

            for( int i = 1; i < NUMS; i+= 2 )
                t.remove( new MyInteger( i ) );
            System.out.println( "Removes complete" );

            if( NUMS < 40 )
                t.printTree( );
            if( ((MyInteger)(t.findMin( ))).intValue( ) != 2 ||
                ((MyInteger)(t.findM( ))).intValue( ) != NUMS - 2 )
                System.out.println( "FindMin or FindM error!" );

            for( int i = 2; i < NUMS; i+=2 )
                if( ((MyInteger)t.find( new MyInteger( i ) )).intValue( ) != i )
                    System.out.println( "Error: find fails for " + i );

            for( int i = 1; i < NUMS; i+=2 )
                if( t.find( new MyInteger( i ) )  != null )
                    System.out.println( "Error: Found deleted item " + i );
        }
    }
