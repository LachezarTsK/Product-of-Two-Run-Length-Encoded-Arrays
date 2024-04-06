
#include <span>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

public:
    vector<vector<int>> findRLEArray(vector<vector<int>>& firstInputEncoded, vector<vector<int>>& secondInputEncoded) const {

        return calculateProductBetweenBothInputs(firstInputEncoded, secondInputEncoded);
    }

private:
    vector<vector<int>> calculateProductBetweenBothInputs(span<vector<int>> firstInputEncoded, span<vector<int>> secondInputEncoded) const {
        int firstInputIndex = 0;
        int secondInputIndex = 0;

        int previousProductValue = 0;
        int previousProductFrequency = 0;

        vector<vector<int>> productOfRunLengthEncodedInputs;

        while (firstInputIndex < firstInputEncoded.size() && secondInputIndex < secondInputEncoded.size()) {

            int firstValue = firstInputEncoded[firstInputIndex][0];
            int secondValue = secondInputEncoded[secondInputIndex][0];
            int firstFrequency = firstInputEncoded[firstInputIndex][1];
            int secondFrequency = secondInputEncoded[secondInputIndex][1];

            int productValue = firstValue * secondValue;
            int productFrequency = min(firstFrequency, secondFrequency);

            if (productValue == previousProductValue || previousProductFrequency == 0) {
                previousProductFrequency += productFrequency;
                previousProductValue = productValue;
            }
            else {
                productOfRunLengthEncodedInputs.push_back(vector<int>{previousProductValue, previousProductFrequency});
                previousProductValue = productValue;
                previousProductFrequency = productFrequency;
            }

            firstInputEncoded[firstInputIndex][1] = firstFrequency - productFrequency;
            secondInputEncoded[secondInputIndex][1] = secondFrequency - productFrequency;

            firstInputIndex += (firstInputEncoded[firstInputIndex][1] > 0) ? 0 : 1;
            secondInputIndex += (secondInputEncoded[secondInputIndex][1] > 0) ? 0 : 1;
        }

        productOfRunLengthEncodedInputs.push_back(vector<int>{previousProductValue, previousProductFrequency});

        return productOfRunLengthEncodedInputs;
    }
};
