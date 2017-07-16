package assignment01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment01 {

    public enum Commands {
        SHOW_ALL, SHOW, UPDATE, DELETE, ADD_NEW, EXIT, UNSPECIFIED
    }

    public static void main(String[] args) {

        Commands commands = Commands.UNSPECIFIED;

        System.out.println("Dobrodošli!");
        System.out.println("-----------");
        System.out.println("Dostupne komande su:"
                + "\nSHOW_ALL - prikaz svih zaposlenih"
                + "\nSHOW     - prikaz samo određenih zaposlenih po nekom od kriterijuma"
                + "\nUPDATE   - izmena podataka o zaposlenom na osnovu kriterijuma"
                + "\nDELETE   - brisanje zaposlenog na osnovu kriterijuma"
                + "\nADD_NEW  - unos novog zaposlenog"
                + "\nEXIT     - izlaz iz programa\n");

        boolean end = false;

        Scanner sc = new Scanner(System.in);

        while (end != true) {

            while (commands == Commands.UNSPECIFIED) {

                System.out.println("Unesite komandu:");

                String command = sc.next().toUpperCase();

                try {
                    commands = commands.valueOf(command);
                } catch (Exception e) {
                    commands = Commands.UNSPECIFIED;
                    System.out.println("Nije dostupna uneta komanda!\n");
                    System.out.println();
                }

            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment01", "root", "");) {

                String employeeData = null;
                String[] employeeProps = null;
                int id;
                Employee employee = null;
                boolean internal_end = false;
                String choice;

                switch (commands) {

                    case SHOW_ALL:
                        System.out.println("Izabrali ste prikaz svih zaposlenih.\n");

                        ShowAll(conn);

                        System.out.println();
                        commands = Commands.UNSPECIFIED;
                        break;
                    case SHOW:
                        System.out.println("Izabrali ste prikaz zaposlenih po zadatom kriterijuma.\n");

                        while (internal_end != true) {
                            sc = new Scanner(System.in);
                            System.out.println("Unesite redni broj kriterijuma:"
                                    + "\n1 - ime i prezime\n2 - godine"
                                    + "\n3 - adresa\n4 - visina dohotka");
                            String criteria = sc.next();

                            Show(conn, criteria);

                            sc = new Scanner(System.in);
                            System.out.println("Da li želite da izvršite novu pretragu? (DA/NE)");
                            choice = sc.next().toUpperCase();
                            internal_end = choice.equals("NE");

                        }

                        System.out.println();
                        commands = Commands.UNSPECIFIED;
                        break;
                    case UPDATE:
                        System.out.println("Izabrali ste izmenu podataka o zaposlenom.\n");

                        while (internal_end != true) {
                            sc = new Scanner(System.in);
                            System.out.println("Unesite ID zaposlenog čije podatke želite da izmenite:");
                            id = sc.nextInt();
                            System.out.println("Unesite nove podatke u formatu: ime prezime;godine;adresa;visina dohotka");
                            sc = new Scanner(System.in);
                            employeeData = sc.nextLine();
                            employeeProps = employeeData.split(";");
                            employee = new Employee(employeeProps[0], Integer.valueOf(employeeProps[1]), employeeProps[2], Double.valueOf(employeeProps[3]));

                            Update(conn, id, employee);

                            sc = new Scanner(System.in);
                            System.out.println("Da li želite da izmenite podatke drugog zaposlenog? (DA/NE)");
                            choice = sc.next().toUpperCase();
                            internal_end = choice.equals("NE");

                        }

                        System.out.println();
                        commands = Commands.UNSPECIFIED;
                        break;
                    case DELETE:
                        System.out.println("Izabrali ste brisanje zaposlenog iz baze.\n");

                        while (internal_end != true) {
                            sc = new Scanner(System.in);
                            System.out.println("Unesite ID zaposlenog kojeg želite da izbrišete iz baze:");
                            id = sc.nextInt();

                            Delete(conn, id);

                            sc = new Scanner(System.in);
                            System.out.println("Da li želite da izbrišete drugog zaposlenog iz baze? (DA/NE)");
                            choice = sc.next().toUpperCase();
                            internal_end = choice.equals("NE");

                        }

                        System.out.println();
                        commands = Commands.UNSPECIFIED;
                        break;
                    case ADD_NEW:
                        System.out.println("Izabrali ste unos novog zaposlenog u bazu.\n");

                        while (internal_end != true) {
                            sc = new Scanner(System.in);
                            System.out.println("Unesite podatke o zaposlenom u formatu: ime prezime;godine;adresa;visina dohotka");
                            employeeData = sc.nextLine();
                            employeeProps = employeeData.split(";");
                            employee = new Employee(employeeProps[0], Integer.valueOf(employeeProps[1]), employeeProps[2], Double.valueOf(employeeProps[3]));

                            AddNew(conn, employee);

                            sc = new Scanner(System.in);
                            System.out.println("Da li želite da unesete novog zaposlenog? (DA/NE)");
                            choice = sc.next().toUpperCase();
                            internal_end = choice.equals("NE");
                        }

                        System.out.println();
                        commands = Commands.UNSPECIFIED;
                        break;

                    case EXIT:
                        System.out.println("Izabrali ste izlazak iz programa.");

                        end = true;

                        System.out.println("----------");
                        System.out.println("Doviđenja!");
                        break;

                    default:
                        break;

                }

            } catch (SQLException exception) {
                System.out.println("Greška tokom konektovanja sa bazom:\n" + exception.getMessage());
            }

        }

        sc.close();

    }

// Iscitavanje podataka iz baze
    private static void Reading(Statement st) throws SQLException {

        Employee employee = null;
        List<Employee> employee_list = new ArrayList();

        ResultSet rs = st.getResultSet();

        while (rs.next()) {

            employee = new Employee(rs.getInt("employee_id"), rs.getString("name"), rs.getInt("age"), rs.getString("address"), rs.getDouble("sallary"));
            employee_list.add(employee);

        }

        for (Employee e : employee_list) {
            System.out.println(e);
        }

    }

    // Prikaz svih zaposlenih
    private static void ShowAll(Connection conn) throws SQLException {

        Statement st = conn.createStatement();
        st.executeQuery("SELECT * FROM employee");

        Reading(st);

    }

    // Prikaz samo odredjenih zaposlenih po nekom od kriterijuma
    private static void Show(Connection conn, String criteria) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Employee employee = null;
        List<Employee> employee_list = new ArrayList();
        Statement st = conn.createStatement();

        switch (criteria) {

            case "1":
                System.out.println("Unesite traženo ime i prezime:");
                String name = sc.nextLine();
                st.executeQuery("SELECT * FROM employee WHERE name='" + name + "'");

                Reading(st);

                break;
            case "2":
                System.out.println("Da li želite pretragu po:"
                        + "\n1 - tačnim godinama"
                        + "\n2 - opsegu (od ... do)");
                int option = sc.nextInt();

                if (option == 1) {
                    System.out.println("Unesite tražene godine:");
                    int age = Integer.valueOf(sc.next());
                    st.executeQuery("SELECT * FROM employee WHERE age='" + age + "'");

                } else if (option == 2) {
                    System.out.println("Unesite opseg u formatu: minimum-maksimum");
                    String age_range = sc.next();
                    String[] age = age_range.split("-");
                    st.executeQuery("SELECT * FROM employee WHERE age BETWEEN " + Integer.valueOf(age[0]) + " AND " + Integer.valueOf(age[1]));
                }

                Reading(st);

                break;
            case "3":
                System.out.println("Unesite traženu adresu:");
                String address = sc.nextLine();
                st.executeQuery("SELECT * FROM employee WHERE address='" + address + "'");

                Reading(st);

                break;
            case "4":
                System.out.println("Unesite traženi opseg dohotka u formatu: minimum-maksimum");
                String sallary_range = sc.next();
                String[] sallary = sallary_range.split("-");
                st.executeQuery("SELECT * FROM employee WHERE sallary BETWEEN " + Double.valueOf(sallary[0]) + " AND " + Double.valueOf(sallary[1]));

                Reading(st);

                break;

            default:
                break;

        }

    }

    // Izmena podataka o zaposlenom na osnovu kriterijuma
    private static void Update(Connection conn, int id, Employee employee) throws SQLException {

        PreparedStatement st = conn.prepareStatement("UPDATE employee SET name=?, age=?, address=?, sallary=? WHERE employee_id=?");
        st.setString(1, employee.getName());
        st.setString(2, String.valueOf(employee.getAge()));
        st.setString(3, employee.getAddress());
        st.setString(4, String.valueOf(employee.getSallary()));
        st.setString(5, String.valueOf(id));
        st.execute();

        System.out.println("Izmenjeni su podaci o zaposlenom sa ID-jem: " + id);

    }

    // Brisanje zaposlenog na osnovu kriterijuma
    private static void Delete(Connection conn, int id) throws SQLException {

        PreparedStatement st = conn.prepareStatement("DELETE FROM employee WHERE employee_id=?");
        st.setString(1, String.valueOf(id));
        st.execute();

        System.out.println("Obrisan je zaposleni sa ID-jem: " + id);

    }

    // Unos novog zaposlenog
    private static void AddNew(Connection conn, Employee employee) throws SQLException {

        PreparedStatement st = conn.prepareStatement("INSERT INTO employee (name,age,address,sallary) VALUES (?,?,?,?)");
        st.setString(1, employee.getName());
        st.setString(2, String.valueOf(employee.getAge()));
        st.setString(3, employee.getAddress());
        st.setString(4, String.valueOf(employee.getSallary()));
        st.execute();

        ResultSet rs = st.executeQuery("SELECT last_insert_id() AS employee_id FROM employee");
        rs.next();
        System.out.println("Unet je nov zaposleni sa ID-jem: " + rs.getString("employee_id"));

    }

}
