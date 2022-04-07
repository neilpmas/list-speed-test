package dev.neilmason;

import org.openjdk.jmh.annotations.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ListTest {

    public static final int ARRAY_SIZE = 10000000;



    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public long ArrayList(TestArrayObject testArrayObject) {
        List<SillyObject> arrayList = new ArrayList<>();
        for(SillyObject sillyObject:testArrayObject.getArrayOfSillyObjects()){
            arrayList.add(sillyObject);
        }

        long total = 0;
        for(SillyObject sillyObject:arrayList){
            total += sillyObject.getAmount();
        }

        return total;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public long LinkedList(TestArrayObject testArrayObject) {
        List<SillyObject> linkedList = new LinkedList<>();
        for(SillyObject sillyObject:testArrayObject.getArrayOfSillyObjects()){
            linkedList.add(sillyObject);
        }

        long total = 0;
        for(SillyObject sillyObject:linkedList){
            total += sillyObject.getAmount();
        }

        return total;
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    public static class SillyObject{
        public int getAmount() {
            return amount;
        }

        private final int amount;

        SillyObject(int amount) {
            this.amount = amount;
        }
    }

    @State(Scope.Thread)
    public static class TestArrayObject {
        private final SillyObject[] arrayOfSillyObjects;

        public TestArrayObject() {
            arrayOfSillyObjects = new SillyObject[ARRAY_SIZE];
            for(int i = 0; i < arrayOfSillyObjects.length; i++){
                arrayOfSillyObjects[i] = new SillyObject(ThreadLocalRandom.current().nextInt(1,5));
            }
        }

        public SillyObject[] getArrayOfSillyObjects() {
            return arrayOfSillyObjects;
        }
    }
}

