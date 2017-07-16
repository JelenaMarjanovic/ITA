package assignment04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Assignment04 {

    public enum Commands {
        SHOW_ALL, SHOW, UPDATE, DELETE, ADD_NEW, EXIT, UNSPECIFIED
    }

    public static void main(String[] args) {

        Commands commands = Commands.UNSPECIFIED;

        System.out.println("Dobrodošli!");
        System.out.println("-----------");
        System.out.println("Dostupne komande su;"
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
                    commands = Commands.valueOf(command);
                } catch (Exception e) {
                    commands = Commands.UNSPECIFIED;
                    System.out.println("Nije dostupna uneta komanda!\n");
                    System.out.println();
                }
            }

            Session session = HibernateUtil.createSessionFactory().openSession();

            String employeeData = null;
            String[] employeeProps = null;
            int id;
            boolean internal_end = false;
            String choice;

            switch (commands) {

                case SHOW_ALL:
                    System.out.println("Izabrali ste prikaz svih zapolsenih.\n");

                    String hql = "from Employee as employee";
                    RetrieveData(session, hql);

                    System.out.println();
                    commands = Commands.UNSPECIFIED;
                    break;

                case SHOW:
                    System.out.println("Izabrali ste prikaz zaposlenih po zadatom kriterijumu.\n");

                    while (internal_end != true) {

                        sc = new Scanner(System.in);
                        System.out.println("Unesite redni broj kriterijuma:"
                                + "\n1 - ime i prezime\n2 - godine"
                                + "\n3 - adresa\n4 - visina dohotka");
                        String criteria = sc.next();

                        RetrieveData(session, Show(criteria));

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

                        Update(session, id, employeeProps);

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

                        Delete(session, id);

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

                        AddNew(session, employeeProps);

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
                    HibernateUtil.close();

                    System.out.println("----------");
                    System.out.println("Doviđenja!");
                    break;

                default:
                    break;

            }

        }

        sc.close();

    }

    // Listanje podataka dobijenih iz baze
    private static void RetrieveData(Session session, String hql) {

        Transaction tx = null;
        List<Employee> employees = new ArrayList();

        try {

            Query query = session.createQuery(hql);

            tx = session.beginTransaction();

            employees = query.list();

            tx.commit();

        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    // Prikaz samo odredjenih zaposlenih po nekom od kriterijuma
    private static String Show(String criteria) {

        Scanner sc = new Scanner(System.in);
        String hql = null;

        switch (criteria) {

            case "1":
                System.out.println("Unesite traženo ime i prezime:");
                String name = sc.nextLine();

                hql = "from Employee as employee where employee.name='" + name + "'";

                break;

            case "2":
                System.out.println("Da li želite pretragu po:"
                        + "\n1 - tačnim godinama"
                        + "\n2 - opsegu (od ... do)");
                int option = sc.nextInt();

                if (option == 1) {
                    System.out.println("Unesite tražene godine:");
                    int age = Integer.valueOf(sc.next());

                    hql = "from Employee as employee where age='" + age + "'";
                } else if (option == 2) {
                    System.out.println("Unesite opseg u formatu: minimum-maksimum");
                    String age_range = sc.next();
                    String[] age = age_range.split("-");

                    hql = "from Employee as employee where age between " + Integer.valueOf(age[0]) + " and " + Integer.valueOf(age[1]) + " order by employee.age asc";
                }

                break;

            case "3":
                System.out.println("Unesite traženu adresu:");
                String address = sc.nextLine();

                hql = "from Employee as employee where address='" + address + "'";

                break;

            case "4":
                System.out.println("Unesite traženi opseg dohotka u formatu: minimum-maksimum");
                String sallary_range = sc.next();
                String[] sallary = sallary_range.split("-");

                hql = "from Employee as employee where sallary between " + Double.valueOf(sallary[0]) + " and " + Double.valueOf(sallary[1]) + " order by employee.sallary asc";

                break;

            default:
                break;

        }

        return hql;

    }

    // Izmena podataka o zaposlenom na osnovu kriterijuma
    private static void Update(Session session, int id, String[] employeeProps) {

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Employee employee = (Employee) session.load(Employee.class, id);
            employee.setName(employeeProps[0]);
            employee.setAge(Integer.valueOf(employeeProps[1]));
            employee.setAddress(employeeProps[2]);
            employee.setSallary(Double.valueOf(employeeProps[3]));

            session.update(employee);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

    }

    // Unos novog zaposlenog
    private static void AddNew(Session session, String[] employeeProps) {

        Transaction tx = null;

        Employee employee = new Employee(employeeProps[0], Integer.valueOf(employeeProps[1]), employeeProps[2], Double.valueOf(employeeProps[3]));

        try {

            tx = session.beginTransaction();

            session.persist(employee);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

        System.out.println("Unet je nov zaposleni sa ID-jem: " + employee.getId());

    }

    // Brisanje zaposlenog na osnovu kriterijuma
    private static void Delete(Session session, int id) {

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Employee employee = (Employee) session.load(Employee.class, id);

            session.delete(employee);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();
        }

        System.out.println("Obrisan je zaposleni sa ID-jem: " + id);

    }

}
