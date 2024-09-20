from typing import Generator, Any

def decompose(n: int) -> Generator[int, Any, None]:
    """
    Generates the place values  of a given integer in base-10 starting
    from the smallest place value (ones place).

    Args:
        n: The integer to decompose. Must be a non-negative integer.

    Yields:
        int: Each place value of the number starting from the smallest
        (ones place) and progressing to the largest.
    
    Example:
        >>> list(decompose(1234))
        [4, 30, 200, 1000]
    """
    i = 1
    while n > 0:
        yield (n % 10) * i
        n //= 10
        i *= 10


def grade_multiplication(x: int, y: int) -> int:
    """
    Computes the product of two numbers using grade school math algorithm

    Args:
        x: number to be multiplied
        y: number to be multiplied

    Returns:
        int: The product of x and y
    """
    result = 0
    for i in decompose(x):
        for j in decompose(y):
            result +=  (i * j)
    return result

print(grade_multiplication(1234, 5678))
