public class Main {
    public static void main(String[] args) {
        //init inventory
        Product[] inventory = {
            new Product("Laptop", 999.99, 5),
            new Product("Smartphone", 499.99, 10),
            new Product("Tablet", 299.99, 0),
            new Product("Smartwatch", 199.99, 3)
        };

        System.out.println("---------------------------------------------------------");
        double totalValue = calcTotalValue(inventory);
        System.out.printf("The total inventory value is: %.2f\n", totalValue);

        Product mostExpensiveProduct = findTheMostExpensiveProduct(inventory);
        System.out.println("The name of product with the highest price is: " + mostExpensiveProduct.getName());

        boolean headphonesExist = findProductName(inventory, "Headphones");
        System.out.println("Does Headphones exist in the stock? " + headphonesExist);

        System.out.println("---------------------------------------------------------");
        selectionSort(inventory, (t1, t2) -> t1.getPrice() < t2.getPrice());
        System.out.println("Inventory after sorting by ascending price");
        printInventory(inventory);

        System.out.println("---------------------------------------------------------");
        selectionSort(inventory, (t1, t2) -> t1.getPrice() > t2.getPrice());
        System.out.println("Inventory after sorting by descending price");
        printInventory(inventory);

        System.out.println("---------------------------------------------------------");
        selectionSort(inventory, (t1, t2) -> t1.getQuantity() < t2.getQuantity());
        System.out.println("Inventory after sorting by ascending quanity");
        printInventory(inventory);

        System.out.println("---------------------------------------------------------");
        selectionSort(inventory, (t1, t2) -> t1.getQuantity() > t2.getQuantity());
        System.out.println("Inventory after sorting by descending quanity");
        printInventory(inventory);

    }

    public static double calcTotalValue(Product[] products) {
        double totalValue = 0;
        for (Product product: products) {
            totalValue += product.calcValue();
        }
        return totalValue;
    }

    public static Product findTheMostExpensiveProduct(Product[] products) {
        Product mostExpensiveProduct = products[0];
        for (int i = 1; i < products.length; ++i) {
            if (mostExpensiveProduct.calcValue() < products[i].calcValue()) {
                mostExpensiveProduct = products[i];
            }
        }

        return mostExpensiveProduct;
    }

    public static boolean findProductName(Product[] products, String productName) {
        for (Product product: products) {
            if (product.getName().equals(productName))
                return true;
        }

        return false;
    }

    public static <T> void selectionSort(Product[] products, Comparator<Product> comparator) {
        for (int i = 0; i < products.length - 1; ++i) {
            int minOrMax = i;
            for (int j = i + 1; j < products.length; ++j) {
                if (comparator.compare(products[j], products[minOrMax])) {
                    minOrMax = j;
                }
            }

            if (minOrMax != i) {
                Product tmp = products[minOrMax];
                products[minOrMax] = products[i];
                products[i] = tmp;
            }
        }
    }

    public static void printInventory(Product[] inventory) {
        for (Product product: inventory) {
            System.out.println(product.toString());
        }
    }
}
