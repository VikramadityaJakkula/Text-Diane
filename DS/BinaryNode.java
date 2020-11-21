    package DS;


    class BinaryNode
    {
        BinaryNode( Comparable Element )
        {
            this( Element, null, null );
        }

        BinaryNode( Comparable Element, BinaryNode lt, BinaryNode rt )
        {
            element  = Element;
            left     = lt;
            right    = rt;
        }

        Comparable element;      // The data in the node
        BinaryNode left;         // Left child
        BinaryNode right;        // Right child
    }
