import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EX_Stream {
    public void run() throws IOException {
        Stream<String> stream =
                Files.lines(Paths.get("c:\\Temp\\OS.txt"), Charset.forName("UTF-8"));
        stream.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        EX_Stream ex_stream=new EX_Stream();
        ex_stream.run();
    }
}
