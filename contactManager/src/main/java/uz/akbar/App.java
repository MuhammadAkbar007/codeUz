package uz.akbar;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		start();
	}

	public static void start() {
		DataBaseUtil.createTable();

		boolean loop = true;

		while (loop) {
			showMenu();
			int action = getAction();

			switch (action) {
				case 1:
					System.out.println("*** Add contact ***");
					addContact();
					break;
				case 2:
					System.out.println("*** Contact list ***");
					break;
				case 3:
					System.out.println("*** Delete contact ***");
					break;
				case 4:
					System.out.println("*** Search contact ***");
					break;
				case 0:
					System.out.println("*** Exit ***");
					loop = false;
					break;
				default:
					System.out.println("Wrong input!");
					break;
			}

		}
	}

	public static void showMenu() {
		System.out.println();
		System.out.println("*** Menu ***");
		System.out.println("1. Add Contact");
		System.out.println("2. Contact List");
		System.out.println("3. Delete Contact");
		System.out.println("4. Search Contact");
		System.out.println("0. Exit");
		System.out.println();
	}

	public static int getAction() {
		System.out.println();
		System.out.print("Enter action => ");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static void addContact() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter name: ");
		String name = scanner.next();

		System.out.print("Enter surname: ");
		String surname = scanner.next();

		System.out.print("Enter phone: ");
		String phone = scanner.next();

		Contact contact = new Contact();
		contact.setName(name);
		contact.setSurname(surname);
		contact.setPhone(phone);

		ContactRepository contactRepository = new ContactRepository();
		boolean result = contactRepository.saveContact(contact);

		if (result) {
			System.out.println("Contact added.");
		} else {
			System.out.println("Something went wrong!");
		}
	}

}
