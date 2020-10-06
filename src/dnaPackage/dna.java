package dnaPackage;

import java.io.IOException;

public class dna {
    // RMB op seq -> generate... -> Getter and Setter
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        if (seq.matches("^[ATGC]*")) {
            this.seq = seq;
        } else {
            System.out.println("geen goede sequentie, gebruik ATGC");

        }
    }
    //constructor
    public dna(String seq) {
        setSeq(seq);
    }

    private String seq;
}

