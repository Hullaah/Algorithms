import pathlib
import sys
import importlib

BASE_DIR = pathlib.Path(__file__).parent


def test(func, test_cases) -> None: ...


def run_test(directory: pathlib.Path) -> None:
    module = importlib.import_module(directory.name)
    exported = getattr(module, "__all__", None)
    if exported is None:
        print(f"Skipping {directory}: Couldn't figure out function to be tested")
    elif len(exported) > 1:
        print(
            f"Skipping {directory}: Ambiguity "
            "on function to be tested as too many functions were specified"
        )
    else:
        func = getattr(module, exported[0])
        test_cases_dir = directory / "testcases"
        test_cases = dict(
            zip(test_cases_dir.glob("input*.txt"), test_cases_dir.glob("output*.txt"))
        )
        test(func, test_cases)


def main():
    if len(sys.argv) == 1:
        print("Runining test on all subdirectories...")
        for directory in BASE_DIR.iterdir():
            if directory.is_dir() and not directory.name.startswith((".", "_")):
                run_test(directory)
    else:
        for s in sys.argv[1:]:
            directory = BASE_DIR / s
            if directory.exists():
                run_test(directory)
            else:
                print(f"Skipping {directory}: Directory not found.")


if __name__ == "__main__":
    main()
