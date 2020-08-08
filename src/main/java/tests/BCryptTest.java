package tests;

import org.junit.jupiter.api.Test;
import utils.BCrypt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptTest {
    private String[] passwords = {"Jij1*!@)(#naaaaaaaaAskkkasdllll",
            "RuNHIj5BK236TC",
            "StillDoggOOP_1",
            "MeArviYoParoliTu"};

    @Test
    public void simpleTest() {
        String password = "Oesischabareba";
        String savedPassword = "Oesischabareba";
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        assertTrue(BCrypt.checkpw(savedPassword, generatedSecuredPasswordHash));

    }

    @Test
    public void mediocreTest() {
        List<String> hashedPasswords = new ArrayList<>();
        for (String password : passwords) {
            String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            hashedPasswords.add(generatedSecuredPasswordHash);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < passwords.length; j++) {
                assertTrue(BCrypt.checkpw(passwords[j], hashedPasswords.get(j)));
                String almostSamePassword = passwords[j] + " ";
                assertFalse(BCrypt.checkpw(almostSamePassword, hashedPasswords.get(j)));

            }
        }
    }

}