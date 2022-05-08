package ua.palamar.view;

public class ProgressBar {

    private static int dots;

    static public void refreshBar() {
        if (dots == 3) {
            dots = 0;
        } else {
            dots++;
        }

        System.out.print("Loading" + ".".repeat(dots) + "\r");
    }

}
