package models;
import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void user_instantiatesCorrectly_true() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals(true, brian instanceof User);
    }

    @Test
    public void name_intantiatesWithName_name() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals("Brian", brian.getName());
    }

    @Test
    public void position_intantiatesWithPosition_position() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals("CEO", brian.getPosition());
    }

    @Test
    public void role_intantiatesWithRole_role() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals("Manage Bakers", brian.getRole());
    }

    @Test
    public void department_intantiatesWithDepartment_department() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals("Bakery", brian.getDepartment());
    }

    @Test
    public void setName_setsUsersName() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        brian.setName("Muigai");
        assertEquals("Muigai", brian.getName());
    }

    @Test
    public void setDepartment_setsUsersDepartment() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        brian.setDepartment("Muigai");
        assertEquals("Muigai", brian.getDepartment());
    }

    @Test
    public void userDetails_checksIfAllUserDetailsAreTheSame() {
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        User muigai = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        assertEquals(true, brian.equals(muigai));
    }

    @Test
    public void save_savesUserIntoDatabase_true() {
        User user = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        user.add(user);
        assertEquals(true, user.equals(User.findById(user.getId())));
    }

    @Test
    public void save_allUsersAreSavedToDatabase_true() {
        User user1 = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        user1.add(user1);
        User user2 = new User("Muigai", "Chairman", "Manage Board", "Board");
        user2.add(user2);
        assertEquals(true, User.all().contains(user1));
        assertEquals(true, User.all().contains(user2));
    }

    @Test
    public void delete_deletesUserById_false() {
        User user1 = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        user1.add(user1);
        User user2 = new User("Muigai", "Chairman", "Manage Board", "Board");
        user2.add(user2);
        User.deleteById(user1.getId());
        assertEquals(false, User.all().contains(user1));
    }

    @Test
    public void deleteAll_deletesAllUsers() {
        User user1 = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        user1.add(user1);
        User user2 = new User("Muigai", "Chairman", "Manage Board", "Board");
        user2.add(user2);
        User.deleteAll();
        assertEquals(0, User.all().size());
    }
}
