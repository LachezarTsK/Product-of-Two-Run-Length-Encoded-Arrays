
using System;
using System.Collections.Generic;

public class Solution
{
    public IList<IList<int>> FindRLEArray(int[][] firstInputEncoded, int[][] secondInputEncoded)
    {

        return calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded);
    }

    private IList<IList<int>> calculateProductBetweenBothInputs(int[][] firstInputEncoded, int[][] secondInputEncoded)
    {
        int firstInputIndex = 0;
        int secondInputIndex = 0;

        int previousProductValue = 0;
        int previousProductFrequency = 0;

        IList<IList<int>> productOfRunLengthEncodedInputs = new List<IList<int>>();

        while (firstInputIndex < firstInputEncoded.Length && secondInputIndex < secondInputEncoded.Length)
        {

            int firstValue = firstInputEncoded[firstInputIndex][0];
            int secondValue = secondInputEncoded[secondInputIndex][0];
            int firstFrequency = firstInputEncoded[firstInputIndex][1];
            int secondFrequency = secondInputEncoded[secondInputIndex][1];

            int productValue = firstValue * secondValue;
            int productFrequency = Math.Min(firstFrequency, secondFrequency);

            if (productValue == previousProductValue || previousProductFrequency == 0)
            {
                previousProductFrequency += productFrequency;
                previousProductValue = productValue;
            }
            else
            {
                productOfRunLengthEncodedInputs.Add(new List<int>() { previousProductValue, previousProductFrequency });
                previousProductValue = productValue;
                previousProductFrequency = productFrequency;
            }

            firstInputEncoded[firstInputIndex][1] = firstFrequency - productFrequency;
            secondInputEncoded[secondInputIndex][1] = secondFrequency - productFrequency;

            firstInputIndex += (firstInputEncoded[firstInputIndex][1] > 0) ? 0 : 1;
            secondInputIndex += (secondInputEncoded[secondInputIndex][1] > 0) ? 0 : 1;
        }

        productOfRunLengthEncodedInputs.Add(new List<int>() { previousProductValue, previousProductFrequency });

        return productOfRunLengthEncodedInputs;
    }
}
