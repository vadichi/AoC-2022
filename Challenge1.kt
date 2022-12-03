import java.io.File

fun main() {
    val file = File("Challenge1.txt");

    val calories = arrayListOf<Int>(0);
    var index = 0;

    file.forEachLine {
        if (it == "") {
            calories.add(0);
            index++;
        } else {
            calories[index] += Integer.parseInt(it);
        }
    }

    calories.sortDescending();
    println(calories[0]);
}