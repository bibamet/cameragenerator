package com.example.cameragenerator.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class NumberGenerator {

    private static boolean treeDigits = false;
    private final Random randomizer;
    private final String letters = "АВЕКМНОРСТУХ";
    private final String digits = "0123456789";

    public String getRandomNuber() {

        StringBuilder number = new StringBuilder();
        number.append(letters.charAt(randomizer.nextInt(letters.length() - 1)));

        for (int i = 0; i < 3; i++) {
            number.append(digits.charAt(randomizer.nextInt(digits.length() - 1)));
        }

        for (int i = 0; i < 2; i++) {
            number.append(letters.charAt(randomizer.nextInt(letters.length() - 1)));
        }

        if (treeDigits) {
            for (int i = 0; i < 3; i++) {
                number.append(digits.charAt(randomizer.nextInt(digits.length() - 1)));
            }
            treeDigits = !treeDigits;
        } else {
            for (int i = 0; i < 2; i++) {
                number.append(digits.charAt(randomizer.nextInt(digits.length() - 1)));
            }
            treeDigits = !treeDigits;
        }
        return number.toString();
    }

}
