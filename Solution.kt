
import kotlin.math.min;

class Solution {
    
    fun findRLEArray(firstInputEncoded: Array<IntArray>, secondInputEncoded: Array<IntArray>): List<List<Int>> {

        return calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded);
    }

    private fun calculateProductBetweenBothInputs(firstInputEncoded: Array<IntArray>, secondInputEncoded: Array<IntArray>): List<List<Int>> {

        var firstInputIndex = 0;
        var secondInputIndex = 0;

        var previousProductValue = 0;
        var previousProductFrequency = 0;

        val productOfRunLengthEncodedInputs = ArrayList<List<Int>>();

        while (firstInputIndex < firstInputEncoded.size && secondInputIndex < secondInputEncoded.size) {

            val firstValue = firstInputEncoded[firstInputIndex][0];
            val secondValue = secondInputEncoded[secondInputIndex][0];
            val firstFrequency = firstInputEncoded[firstInputIndex][1];
            val secondFrequency = secondInputEncoded[secondInputIndex][1];

            val productValue = firstValue * secondValue;
            val productFrequency = min(firstFrequency, secondFrequency);

            if (productValue == previousProductValue || previousProductFrequency == 0) {
                previousProductFrequency += productFrequency;
                previousProductValue = productValue;
            } else {
                productOfRunLengthEncodedInputs.add(listOf(previousProductValue, previousProductFrequency));
                previousProductValue = productValue;
                previousProductFrequency = productFrequency;
            }

            firstInputEncoded[firstInputIndex][1] = firstFrequency - productFrequency;
            secondInputEncoded[secondInputIndex][1] = secondFrequency - productFrequency;

            firstInputIndex += if (firstInputEncoded[firstInputIndex][1] > 0) 0 else 1;
            secondInputIndex += if (secondInputEncoded[secondInputIndex][1] > 0) 0 else 1;
        }

        productOfRunLengthEncodedInputs.add(listOf(previousProductValue, previousProductFrequency));

        return productOfRunLengthEncodedInputs;
    }
}
