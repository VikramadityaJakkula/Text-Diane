    package DS;

    
    class BinomialNode
    {
        BinomialNode( Comparable Element )
        {
            this( Element, null, null );
        }

        BinomialNode( Comparable Element, BinomialNode lt, BinomialNode nt )
        {
            element     = Element;
            leftChild   = lt;
            nextSibling = nt;
        }

            
        Comparable   element;     // The data in the node
        BinomialNode leftChild;   // Left child
        BinomialNode nextSibling; // Right child
    }
