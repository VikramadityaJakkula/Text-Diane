    package DS;

    // Basic node stored in AVL trees
    

    class AvlNode
    {
        AvlNode( Comparable Element )
        {
            this( Element, null, null );
        }

        AvlNode( Comparable Element, AvlNode lt, AvlNode rt )
        {
            element  = Element;
            left     = lt;
            right    = rt;
            height   = 0;
        }

	   Comparable element;      // The data in the node
        AvlNode    left;         // Left child
        AvlNode    right;        // Right child
        int        height;       // Height
    }
