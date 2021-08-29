package models;
import org.junit.*;
import static org.junit.Assert.*;

public class DepartmentTest {
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Department_instantiatesCorrectly_true() {
        Department sales = new Department("sales", "Sell software products");
        assertEquals(true, sales instanceof Department);
    }

    @Test
    public void name_intantiatesWithName_name() {
        Department sales = new Department("sales", "Sell software products");
        assertEquals("sales", sales.getName());
    }

    @Test
    public void description_intantiatesWithDescription_description() {
        Department sales = new Department("sales", "Sell software products");
        assertEquals("Sell software products", sales.getDescription());
    }

    @Test
    public void initialCount_expectedZeroEmployees() {
        Department sales = new Department("sales", "Sell software products");
        assertEquals(0, sales.getEmployee_count());
    }

    @Test
    public void add_addNewDepartment() {
        Department sales = new Department("sales", "Sell software products");
        sales.add(sales);
        assertEquals(true, Department.all().contains(sales));
    }

    @Test
    public void department_getDepartment() {
        Department sales = new Department("sales", "sell software products");
        sales.add(sales);
        Department marketing = new Department("marketing", "market software products");
        marketing.add(marketing);
        assertEquals(true, Department.all().contains(sales));
        assertEquals(true, Department.all().contains(marketing));
    }

    @Test
    public void add_addUserToDepartment() {
        Department sales = new Department("sales", "Sell software products");
        sales.add(sales);
        User brian = new User("Brian", "CEO", "Manage Bakers", "Bakery");
        sales.addUserToDepartment(brian, sales);
        assertEquals("sales", brian.getDepartment());
    }

    @Test
    public void delete_deleteAllDepartments() {
        Department sales = new Department("sales", "sell software products");
        sales.add(sales);
        Department marketing = new Department("marketing", "market software products");
        marketing.add(marketing);
        Department.clearAll();
        assertEquals(0, Department.all().size());
    }
}
