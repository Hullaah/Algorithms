def karatsuba(x: int, y: int):
    """
    Computes the product of x and y using a divide and conquer algorithm

    Args:
        x: number to be multiplied
        y: number to be multiplied

    Returns:
        int: The product of x and y
    """
    # The algorithm works by dividing a number of length n into n / 2 and
    # caculating the product of length n / 2 recursively combining the total
    # result  of the product of each
    # x = 10 ^ (n // 2) * a + b
    # y = 10 ^ (n // 2) * c + d

    if x % 10 == x and y % 10 == y:
        return x * y
    else:
        n = max(len(str(x)), len(str(y)))
        exponent = n - (n // 2)

        a = x // 10**exponent
        b = x % 10**exponent
        c = y // 10**exponent
        d = y % 10**exponent

        ac = karatsuba(a, c)
        bd = karatsuba(b, d)
        ad_plus_bc = karatsuba(a + b, c + d) - (ac + bd)
        return (ac * 10 ** (exponent * 2)) + (ad_plus_bc * 10**exponent) + bd


print(karatsuba(999999, 999999), 999999 * 999999)
