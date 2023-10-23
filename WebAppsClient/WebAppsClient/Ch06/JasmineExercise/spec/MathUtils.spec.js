let MathUtils = require('../MathUtils');

// This is our test suite
describe('MathUtils', () => {
    let calc;

    // This will be called before running each spec
    beforeEach(() => {
        calc = new MathUtils();
    });

    // This will be called after running each spec
    // Setting calc to null is not really necessary, but shown as an example
    afterEach(() => {
        calc = null;
    });

    it('should return correct answer for divide', () => {
        expect(calc.divide(8, 2)).toEqual(4);
    })

    // describes may be nested to group related tests together
    describe('when calc is used to perform basic math operations', () => {

        // Spec for sum operation
        it('should be able to calculate sum of 3 and 5', () => {
            // 3. ARRANGE
            let expectedResult = 8;

            // 1. ACT
            const result = calc.sum(3, 5);

            // 2. ASSERT
            expect(result).toEqual(expectedResult);
        });

        // Spec for multiply operation
        it('should be able to multiply 3 and 5', () => {
            const result = calc.multiply(3, 5);
            expect(result).toEqual(15);
        });

    });

    // describes may be nested to group related tests together
    describe('when calc is used to calculate factorials', () => {
        // TODO: write a test for function factorial using the number 9
        it('should calculate the factorial of 9', () => {
            const result = calc.factorial(9);
            expect(result).toEqual(362880);
        })
         
        // TODO: write a test for function factorial using the number -7
        // You expect this spec to throw an error
        // HINT: you will need toThrowError instead of toEqual
        it('should throw an error for negative number', () => {
            expect(() => {
                calc.factorial(-7);
            }).toThrowError(Error);
        });
    });
});
