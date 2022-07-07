package jads.lesson8;


import java.util.Objects;

public class Main {
    static class Cat {
        int age;
        public Cat(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }
        @Override
        public int hashCode() {
            return Objects.hash(age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            jads.lesson4.Cat cat = (jads.lesson4.Cat) o;
            return age == cat.getAge();
        }

    }

    static class HashTable{
        private Cat[] hashArray;
        private int arrSize;
        private Cat nullItem;

        public HashTable(int size) {
            this.arrSize = size;
            hashArray = new Cat[size];
            nullItem = new Cat(-1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrSize; i++) {
                sb.append((hashArray[i] != null) ? hashArray[i].getAge() : "*");
                if (i < arrSize -1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        private int hashFunc(int key) {
            return key % arrSize;
        }
        public Cat find(int key) {
            int hashVal = hashFunc(key);
            while (hashArray[hashVal] != null) {
                if (hashArray[hashVal].getAge() == key)
                    return hashArray[hashVal];
                ++hashVal;
                hashVal %= arrSize;
            }
            return null;
        }

        public void insert(Cat item){
            int key = item.getAge();
            int hashVal = hashFunc(key);
            while (hashArray[hashVal] != null && hashArray[hashVal] != nullItem) {
                ++hashVal;
                hashVal%=arrSize;
            }
            hashArray[hashVal] = item;
        }

        public Cat delete(int key){
            int hashVal = hashFunc(key);
            while (hashArray[hashVal] != null) {
                if (hashArray[hashVal].getAge() == key){
                    Cat temp = hashArray[hashVal];
                    hashArray[hashVal] = nullItem;
                    return temp;
                }
                ++hashVal;
                hashVal%=arrSize;
            }
            return null;
        }

    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(25);
        hashTable.insert(new Cat(10));
        hashTable.insert(new Cat(20));
        hashTable.insert(new Cat(30));
        hashTable.insert(new Cat(40));
        hashTable.insert(new Cat(50));
        hashTable.insert(new Cat(60));
        hashTable.insert(new Cat(70));
        System.out.println(hashTable.toString());
        hashTable.delete(75);
        System.out.println(hashTable.toString());
    }

    static class Link<T> {
        private T link;
        private Link<T> next;
        public Link(T link){
            this.link = link;
        }
        public Link<T> getNext() {
            return next;
        }
        public void setNext(Link<T> next) {
            this.next = next;
        }
        public T getValue(){
            return link;
        }
    }
    static class LinkedList<T> {
        private Link<T> first;
        public LinkedList(){
            first = null;
        }
        public boolean isEmpty(){
            return (first == null);
        }
        public void insert(T link){
            Link<T> l = new Link<>(link);
            l.setNext(first);
            this.first = l;
        }
        public Link<T> delete(){
            Link<T> temp = first;
            first = first.getNext();
            return temp;
        }
        public void display(){
            Link<T> current = first;
            while (current != null) {
                System.out.println(current.getValue());
                current = current.getNext();
            }
        }
        public T find(T searchNode){
            Link<T> findNode = new Link<>(searchNode);
            Link<T> current = first;
            while (current != null) {
                if (current.getValue().equals(findNode.getValue())){
                    return findNode.getValue();
                }
                current = current.getNext();
            }
            return null;
        }
    }
}



