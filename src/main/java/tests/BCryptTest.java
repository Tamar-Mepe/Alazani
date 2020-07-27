package tests;

import org.junit.jupiter.api.Test;
import utils.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BCryptTest {
    private String passwords[] = {"Jij1*!@)(#naaaaaaaaAskkkasdllll",
            "RuNHIj5BK236TC",
            "StillDoggOOP_1",
            "MeArviYoParoliTu"};
    @Test
    void simpleTest() throws SQLException {
        String password ="Oesischabareba";
        String savedPassword ="Oesischabareba";
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        assertTrue(BCrypt.checkpw(savedPassword, generatedSecuredPasswordHash));

    }
    @Test
    void mediocreTest() throws SQLException {
        List<String> hashedPasswords = new ArrayList<String>();
        for(int i=0; i<passwords.length; i++){
            String generatedSecuredPasswordHash = BCrypt.hashpw(passwords[i], BCrypt.gensalt());
            hashedPasswords.add(generatedSecuredPasswordHash);
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<passwords.length; j++){
                assertTrue(BCrypt.checkpw(passwords[j], hashedPasswords.get(j)));
                String almostSamePassword = passwords[j]+" ";
                assertFalse(BCrypt.checkpw(almostSamePassword, hashedPasswords.get(j)));

            }
        }
    }

}