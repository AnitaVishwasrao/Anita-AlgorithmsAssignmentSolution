import java.util.Scanner;

public class Stockers {
	static Scanner scanner = new Scanner(System.in);
	private int noOfCompanies, companyCountForWhichSharePriceRose;

	CompanyStock[] companyStocks = null;
	SearchSortHelper searchSortHelper = null;

	public Stockers() {
		searchSortHelper = new SearchSortHelper();
		companyCountForWhichSharePriceRose = 0;
	}

	public void insertValues() {
		System.out.print("Enter the no of companies: ");
		noOfCompanies = scanner.nextInt();
		companyStocks = new CompanyStock[noOfCompanies];

		for (int i = 0; i < noOfCompanies; i++) {
			CompanyStock cs = new CompanyStock();
			System.out.print("Enter current stock price of the company " + (i + 1) + ": ");
			cs.sharePrice = scanner.nextDouble();
			System.out.print("Whether company's stock price rose today compare to yesterday? ");
			cs.isPriceSameAsYesterday = scanner.nextBoolean();
			if (cs.isPriceSameAsYesterday)
				this.companyCountForWhichSharePriceRose++;
			companyStocks[i] = cs;
		}
	}

	// Sorts the share details in ascending order
	public void sort() {
		searchSortHelper.mergeSort(companyStocks, 0, noOfCompanies - 1);
	}

	public void searchSharePrice() {
		System.out.print("enter the key value: ");
		double sharePrice = scanner.nextDouble();
		int index = searchSortHelper.binarySearch(companyStocks, 0, noOfCompanies - 1, sharePrice);
		if (index > -1) {
			System.out.println("Stock of value " + sharePrice + " is present");
		} else {
			System.out.println("Stock of value " + sharePrice + " not found");
		}
	}

	// prints array values in ascending order
	public void printShareValuesAsc() {

		for (int i = 0; i < noOfCompanies; i++) {
			System.out.print(companyStocks[i].sharePrice + " ");
//			if (i < (noOfCompanies - 1))
//				System.out.print(", ");
		}
	}

	// prints array values in descending order
	public void printShareValuesDesc() {
		for (int i = noOfCompanies - 1; i >= 0; i--) {
			System.out.print(companyStocks[i].sharePrice + " ");
//			if (i > 0)
//				System.out.print(", ");
		}

	}

	public int getCompanyCountForWhichStockPriceRose() {
//		int count = 0;
//		for(int i=0;i<noOfCompanies;i++)
//		{
//			if(!companyStocks[i].isPriceSameAsYesterday)
//				count++;
//		}
//		return count;

		return companyCountForWhichSharePriceRose;
	}

	public int getCompanyCountForWhichStockPriceDeclined() {
		return noOfCompanies - companyCountForWhichSharePriceRose;
	}

	public static void main(String[] args) {
		Stockers stockers = new Stockers();
		stockers.insertValues(); // read all company and stock details
		stockers.sort(); // sort the details

		try {
			while (true) {

				System.out.println("===========================================================");
				System.out.println("1. Display the companies stock prices in ascending order\n"
						+ "2. Display the companies stock prices in descending order\n"
						+ "3. Display the total no of companies for which stock prices rose today\n"
						+ "4. Display the total no of companies for which stock prices declined today\n"
						+ "5. Search a specific stock price\n" + "6. press 0 to exit");
				System.out.println("===========================================================");
				int option = scanner.nextInt();

				switch (option) {
				case 1:
					System.out.println("Stock prices in ascending order are: ");
					// System.out.print("[");
					stockers.printShareValuesAsc();
					// System.out.println("]");
					break;
				case 2:
					System.out.println("Stock prices in descending order are: ");
					// System.out.print("[");
					stockers.printShareValuesDesc();
					// System.out.println("]");
					break;
				case 3:
					System.out.println("Total no of companies whose stock price rose today : "
							+ stockers.getCompanyCountForWhichStockPriceRose());
					break;
				case 4:
					System.out.println("Total no of companies whose stock price declined today : "
							+ stockers.getCompanyCountForWhichStockPriceDeclined());
					break;
				case 5:
					stockers.searchSharePrice();
					break;
				case 0:
					scanner.close();
					System.out.println("Exited successfully");
					System.exit(0);
				default:
					System.out.println("Invalid choice");
					break;
				}
				System.out.println();
			}
		} catch (Exception e) {
		} finally {
			scanner.close();
		}

	}
}
