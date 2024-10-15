from count_inversions import count_inversions
from pathlib import Path


BASE_DIR = Path(__file__).parent

def test_no_inversions():
    """
    Tests that the count_inversions algorithm works properly when no
    inversions
    """
    inversions, _ = count_inversions([x for x in range(10)])
    assert inversions == 0


def test_count_inversions_small():
    """
    Tests the count_inversions function with a small dataset
    """
    with open(BASE_DIR / "data-small.txt", "r") as data_small:
        numbers = [int(x.strip()) for x in data_small]
        inversions, _ = count_inversions(numbers)
        assert inversions == 28


def test_count_inversions_large():
    """
    Tests the count_inversions function with a large dataset
    """
    with open(BASE_DIR / "data-large.txt", "r") as data_large:
        numbers = [int(x.strip()) for x in data_large]
        inversions, _ = count_inversions(numbers)
        assert inversions == 2407905288
