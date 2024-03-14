package hotelmanagementapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {





        private final SimpleIntegerProperty id;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty phoneNumber;
        private final SimpleStringProperty email;

        private final SimpleStringProperty positoin;
        private final SimpleStringProperty departement;
        private final SimpleStringProperty sex;
        private final SimpleDoubleProperty salary;


        public Employee(int id, String firstName, String lastName, String phoneNumber, String email, String positoin, String departement, String sex, double salary) {
            this.id = new SimpleIntegerProperty(id);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.phoneNumber = new SimpleStringProperty(phoneNumber);
            this.email = new SimpleStringProperty(email);
            this.positoin = new SimpleStringProperty(positoin);
            this.departement = new SimpleStringProperty(departement);
            this.sex = new SimpleStringProperty(sex);
            this.salary = new SimpleDoubleProperty(salary);

        }


        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public String getFirstName() {
            return firstName.get();
        }

        public SimpleStringProperty firstNameProperty() {
            return firstName;
        }

        public String getLastName() {
            return lastName.get();
        }

        public SimpleStringProperty lastNameProperty() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber.get();
        }

        public SimpleStringProperty phoneNumberProperty() {
            return phoneNumber;
        }

        public String getEmail() {return email.get();}

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public String getPositoin() {
            return positoin.get();
        }

        public SimpleStringProperty positoinProperty() {
            return positoin;
        }

        public String getDepartement() {
            return departement.get();
        }

        public SimpleStringProperty departementProperty() {
            return departement;
        }

        public String getSex() {
            return sex.get();
        }

        public SimpleStringProperty sexProperty() {
            return sex;
        }

        public double getSalary() {
            return salary.get();
        }

        public SimpleDoubleProperty salaryProperty() {
            return salary;
        }



}
