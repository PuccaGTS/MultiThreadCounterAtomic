import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger nick_3_letter = new AtomicInteger(0);
    public static AtomicInteger nick_4_letter = new AtomicInteger(0);
    public static AtomicInteger nick_5_letter = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        //палиндром
        new Thread(() -> {
            for (String nick : texts) {
                if (isPalindrome(nick)) {
                    switch (nick.length()) {
                        case 3:
                            nick_3_letter.incrementAndGet();
                            break;
                        case 4:
                            nick_4_letter.incrementAndGet();
                            break;
                        case 5:
                            nick_5_letter.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        //одинаковые буквы
        new Thread(() -> {
            for (String nick : texts) {
                if (isSameLetterWord(nick)) {
                    switch (nick.length()) {
                        case 3:
                            nick_3_letter.incrementAndGet();
                            break;
                        case 4:
                            nick_4_letter.incrementAndGet();
                            break;
                        case 5:
                            nick_5_letter.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        //сортированная строка
        new Thread(() -> {
            for (String nick : texts) {
                if (isSortString(nick)) {
                    switch (nick.length()) {
                        case 3:
                            nick_3_letter.incrementAndGet();
                            break;
                        case 4:
                            nick_4_letter.incrementAndGet();
                            break;
                        case 5:
                            nick_5_letter.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + nick_3_letter.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + nick_4_letter.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + nick_5_letter.get() + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String nickName) {
        return nickName.equalsIgnoreCase(new StringBuilder(nickName).reverse().toString());
    }

    public static boolean isSameLetterWord(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) == nickName.charAt(i + 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortString(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) <= nickName.charAt(i + 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
