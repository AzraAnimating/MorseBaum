package de.azraanimating.morsebaum;

public class MorseBaum {

    private final BinaryTree morsebaum;

    public MorseBaum()
    {
        this.morsebaum = new BinaryTree<String>("Wurzel", this.initLeftTree(), this.initRightTree());
    }

    private BinaryTree initLeftTree()
    {
        return new BinaryTree<String>("T",
                new BinaryTree<String>("M",
                        new BinaryTree<String>("O"),
                        new BinaryTree<String>("G",
                                new BinaryTree("Q"),
                                new BinaryTree("Z")))
                ,
                new BinaryTree<String>("N",
                        new BinaryTree<String>("K",
                                new BinaryTree<String>("Y"),
                                new BinaryTree<String>("C")),
                        new BinaryTree<String>("D",
                                new BinaryTree<String>("X"),
                                new BinaryTree<String>("B")))
        );
    }

    private BinaryTree initRightTree()
    {
        return new BinaryTree<String>("E",
                new BinaryTree<String>("A",
                        new BinaryTree<String>("W",
                                new BinaryTree<String>("J"),
                                new BinaryTree<String>("P")),
                        new BinaryTree<String>("R",
                                new BinaryTree<String>(),
                                new BinaryTree<String>("L"))),
                new BinaryTree<String>("I",
                        new BinaryTree<String>("U",
                                new BinaryTree<String>(),
                                new BinaryTree<String>("F")),
                        new BinaryTree<String>("S",
                                new BinaryTree<String>("V"),
                                new BinaryTree<String>("H"))));
    }

    //Decoding

    private String[] split(String pCode)
    {
        String[] lSymbols = new String[pCode.length()];

        String lToBeDecoded = pCode;

        for(int i = 0 ; i < pCode.length() ; i++)
        {
            String lEditChar = lToBeDecoded;
            lSymbols[i] = lEditChar.substring(0,1);
            if(lToBeDecoded.length() > 1)
            {
                lToBeDecoded = lToBeDecoded.substring(1);
            }
            else
            {
                lSymbols[i] = lToBeDecoded;
            }

        }


        return lSymbols;
    }


    private void decode(String pCode, BinaryTree<String> pBaum)
    {
        if(pCode.equalsIgnoreCase("/")) {
            System.out.print(" ");
            return;
        }

        if(pCode.length() < 1) {
            System.out.print(pBaum.getContent());
        }

        if (pCode.startsWith(".")) {
            this.decode(pCode.substring(1), pBaum.getRightTree());
        } else if (pCode.startsWith("-")) {
            this.decode(pCode.substring(1), pBaum.getLeftTree());
        }
    }

    private void encode(String pSearchedChar, String currentCode, BinaryTree<String> pTree) {
        if(!pTree.isEmpty()) {
            if(!pTree.getContent().equalsIgnoreCase(pSearchedChar)) {
                this.encode(pSearchedChar, currentCode + "-", pTree.getLeftTree());
                this.encode(pSearchedChar, currentCode + ".", pTree.getRightTree());
            } else {
                System.out.print(currentCode);
            }
        }
    }

    public void printDecoded(String pMessageToDecode)
    {
        String[] codes = pMessageToDecode.split(" ");

        for (String code : codes) {
            this.decode(code, this.morsebaum);
            System.out.print(" ");
        }
    }

    public void ausgabe(BinaryTree<String> pTree) {
        if(!pTree.isEmpty()) {
            this.ausgabe(pTree.getLeftTree());
            System.out.print(pTree.getContent());
            this.ausgabe(pTree.getRightTree());
        }
    }

    public void triggerAusgabe() {
        this.ausgabe(this.morsebaum);
    }

    public void encodeString(String pStringToEncode) {
        String[] words = pStringToEncode.split(" ");

        for (String word : words) {
            for (String letter : this.split(word)) {
                this.encode(letter, "", this.morsebaum);
                System.out.print(" ");
            }
            System.out.print("/ ");
        }
    }
}