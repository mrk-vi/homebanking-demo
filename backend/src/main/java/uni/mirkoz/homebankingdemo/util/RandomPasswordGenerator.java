package uni.mirkoz.homebankingdemo.util;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class RandomPasswordGenerator implements PasswordGenerator {

    private final RandomString randomString;

    public RandomPasswordGenerator() {
        this.randomString = new RandomString(8);
    }

    @Override
    public String newPassword() {
        return randomString.nextString();
    }

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new RandomPasswordGenerator();
        System.out.println(passwordGenerator.newPassword());
    }

}
