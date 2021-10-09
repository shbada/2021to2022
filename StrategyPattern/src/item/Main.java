package item;

public class Main {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();

        String message = "hello java";

        // base64 전략 셋팅
        EncodingStrategy base64 = new Base64Strategy();

        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);

        // normal 전략 셋팅
        EncodingStrategy normal = new NormalStrategy();

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);

        System.out.println("base64Result : " + base64Result);
        System.out.println("normalResult : " + normalResult);

        // append 전략 셋팅
        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);

        System.out.println("normalResult : " + appendResult);
    }
}
