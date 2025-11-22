package org.daodao;

public class ParameterDemo {
    public static void main(String[] args) {
        Person person = new Person("Alice");
        changeName(person);
        System.out.println("名字:"+person.getName()); // 输出 新名字，因为名字被改变了
        
        changePerson(person);
        System.out.println("名字:"+person.getName()); // 输出 原始对象 的名字
    }
 
    public static void changeName(Person person) {
        person.setName("Bob"); // 修改对象的状态
    }
    

	public static void changePerson(Person person) {
	    person = new Person("Charlie"); // 这不会影响原始的 person 对象
	}
}
 
class Person {
    private String name;
    public Person(String name) { this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}