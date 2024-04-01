import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesDecrpyter {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static byte[] decodeBase64(String encodedString) {
        return Base64.getDecoder().decode(encodedString);
    }

    public static String decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String keyStr = "8iE3bf1se6N76HGPP8S0Xw==";
        String ivStr = "cHml3oX848/0uBwDJtChOA==";
        String ciphertextStr = "QDr9NZNG9Bgc3TTnfRuqjjzf/kVSYwbP7F9mR4GQZ/IneIh7HTc/xnwzEeVBc\n" +
                "H3pPlIbLFySKZruedJc9X87CGNDJ1f2Dat8BR3Ypbei5Q42xc306/AkSuGsjfqb\n" +
                "X9/ELxmdKn7MyvY/Jbc0v0AJHV6odgNzygKRRrFJcUIF/50=";
        ciphertextStr = ciphertextStr.replaceAll("\n", "");
        byte[] key = decodeBase64(keyStr);
        byte[] iv = decodeBase64(ivStr);
        byte[] ciphertext = decodeBase64(ciphertextStr);

        String plaintext = decrypt(key, iv, ciphertext);
        System.out.println("복호화된 문자열: " + plaintext);
    }
}