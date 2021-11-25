package org.example.phoneapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Welcome");
        showInitialOptions();

    }

    private static void showInitialOptions() {
        System.out.println("Select one:" +
                "\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("Select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send new message" +
                "\n\t3. Go back");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Enter contact's name:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Enter contact's name");
            sendNewMessage();
        } else {
            boolean found = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    found = true;
                }
            }

            if (found) {
                System.out.println("Enter message");
                String text = scanner.next();

                if (text.equals("")) {
                    System.out.println("Enter message");
                    sendNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(text, name, id);
                    for (Contact c : contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                        }
                    }
                }
            } else {
                System.out.println("Contact not found");
            }
        }

        showInitialOptions();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c : contacts) {
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size() > 0) {
            for (Message m : allMessages) {
                m.getDetails();
                System.out.println("************");
            }
        } else {
            System.out.println("No messages");
        }

        showInitialOptions();
    }

    private static void manageContacts() {
        System.out.println("Select one:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Enter contact's name:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Enter contact's name");
            deleteContact();
        } else {
            boolean found = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)){
                    found = true;
                    contacts.remove(c);
                }
            }

            if (!found) {
                System.out.println("Contact not found");
            }
        }

        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Enter contact's name:");
        String name = scanner.next();
        if (name.equals("")) {
            System.out.println("Enter contact's name");
            searchForContact();
        } else {
            boolean found = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)){
                    found = true;
                    c.getDetails();
                }
            }

            if (!found) {
                System.out.println("Contact not found");
            }
        }

        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("Enter the contact's name:");
        String name = scanner.next();
        System.out.println("Enter the contact's number:");
        String number = scanner.next();
        System.out.println("Enter the contact's email:");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Enter all information");
            addNewContact();
        } else {
            boolean doesExist = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
            }

            if (doesExist) {
                System.out.println("Contact already saved on device");
            } else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println("Contact added");
            }
        }

        showInitialOptions();
    }

    private static void showAllContacts() {
        if (contacts.size() > 0) {
            for (Contact c : contacts) {
                c.getDetails();
                System.out.println("************");
            }
        } else {
            System.out.println("No contacts");
        }

        showInitialOptions();
    }
}
