package youtube;

import youtube.Domineering.Domino;

public class ArrayDomino implements Domino
{

    int[] numbers;
    int leftIndex;

    public ArrayDomino(int left, int right)
    {
        numbers = new int[]
        {
            left, right
        };
        leftIndex = 0;
    }

    public void flip()
    {
        leftIndex = 1 - leftIndex;
    }

    public int getLeft()
    {
        return numbers[leftIndex];
    }

    public int getRight()
    {
        return numbers[1 - leftIndex];
    }

    public String toString()
    {
        return numbers[leftIndex] + "-" + numbers[1 - leftIndex];
    }
}
