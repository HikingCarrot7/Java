package cosas;

import java.util.Arrays;

public class Referencias
{
	private String[] nombres =
	{ "Nicolas", "Javier" };

	private int i = 0;

	public Referencias(int i)
	{
		this.i = i;
	}

	private interface Saludar
	{
		public void saludar();
	}

	private interface IDPersona
	{
		public Referencias crear(int i);
	}

	public void operar()
	{

		Saludar s = Referencias::ReferenciaMetodoStatic;

		/*
		 * Saludar s = new Saludar() {
		 * 
		 * @Override public void saludar() { Referencias.ReferenciaMetodoStatic();
		 * 
		 * }
		 * 
		 * };
		 */

		/*
		 * Saludar s1 = () -> { Referencias.ReferenciaMetodoStatic(); };
		 */

		s.saludar();
	}

	public void ReferenciaConstructor()
	{
		/*
		 * IDPersona iper = new IDPersona() {
		 * 
		 * @Override public Referencias crear(int i) { return new Referencias(i); }
		 * 
		 * };
		 * 
		 * iper.crear(5);
		 */

		/*
		 * IDPersona iper = (i) -> new Referencias(i);
		 * 
		 * iper.crear(5);
		 */

		IDPersona iper = Referencias::new;

		iper.crear(5);

	}

	public void ReferenciaMetodoInstancia()
	{
		/*
		 * Arrays.sort(nombres, new Comparator<String>() {
		 * 
		 * @Override public int compare(String arg0, String arg1) {
		 * 
		 * return arg0.compareTo(arg1); }
		 * 
		 * });
		 */

		// Arrays.sort(nombres, (p1, p2) -> p1.compareTo(p2));

		Arrays.sort(nombres, String::compareTo);

		System.out.println(Arrays.toString(nombres));

	}

	public void ReferenciaMetodoInstanciaParticular()
	{
		System.out.println(i);
	}

	public static void ReferenciaMetodoStatic()
	{
		System.out.println("Esta es una referencia a un método static");
	}

	public static void main(String[] args)
	{
		Referencias r1 = new Referencias(1);
		Referencias r2 = new Referencias(2);

		Saludar s = r2::ReferenciaMetodoInstanciaParticular;

		s.saludar();
	}

}
