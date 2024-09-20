from mergesort import mergesort
from random import shuffle

def test_mergesort():
    """
    Tests the merge sort algorithm
    """
    assert mergesort([]) == []
    assert mergesort([1]) == [1]
    assert mergesort([99, 0]) == [0, 99]
    assert mergesort([21, 31, 40, 67, 89, 90, 100]) == [21, 31, 40, 67, 89, 90, 100]
    assert mergesort([89, 0, 0, 0, 1]) == [0, 0, 0, 1, 89]
    assert mergesort([7, 8, 6, 5, 100, 4, 10, 1]) == [1, 4, 5, 6, 7, 8, 10, 100]
    x = [x for x in range(10000)]
    shuffle(x)
    assert mergesort(x) == [x for x in range(10000)]
