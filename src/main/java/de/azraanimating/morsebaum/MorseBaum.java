package de.azraanimating.morsebaum;

import de.azraanimating.morsebaum.BinaryTree;

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
                        new BinaryTree<String>("R")),
                new BinaryTree<String>("I",
                        new BinaryTree<String>("U",
                                new BinaryTree<String>(),
                                new BinaryTree<String>("F")),
                        new BinaryTree<String>("S",
                                new BinaryTree<String>("V"),
                                new BinaryTree<String>("H"))));
    }

    //Decoding

    private String decode(String pInputCode)
    {
        String[] lCodes = pInputCode.split(" ");
        StringBuilder lFinalStringBuilder = new StringBuilder();

        for(int i = 0 ; i < lCodes.length ; i++)
        {
            lFinalStringBuilder.append(this.getStringForCode(lCodes[i]));
        }

        return lFinalStringBuilder.toString();
    }

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

    private String getStringForCode(String pCode)
    {
        String[] lSymbols = this.split(pCode);
        StringBuilder stringBuilder = new StringBuilder();
        this.followPath(this.morsebaum, lSymbols, stringBuilder, 0);
        return stringBuilder.toString();
    }

    private void followPath(BinaryTree pBaum, String[] pSymbol, StringBuilder pStringBuilder, int pCurrentIndex)
    {
        if(pCurrentIndex >= pSymbol.length - 1 && pCurrentIndex > 0) {
            if(pBaum.getLeftTree() != null && pBaum.getRightTree() != null) {
                if (pSymbol[pCurrentIndex - 1].equals(".")) {
                    if(!pBaum.getRightTree().isEmpty()) {
                        pStringBuilder.append(pBaum.getRightTree().getContent());
                    } else {
                        pStringBuilder.append(pBaum.getContent());
                    }
                } else if (pSymbol[pCurrentIndex - 1].equals("-")) {
                    if(!pBaum.getLeftTree().isEmpty()) {
                        pStringBuilder.append(pBaum.getLeftTree().getContent());
                    } else {
                        pStringBuilder.append(pBaum.getContent());
                    }
                }
                return;
            } else {
                pStringBuilder.append(pBaum.getContent());
            }
        }
        if(pSymbol[pCurrentIndex].equals("."))
        {
            if(pBaum.getRightTree() != null)
            {
                this.followPath(pBaum.getRightTree(), pSymbol, pStringBuilder, pCurrentIndex + 1);
            } else {
                pStringBuilder.append(pBaum.getContent());
            }
        }
        else if(pSymbol[pCurrentIndex].equals("-"))
        {
            if(pBaum.getRightTree() != null)
            {
                this.followPath(pBaum.getLeftTree(), pSymbol, pStringBuilder, pCurrentIndex + 1);
            } else {
                pStringBuilder.append(pBaum.getContent());
            }
        }
    }

    public void printDecoded(String pMessageToDecode)
    {
        System.out.println(this.decode(pMessageToDecode));
    }
}