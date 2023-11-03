import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;
    String email;

    Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

class Node {
    Contact contact;
    Node left, right;

    Node(Contact contact) {
        this.contact = contact;
        left = right = null;
    }
}

class AddressBook {
    Node root;

    AddressBook() {
        root = null;
    }

    Node insert(Node root, Contact contact) {
        if (root == null) {
            root = new Node(contact);
            return root;
        }

        if (contact.name.compareTo(root.contact.name) < 0) {
            root.left = insert(root.left, contact);
        } else if (contact.name.compareTo(root.contact.name) > 0) {
            root.right = insert(root.right, contact);
        }

        return root;
    }

    void insert(Contact contact) {
        root = insert(root, contact);
    }

    Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    Node delete(Node root, String name) {
        if (root == null) return root;

        if (name.compareTo(root.contact.name) < 0) {
            root.left = delete(root.left, name);
        } else if (name.compareTo(root.contact.name) > 0) {
            root.right = delete(root.right, name);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.contact = findMin(root.right).contact;
            root.right = delete(root.right, root.contact.name);
        }

        return root;
    }

    void delete(String name) {
        root = delete(root, name);
    }

    Contact search(Node root, String name) {
        if (root == null || root.contact.name.equals(name)) {
            return root != null ? root.contact : null;
        }

        if (name.compareTo(root.contact.name) < 0) {
            return search(root.left, name);
        }

        return search(root.right, name);
    }

    Contact search(String name) {
        return search(root, name);
    }

    void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println("Name: " + root.contact.name);
            System.out.println("Phone: " + root.contact.phoneNumber);
            System.out.println("Email: " + root.contact.email);
            System.out.println();
            inOrderTraversal(root.right);
        }
    }

    void listContacts() {
        inOrderTraversal(root);
    }
}

public class AddressBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("Address Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. List Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    addressBook.insert(new Contact(name, phoneNumber, email));
                    System.out.println("Contact added.");
                    break;
                case 2:
                    System.out.print("Enter Name to Remove: ");
                    name = scanner.nextLine();
                    addressBook.delete(name);
                    System.out.println("Contact removed.");
                    break;
                case 3:
                    System.out.print("Enter Name to Search: ");
                    name = scanner.nextLine();
                    Contact contact = addressBook.search(name);
                    if (contact != null) {
                        System.out.println("Contact Found:");
                        System.out.println("Name: " + contact.name);
                        System.out.println("Phone: " + contact.phoneNumber);
                        System.out.println("Email: " + contact.email);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    System.out.println("Listing Contacts:");
                    addressBook.listContacts();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
