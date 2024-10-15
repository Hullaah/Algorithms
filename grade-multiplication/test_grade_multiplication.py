from grade_multiplication import grade_multiplication


def test_grade_multiplication():
    """
    Tests the grade_multiplication multiplication algorithm
    """
    assert grade_multiplication(123456, 78910) == 123456 * 78910
    assert grade_multiplication(999103, 180) == 999103 * 180
    assert grade_multiplication(99999, 9999) == 99999 * 9999
    assert grade_multiplication(
        3141592653589793238462643383279502884197169399375105820974944592,
        2718281828459045235360287471352662497757247093699959574966967627,
    ) == (
        3141592653589793238462643383279502884197169399375105820974944592
        * 2718281828459045235360287471352662497757247093699959574966967627
    )
