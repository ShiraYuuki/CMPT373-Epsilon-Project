package store;

import io.ebean.Ebean;
import io.ebean.Transaction;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import play.Application;
import play.test.Helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserStoreTest {

    private String contactEmail = "contact.email@sfu.ca";
    private String sfuEmail = "sfu.email@sfu.ca";
    private String phoneNumber = "60412234567";
    private String photoUrl = "http.cat/404";

    public static String createDdl = "";
    public static String dropDdl = "";

    public static Application app;

    @BeforeClass
    public static void startApp() throws IOException {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);

        // Reading the evolution file
        String evolutionContent = FileUtils.readFileToString(
                app.getWrappedApplication().getFile("conf/evolutions/default/1.sql"));

        String[] splittedEvolutionContent = evolutionContent.split("# --- !Ups");
        String[] upsDowns = splittedEvolutionContent[1].split("# --- !Downs");

        createDdl = upsDowns[0];
        dropDdl = upsDowns[1];
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void wipeDatabase() {
        dropTables();
        upTables();
    }

    private void dropTables() {
        Transaction transaction = Ebean.beginTransaction();
        Connection connection = transaction.getConnection();
        List<String> dropSql = Arrays.asList(dropDdl.split("\n"))
                .stream()
                .filter(statement -> statement.trim().isEmpty() == false)
                .collect(Collectors.toList());
        dropSql.forEach( sql -> {
            Statement statement = null;
            try {
                connection.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                fail(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        Ebean.commitTransaction();
        Ebean.endTransaction();
    }

    private void upTables() {
        Transaction transaction = Ebean.beginTransaction();
        Connection connection = transaction.getConnection();
        Arrays.asList(createDdl.split("\n\n"))
                .stream()
                .filter(statement -> statement.trim().isEmpty() == false)
                .map(statement -> statement.replace('\n', ' '))
                .forEach( sql -> {
                    try {
                        connection.createStatement().executeUpdate(sql);
                    } catch (SQLException e) {
                        fail(e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                });
        Ebean.commitTransaction();
        Ebean.endTransaction();
    }

    @Test
    public void testCreateUser() {
        User createdUser = null;
        try {
            createdUser = createTestUser()
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getLocalizedMessage());
            e.printStackTrace();
        }
        assertUserSameAsFields(createdUser);
    }

    @Test
    public void testGetCreatedUser() {
        try {
            User createdUser = createTestUser()
                    .thenCompose( user -> UserStore.getInstance().getUserWithUserId(user) )
                    .handle((value, e) -> {
                        fail();
                        return value;
                    } )
                    .get();
            assertNotNull(createdUser);
            assertUserSameAsFields(createdUser);
        } catch (InterruptedException | ExecutionException e) {
            fail(e.getLocalizedMessage());
            e.printStackTrace();
        }
        fail();
    }

    private CompletableFuture<User> createTestUser() {
        return UserStore.getInstance().createUser(sfuEmail, contactEmail, phoneNumber, photoUrl);
    }

    private void assertUserSameAsFields(User user) {
        assertEquals(contactEmail, user.getContactEmail());
        assertEquals(sfuEmail, user.getSfuEmail());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(photoUrl, user.getPhotoUrl());
    }

}
