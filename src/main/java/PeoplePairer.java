
public class PeoplePairer {

    public static PairArranger pairArranger;

    public static void main(String[] args) {
        long iteration = 0;

        while (true) {

            System.out.println("People Pairer");
            pairArranger = new PairArranger();

            // Shortcut hack for now.
            pairArranger.SetUp3TeamsWith9Members();

            pairArranger.ArrangePairs();
            pairArranger.OutputPairs();

            System.out.println("Complete!");
            iteration++;
            System.out.println("Iteration: " + iteration);

            pairArranger.ClearPairs();
        }
    }
}
