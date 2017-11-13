package com.zhenik.devops.exam.calculator.domain.dto;


public class Numbers {
    private Double n1;
    private Double n2;

    public Numbers() { }

    public Numbers(Double n1,Double n2){
        this.n1=n1;
        this.n2=n2;
    }

    public Double getN1() { return n1; }
    public void setN1(Double n1) { this.n1 = n1; }
    public Double getN2() { return n2; }
    public void setN2(Double n2) { this.n2 = n2; }

    @Override
    public String toString() {
        return "Numbers{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                '}';
    }
}
