
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> findRLEArray(int[][] firstInputEncoded, int[][] secondInputEncoded) {

        return calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded);
    }

    private List<List<Integer>> calculateProductBetweenBothInputs(int[][] firstInputEncoded, int[][] secondInputEncoded) {
        int firstInputIndex = 0;
        int secondInputIndex = 0;

        int previousProductValue = 0;
        int previousProductFrequency = 0;

        List<List<Integer>> productOfRunLengthEncodedInputs = new ArrayList<>();

        while (firstInputIndex < firstInputEncoded.length && secondInputIndex < secondInputEncoded.length) {

            int firstValue = firstInputEncoded[firstInputIndex][0];
            int secondValue = secondInputEncoded[secondInputIndex][0];
            int firstFrequency = firstInputEncoded[firstInputIndex][1];
            int secondFrequency = secondInputEncoded[secondInputIndex][1];

            int productValue = firstValue * secondValue;
            int productFrequency = Math.min(firstFrequency, secondFrequency);

            if (productValue == previousProductValue || previousProductFrequency == 0) {
                previousProductFrequency += productFrequency;
                previousProductValue = productValue;
            } else {
                productOfRunLengthEncodedInputs.add(List.of(previousProductValue, previousProductFrequency));
                previousProductValue = productValue;
                previousProductFrequency = productFrequency;
            }

            firstInputEncoded[firstInputIndex][1] = firstFrequency - productFrequency;
            secondInputEncoded[secondInputIndex][1] = secondFrequency - productFrequency;

            firstInputIndex += (firstInputEncoded[firstInputIndex][1] > 0) ? 0 : 1;
            secondInputIndex += (secondInputEncoded[secondInputIndex][1] > 0) ? 0 : 1;
        }

        productOfRunLengthEncodedInputs.add(List.of(previousProductValue, previousProductFrequency));

        return productOfRunLengthEncodedInputs;
    }
}
