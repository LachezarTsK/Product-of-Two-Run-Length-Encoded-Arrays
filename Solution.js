
/**
 * @param {number[][]} firstInputEncoded
 * @param {number[][]} secondInputEncoded
 * @return {number[][]}
 */
var findRLEArray = function (firstInputEncoded, secondInputEncoded) {

    return calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded);
};

/**
 * @param {number[][]} firstInputEncoded
 * @param {number[][]} secondInputEncoded
 * @return {number[][]}
 */
function calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded) {
    let firstInputIndex = 0;
    let secondInputIndex = 0;

    let previousProductValue = 0;
    let previousProductFrequency = 0;

    const productOfRunLengthEncodedInputs = new Array();

    while (firstInputIndex < firstInputEncoded.length && secondInputIndex < secondInputEncoded.length) {

        let firstValue = firstInputEncoded[firstInputIndex][0];
        let secondValue = secondInputEncoded[secondInputIndex][0];
        let firstFrequency = firstInputEncoded[firstInputIndex][1];
        let secondFrequency = secondInputEncoded[secondInputIndex][1];

        let productValue = firstValue * secondValue;
        let productFrequency = Math.min(firstFrequency, secondFrequency);

        if (productValue === previousProductValue || previousProductFrequency === 0) {
            previousProductFrequency += productFrequency;
            previousProductValue = productValue;
        } else {
            productOfRunLengthEncodedInputs.push([previousProductValue, previousProductFrequency]);
            previousProductValue = productValue;
            previousProductFrequency = productFrequency;
        }

        firstInputEncoded[firstInputIndex][1] = firstFrequency - productFrequency;
        secondInputEncoded[secondInputIndex][1] = secondFrequency - productFrequency;

        firstInputIndex += (firstInputEncoded[firstInputIndex][1] > 0) ? 0 : 1;
        secondInputIndex += (secondInputEncoded[secondInputIndex][1] > 0) ? 0 : 1;
    }

    productOfRunLengthEncodedInputs.push([previousProductValue, previousProductFrequency]);

    return productOfRunLengthEncodedInputs;
}
