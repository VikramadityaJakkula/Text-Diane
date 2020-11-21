    package DS;

    // Basic node stored in treaps
    

    class TreapNode
    {
            
        TreapNode( Comparable Element )
        {
            this( Element, null, null );
        }

        TreapNode(Comparable Element,TreapNode lt,TreapNode rt )
        {
            element  = Element;
            left     = lt;
            right    = rt;
            priority = randomObj.randomInt( );
        }

        Comparable element;      // The data in the node
        TreapNode  left;         // Left child
        TreapNode  right;        // Right child
        int        priority;     // Priority

        private static Random randomObj = new Random( );
    }
