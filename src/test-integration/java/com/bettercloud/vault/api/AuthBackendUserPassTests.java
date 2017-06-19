package com.bettercloud.vault.api;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.AuthResponse;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

/** Integration tests for the Username/Password auth backend. */
public class AuthBackendUserPassTests {

    @ClassRule
    public static final VaultContainer container = new VaultContainer();

    @BeforeClass
    public static void setupClass() throws IOException, InterruptedException {
        container.initAndUnsealVault();
        container.setupUserPassBackend();
    }

    /** Test Authentication with new userpass auth backend */
    @Test
    public void testLoginByUserPass() throws VaultException {
        final Vault vault = container.getVault();
        final AuthResponse response = vault.auth().loginByUserPass(VaultContainer.USER_ID, VaultContainer.PASSWORD);
        final String token = response.getAuthClientToken();

        assertNotNull(token);
        assertNotSame("", token.trim());
    }

}
