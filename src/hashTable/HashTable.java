package hashTable;

import java.io.*;

public class HashTable implements Serializable{
    @Serial
    private static final long serialVersionUID = -2905043625941142383L;
    LinkedList[] users = new LinkedList[100];

    private int hashFunction(String str) {
        int hash = 5381;
        for(int i=0;i<str.length();i++) {
            hash = (hash * 33 + str.charAt(i))%100;
        }
        return hash%100;
    }

    public Node insert(String username, String password) {
        if(users[hashFunction(username)]==null) {
            users[hashFunction(username)] = new LinkedList();
        }
        users[hashFunction(username)].insert(username, password);
        return getUser(username);
    }

    public void insert(Node user){
        if(users[hashFunction(user.username)]==null){
            users[hashFunction(user.username)] = new LinkedList();
        }
        users[hashFunction((user.username))].insert(user);
    }

    public void delete(String username) {
        users[hashFunction(username)].delete(username);
    }

    public String getPassword(String username) {
        return users[hashFunction(username)].getPassword(username);
    }

    public boolean exists(String username) {
        if(users[hashFunction(username)]==null) {
            return false;
        }
        return users[hashFunction(username)].exists(username);
    }

    public Node getUser(String username) {
        return users[hashFunction(username)].getUser(username);
    }

    public static HashTable getTable(){
        HashTable hash = new HashTable();
        File file = new File("D:\\EclipseWorkspace\\SustainableOptimization\\HashTable.txt");
        try{
            if(file.createNewFile()){
                System.out.println("File didn't exists but new file has been created");
                HashTable.setTable(hash);
            }
            else{
                System.out.println("File exists");
            }
        }
        catch(IOException e){
            System.out.println("File didn't exist and couldn't create new file");
        }
        try(FileInputStream fileIn = new FileInputStream(file);ObjectInputStream objIn = new ObjectInputStream(fileIn)){
            hash = (HashTable) objIn.readObject();
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        }
        catch (IOException e){
            System.out.println("Read file couldn't be opened");
        }
        catch(ClassNotFoundException e){
            System.out.println("File corrupted");
        }
        return hash;
    }

    public static void setTable(HashTable hash) {
        File file = new File("D:\\EclipseWorkspace\\SustainableOptimization\\HashTable.txt");
        try{
            if(file.createNewFile()){
                System.out.println("Creating new file");
            }
            else{
                System.out.println("File already exists");
            }
        }
        catch(IOException e){
            System.out.println("File couldn't be written");
        }
        try(FileOutputStream fileOut = new FileOutputStream(file);ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
            objOut.writeObject(hash);
        }
        catch(FileNotFoundException e){
            System.out.println("File doesn't exist");
        }
        catch(IOException e){
            System.out.println("Write file couldn't be opened");
        }
    }
}
