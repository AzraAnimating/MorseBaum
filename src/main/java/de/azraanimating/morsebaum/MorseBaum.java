package de.azraanimating.morsebaum;

public class MorseBaum {

    private final BinaryTree finalTree;

    public MorseBaum() {
        this.finalTree = new BinaryTree("", this.initLeftTree(), this.initRightTree());
    }

    private BinaryTree initLeftTree() {
        return new BinaryTree("T",
                new BinaryTree("M",
                        new BinaryTree("O"),
                        new BinaryTree("G",
                                new BinaryTree("Q"),
                                new BinaryTree("Z")))
                ,
                new BinaryTree("N",
                        new BinaryTree("K",
                                new BinaryTree("Y"),
                                new BinaryTree("C")),
                        new BinaryTree("D",
                                new BinaryTree("X"),
                                new BinaryTree("B")))
        );
    }

    private BinaryTree initRightTree() {
        return new BinaryTree("E",
                new BinaryTree("A",
                        new BinaryTree("W",
                                new BinaryTree("J"),
                                new BinaryTree("P")),
                        new BinaryTree("R")),
                new BinaryTree("I",
                        new BinaryTree("U",
                                new BinaryTree(),
                                new BinaryTree("F")),
                        new BinaryTree("S",
                                new BinaryTree("V"),
                                new BinaryTree("H"))));
    }

    public BinaryTree getFinalTree() {
        return finalTree;
    }
}
