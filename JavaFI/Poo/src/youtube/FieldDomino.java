package youtube;

import youtube.Domineering.Domino;

public class FieldDomino implements Domino
{

    private int left;
    private int right;

    public FieldDomino(int left, int right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public void flip()
    {
        int swap = left;
        left = right;
        right = swap;
    }

    @Override
    public int getLeft()
    {
        return left;
    }

    @Override
    public int getRight()
    {
        return right;
    }

    @Override
    public String toString()
    {
        return left + "-" + right;
    }

}
