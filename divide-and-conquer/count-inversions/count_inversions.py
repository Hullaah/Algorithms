"""
This module implements the count inversion algorithm. The count inversion
algorithm piggy backs on merge sort. Its use case can be observed in
collaborative filtering, a recommender system that works by counting the
number of inversions from different user ratings or choices
"""

def count_inversions_split(a, b):
    k = len(a) + len(b)
    arr = []
    i, j = 0, 0
    inversions = 0
    for _ in range(k):
        if i >= len(a):
            arr.append(b[j])
            j += 1
        elif j >= len(b):
            arr.append(a[i])
            i += 1
        elif a[i] <= b[j]:
            arr.append(a[i])
            i += 1
        else:
            arr.append(b[j])
            j += 1
            inversions += len(a[i:])
    return inversions, arr

def count_inversions[T](arr: list[T]) -> tuple[int, list[T]]:
    """
    Counts the number of inversions in an array

    Args:
        arr: array to count inversions on

    Returns:
        tuple: A tuple of the number of inversions in the array and
        a sorted version of the array
    """
    if len(arr) == 0 or len(arr) == 1:
        return 0, arr
    n = len(arr) // 2
    x, a = count_inversions(arr[:n])
    y, b = count_inversions(arr[n:])
    z, c = count_inversions_split(a, b)
    return x + y + z, c
