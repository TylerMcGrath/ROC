package mine;

public class Mine {
public static void main(String[] args) {
		
		Integer i1=8;
		Integer i2=8;
		Integer i3=new Integer(8);
		Integer i4=5;
		
		System.out.println("i1==i2 : "+(i1==i2));
		System.out.println("i1==i3 : "+(i1==i3));
		System.out.println("i1==i4 : "+(i1==i4));
		
		
		System.out.println("i1.equals(i2) : "+i1.equals(i2));
		System.out.println("i1.equals(i3) : "+i1.equals(i3));
		System.out.println("i1.equals(i4) : "+i1.equals(i4));
		
		int x=100;
		Integer i5=x; //Autoboxing
		System.out.println("x : "+x);
		System.out.println("i5 : "+i5);
		i5=99;
		x=i5;
		System.out.println("x : "+x);
		System.out.println("i5 : "+i5);
		
		System.out.println("Integer.MAX_VALUE : "+Integer.MAX_VALUE);
		System.out.println("Long.MAX_VALUE : "+Long.MAX_VALUE);
		
		System.out.println("Integer.MIN_VALUE : "+Integer.MIN_VALUE);
		
		String s="120";
		Integer i6=Integer.parseInt(s); //WrapperClass.parseXyZ(stringobject); apart from Character class
		System.out.println("i6 : "+i6);
		i6=9999999;
		s=i6.toString();
		System.out.println("s = "+s);
		
		int z=9898989;
		s=z+"";//Anything in java can be converted to the String object by just appending with ""
		System.out.println("s = "+s);
		
		Integer i7=10;
		System.out.println("i7 : "+i7);
		System.out.println("i7.compareTo(10) : "+i7.compareTo(10));
		System.out.println("i7.compareTo(100) : "+i7.compareTo(100));
		System.out.println("i7.compareTo(8) : "+i7.compareTo(8));
		
		
		System.out.println(Integer.toBinaryString(20));// only works for integer
		System.out.println(Integer.toHexString(20));
		System.out.println(Integer.toOctalString(20));// only works for integer
		
		
		Float f=i7.floatValue();
		System.out.println("f = "+f);
		f=88.77f;
		System.out.println("f = "+f);
		int m=f.intValue(); //rounds down
		System.out.println("m = "+m);
		
		Float f1=8f;
		Float f2=8f;
		Float f3=new Float(8);
		Float f4=5f;
		
		System.out.println("f1==f2 : "+(f1==f2));//unlike integers, not the same. Why?
		System.out.println("f1==f3 : "+(f1==f3));
		System.out.println("f1==f4 : "+(f1==f4));
		
		
		System.out.println("f1.equals(f2) : "+f1.equals(f2));
		System.out.println("f1.equals(f3) : "+f1.equals(f3));
		System.out.println("f1.equals(f4) : "+f1.equals(f4));
		
		Double d1=8d;
		Double d2=8d;
		Double d3=new Double(8);
		Double d4=5d;
		
		System.out.println("d1==d2 : "+(d1==d2));//unlike integers, not the same. Why?
		System.out.println("d1==d3 : "+(d1==d3));
		System.out.println("d1==d4 : "+(d1==d4));
		
		
		System.out.println("d1.equals(d2) : "+d1.equals(d2));
		System.out.println("d1.equals(d3) : "+d1.equals(d3));
		System.out.println("d1.equals(d4) : "+d1.equals(d4));
		
		//System.out.println(Double.toBinaryString(20));
		System.out.println(Double.toHexString(20.5));
		//System.out.println(Double.toOctalString(20));
		
		Long L1=8L;
		Long L2=8L;
		Long L3=new Long(8);
		Long L4=5L;
		
		System.out.println("L1==L2 : "+(L1==L2)); //is same, like Integer
		System.out.println("L1==L3 : "+(L1==L3));
		System.out.println("L1==L4 : "+(L1==L4));
		
		
		System.out.println("L1.equals(L2) : "+L1.equals(L2));
		System.out.println("L1.equals(L3) : "+L1.equals(L3));
		System.out.println("L1.equals(L4) : "+L1.equals(L4));
		
		//Try to recreate the same features using Double,Float and Long
	}

}
