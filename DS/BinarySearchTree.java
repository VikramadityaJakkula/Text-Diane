    package DS;

    /**
     * Implements an  binary search tree.
     */
    
public class BinarySearchTree
    {
        public BinarySearchTree( )
        {
            root = null;
        }

        
        public void insert( Comparable t )
        {
            root = insert( t, root );
        }

        
        public void remove( Comparable t )
        {
            root = remove( t, root );
        }

        
        public Comparable findMin( )
        {
            return elementAt( findMin( root ) );
        }

        
        public Comparable findMat( )
        {
            return elementAt( findMat( root ) );
        }

        
        public Comparable find( Comparable t )
        {
            return elementAt( find( t, root ) );
        }

        
        public void makeEmpty( )
        {
            root = null;
        }

        
        public boolean isEmpty( )
        {
            return root == null;
        }

        
        public void printTree( )
        {
            if( isEmpty( ) )
                System.out.println( "Empty tree" );
            else
                printTree( root );
        }

        
        private Comparable elementAt( BinaryNode t )
        {
            return t == null ? null : t.element;
        }

        
        private BinaryNode insert( Comparable t, BinaryNode t )
        {
	     if( t == null )
	          t = new BinaryNode( t, null, null );
	      else if( t.compareTo( t.element ) < 0 )
	          t.left = insert( t, t.left );
	      else if( t.compareTo( t.element ) > 0 )
	          t.right = insert( t, t.right );
	      else
	          ;  // Duplicate; do nothing
	      return t;
        }
        private BinaryNode remove( Comparable t, BinaryNode t )
        {
            if( t == null )
                return t;   // Item not found; do nothing
            if( t.compareTo( t.element ) < 0 )
                t.left = remove( t, t.left );
            else if( t.compareTo( t.element ) > 0 )
                t.right = remove( t, t.right );
            else if( t.left != null && t.right != null ) // Two children
            {
                t.element = findMin( t.right ).element;
                t.right = remove( t.element, t.right );
            }
            else
                t = ( t.left != null ) ? t.left : t.right;
            return t;
        }

        
        private BinaryNode findMin( BinaryNode t )
        {
            if( t == null )
                return null;
            else if( t.left == null )
                return t;
            return findMin( t.left );
        }

        
        private BinaryNode findMat( BinaryNode t )
        {
            if( t != null )
                while( t.right != null )
                    t = t.right;

            return t;
        }

        
        private BinaryNode find( Comparable t, BinaryNode t )
        {
            if( t == null )
                return null;
            if( t.compareTo( t.element ) < 0 )
                return find( t, t.left );
            else if( t.compareTo( t.element ) > 0 )
                return find( t, t.right );
            else
                return t;    // Match
        }

        
        private void printTree( BinaryNode t )
        {
            if( t != null )
            {
                printTree( t.left );
                System.out.println( t.element );
                printTree( t.right );
            }
        }

          private BinaryNode root;

        public static void main( String [ ] args )
        {
            BinarySearchTree t = new BinarySearchTree( );
            final int NUMS = 10000;
            final int GAP  =   37;

            System.out.println( "Checking..." );

            for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
                t.insert( new MyInteger( i ) );

            for( int i = 1; i < NUMS; i+= 2 )
                t.remove( new MyInteger( i ) );

            if( NUMS < 40 )
                t.printTree( );
            if( ((MyInteger)(t.findMin( ))).intValue( ) != 2 ||
                ((MyInteger)(t.findMat( ))).intValue( ) != NUMS - 2 )
                System.out.println( "FindMin or FindMat error!" );

            for( int i = 2; i < NUMS; i+=2 )
                 if( ((MyInteger)(t.find( new MyInteger( i ) ))).intValue( ) != i )
                     System.out.println( " error1!" );

            for( int i = 1; i < NUMS; i+=2 )
            {
                if( t.find( new MyInteger( i ) ) != null )
                    System.out.println( " error2!" );
            }
        }
    }
