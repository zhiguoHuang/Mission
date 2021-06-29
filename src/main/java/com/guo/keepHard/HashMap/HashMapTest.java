package com.guo.keepHard.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        myTransfer();
    }
    static class MyEntry{
        String key;
        String value;
        MyEntry next;
    }

    private static void myTransfer(){
        MyEntry[] table = new MyEntry[4];

        MyEntry first = new MyEntry();
        first.key = "1";
        first.value = "1";

        MyEntry second = new MyEntry();
        second.key = "2";
        second.value = "2";

        table[0] = first;
        table[0].next = second;


        MyEntry entry1 = new MyEntry();
        entry1.key = "entry1";
        entry1.value = "entry1";
        table[1] = entry1;

        MyEntry entry2 = new MyEntry();
        entry2.key = "entry2";
        entry2.value = "entry2";
        table[2] = entry2;

        MyEntry entry3 = new MyEntry();
        entry3.key = "entry3";
        entry3.value = "entry3";
        table[3] = entry3;


        MyEntry[] newTable = new MyEntry[4*2];

        for (MyEntry e : table) {
            while(null != e) {
                MyEntry next = e.next;

                int i = 1;
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }
}