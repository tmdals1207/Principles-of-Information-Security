import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("암호화된 문자열을 입력하세요. 입력을 끝내려면 'exit'을 입력하세요.");

        StringBuilder input = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            input.append(line).append("\n");
        }

        String cipherText = input.toString().trim(); // 입력받은 암호화된 문자열

        Map<Character, Character> rules = new HashMap<>();
        Map<Character, Character> appliedRules = new HashMap<>(); // 적용된 규칙 기록
        System.out.println("암호문을 변경할 규칙을 설정합니다.");
        System.out.println("알파벳을 입력하세요. 입력을 끝내려면 '.'을 입력하세요.");

        int count = 0;
        while (true) {
            System.out.print("바꿀 알파벳을 입력하세요: ");
            char alphabet = scanner.next().charAt(0);
            if (alphabet == '.') {
                break;
            }
            if (!Character.isAlphabetic(alphabet)) {
                System.out.println("알파벳이 아닙니다. 알파벳을 입력하세요.");
                continue;
            }
            System.out.print("'" + alphabet + "'를 어떤 알파벳으로 변경하시겠습니까? ");
            char newChar = scanner.next().charAt(0);
            if (!Character.isAlphabetic(newChar)) {
                System.out.println("알파벳을 입력하세요.");
                continue;
            }
            rules.put(alphabet, newChar);

            // 변환된 암호문 출력
            String replacedString = replaceCharacters(cipherText, rules);
            System.out.println("현재 변환된 문자열: " + replacedString);

            // 적용된 규칙 기록
            appliedRules.put(alphabet, newChar);

            // 암호문 변경 규칙 출력
            System.out.println("암호문 변경 규칙:");
            System.out.println("원본 알파벳\t->\t변경된 알파벳");
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                System.out.print(ch + " :\t");
                if (appliedRules.containsKey(ch)) {
                    System.out.print(appliedRules.get(ch));
                } else {
                    System.out.print(" ");
                }
                System.out.print(", ");
                count++;
                if (count % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    public static String replaceCharacters(String text, Map<Character, Character> rules) {
        StringBuilder replacedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            char replacedChar = Character.toUpperCase(ch);
            if (rules.containsKey(replacedChar)) {
                replacedText.append(rules.get(replacedChar));
            } else {
                replacedText.append(ch);
            }
        }
        return replacedText.toString();
    }
}
