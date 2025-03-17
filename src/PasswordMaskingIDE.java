import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class PasswordMaskingIDE {
    public static void main(String[] args) {
        System.out.println(PasswordField.hashPassword("12345"));
    }
}

