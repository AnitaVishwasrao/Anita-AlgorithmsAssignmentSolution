
public class SearchSortHelper {

	private void merge(CompanyStock[] array, int left, int mid, int right) {
		int length1 = mid - left + 1;
		int length2 = right - mid;

		CompanyStock[] aLeft = new CompanyStock[length1];
		CompanyStock[] aRight = new CompanyStock[length2];

		for (int i = 0; i < length1; i++)
			aLeft[i] = array[i + left];

		for (int i = 0; i < length2; i++)
			aRight[i] = array[mid + 1 + i];

		int i = 0, j = 0, k = left;
		while (i < length1 && j < length2) {
			if (aLeft[i].sharePrice < aRight[j].sharePrice) {
				array[k] = aLeft[i];
				i++;
			} else {
				array[k] = aRight[j];
				j++;
			}
			k++;
		}

		while (i < length1) {
			array[k] = aLeft[i];
			i++;
			k++;
		}
		while (j < length2) {
			array[k] = aRight[j];
			j++;
			k++;
		}
	}

	public void mergeSort(CompanyStock[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(array, left, mid);
			mergeSort(array, mid + 1, right);
			merge(array, left, mid, right);
		}
	}

	public int binarySearch(CompanyStock[] array, int left, int right, double key) {

		if (right >= left) {
			int mid = (left + right) / 2;

			if (array[mid].sharePrice == key) {
				return mid;
			}

			if (array[mid].sharePrice < key) {
				left = mid + 1;

			} else {
				right = mid - 1;

			}
			return binarySearch(array, left, right, key);
		}
		return -1;
	}
}
