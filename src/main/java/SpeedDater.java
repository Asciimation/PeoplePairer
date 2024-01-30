
public class SpeedDater {

    public static DateArranger dateArranger;

    public static void main(String[] args) {
        System.out.println("Speed Dater");
        dateArranger = new DateArranger();

        // Shortcut hack for now.
        dateArranger.SetUp3TeamsWith9Members();

        dateArranger.ArrangeDates();
        dateArranger.OutputDates();

        System.out.println("Complete!");
    }
}
