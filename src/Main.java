import java.io.*;
import java.util.*;

//отладочный класс
class Debugger {
    private static final boolean DEBUG = true;

    public static void log(String message) {
        if (DEBUG) {
            System.out.println("[DEBUG] " + message);
        }
    }

    public static void printArray(int[] arr, int limit) {
        if (DEBUG) {
            System.out.print("[DEBUG] Array: ");
            for (int i = 0; i < Math.min(arr.length, limit); i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("27885.txt"));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] files = new int[N];

            for (int i = 0; i < N; i++) {
                files[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(files);
            Debugger.log("Отсортировали массив");
            Debugger.printArray(files, 20);

            int sum = 0;
            int count = 0;

            //набираем максимум файлов
            for (int i = 0; i < N; i++) {
                if (sum + files[i] <= S) {
                    sum += files[i];
                    count++;
                    Debugger.log("Добавили файл: " + files[i] + ", сумма = " + sum);
                } else {
                    break;
                }
            }

            int maxFile = files[count - 1];
            Debugger.log("Начальный maxFile = " + maxFile);

            //меняем последний файл на более большой
            for (int i = count; i < N; i++) {
                if (sum - files[count - 1] + files[i] <= S) {
                    maxFile = files[i];
                    Debugger.log("Заменили на более большой файл: " + maxFile);
                }
            }

            System.out.println(count + " " + maxFile);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
        }
    }
}