"""
This module implements the mergesort algorithm. It also implements
the merge function that does the combine step of the merge sort
algorithm
"""


def merge[T](a: list[T], b: list[T]) -> list[T]:
    """
    The merge funcion does the combine step of the merge sort algorithm by
    merging two subarrays that have each been sorted.

    Args:
        a: first subarray
        b: second subarray

    Returns:
        list: A merged sorted array of the two subarrays
    """
    k = len(a) + len(b)
    arr = []
    i, j = 0, 0
    for _ in range(k):
        if i >= len(a):
            arr.append(b[j])
            j += 1
        elif j >= len(b):
            arr.append(a[i])
            i += 1
        elif a[i] < b[j]:
            arr.append(a[i])
            i += 1
        else:
            arr.append(b[j])
            j += 1
    return arr


def mergesort[T](arr: list[T]) -> list[T]:
    """
    Implements the merge sort divide and conquer algorithm for sorting
    arrays in O(nlog(n))

    Args:
        arr: array to be sorted

    Returns:
        list: sorted array
    """
    if len(arr) == 0 or len(arr) == 1:
        return arr
    n = len(arr) // 2
    return merge(mergesort(arr[:n]), mergesort(arr[n:]))
