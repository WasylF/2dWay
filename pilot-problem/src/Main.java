
/**
 *
 * @author Wsl_F
 */
import static java.lang.Integer.min;
import java.util.LinkedList;
import parcs.*;
import wslf.algo.*;
import wslf.geometry.*;

public class Main implements AM {

    public static String inputFN;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("you should set input file");
            return;
        }
        inputFN = args[0];

        // Создание задания
        task curtask = new task();

        // Прикрепление jar-файла к процессу
        curtask.addJarFile("wslf.jar");

        // Запуск и окончание задания
        (new Main()).run(new AMInfo(curtask, (channel) null));
        curtask.end();
    }

    @Override
    public void run(AMInfo info) {
        // Загрузка данных
        VisGraph g = new VisGraph(info.curtask.findFile(inputFN));
        int size = g.getSize();

        final int threadsNum = 8;
        point[] p = new point[threadsNum];
        channel[] c = new channel[threadsNum];
        for (int i = 0; i < 8; i++) {
            p[i] = info.createPoint();
            c[i] = p[i].createChannel();
            p[i].execute("Algo");
        }

        LinkedList<LinkedList<Integer>> parts = new LinkedList<>();
        int partSize = size / threadsNum;
        for (int thread = 0; thread < threadsNum; thread++) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = min(size - 1, (thread + 1) * partSize); i >= thread * partSize; i--) {
                list.add(i);
            }
            parts.add(list);
        }
        
        // Разделение входных данных на части и запись их в данные отдельных потоков
        for (int thread= 0; thread<threadsNum; thread++) {
            c[thread].write(g.subTask(parts.get(thread)));
        }

        System.out.println("\nWaiting for result...");

        // Массив, где собираем данные
        VisGraph[] Parts = new VisGraph[threadsNum];

        // Вот здесь мы ожидаем окончания выполнения потоков и записываем их результаты
        // Поменяй Image на название твоего класса 
        for (int thread= 0; thread<threadsNum; thread++) {
            Parts[thread] = (VisGraph) (c[thread].readObject());
        }

        // Сбор результатов
        for (int thread= 1; thread<threadsNum; thread++) {
            Parts[0].add(Parts[thread]);
        }
        
        // Сохранение результата
        System.out.println("\nResult found!. Saving to file output.txt");
        Parts[0].save(info.curtask.addPath("output.txt"));
    }

}
