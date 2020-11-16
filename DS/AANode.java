    package DataStructures;

    // Basic node stored in AVL trees
    // Note that this class is not accessible outside
    // of package DataStructures

    class AVLNode
    {
            // Constructors
        AVLNode( Comparable theElement )
        {
            this( theElement, null, null );
        }

        AVLNode( Comparable theElement, AVLNode lt, AVLNode rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            level    = 1;
        }

             
        Comparable element;      // The data in the node
        AVLNode     left;         // Left child
        AVLNode     right;        // Right child
        int        level;        // Level
    }
