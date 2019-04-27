import java.io.*;

public class Chocolates {

    public static void main(String[] args) throws IOException {
        parseOrdersFile("input/orders.csv");
    }

    static String tallyFinalOrder(String chocolateType, Integer cash, Integer price, Integer bonusRatio) {
        price = (price < 1) ? 1 : price; // Don't give away chocolate for free!
        int bonus = (bonusRatio < 1) ? 0 : (cash / price) / bonusRatio; // No bonuses if there's no bonus ratio.
        int milk = 0, dark = 0, white = 0;

        switch (chocolateType) {
            case "milk":
                milk = (cash / price) + bonus;
                break;
            case "dark":
                dark = (cash / price) + (2 * bonus);
                break;
            case "white":
                white = (cash / price) + bonus;
                milk = bonus;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + chocolateType);
        }

        return String.format("milk %d,dark %d,white %d", milk, dark, white);
    }

    static void parseOrdersFile(String ordersFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ordersFilePath));
        String line = br.readLine(); // Throw away first line with header information.

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            System.out.println(tallyFinalOrder(values[0].replace("\"", ""), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3])));
        }

        br.close();
    }
}
